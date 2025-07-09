package Bean;
import java.sql.Timestamp; // Import Timestamp for created_at
public class UserLogin {
    private int userId;
    private String username;
    private String password_hash; // Stores the password
    private String role;         // Stores the role (e.g., "seller", "customer")
    private Timestamp createdAt; // Stores the creation timestamp


    // Default constructor
    public UserLogin() {
        // No explicit initialization needed here, fields will be null/0 by default
    }


    // Constructor for creating new users (without userId and createdAt, as they are auto-generated)
    public UserLogin(String username, String password_hash, String role) {
        this.username = username;
        this.password_hash = password_hash;
        this.role = role;
    }


    // Constructor for retrieving users from DB (all fields)
    public UserLogin(int userId, String username, String password_hash, String role) {
        this.userId = userId;
        this.username = username;
        this.password_hash = password_hash;
        this.role = role;
    }


    // --- Getters and Setters ---

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword_hash() {
        return password_hash;
    }
    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    //to string
    @Override
    public String toString() {
        return "UserLogin{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                //", UserPassword='" + password_hash + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}


