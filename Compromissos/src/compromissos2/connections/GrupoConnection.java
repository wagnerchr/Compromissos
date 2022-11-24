package compromissos2.connections;

import compromissos2.Grupo;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GrupoConnection {
    
    Connection conn;
    
    public ResultSet autenticacao(Grupo grupo) throws SQLException {
    
        conn = new ConnectionFactory().getConnection();
        
        try {
            
            String sql = "SELECT * FROM grupo WHERE nome = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, grupo.getNome());
            
            ResultSet rs = ps.executeQuery();
            return rs;
            
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, err);
                return null;   
        }
    }
    
    
}
