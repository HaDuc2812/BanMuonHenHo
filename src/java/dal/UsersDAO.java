/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author HA DUC
 */
public class UsersDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;

    public List<Users> getAllUser() {
        List<Users> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash, full_name, gender, birth_date, "
                + "bio, status, role_id, created_at FROM Users where role_id = 1";
        try {
            ps = connection.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setFullName(rs.getString("full_name"));
                user.setGender(rs.getString("gender"));
                user.setBirthDate(rs.getDate("birth_date"));
                user.setBio(rs.getString("bio"));
                user.setStatus(rs.getString("status"));
                user.setRoleId(rs.getInt("role_id"));
                user.setCreatedAt(rs.getDate("created_at"));
                users.add(user);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Users getUsersById(int userId) throws Exception {
        String sql = "Select * from Users where user_id = ?";
        Users u = null;
        try {
            ps = connection.prepareCall(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                u = new Users();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setFullName(rs.getString("full_name"));
                u.setGender(rs.getString("gender"));
                u.setBirthDate(rs.getDate("birth_date"));
                u.setBio(rs.getString("bio"));
                u.setStatus(rs.getString("status"));
                u.setRoleId(rs.getInt("role_id"));
                u.setCreatedAt(rs.getDate("created_at"));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return u;
    }

    public Set<Integer> getUserTags(int userId) {
        Set<Integer> tags = new HashSet<>();
        String sql = "SELECT tag_id FROM UserTags WHERE user_id = ?";
        try {
            ps = connection.prepareCall(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                tags.add(rs.getInt("tag_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }

    public List<Users> getPotentialMatches(Users currentUser) {
        List<Users> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT DISTINCT u.* "
                    + "FROM Users u "
                    + "JOIN UserTags ut1 ON u.user_id = ut1.user_id "
                    + "JOIN UserTags ut2 ON ut1.tag_id = ut2.tag_id "
                    + "WHERE ut2.user_id = ? "
                    + "  AND LOWER(u.gender) <> LOWER(?) "
                    + "  AND u.user_id <> ?";

            System.out.println("DEBUG SQL: " + sql);
            System.out.println("DEBUG PARAMS: user_id=" + currentUser.getUserId() + ", gender=" + currentUser.getGender());

            ps = connection.prepareStatement(sql);
            ps.setInt(1, currentUser.getUserId());
            ps.setString(2, currentUser.getGender());
            ps.setInt(3, currentUser.getUserId());

            rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                Users u = new Users();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setFullName(rs.getString("full_name"));
                u.setGender(rs.getString("gender"));
                u.setBirthDate(rs.getDate("birth_date"));
                u.setBio(rs.getString("bio"));
                u.setStatus(rs.getString("status"));
                u.setRoleId(rs.getInt("role_id"));
                u.setCreatedAt(rs.getDate("created_at"));

                System.out.println("→ MATCH FOUND: " + u.getFullName() + " (" + u.getGender() + ")");
                list.add(u);
            }

            if (!found) {
                System.out.println("⚠️ No potential matches found for " + currentUser.getFullName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
