/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author maith
 */
public class User {

    private int userId;
    private String username;
    private String passwordHash;
    private String fullName;
    private String gender;
    private Date birthDate;
    private String bio;
    private String status;
    private int roleId;

    public User() {
    }

    // Constructor đầy đủ
    public User(int userId, String username, String passwordHash, String fullName,
            String gender, Date birthDate, String bio, String status, int roleId) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.bio = bio;
        this.status = status;
        this.roleId = roleId;
    }

    // Constructor cho đăng ký mới (không có userId)
    public User(String username, String passwordHash, String fullName,
            String gender, Date birthDate, String bio, String status, int roleId) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.bio = bio;
        this.status = status;
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
