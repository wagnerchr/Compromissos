package view;

import com.mysql.cj.conf.PropertyKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import compromissos2.Usuario;
import compromissos2.connections.ConnectionFactory;
import compromissos2.connections.UserConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static view.AddContato.usuario;


public class VerContato extends javax.swing.JFrame {

    static Usuario contato;
    static Usuario usuario;
    
    static Boolean edit = false;
    static ArrayList<JTextField> campos = new ArrayList<>();
    
    
    public VerContato(Usuario contato, Usuario usuario) {
        
        initComponents();
        
        
        

       

        this.contato = contato;
        this.usuario = usuario;   
        
        this.setLocationRelativeTo(null);         
        this.setResizable(false);
        this.setDefaultCloseOperation(0);
     
        
        campos.addAll(Arrays.asList(      
                textNome,
                textData_nasc,
                textEndereco,
                textTelefone,
                textEmail      
        ));
               
        textNome.setText(contato.getNome());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        textData_nasc.setText(dateFormat.format(contato.getData_nasc()));
                          
        textEndereco.setText(contato.getEndereco());
        textTelefone.setText(contato.getTelefone());
        textEmail.setText(contato.getEmail());
        
        corregaTexto(campos, edit);
                               
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField();
        textData_nasc = new javax.swing.JTextField();
        textEndereco = new javax.swing.JTextField();
        textTelefone = new javax.swing.JTextField();
        textEmail = new javax.swing.JTextField("Email");
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);

        jLabel1.setText("jLabel1");

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Excluir Contato");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textNome)
                            .addComponent(textData_nasc)
                            .addComponent(textEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(textTelefone)
                            .addComponent(textEmail))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(155, 155, 155)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                        .addComponent(btnEdit)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(textData_nasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(textEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(btnEdit))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection conn;
    
    public void corregaTexto(ArrayList<JTextField> campos, Boolean e) {
        
        for(int i = 0; i < campos.size(); i++) 
            campos.get(i).setEnabled(e);                
    }
       
/*
    public String ConvertDataSQL(Date data) {

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(data);

        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        //String diaS = dia <= 9 ? "0" + String.valueOf(dia) : String.valueOf(dia);
        
        String dataSQL = ano + "-" + mes + "-" + dia;
        return dataSQL;
    }
*/    
// DATE TO STRING
    /*
    public String ConvertData(Date data) {
           
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(data);

        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH) + 1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
           
        String mesS = mes <= 9 ? "0" + String.valueOf(mes) : String.valueOf(mes);
        String diaS = mes <= 9 ? "0" + String.valueOf(dia) : String.valueOf(dia);
        
        String dataSQL = diaS + "/" + mesS + "/" + ano;
        
        return dataSQL;
    }
    */
//
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.dispose();
       new Home(usuario).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        if(btnEdit.getText() == "Confirmar")
            editarContato(contato);
        
        if(!edit) {              
            edit = true;
            corregaTexto(campos, edit);
            btnEdit.setText("Confirmar");
        } else {
            
            edit = false;
            corregaTexto(campos, edit);
            btnEdit.setText("Editar");           
        }
        
        
  
    }//GEN-LAST:event_btnEditActionPerformed

// EXCLUIR CONTATO
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          
        int dialogResult = JOptionPane.showConfirmDialog(this, "Your Message", "Title on Box", JOptionPane.YES_NO_OPTION);
       
