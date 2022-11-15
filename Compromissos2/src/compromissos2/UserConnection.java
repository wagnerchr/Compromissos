
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
                String sql = "SELECT * FROM cadastro WHERE login = ? AND senha = ?";
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
