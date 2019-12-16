package by.menko.finalproject.entity;

import by.menko.finalproject.entity.enumtype.Role;

import java.sql.Timestamp;


public class UserInfo extends Entity {
    private String firstName;
    private String lastName;
    private String nickname;
    private String phone;
    private Timestamp dateRegistration;
    private String email;
    private String image;
    private String password;
    private Role role;
    private Boolean flagBlocked;
    private String salt;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFlagBlocked(Boolean flagBlocked) {
        this.flagBlocked = flagBlocked;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getFlagBlocked() {
        return flagBlocked;
    }

    public void setFlagBlocked(Integer id) {
        if (id == 0) {
            this.flagBlocked = false;
        } else if (id == 1) {
            this.flagBlocked = true;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Timestamp dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserInfo userInfo = (UserInfo) o;

        if (!firstName.equals(userInfo.firstName)) return false;
        if (!lastName.equals(userInfo.lastName)) return false;
        if (!nickname.equals(userInfo.nickname)) return false;
        if (!phone.equals(userInfo.phone)) return false;
        if (!dateRegistration.equals(userInfo.dateRegistration)) return false;
        if (!email.equals(userInfo.email)) return false;
        if (!image.equals(userInfo.image)) return false;
        if (!password.equals(userInfo.password)) return false;
        if (role != userInfo.role) return false;
        if (!flagBlocked.equals(userInfo.flagBlocked)) return false;
        return salt.equals(userInfo.salt);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + nickname.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + dateRegistration.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + flagBlocked.hashCode();
        result = 31 * result + salt.hashCode();
        return result;
    }
}
