package io.ugurh.sphub;

import io.ugurh.sphub.account.Account;
import io.ugurh.sphub.account.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 6.05.2023 - 00:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SphubApplication.class)
public class AccountUnitTest {

    @Autowired
    AccountRepository repository;

    @Test
    public void should_find_no_accounts_if_repository_is_empty() {
        List<Account> accounts = repository.findAll();

        assertThat(accounts).isNotNull();
    }

    @Test
    public void should_store_a_account() {
        Account newAccount = new Account("ugur.harun", "password", "ugur.harun@yandex.com");

        Account account = repository.save(newAccount);

        assertThat(account).hasFieldOrPropertyWithValue("email", "ugur.harun@yandex.com");
        assertThat(account).hasFieldOrPropertyWithValue("username", "ugur.harun");
        assertThat(account).hasFieldOrPropertyWithValue("password", "password");
    }

    @Test
    public void should_find_all_accounts() {
        repository.deleteAll();

        Account account1 = new Account("user1", "password1", "user1@gmail.com");
        Account account2 = new Account("user2", "password2", "user2@gmail.com");
        Account account3 = new Account("user3", "password3", "user3@gmail.com");

        List<Account> accounts = List.of(account1, account2, account3);
        repository.saveAll(accounts);

        List<Account> accountsDB = repository.findAll();

        assertThat(accountsDB).hasSize(3);
    }


    @Test
    public void should_find_account_by_email() {
        Account account1 = new Account("user3", "password4", "user3@gmail.com");
        repository.save(account1);

        Account account2 = new Account("user3", "password4", "user4@gmail.com");
        repository.save(account2);

        List<Account> accounts = repository.findByEmail(account2.getEmail());

        assertThat(accounts).isNotNull();
    }

    @Test
    public void should_find_account_by_username() {
        Account account = new Account("user", "password", "user@gmail.com");
        repository.save(account);

        List<Account> accounts = repository.findByUsername(account.getUsername());

        assertThat(accounts).extracting(Account::getUsername).containsOnly("user");
    }

    @Test
    public void should_find_active_accounts() {
        repository.deleteAll();

        Account account1 = new Account("user1", "password1", "user1@gmail.com");
        repository.save(account1);

        Account account2 = new Account("user2", "password2", "user2@gmail.com");
        repository.save(account2);

        List<Account> accounts = repository.findByIsActive(true);

        assertThat(accounts).hasSize(2);
    }

    @Test
    public void should_update_account_by_id() {
        repository.deleteAll();

        Account account1 = new Account("user1", "password1", "user1@gmail.com");
        repository.save(account1);

        Account account2 = new Account("user2", "password2", "user2@gmail.com");
        repository.save(account2);

        Account updateUser = new Account("update_user", "update_user_password", "update_user_password@gmail.com");

        Account account = repository.findByUsername(account1.getUsername()).get(0);
        account.setUsername(updateUser.getUsername());
        account.setPassword(updateUser.getPassword());
        account.setEmail(updateUser.getEmail());

        repository.save(account);

        Account checkAccount = repository.findById(account.getUserId()).get();

        assertThat(checkAccount.getUsername()).isEqualTo(updateUser.getUsername());
        assertThat(checkAccount.getEmail()).isEqualTo(updateUser.getEmail());
        assertThat(checkAccount.getPassword()).isEqualTo(updateUser.getPassword());
    }


    @Test
    public void should_delete_account_by_id() {
        repository.deleteAll();

        Account account1 = new Account("user1", "password1", "user1@gmail.com");
        repository.save(account1);

        Account account2 = new Account("user2", "password2", "user2@gmail.com");
        repository.save(account2);

        repository.deleteById(1);

        List<Account> accounts = repository.findAll();

        assertThat(accounts).hasSize(1);
    }

    @Test
    public void should_delete_all_accounts() {
        Account account1 = new Account("user1", "password1", "user1@gmail.com");
        repository.save(account1);

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

}