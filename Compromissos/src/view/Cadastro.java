/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import compromissos2.connections.ConnectionFactory;
import compromissos2.Hash;
import compromissos2.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextField;

/**
 *
 * @author Pichau
 */
public class Cadastro extends javax.swing.JFrame {

    //public static Connection conn;
    
    public Cadastro() {

        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
    // PlaceHolders 
        ArrayList<JTextField> campos = new ArrayList<>(Arrays.asList(
                textNome,
                textLogin,
                textData_nasc,
                textEndereco,
                textTelefone,
                textEmail,
                textSenha,
                textSenhaConfirm
                
        ));
        
        startPlaceHolders(campos);
    
    }

    public void startPlaceHolders(ArrayList<JTextField> campos) {
        
        for (int i = 0; i < campos.size(); i++) {
            Font font = campos.get(i).getFont();
            font = font.deriveFont(Font.ITALIC);
            campos.get(i).setFont(font);
            campos.get(i).setForeground(Color.gray);
        }
    }

    public void singlePlaceHolder(JTextField texto) {
        Font font = texto.getFont();
        font = font.deriveFont(Font.ITALIC);       
        texto.setFont(font);
        texto.setForeground(Color.gray);
    }

    public void removePlaceHolder(JTextField texto) {
        Font font = texto.getFont();
        font = font.deriveFont(Font.PLAIN | Font.BOLD);
        texto.setFont(font);
        texto.setForeground(Color.black);
    }
    
      
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinField1 = new com.toedter.components.JSpinField();
        textNome = new javax.swing.JTextField("Nome do Usuário");
        textEndereco = new javax.swing.JTextField("Endereço");
        textTelefone = new javax.swing.JTextField("Número de Telefone");
        textEmail = new javax.swing.JTextField("Email");
        textSenha = new javax.swing.JTextField("Senha");
        textSenhaConfirm = new javax.swing.JTextField("Confirmar Senha");
        btnCadastrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textLogin = new javax.swing.JTextField("Login");
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        textData_nasc = new javax.swing.JTextField("dd/MM/yyyy");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textNomeFocusLost(evt);
            }
        });

        textEndereco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textEnderecoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textEnderecoFocusLost(evt);
            }
        });

        textTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textTelefoneFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textTelefoneFocusLost(evt);
            }
        });

        textEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textEmailFocusLost(evt);
            }
        });

        textSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textSenhaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textSenhaFocusLost(evt);
            }
        });
        textSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSenhaActionPerformed(evt);
            }
        });

        textSenhaConfirm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textSenhaConfirmFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textSenhaConfirmFocusLost(evt);
            }
        });
        textSenhaConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSenhaConfirmActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        jLabel1.setText("Data de Nascimento");

        textLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textLoginFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textLoginFocusLost(evt);
            }
        });

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Cadastrar Usuário");

        textData_nasc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textData_nascFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textData_nascFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textData_nasc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSenhaConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrar))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(textEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textData_nasc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSenhaConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        textEndereco.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection conn;

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {

            String nome, data_nascS, login, endereco, telefone, email, senha, senhaConfirm;
            // Date data_nasc;

            
            nome = textNome.getText();          
            data_nascS = textData_nasc.getText();
            endereco = textEndereco.getText();
            telefone = textTelefone.getText();
            email = textEmail.getText();
            
            login = textLogin.getText();
            
        // SENHAS 
            Hash hash = new Hash();
            senha = hash.hashSenha(textSenha.getText());
            senhaConfirm = hash.hashSenha(textSenhaConfirm.getText());
             
            
            Date data_nasc = formatter.parse(data_nascS);

           

            // CONFIRMA SENHAS IGUAIS
            if (!senha.equals(senhaConfirm)) {             
               JOptionPane.showMessageDialog(null, "As senhas devem ser iguais!");
            } else if (senha.equals(senhaConfirm)) {     
                
                Usuario usuario = new Usuario(                     
                        nome,
                        login,
                        data_nasc,
                        endereco, telefone,
                        email,
                        senha,
                        senhaConfirm);
                
                // ADICIONAR AO MYSQL
                InsereUsuarioBanco(usuario);
                
                // Fecha Janela
                new Login().setVisible(true);
                this.setVisible(false);
            }                     
        } catch (Exception e) {
            System.out.println(e);
        }
    }

