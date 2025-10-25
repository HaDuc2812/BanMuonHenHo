/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Role;
import model.Tag;
import viewmodels.UserDetail;

/**
 *
 * @author maith
 */
public class UserDAO extends DBContext {

    /**
     * Lấy user theo username (cho login)
     */
    public UserDetail getUserByUsername(String username) {
        UserDetail user = null;
        String sql = "SELECT u.user_id, u.username, u.password_hash, u.full_name, "
                + "u.gender, u.birth_date, u.bio, u.status, "
                + "r.role_id, r.role_name, u.created_at "
                + "FROM Users u "
                + "JOIN Roles r ON u.role_id = r.role_id "
                + "WHERE u.username = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));

                user = new UserDetail(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("full_name"),
                        rs.getString("gender"),
                        rs.getDate("birth_date"),
                        rs.getString("bio"),
                        rs.getString("status"),
                        role,
                        rs.getTimestamp("created_at")
                );

                // Lấy tags của user
                user.setTags(getUserTags(user.getUserId()));
            }
        } catch (SQLException e) {
            System.out.println("getUserByUsername Error: " + e.getMessage());
        }

        return user;
    }

    /**
     * Lấy user theo ID
     */
    public UserDetail getUserByID(int userId) {
        UserDetail user = null;
        String sql = "SELECT u.user_id, u.username, u.password_hash, u.full_name, "
                + "u.gender, u.birth_date, u.bio, u.status, "
                + "r.role_id, r.role_name, u.created_at "
                + "FROM Users u "
                + "JOIN Roles r ON u.role_id = r.role_id "
                + "WHERE u.user_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));

                user = new UserDetail(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("full_name"),
                        rs.getString("gender"),
                        rs.getDate("birth_date"),
                        rs.getString("bio"),
                        rs.getString("status"),
                        role,
                        rs.getTimestamp("created_at")
                );

                // Lấy tags của user
                user.setTags(getUserTags(userId));
            }
        } catch (SQLException e) {
            System.out.println("getUserByID Error: " + e.getMessage());
        }

        return user;
    }

    /**
     * Tạo user mới (Register) - Trả về userId vừa tạo
     */
    public int createUser(User user) {
        // Kiểm tra username đã tồn tại chưa
        UserDetail found = getUserByUsername(user.getUsername());
        if (found != null) {
            System.out.println("Username đã tồn tại: " + user.getUsername());
            return -1;
        }

        String sql = "INSERT INTO Users(username, password_hash, full_name, gender, "
                + "birth_date, bio, status, role_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getGender());
            ps.setDate(5, new Date(user.getBirthDate().getTime()));
            ps.setString(6, user.getBio());
            ps.setString(7, user.getStatus());
            ps.setInt(8, user.getRoleId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // Lấy userId vừa được tạo
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    System.out.println("Tạo user thành công với ID: " + userId);
                    return userId;
                }
            }
        } catch (SQLException e) {
            System.out.println("createUser Error: " + e.getMessage());
        }

        return -1;
    }

    /**
     * Cập nhật thông tin user
     */
    public boolean updateUser(User user) {
        String sql = "UPDATE Users SET "
                + "full_name = ?, gender = ?, birth_date = ?, bio = ?, "
                + "password_hash = ? "
                + "WHERE user_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getGender());
            ps.setDate(3, new Date(user.getBirthDate().getTime()));
            ps.setString(4, user.getBio());
            ps.setString(5, user.getPasswordHash());
            ps.setInt(6, user.getUserId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cập nhật user thành công: " + user.getUserId());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("updateUser Error: " + e.getMessage());
        }

        return false;
    }

    /**
     * Xóa user
     */
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE user_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Xóa user thành công: " + userId);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("deleteUser Error: " + e.getMessage());
        }

        return false;
    }

    /**
     * Lấy danh sách tags của user
     */
    public List<Tag> getUserTags(int userId) {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT t.tag_id, t.tag_name "
                + "FROM Tags t "
                + "JOIN UserTags ut ON t.tag_id = ut.tag_id "
                + "WHERE ut.user_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tag tag = new Tag(
                        rs.getInt("tag_id"),
                        rs.getString("tag_name")
                );
                tags.add(tag);
            }
        } catch (SQLException e) {
            System.out.println("getUserTags Error: " + e.getMessage());
        }

        return tags;
    }

    /**
     * Thêm tag cho user
     */
    public void addUserTag(int userId, int tagId) {
        String sql = "INSERT INTO UserTags(user_id, tag_id) VALUES (?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, tagId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addUserTag Error: " + e.getMessage());
        }
    }

    /**
     * Xóa tất cả tags của user
     */
    public void deleteAllUserTags(int userId) {
        String sql = "DELETE FROM UserTags WHERE user_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteAllUserTags Error: " + e.getMessage());
        }
    }

    /**
     * Cập nhật tags của user (xóa cũ, thêm mới)
     */
    public void updateUserTags(int userId, List<Integer> tagIds) {
        // Xóa tất cả tags cũ
        deleteAllUserTags(userId);

        // Thêm tags mới
        if (tagIds != null && !tagIds.isEmpty()) {
            for (int tagId : tagIds) {
                addUserTag(userId, tagId);
            }
        }

        System.out.println("Cập nhật tags cho user " + userId + " thành công");
    }
}
