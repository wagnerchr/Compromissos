package compromissos2;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        
        String url = "jdbc:mysql://localhost:3306/compromissos?useSSL=false";
        String user = "root";
        String password = "123456";
            
        try {      
            
            Class.forName("com.mysql.jdbc.Driver");           
        } catch (ClassNotFoundException e) {
            
            System.out.println(e);
            
        } finally {
            
            return DriverManager.getConnection(url, user, password);
        }
           
    }  
}