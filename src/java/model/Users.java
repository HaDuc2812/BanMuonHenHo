package model;

import java.sql.Date;

public class Users {

    private int userId;
    private String username;
    private String passwordHash;
    private String fullName;
    private String gender;
    private Date birthDate;
    private String bio;
    private String status;  // pending | matched | confirmed
    private int roleId;
    private Date createdAt;

    // ===== Constructors =====
    public Users() {
    }

    public Users(int userId, String username, String passwordHash, String fullName,
            String gender, Date birthDate, String bio, String status,
            int roleId, Date createdAt) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.bio = bio;
        this.status = status;
        this.roleId = roleId;
        this.createdAt = createdAt;
    }

    // For registration (auto id + date)
    public Users(String username, String passwordHash, String fullName,
            String gender, Date birthDate, String bio) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.bio = bio;
        this.status = "pending";
        this.roleId = 1;
    }

    // ===== Getters and Setters =====
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // ===== Debugging =====
    @Override
    public String toString() {
        return "User{"
                + "userId=" + userId
                + ", username='" + username + '\''
                + ", fullName='" + fullName + '\''
                + ", gender='" + gender + '\''
                + ", birthDate=" + birthDate
                + ", status='" + status + '\''
                + ", roleId=" + roleId
                + '}';
    }
}
