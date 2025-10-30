/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Match;
import model.Users;

/**
 *
 * @author HA DUC
 */
public class MatchDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;

    public boolean addMatch(Match m) {
        String sql = "INSERT INTO Matches (user1_id, user2_id, admin_id, status) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, m.getUser1Id());
            ps.setInt(2, m.getUser2Id());
            ps.setInt(3, m.getAdminId());
            ps.setString(4, m.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Match> getAllMatch() {
        List<Match> list = new ArrayList<>();
        String sql = "Select * from Matches";
        try {
            ps = connection.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Match m = new Match();
                m.setMatchId(rs.getInt("match_id"));
                m.setUser1Id(rs.getInt("user1_id"));
                m.setUser2Id(rs.getInt("user2_id"));
                m.setAdminId(rs.getInt("admin_id"));
                m.setMatchDate(rs.getTimestamp("match_date"));
                m.setStatus(rs.getString("status"));

                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Match getMatchById(int id) {
        String sql = """
        SELECT m.*, 
               u1.username AS user1_name, 
               u2.username AS user2_name 
        FROM Matches m
        JOIN Users u1 ON m.user1_id = u1.user_id
        JOIN Users u2 ON m.user2_id = u2.user_id
        WHERE m.match_id = ?
    """;
        try {
            ps = connection.prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Match m = new Match();
                m.setMatchId(rs.getInt("match_id"));
                m.setUser1Id(rs.getInt("user1_id"));
                m.setUser2Id(rs.getInt("user2_id"));
                m.setAdminId(rs.getInt("admin_id"));
                m.setMatchDate(rs.getTimestamp("match_date"));
                m.setStatus(rs.getString("status"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateMatch(Match m) {
        String sql = "UPDATE Matches SET user1_id=?, user2_id=?, admin_id=?, status=? WHERE match_id=?";
        try {
            ps = connection.prepareCall(sql);
            ps.setInt(1, m.getUser1Id());
            ps.setInt(2, m.getUser2Id());
            ps.setInt(3, m.getAdminId());
            ps.setString(4, m.getStatus());
            ps.setInt(5, m.getMatchId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMatch(int id) {
        String sql = "DELETE FROM Matches WHERE match_id = ?";
        try {
            ps = connection.prepareCall(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Match> getPendingMatchesForUser(int userId) {
        List<Match> list = new ArrayList<Match>();
        String sql = "SELECT * FROM Matches WHERE (user1_id = ? OR user2_id = ?) AND status = 'pending'";
        try {
            ps = connection.prepareCall(sql);
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Match m = new Match();
                m.setMatchId(rs.getInt("match_id"));
                m.setUser1Id(rs.getInt("user1_id"));
                m.setUser2Id(rs.getInt("user2_id"));
                m.setAdminId(rs.getInt("admin_id"));
                m.setStatus(rs.getString("status"));
                m.setMatchDate(rs.getTimestamp("match_date"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean updateMatchStatus(int matchId, String status) throws SQLException{
         String sql = "UPDATE Matches SET status = ? WHERE match_id = ?";
         try{
             ps = connection.prepareCall(sql);
             ps.setString(1, status);
             ps.setInt(2, matchId);
             int rows = ps.executeUpdate();
             return rows >0;
         }catch(Exception e){
             e.printStackTrace();
             return false;
         }
    }
}
