package oogasalad.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to interact with the database.
 */
public class DatabaseHelper {

  private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://106.15.233.200:3306/CS308";
  private static final String USER = "CS308";
  private static final String PASS = "CS308Farm";

  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    Class.forName(JDBC_DRIVER);
    return DriverManager.getConnection(DB_URL, USER, PASS);
  }

  public static boolean userExists(String username) {
    String sql = "SELECT * FROM user WHERE username=?";
    try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, username);
      try (ResultSet rs = stmt.executeQuery()) {
        return rs.next();
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean isValidUser(String username, String password) {
    String sql = "SELECT * FROM user WHERE username=? AND password=?";
    try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, username);
      stmt.setString(2, password);
      try (ResultSet rs = stmt.executeQuery()) {
        return rs.next();
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static int getUserId(String username) {
    String sql = "SELECT id FROM user WHERE username=?";
    try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, username);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return rs.getInt("id");
        }
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return -1;
  }


  public static boolean addUser(String username, String email, String password) {
    if (userExists(username)) {
      return false;
    }
    String sql = "INSERT INTO user (username, email, password) VALUES (?, ?, ?)";
    try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, username);
      stmt.setString(2, email);
      stmt.setString(3, password);
      int rowsAffected = stmt.executeUpdate();
      return rowsAffected > 0;
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      return false;
    }
  }

}
