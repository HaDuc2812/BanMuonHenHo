/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Role;

/**
 *
 * @author maith
 */
public class RoleDAO extends DBContext {

    /**
     * Lấy tất cả roles
     */
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT role_id, role_name FROM Roles ORDER BY role_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Role role = new Role(
                        rs.getInt("role_id"),
                        rs.getString("role_name")
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            System.out.println("getAllRoles Error: " + e.getMessage());
        }

        return roles;
    }

    /**
     * Lấy role theo ID
     */
    public Role getRoleById(int roleId) {
        Role role = null;
        String sql = "SELECT role_id, role_name FROM Roles WHERE role_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role = new Role(
                        rs.getInt("role_id"),
                        rs.getString("role_name")
                );
            }
        } catch (SQLException e) {
            System.out.println("getRoleById Error: " + e.getMessage());
        }

        return role;
    }

    /**
     * Lấy role theo tên
     */
    public Role getRoleByName(String roleName) {
        Role role = null;
        String sql = "SELECT role_id, role_name FROM Roles WHERE role_name = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, roleName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role = new Role(
                        rs.getInt("role_id"),
                        rs.getString("role_name")
                );
            }
        } catch (SQLException e) {
            System.out.println("getRoleByName Error: " + e.getMessage());
        }

        return role;
    }

    /**
     * Tạo role mới
     */
    public boolean createRole(String roleName) {
        String sql = "INSERT INTO Roles(role_name) VALUES (?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, roleName);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Tạo role thành công: " + roleName);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("createRole Error: " + e.getMessage());
        }

        return false;
    }

    /**
     * Cập nhật role
     */
    public boolean updateRole(int roleId, String roleName) {
        String sql = "UPDATE Roles SET role_name = ? WHERE role_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, roleName);
            ps.setInt(2, roleId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cập nhật role thành công: " + roleId);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("updateRole Error: " + e.getMessage());
        }

        return false;
    }

    /**
     * Xóa role
     */
    public boolean deleteRole(int roleId) {
        String sql = "DELETE FROM Roles WHERE role_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, roleId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Xóa role thành công: " + roleId);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("deleteRole Error: " + e.getMessage());
        }

        return false;
    }
}
