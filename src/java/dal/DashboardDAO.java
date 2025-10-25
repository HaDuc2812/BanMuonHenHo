package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardDAO extends DBContext {

    public int getTotalUsers() {
        String sql = "SELECT COUNT(*) FROM users";
        return getCount(sql);
    }

    public int getTotalMatches() {
        String sql = "SELECT COUNT(*) FROM matches";
        return getCount(sql);
    }

    public int getPendingMatches() {
        String sql = "SELECT COUNT(*) FROM matches WHERE status = 'Pending'";
        return getCount(sql);
    }

    public int getCompletedMatches() {
        String sql = "SELECT COUNT(*) FROM matches WHERE status = 'Completed'";
        return getCount(sql);
    }

    private int getCount(String sql) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
