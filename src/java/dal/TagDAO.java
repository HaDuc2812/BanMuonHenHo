/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Tag;

public class TagDAO extends DBContext {

    /**
     * Lấy tất cả tags
     */
    public List<Tag> getAllTags() {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT tag_id, tag_name FROM Tags ORDER BY tag_name";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tag tag = new Tag(
                        rs.getInt("tag_id"),
                        rs.getString("tag_name")
                );
                tags.add(tag);
            }
        } catch (SQLException e) {
            System.out.println("getAllTags Error: " + e.getMessage());
        }

        return tags;
    }

    /**
     * Lấy tag theo ID
     */
    public Tag getTagById(int tagId) {
        Tag tag = null;
        String sql = "SELECT tag_id, tag_name FROM Tags WHERE tag_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, tagId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tag = new Tag(
                        rs.getInt("tag_id"),
                        rs.getString("tag_name")
                );
            }
        } catch (SQLException e) {
            System.out.println("getTagById Error: " + e.getMessage());
        }

        return tag;
    }

    /**
     * Tạo tag mới
     */
    public boolean createTag(String tagName) {
        String sql = "INSERT INTO Tags(tag_name) VALUES (?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, tagName);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Tạo tag thành công: " + tagName);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("createTag Error: " + e.getMessage());
        }

        return false;
    }

    /**
     * Cập nhật tag
     */
    public boolean updateTag(int tagId, String tagName) {
        String sql = "UPDATE Tags SET tag_name = ? WHERE tag_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, tagName);
            ps.setInt(2, tagId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cập nhật tag thành công: " + tagId);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("updateTag Error: " + e.getMessage());
        }

        return false;
    }

    /**
     * Xóa tag
     */
    public boolean deleteTag(int tagId) {
        String sql = "DELETE FROM Tags WHERE tag_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, tagId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Xóa tag thành công: " + tagId);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("deleteTag Error: " + e.getMessage());
        }

        return false;
    }
}
