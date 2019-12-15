package com.example.loginregistration;

public class UserProfile {

    public String userAge;
    public String userEmail;
    public String userName;
    public String userPhone;
    public String userPassword;

    public UserProfile() {
    }

    public UserProfile(String userAge, String userName, String userPhone) {
        this.userAge = userAge;
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public UserProfile(String userAge, String userEmail, String userName, String userPhone, String userPassword) {
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
