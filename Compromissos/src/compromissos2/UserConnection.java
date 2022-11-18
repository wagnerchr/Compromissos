
package compromissos2;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UserConnection {
    
   
        Connection conn;
        
        public ResultSet autenticacao(Usuario usuario) throws SQLException {
            
            conn = new ConnectionFactory().getConnection();
            
            try {
                
                if(usuario.getLogin() == null) {
                    
                    String sql = "SELECT * FROM pessoa WHERE email = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                     ps.setString(1, usuario.getEmail()); 
                     
                    ResultSet rs = ps.executeQuery();
                    return rs;
                    
                }
                
                String sql = "SELECT * FROM pessoa WHERE login = ? AND senha = ?";
                                          
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, usuario.getLogin());   
                ps.setString(2, usuario.getSenha());   
                
                
                ResultSet rs = ps.executeQuery();
                return rs;
                
                
            } catch ( SQLException err) {
                JOptionPane.showMessageDialog(null, err);
                return null;
            }
            
        }
    
    
}
