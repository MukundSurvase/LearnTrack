package src.com.airtribe.learntrack.entity;

import java.util.Objects;

public class Login {
    private String loginId;
    private String password;
    private Role role;
    private boolean isActive;

    public Login(String loginId, String password, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