// INSERINDO NOVO OBJETO AO BANCO DE DADOS MYSQL 
    private void InsereUsuarioBanco(Usuario usuario) {
        
        // nome, data_nasc, endereco, telefone, email, senha
        String query = "insert into pessoa() values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;

        try {
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);

            String dataSQL = ConvertData(usuario.getData_nasc());         
            ps = conn.prepareStatement(query);
            
            ps.setString(1, null);       
            ps.setString(2, usuario.getNome());
            ps.setString(3, dataSQL);
            ps.setString(4, usuario.getEndereco());
            ps.setString(5, usuario.getTelefone());
            ps.setString(6, usuario.getEmail());
        // Login
            ps.setString(7, usuario.getLogin());
            ps.setString(8, usuario.getSenha());
            ps.setBoolean(9, true);

            ps.execute();
            conn.commit();
            ps.close();

            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

// PLACEHOLDERS
    
    private void textNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNomeFocusGained
        if(textNome.getText().equals("Nome do Usuário"))
            textNome.setText("");
        removePlaceHolder(textNome);
    }//GEN-LAST:event_textNomeFocusGained

    private void textNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNomeFocusLost
        if(textNome.getText().equals("")) {
            textNome.setText("Nome do Usuário");
            singlePlaceHolder(textNome);
        }
    }//GEN-LAST:event_textNomeFocusLost

    private void textLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textLoginFocusGained
        if(textLogin.getText().equals("Login"))
            textLogin.setText("");
         removePlaceHolder(textLogin);
    }//GEN-LAST:event_textLoginFocusGained

    private void textLoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textLoginFocusLost
        if(textLogin.getText().equals("")) {
            textLogin.setText("Login");
            singlePlaceHolder(textLogin);
        }
    }//GEN-LAST:event_textLoginFocusLost
        
    private void textEnderecoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textEnderecoFocusGained
        if(textEndereco.getText().equals("Endereço"))
            textEndereco.setText("");
         removePlaceHolder(textEndereco);
    }//GEN-LAST:event_textEnderecoFocusGained

    private void textEnderecoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textEnderecoFocusLost
        if(textEndereco.getText().equals("")) {
            textEndereco.setText("Endereço");
            singlePlaceHolder(textEndereco);
        }
    }//GEN-LAST:event_textEnderecoFocusLost

    private void textTelefoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textTelefoneFocusGained
         if(textTelefone.getText().equals("Número de Telefone"))
            textTelefone.setText("");
         removePlaceHolder(textTelefone);
    }//GEN-LAST:event_textTelefoneFocusGained

    private void textTelefoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textTelefoneFocusLost
         if(textTelefone.getText().equals("")) {
            textTelefone.setText("Número de Telefone");
            singlePlaceHolder(textTelefone);
        }
    }//GEN-LAST:event_textTelefoneFocusLost

    private void textEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textEmailFocusGained
         if(textEmail.getText().equals("Email"))
            textEmail.setText("");
         removePlaceHolder(textEmail);
    }//GEN-LAST:event_textEmailFocusGained

    private void textEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textEmailFocusLost
         if(textEmail.getText().equals("")) {
            textEmail.setText("Email");
            singlePlaceHolder(textEmail);
        }
    }//GEN-LAST:event_textEmailFocusLost

    private void textSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSenhaFocusGained
         if(textSenha.getText().equals("Senha"))
            textSenha.setText("");
         removePlaceHolder(textSenha);
    }//GEN-LAST:event_textSenhaFocusGained

    private void textSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSenhaFocusLost
         if(textSenha.getText().equals("")) {
            textSenha.setText("Senha");
            singlePlaceHolder(textSenha);
        }
    }//GEN-LAST:event_textSenhaFocusLost

    private void textSenhaConfirmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSenhaConfirmFocusGained
         if(textSenhaConfirm.getText().equals("Confirmar Senha"))
            textSenhaConfirm.setText("");
         removePlaceHolder(textSenhaConfirm);
    }//GEN-LAST:event_textSenhaConfirmFocusGained

    private void textSenhaConfirmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSenhaConfirmFocusLost
         if(textSenhaConfirm.getText().equals("")) {
            textSenhaConfirm.setText("Confirmar Senha");
            singlePlaceHolder(textSenhaConfirm);
        }
    }//GEN-LAST:event_textSenhaConfirmFocusLost

    private void textSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSenhaActionPerformed

    private void textSenhaConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSenhaConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSenhaConfirmActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textData_nascFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textData_nascFocusGained
        if(textData_nasc.getText().equals("dd/MM/yyyy"))
            textData_nasc.setText("");
         removePlaceHolder(textData_nasc);
    }//GEN-LAST:event_textData_nascFocusGained

    private void textData_nascFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textData_nascFocusLost
        if(textData_nasc.getText().equals("")) {
            textData_nasc.setText("dd/MM/yyyy");
            singlePlaceHolder(textData_nasc);
        }
    }//GEN-LAST:event_textData_nascFocusLost

// END PLACEHOLDERS
    
    
    public String ConvertData(Date data) {

        String FORMATO_ANTIGO = "dd/MM/yyyy";
        // String SQL_FORMATO = "yyyy-MM-dd";

        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_ANTIGO);

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(data);

        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        String dataSQL = ano + "-" + mes + "-" + dia;
        return dataSQL;
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
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cadastro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.toedter.components.JSpinField jSpinField1;
    private javax.swing.JTextField textData_nasc;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textEndereco;
    private javax.swing.JTextField textLogin;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextField textSenha;
    private javax.swing.JTextField textSenhaConfirm;
    private javax.swing.JTextField textTelefone;
    // End of variables declaration//GEN-END:variables
}
