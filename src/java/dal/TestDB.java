/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author HA DUC
 */
public class TestDB {
     public static void main(String[] args) {
        DBContext db = new DBContext();

        if (db.connection != null) {
            System.out.println("✅ Database connection successful!");

            // Optional: Try a small query to confirm data access
            try (Statement st = db.connection.createStatement()) {
                ResultSet rs = st.executeQuery("SELECT TOP 1 * FROM Users");
                while (rs.next()) {
                    System.out.println("User ID: " + rs.getInt("user_id"));
                    System.out.println("Username: " + rs.getString("username"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("❌ Database connection failed.");
        }
    }
}
