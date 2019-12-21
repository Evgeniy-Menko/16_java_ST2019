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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserInfo userInfo = (UserInfo) o;

        if (firstName != null ? !firstName.equals(userInfo.firstName) : userInfo.firstName != null) return false;
        if (lastName != null ? !lastName.equals(userInfo.lastName) : userInfo.lastName != null) return false;
        if (nickname != null ? !nickname.equals(userInfo.nickname) : userInfo.nickname != null) return false;
        if (phone != null ? !phone.equals(userInfo.phone) : userInfo.phone != null) return false;
        if (dateRegistration != null ? !dateRegistration.equals(userInfo.dateRegistration) : userInfo.dateRegistration != null)
            return false;
        if (email != null ? !email.equals(userInfo.email) : userInfo.email != null) return false;
        if (image != null ? !image.equals(userInfo.image) : userInfo.image != null) return false;
        if (password != null ? !password.equals(userInfo.password) : userInfo.password != null) return false;
        if (role != userInfo.role) return false;
        if (flagBlocked != null ? !flagBlocked.equals(userInfo.flagBlocked) : userInfo.flagBlocked != null)
            return false;
        return salt != null ? salt.equals(userInfo.salt) : userInfo.salt == null;
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
