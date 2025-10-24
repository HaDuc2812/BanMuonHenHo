/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HA DUC
 */
public class UsersWithTags {
    private int userId;
    private String userName;
    private String tags;

    public UsersWithTags() {
    }

    public UsersWithTags(int userId, String userName, String tags) {
        this.userId = userId;
        this.userName = userName;
        this.tags = tags;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UsersWithTags{");
        sb.append("userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }
    
}
