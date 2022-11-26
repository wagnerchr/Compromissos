package compromissos2.connections;

import compromissos2.Compromisso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CompromissoConnection {
    
    Connection conn;
    
    public ResultSet autenticacao(Compromisso compromisso) throws SQLException {
    
        conn = new ConnectionFactory().getConnection();
        
        try {
        
            String sql = "SELECT * FROM compromisso WHERE nome = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, compromisso.getNome());
            
            ResultSet rs = ps.executeQuery();
            return rs;
            
        
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, err);
                return null;   
        } 
    }
    
}
