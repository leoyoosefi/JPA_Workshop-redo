package se.lexicon.jpa_workshopredo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;
    private String username;
    private String password;
    private LocalDateTime regDate;
    @OneToOne
    @JoinColumn(name = "user_details_details_id")
    private Details userDetails;

    public AppUser() {
        this.regDate = LocalDateTime.now();
    }

    public AppUser(String username, String password, Details userDetails) {
        this();
        this.username = username;
        this.password = password;
        this.userDetails = userDetails;
    }

    public AppUser(int appUserId, String username, String password, Details userDetails) {
        this();
        this.appUserId = appUserId;
        this.username = username;
        this.password = password;
        this.userDetails = userDetails;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
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

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public Details getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Details userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return appUserId == appUser.appUserId && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && Objects.equals(regDate, appUser.regDate) && Objects.equals(userDetails, appUser.userDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserId, username, password, regDate, userDetails);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                ", userDetails=" + userDetails +
                '}';
    }
}
