package org.ioc.daw.user;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private int rank;
    private Timestamp createdOn;
    private boolean active;

    public User(int userId, String username, String password, String name, String email, int rank, Timestamp createdOn, boolean active) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.rank = rank;
        this.createdOn = createdOn;
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getRank() {
        return rank;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return getName() + " (" + getUsername() + ")";
    }
}
