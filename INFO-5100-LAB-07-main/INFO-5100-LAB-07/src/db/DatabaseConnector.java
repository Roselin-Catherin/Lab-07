/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;
 
/**
 * All JDBC code lives here so the UI panels never write SQL themselves.
 *
 * Before running:
 *   1. Run DatabaseQueries.sql (project root) in MySQL.
 *   2. Add the MySQL Connector/J JAR to the project
 *      (NetBeans: right-click Libraries > Add JAR/Folder).
 *   3. Set db.user / db.password (see below) to match your MySQL install.
 *
 * @author Roselin
 */
public class DatabaseConnector {
 
    // Defaults can be overridden with -Ddb.url=... -Ddb.user=... -Ddb.password=...
    // (or DB_URL / DB_USER / DB_PASSWORD environment variables) so no real password is committed.
    private static final String URL = setting("db.url", "DB_URL", "jdbc:mysql://localhost:3306/user_registration");
    private static final String USER = setting("db.user", "DB_USER", "root");
    private static final String PASSWORD = setting("db.password", "DB_PASSWORD", "root");

    private static String setting(String property, String envVar, String fallback) {
        String value = System.getProperty(property);
        if (value == null || value.isEmpty()) {
            value = System.getenv(envVar);
        }
        return (value == null || value.isEmpty()) ? fallback : value;
    }
 
    private DatabaseConnector() {
        // utility class, no instances
    }
 
    /** Opens a new connection. Callers close it with try-with-resources. */
    public static Connection getConnection() throws SQLException {
        try {
            // Not strictly required with JDBC 4+, but gives a clear error if the JAR is missing
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found. Add mysql-connector-j-x.x.x.jar to the project Libraries.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
 
    // ===================== CREATE =====================
    /** Inserts a user and stores the generated id back into the object. */
    public static void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (first_name, last_name, gender, age, phone, email, "
                   + "continent, hobbies, photo, date_of_birth) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
 
            fillStatement(stmt, user);
            stmt.executeUpdate();
 
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setId(keys.getInt(1));
                }
            }
        }
    }
 
    // ===================== READ =====================
    public static List<User> getAllUsers() throws SQLException {
        String sql = "SELECT id, first_name, last_name, gender, age, phone, email, continent, "
                   + "hobbies, photo, date_of_birth FROM users ORDER BY id";
 
        List<User> users = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
 
            while (rs.next()) {
                java.sql.Date dob = rs.getDate("date_of_birth");
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("continent"),
                        rs.getString("hobbies"),
                        rs.getString("photo"),
                        dob == null ? null : new java.util.Date(dob.getTime())
                ));
            }
        }
        return users;
    }
 
    // ===================== UPDATE =====================
    public static void editUser(User user) throws SQLException {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, gender = ?, age = ?, phone = ?, "
                   + "email = ?, continent = ?, hobbies = ?, photo = ?, date_of_birth = ? WHERE id = ?";
 
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
 
            fillStatement(stmt, user);
            stmt.setInt(11, user.getId());
            stmt.executeUpdate();
        }
    }
 
    // ===================== DELETE =====================
    public static void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
 
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
 
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
 
    /** Sets parameters 1-10 in the same column order used by INSERT and UPDATE. */
    private static void fillStatement(PreparedStatement stmt, User user) throws SQLException {
        stmt.setString(1, user.getFirstName());
        stmt.setString(2, user.getLastName());
        stmt.setString(3, user.getGender());
        stmt.setInt(4, user.getAge());
        stmt.setString(5, user.getPhone());
        stmt.setString(6, user.getEmail());
        stmt.setString(7, user.getContinent());
        stmt.setString(8, user.getHobbies());
        stmt.setString(9, user.getPhoto());
        stmt.setDate(10, user.getDate() == null ? null : new java.sql.Date(user.getDate().getTime()));
    }
}
 