package io.ugurh.sphub.account;

import jakarta.persistence.*;

import java.sql.Timestamp;


/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 4.05.2023 - 19:45
 */
@Entity
@Table(name = "ACCOUNTS")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_seq")
    @SequenceGenerator(name = "accounts_seq", sequenceName = "accounts_seq", allocationSize = 1)
    @Column(name = "user_id")
    private int userId;
    private String username;
    private String password;
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastLogin;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
}