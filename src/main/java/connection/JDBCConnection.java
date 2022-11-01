package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    public static Connection connect() throws SQLException {
        // this is for demo but please don't setup username and password in code , instead read from config file or Vault/KMS
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/User?serverTimezone=UTC", "root", "root");
        if(connection != null){
                System.out.println("Connected to database");
        }
        return connection;
    }
}
