package DaoImplementation;
import Bean.UserLogin;
import Connection.CONNECTIONDB;
import DaoInterface.UserLoginInterface;

import java.sql.*;
//package import with class

//dao
public class ImplementationClassOfUserLogin implements UserLoginInterface {


    @Override
    public void RegisterNewUser(UserLogin UL) {
        // Check if username already exists before inserting
        if (getUserByUsername(UL.getUsername()) != null) {
            System.out.println("Error: Username '" + UL.getUsername() + "' already exists. Please choose a different username.");
            return;
        }

        String query = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";

        try (Connection con = CONNECTIONDB.createDBConnection(); // Get connection from your CONNECTIONDB utility
             PreparedStatement preparedStatement = con.prepareStatement(query)) {


            preparedStatement.setString(1,UL.getUsername());
            preparedStatement.setString(2,UL.getPassword_hash());
            preparedStatement.setString(3,UL.getRole());

            int cnt = preparedStatement.executeUpdate();
            if (cnt != 0) {
                System.out.println("User Inserted successfully ");
            } else {
                System.out.println("User not Inserted please try again ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserLogin AlreadyRegistered(String username , String password_hash) {
        String query = "SELECT user_id, username, password_hash, role, created_at FROM users WHERE username = ? and password_hash=?";



        try (Connection con = CONNECTIONDB.createDBConnection(); // Get connection from your CONNECTIONDB utility
             PreparedStatement pstm = con.prepareStatement(query)) {

            pstm.setString(1, username);
            pstm.setString(2, password_hash);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return new UserLogin(rs.getInt("user_id"), rs.getString("username"), rs.getString("password_hash"), rs.getString("role"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    @Override
    public UserLogin getUserByUsername(String username) { // Retained your method name

        UserLogin ui =null;

        String query = "SELECT user_id, username, password_hash, role FROM users WHERE username = ?";;
        try (Connection con = CONNECTIONDB.createDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            // Set the username parameter for the PreparedStatement
            preparedStatement.setString(1,username);

            // Execute the query and get the ResultSet
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if a row was returned (meaning ui was found)
                if (resultSet.next()) {
                    // Corrected: Added 'new UserLogin' to instantiate the object
                    ui = new UserLogin(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password_hash"),
                            resultSet.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving ui by username: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
        return ui; // Return the UserLogin object (will be null if not found or error)
    }


    @Override
    public void DetailsOfUser( ) {
        Connection con = CONNECTIONDB.createDBConnection();  // Get connection from your CONNECTIONDB utility

        String query = "SELECT * FROM users ";

        System.out.println("Users details : ");

        System.out.println("------------  User Details  ----------");
        System.out.println("--------------------------------------------------------------------------------------------------");
        try {
            Statement statement=con.createStatement();

            ResultSet resultSet1 = statement.executeQuery(query);
            while (resultSet1.next()) {

                System.out.println(resultSet1.getInt(1));
                System.out.println(resultSet1.getString(2));
                System.out.println(resultSet1.getString(3));
                System.out.println(resultSet1.getString(4));
                System.out.println(resultSet1.getTimestamp(5));

                System.out.println("=========================================================");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


