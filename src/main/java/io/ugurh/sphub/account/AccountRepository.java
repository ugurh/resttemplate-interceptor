package io.ugurh.sphub.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 4.05.2023 - 19:48
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "SELECT * FROM Accounts t WHERE t.email LIKE %?1%", nativeQuery = true)
    List<Account> findByEmailLike(String email);

}
