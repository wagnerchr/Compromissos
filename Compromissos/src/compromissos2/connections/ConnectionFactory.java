package compromissos2.connections;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

// CONECTAR AO BANCO
public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        
        String url = "jdbc:mysql://localhost:3306/compromissos?allowPublicKeyRetrieval=true&useSSL=false";
        String user = "root";
        String password = "123456";
            
        try {      
            
            Class.forName("com.mysql.cj.jdbc.Driver");           
        } catch (ClassNotFoundException e) {
            
            System.out.println(e);
            
        } finally {
            
            return DriverManager.getConnection(url, user, password);
        }
           
    }  
}