package ua.forum.dto;

public class AccountDetail {
    private boolean loggedIn;
    private String username;

    public AccountDetail(boolean loggedIn, String username) {
        this.loggedIn = loggedIn;
        this.username = username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUsername() {
        return username;
    }
}
