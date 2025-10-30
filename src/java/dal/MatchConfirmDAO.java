/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HA DUC
 */
public class MatchConfirmDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;

    public boolean insertMatchConfirmation(int matchId, int userId, String status) {
        String sql = "INSERT INTO match_confirmations (match_id, user_id, status) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, matchId);
            ps.setInt(2, userId);
            ps.setString(3, status);

            System.out.println("=== insertMatchConfirmation Debug ===");
            System.out.println("Match ID: " + matchId);
            System.out.println("User ID: " + userId);
            System.out.println("Status: " + status);

            int rows = ps.executeUpdate();
            System.out.println("Rows affected: " + rows);

            return rows > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        try {

            // ✅ Gọi DAO để test
            MatchConfirmDAO dao = new MatchConfirmDAO();
            int matchId = 15;   // 🔹 thay bằng ID thực có trong bảng Matches
            int userId = 11;     // 🔹 thay bằng ID thực có trong bảng Users
            String status = "confirmed";  // hoặc "rejected"

            boolean result = dao.insertMatchConfirmation(matchId, userId, status);

            if (result) {
                System.out.println("✅ Dữ liệu đã được chèn thành công vào MatchConfirmations!");
            } else {
                System.out.println("❌ Chèn thất bại (có thể do trùng khóa hoặc lỗi kết nối).");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
