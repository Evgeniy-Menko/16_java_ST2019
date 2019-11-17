package by.menko.finalproject.entity;

import java.util.Date;

public class UserInfo extends User {
    private String firstName;
    private String lastName;
    private String nickname;
    private String phone;
    private Date dateRegistration;

    public String getFirstName() {
        return firstName;
    }

    public UserInfo setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserInfo setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserInfo setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public UserInfo setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
        return this;
    }
}
