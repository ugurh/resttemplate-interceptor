package io.ugurh.sphub.account;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 4.05.2023 - 19:49
 */

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findByEmail(String email) {
        return accountRepository.findByEmailLike(email);
    }

    public List<Account> findAllWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size).withSort(Sort.by(sortBy).descending());
        return accountRepository.findAll(pageable).getContent();
    }

    public boolean addAccounts1(List<Account> accounts) {
        List<Account> persistedAccounts = accountRepository.saveAll(accounts);
        return persistedAccounts.size() == accounts.size();
    }

    public boolean addAccounts2(List<Account> accounts) {
        Session session = entityManager.unwrap(Session.class);

        int insertedRecords = session.doReturningWork(connection -> {
                    int result = executeBatch(connection, accounts).length;
                    connection.commit();
                    return result;
                }
        );
        return insertedRecords == accounts.size();
    }

    private int[] executeBatch(Connection connection, List<Account> accounts) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into accounts (username, password, email, created_on, last_login) values(?, ?,?,?,?)");
        connection.setAutoCommit(false);
        accounts.forEach(account ->
                {
                    try {
                        setFields(preparedStatement, account);
                        preparedStatement.addBatch();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return preparedStatement.executeBatch();
    }

    private void setFields(PreparedStatement preparedStatement, Account account) throws SQLException {
        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.setString(3, account.getEmail());
        preparedStatement.setTimestamp(4, account.getCreatedOn());
        preparedStatement.setTimestamp(5, account.getLastLogin());
    }

    @Transactional
    public boolean addAccounts3(List<Account> accounts) {
        String base_query = "insert into accounts (username, password, email, created_on, last_login) values ";
        String values = accounts.stream().map(a -> "( " + "'" + a.getUsername() + "'" + "," + "'" + a.getPassword() + "'" + "," + "'" + a.getEmail() + "'" + "," + "'" + a.getCreatedOn() + "'" + "," + "'" + a.getLastLogin() + "'" + ")").collect(Collectors.joining(","));
        System.out.println(base_query + values);
        Query query = entityManager.createNativeQuery(base_query + values);
        int result = query.executeUpdate();
        return result == accounts.size();
    }

}