        if(dialogResult == 0) 
            excluirContato(contato);
        else 
             System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void editarContato(Usuario contato) {
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            
            String nome, data_nascS, endereco, telefone, email;
              
            nome = textNome.getText();   
            data_nascS = textData_nasc.getText();
            endereco = textEndereco.getText();
            telefone = textTelefone.getText();
            email = textEmail.getText();
             
            Date data_nasc = formatter.parse(data_nascS); 
            
            System.out.println("EDITANDO DATA : " + data_nascS);
            
            Usuario contatoEdit = new Usuario(
                    nome,
                    data_nasc,
                    endereco,
                    telefone,
                    email
            );
              
            
            InsereContatoBanco(contatoEdit);
            
            this.setVisible(false);
            Home home = new Home(usuario);
            home.setVisible(true);
   
        } catch(Exception ex) {        
            JOptionPane.showMessageDialog(null, "Erro em EditaContato: " + ex.getMessage());
        }  
    }
    
    public void excluirContato(Usuario contato) {
    
        String query = "DELETE FROM pessoa WHERE id = ?"; 
        
        PreparedStatement ps;
        
        try {
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            ps = conn.prepareStatement(query);
            
            ps.setInt(1, contato.getId());
         
            ps.executeUpdate();   
            conn.commit();
            ps.close();
            
            excluirLigacao(contato);
                      
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro em excluirContato: " + ex.getMessage());
        
        } 
    }
    
    public void excluirLigacao(Usuario contato) {
        
        String query = "DELETE FROM pessoacontato WHERE id_contato = ?";
        PreparedStatement ps;
        
        try {
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            ps = conn.prepareStatement(query);
            
            ps.setInt(1, contato.getId());     
            
            ps.executeUpdate();               
            conn.commit();
            ps.close();
                        
            JOptionPane.showMessageDialog(null, "Usuario Excluído!");
            this.dispose();
            new Home(usuario).setVisible(true);
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro em excluirLigacao: " + ex.getMessage());
        
        } 
    }
    
    public void InsereContatoBanco(Usuario contatoEdit) {
    
        String query = "UPDATE pessoa SET nome = ?, data_nasc = ?, endereco = ?, telefone = ?, email = ? WHERE id = + '" + contato.getId() + "' ";      
        PreparedStatement ps;
               
        try {
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            // String dataSQL = ConvertDataSQL( contatoEdit.getData_nasc() ); 
                       
            java.sql.Date sqlDate = new java.sql.Date(contatoEdit.getData_nasc().getTime());
          
            ps = conn.prepareStatement(query);
            
            ps.setString(1, contatoEdit.getNome());
            ps.setDate(2, sqlDate);
            ps.setString(3, contatoEdit.getEndereco());
            ps.setString(4, contatoEdit.getTelefone());
            ps.setString(5, contatoEdit.getEmail());          
                
            ps.executeUpdate();
            conn.commit();
            ps.close();
            
            //ConectaContato(contato);
            JOptionPane.showMessageDialog(null, "Contato editado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em InsereContatoBanco: " + ex.getMessage());
        }
    
    }
    
    
    public void ConectaContato(Usuario contato) {
          
        try {   
            
            // Conexão com usuário desta conta
           
            UserConnection connection_usuario = new UserConnection();   
            
            System.out.println("Usuario: " + contato );         
            
            
            ResultSet rsUsuario = connection_usuario.autenticacao(usuario);           
            ResultSet rsContato = connection_usuario.autenticacao(contato);           
            
             String idUsuario = "", idContato = "";
            
            if(rsUsuario.next()) 
                idUsuario = rsUsuario.getString("id");
            else 
                JOptionPane.showMessageDialog(null, "Problema ao estabelecer conexão com o usuário");
                        
            if(rsContato.next()) 
                idContato = rsContato.getString("id");
            else 
                JOptionPane.showMessageDialog(null, "Problema ao estabelecer conexão com o contato adicionado");

            // ID USUÁRIO E ID CONTATO
            String query = "insert into pessoacontato() values(?, ?)";
            
        // Conexão com banco
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            PreparedStatement ps = conn.prepareStatement(query);  
                 
            ps.setInt(1, Integer.parseInt(idUsuario));
            ps.setInt(2, Integer.parseInt(idContato));
            
     
            ps.execute();
            conn.commit();
            ps.close();
             
                      
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());          
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VerContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VerContato(contato, usuario).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField textData_nasc;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textEndereco;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextField textTelefone;
    // End of variables declaration//GEN-END:variables
}
