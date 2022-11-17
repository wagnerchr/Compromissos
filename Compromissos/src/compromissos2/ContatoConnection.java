/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compromissos2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Pichau
 */
public class ContatoConnection {
    
    Connection conn;
        
        public ResultSet autenticacao(Usuario usuario) throws SQLException {
            
            conn = new ConnectionFactory().getConnection();
            
            try {
                
                String sql = "SELECT * FROM contato WHERE nome = ? AND ";
                                          
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
