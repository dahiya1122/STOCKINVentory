package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class CONNECTIONDB {
    private static String URL = "jdbc:mysql://localhost:3306/stockinventory ";
    private static String USERNAME = "root";
    private static String PASSWORD = "31072004";

    public static Connection connection;

    public static Connection createDBConnection() {



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }




        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}



