/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodels;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import model.Role;
import model.Tag;

/**
 *
 * @author maith
 */
public class UserDetail {

    public int userId;
    public String username;
    public String passwordHash;
    public String fullName;
    public String gender;
    public Date birthDate;
    public String bio;
    public String status;
    public Role role;
    public Timestamp createdAt;
    public List<Tag> tags;  // Danh sách sở thích

    public UserDetail() {
    }

    // Constructor không có tags
    public UserDetail(int userId, String username, String passwordHash, String fullName,
            String gender, Date birthDate, String bio, String status,
            Role role, Timestamp createdAt) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.bio = bio;
        this.status = status;
        this.role = role;
        this.createdAt = createdAt;
    }

    // Constructor đầy đủ có tags
    public UserDetail(int userId, String username, String passwordHash, String fullName,
            String gender, Date birthDate, String bio, String status,
            Role role, Timestamp createdAt, List<Tag> tags) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.bio = bio;
        this.status = status;
        this.role = role;
        this.createdAt = createdAt;
        this.tags = tags;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
