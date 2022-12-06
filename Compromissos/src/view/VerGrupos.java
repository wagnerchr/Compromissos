package view;

import compromissos2.Compromisso;
import compromissos2.Grupo;
import compromissos2.Usuario;
import compromissos2.connections.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static view.VerContato.campos;
import static view.VerContato.contato;
import static view.VerContato.edit;
import static view.VerContato.usuario;


public class VerGrupos extends javax.swing.JFrame {

    static Grupo grupo;
    static Usuario usuario;
    
    static Boolean edit = false;
    static ArrayList<JTextField> campos = new ArrayList<>();

        
    public VerGrupos(Grupo grupo, Usuario usuario) {
        
        initComponents();
        
        this.grupo = grupo;
        this.usuario = usuario;
        
        this.setLocationRelativeTo(null);         
        this.setResizable(false);
        this.setDefaultCloseOperation(0);
        
        campos.addAll(Arrays.asList(      
                textNome,
                textDescricao               
        ));
        
        textNome.setText(grupo.getNome());
        textDescricao.setText(grupo.getDescricao());
             
        corregaTexto(campos, edit); 
        carregaContatos(grupo, usuario);        
    }
    
    public void corregaTexto(ArrayList<JTextField> campos, Boolean e) {
        
        for(int i = 0; i < campos.size(); i++) 
            campos.get(i).setEnabled(e);                
    }

    
     private void carregaContatos(Grupo grupo, Usuario usuario) {
           
        try {      
            
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
                     
            String query = "SELECT * FROM pessoa p WHERE login is null AND p.id IN ( SELECT id_pessoa FROM pessoagrupo pc WHERE pc.id_grupo = ?);";
                    
            
            PreparedStatement ps = conn.prepareStatement(query);  
            ps.setInt(1, grupo.getId());
                     
            ResultSet rs = ps.executeQuery();            
                       
            DefaultListModel demoList = new DefaultListModel();

            while(rs.next()) {               
                demoList.addElement(rs.getString("nome"));                               
            }
                       
            listGrupos.setModel(demoList);
                     
                                  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        }    
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textNome = new javax.swing.JTextField();
        textDescricao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listGrupos = new javax.swing.JList<>();
        btnVoltar = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textNome.setText("jTextField1");

        textDescricao.setText("jTextField1");

        listGrupos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listGrupos);

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir Grupo");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                .addComponent(btnExcluir)
                .addGap(154, 154, 154)
                .addComponent(btnEdit)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVoltar)
                            .addComponent(btnEdit)
                            .addComponent(btnExcluir)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 190, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection conn;
    
    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.dispose();
        new Home(usuario).setVisible(true);
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        if(btnEdit.getText() == "Confirmar")
            editarGrupo(grupo);
        
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

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        
        int dialogResult = JOptionPane.showConfirmDialog(this, "Quer mesmo excluir este grupo?", "Excluir Grupo", JOptionPane.YES_NO_OPTION);
       
        if(dialogResult == 0) 
            excluirLigacao(grupo);
           
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void excluirGrupo(Grupo grupo) {
    
        String query = "DELETE FROM grupo WHERE id = ?";        
        PreparedStatement ps;
        
        try {
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            ps = conn.prepareStatement(query);
            
            ps.setInt(1, grupo.getId());
         
            ps.executeUpdate();   
            conn.commit();
            ps.close();
                  
            JOptionPane.showMessageDialog(null, "Grupo Excluído!");
            this.dispose();
            new Home(usuario).setVisible(true);
                    
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro em excluirContato: " + ex.getMessage());
        
        } 
    }
    
    private void excluirLigacao(Grupo grupo) {
    
        String query = "DELETE FROM pessoagrupo WHERE id_grupo = ?";
        PreparedStatement ps;
        
        try {
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            ps = conn.prepareStatement(query);
            
            ps.setInt(1, grupo.getId());     
            
            ps.executeUpdate();               
            conn.commit();
            ps.close();
                  
            excluirGrupo(grupo);
                          
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro em excluirLigacao: " + ex.getMessage());      
        }  
    }
    
    
    private void editarGrupo(Grupo grupo) {
    
        try {
            
            String nome, descricao;
              
            nome = textNome.getText();   
            descricao = textDescricao.getText();
             
    
            Grupo grupoEdit = new Grupo(
                    nome,
                    descricao         
            );
                
            InsereGrupoBanco(grupoEdit);
            
            
            this.setVisible(false);
            Home home = new Home(usuario);
            home.setVisible(true);
   
        } catch(Exception ex) {        
            JOptionPane.showMessageDialog(null, "Erro em EditaContato: " + ex.getMessage());
        }    
    }
    
    private void InsereGrupoBanco(Grupo grupoEdit) {
    
        String query = "UPDATE grupo SET nome = ?, descricao = ? WHERE id = + '" + grupo.getId() + "' ";      
        PreparedStatement ps;
               
        try {
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
                   
            ps = conn.prepareStatement(query);
            
            ps.setString(1, grupoEdit.getNome());
            ps.setString(2, grupoEdit.getDescricao());                  
                
            ps.executeUpdate();
            conn.commit();
            ps.close();
                     
            JOptionPane.showMessageDialog(null, "Grupo editado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em InsereContatoBanco: " + ex.getMessage());
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
            java.util.logging.Logger.getLogger(VerGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerGrupos(grupo, usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listGrupos;
    private javax.swing.JTextField textDescricao;
    private javax.swing.JTextField textNome;
    // End of variables declaration//GEN-END:variables
}
