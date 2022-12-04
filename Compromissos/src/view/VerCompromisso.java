package view;

import java.sql.Connection;
import compromissos2.Compromisso;
import compromissos2.Usuario;
import compromissos2.connections.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static view.VerContato.campos;


public class VerCompromisso extends javax.swing.JFrame {

    static Compromisso compromisso;
    static Usuario usuario;
    
    static Boolean edit = false;
    
    
    public VerCompromisso(Compromisso compromisso, Usuario usuario) {
        
        initComponents();
        
        this.compromisso = compromisso;
        this.usuario = usuario;
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        textNome.setText(compromisso.getNome());
        textDescricao.setText(compromisso.getDescricao());
        textLocal.setText(compromisso.getLocalc());
    
        String inicio = compromisso.getInicio().toString();
        String fim = compromisso.getFim().toString();
        
        textInicio.setText(inicio.substring(0, 10) + " " + inicio.substring(11));
        textFim.setText(fim.substring(0, 10) + " " + fim.substring(11));
                 
        
        campos.addAll(Arrays.asList(      
                textNome,
                textDescricao,
                textLocal,
                textInicio,
                textFim      
        ));
        
     
        carregaTexto(campos, edit);
    }

    public void carregaTexto(ArrayList<JTextField> campos, Boolean e) {
        
        for(int i = 0; i < campos.size(); i++) 
            campos.get(i).setEnabled(e);                
    }
    
    private void editarCompromisso(Compromisso compromisso) {
    

        try {
            
            String nome, descricao, localc, data_inicio, data_fim;
                   
            nome = textNome.getText();   
            descricao = textDescricao.getText();
            localc = textLocal.getText();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime inicio = LocalDateTime.parse(textInicio.getText(), formatter);
            LocalDateTime fim = LocalDateTime.parse(textFim.getText(), formatter);
     
                 
            Compromisso compromissoEdit = new Compromisso(
                    nome,     
                    descricao,
                    localc,
                    inicio,
                    fim
            );
            
            System.out.println("NOME FICOU ASSIM : " + compromissoEdit.getNome() );
              
            
            InsereCompromissoBanco(compromissoEdit);
            
            this.setVisible(false);
            Home home = new Home(usuario);
            home.setVisible(true);
   
        } catch(Exception ex) {        
            JOptionPane.showMessageDialog(null, "Erro em EditaContato: " + ex.getMessage());
        }  
    }
    
    private void InsereCompromissoBanco(Compromisso compromissoEdit) {
        
        String query = "UPDATE compromisso SET nome = ?, descricao = ?, localc = ?, data_inicio = ?, data_fim = ? WHERE id = + '" + compromisso.getId() + "' ";  
        PreparedStatement ps;
        
        
        System.out.println("ID IDID I DI " + compromisso.getId());
        
         try {
             
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
                                        
            ps = conn.prepareStatement(query);
            
            ps.setString(1, compromissoEdit.getNome());
            ps.setString(2, compromissoEdit.getDescricao());
            ps.setString(3, compromissoEdit.getLocalc());
            ps.setTimestamp(4, Timestamp.valueOf(compromissoEdit.getInicio()));
            ps.setTimestamp(5, Timestamp.valueOf(compromissoEdit.getFim()));
                 
                
            ps.executeUpdate();
            conn.commit();
            ps.close();
            
            //ConectaContato(contato);
            JOptionPane.showMessageDialog(null, "Compromisso editado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em InsereContatoBanco: " + ex.getMessage());
        }
    
    }
       
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textNome = new javax.swing.JTextField();
        textDescricao = new javax.swing.JTextField();
        textLocal = new javax.swing.JTextField();
        textInicio = new javax.swing.JTextField();
        textFim = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textLocalActionPerformed(evt);
            }
        });

        textInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textInicioActionPerformed(evt);
            }
        });

        textFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFimActionPerformed(evt);
            }
        });

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jButton2.setText("Voltar");

        jButton3.setText("Escluir Compromisso");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Contatos adicionados: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(173, 173, 173)
                        .addComponent(btnEdit)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textNome)
                                .addComponent(textDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                .addComponent(textLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(textFim, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(textLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection conn;
    
    private void textFimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFimActionPerformed

    private void textInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textInicioActionPerformed

    private void textLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLocalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textLocalActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        if(btnEdit.getText() == "Confirmar")
            editarCompromisso(compromisso);
        
        if(!edit) {              
            edit = true;
            carregaTexto(campos, edit);
            btnEdit.setText("Confirmar");
        } else {
            
            edit = false;
            carregaTexto(campos, edit);
            btnEdit.setText("Editar");           
        }
    }//GEN-LAST:event_btnEditActionPerformed

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
            java.util.logging.Logger.getLogger(VerCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerCompromisso(compromisso, usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField textDescricao;
    private javax.swing.JTextField textFim;
    private javax.swing.JTextField textInicio;
    private javax.swing.JTextField textLocal;
    private javax.swing.JTextField textNome;
    // End of variables declaration//GEN-END:variables
}
