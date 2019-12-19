package by.menko.finalproject.entity;

import by.menko.finalproject.entity.enumtype.Role;

import java.sql.Timestamp;


public class UserInfo extends Entity {
    /**
     * first name.
     */
    private String firstName;
    /**
     * last name.
     */
    private String lastName;
    /**
     * nickname.
     */
    private String nickname;
    /**
     * phone.
     */
    private String phone;
    /**
     * date registration.
     */
    private Timestamp dateRegistration;
    /**
     * email.
     */
    private String email;
    /**
     * path image.
     */
    private String image;
    /**
     * password.
     */
    private String password;
    /**
     * Role.
     */
    private Role role;
    /**
     * flag blocked.
     */
    private Boolean flagBlocked;
    /**
     * salt for password.
     */
    private String salt;

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public void setFlagBlocked(final Boolean flagBlocked) {
        this.flagBlocked = flagBlocked;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(final String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public Boolean getFlagBlocked() {
        return flagBlocked;
    }

    public void setFlagBlocked(final Integer id) {
        if (id == 0) {
            this.flagBlocked = false;
        } else if (id == 1) {
            this.flagBlocked = true;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public Timestamp getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(final Timestamp dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @Override
    public boolean equals(final Object o) {
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
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (dateRegistration != null ? dateRegistration.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (flagBlocked != null ? flagBlocked.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        return result;
    }
}
