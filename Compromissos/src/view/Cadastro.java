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
        ArrayList<JTextField> campos = new ArrayList<>(Arrays.asList(textNome,
                textLogin,
                textData_nasca,
                textEndereco,
                textTelefone,
                textEmail,
                textSenha,
                textSenhaConfirm
                
        ));
        
        btnCadastrar.setOpaque(true);
        btnVoltar.setOpaque(true);
        btnCadastrar.setBorderPainted(false);
        btnVoltar.setBorderPainted(false);
        
        
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
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel1 = new javax.swing.JPanel();
        textEmail = new javax.swing.JTextField("Email");
        textSenha = new javax.swing.JTextField("Senha");
        textSenhaConfirm = new javax.swing.JTextField("Confirmar Senha");
        jLabel1 = new javax.swing.JLabel();
        textLogin = new javax.swing.JTextField("Login");
        btnVoltar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField("Nome do Usuário");
        textEndereco = new javax.swing.JTextField("Endereço");
        textData_nasca = new javax.swing.JTextField("dd/MM/yyyy");
        textTelefone = new javax.swing.JTextField("Número de Telefone");
        btnCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(203, 241, 245));

        jPanel1.setBackground(new java.awt.Color(203, 241, 245));
        jPanel1.setForeground(new java.awt.Color(203, 241, 245));
        jPanel1.setPreferredSize(new java.awt.Dimension(451, 556));

        textEmail.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textEmailFocusLost(evt);
            }
        });

        textSenha.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
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

        textSenhaConfirm.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(67, 154, 151));
        jLabel1.setText("Data de Nascimento");

        textLogin.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textLoginFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textLoginFocusLost(evt);
            }
        });

        btnVoltar.setBackground(new java.awt.Color(67, 154, 151));
        btnVoltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(67, 154, 151));
        jLabel2.setText("Cadastrar Usuário");

        textNome.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textNomeFocusLost(evt);
            }
        });

        textEndereco.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textEndereco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textEnderecoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textEnderecoFocusLost(evt);
            }
        });

        textData_nasca.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textData_nasca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textData_nascaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textData_nascaFocusLost(evt);
            }
        });
        textData_nasca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textData_nascaKeyPressed(evt);
            }
        });

        textTelefone.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textTelefoneFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textTelefoneFocusLost(evt);
            }
        });

        btnCadastrar.setBackground(new java.awt.Color(67, 154, 151));
        btnCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(btnVoltar)
                    .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textData_nasca, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textSenhaConfirm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textEndereco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textData_nasca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSenhaConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        textEndereco.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(457, 457, 457))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection conn;
        
    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {

            String nome, data_nascS, login, endereco, telefone, email, senha, senhaConfirm;
            // Date data_nasc;

            nome = textNome.getText();
            data_nascS = textData_nasca.getText();
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
                new Login();
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

                java.sql.Date sqlDate = new java.sql.Date(usuario.getData_nasc().getTime());

                ps = conn.prepareStatement(query);

                ps.setString(1, null);
                ps.setString(2, usuario.getNome());

                ps.setDate(3, sqlDate);

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

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.setVisible(false);
        new Login();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void textSenhaConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSenhaConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSenhaConfirmActionPerformed

    private void textSenhaConfirmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSenhaConfirmFocusLost
        if(textSenhaConfirm.getText().equals("")) {
            textSenhaConfirm.setText("Confirmar Senha");
            singlePlaceHolder(textSenhaConfirm);
        }
    }//GEN-LAST:event_textSenhaConfirmFocusLost

    private void textSenhaConfirmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSenhaConfirmFocusGained
        if(textSenhaConfirm.getText().equals("Confirmar Senha"))
        textSenhaConfirm.setText("");
        removePlaceHolder(textSenhaConfirm);
    }//GEN-LAST:event_textSenhaConfirmFocusGained

    private void textSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSenhaActionPerformed

    private void textSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSenhaFocusLost
        if(textSenha.getText().equals("")) {
            textSenha.setText("Senha");
            singlePlaceHolder(textSenha);
        }
    }//GEN-LAST:event_textSenhaFocusLost

    private void textSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSenhaFocusGained
        if(textSenha.getText().equals("Senha"))
        textSenha.setText("");
        removePlaceHolder(textSenha);
    }//GEN-LAST:event_textSenhaFocusGained

    private void textEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textEmailFocusLost
        if(textEmail.getText().equals("")) {
            textEmail.setText("Email");
            singlePlaceHolder(textEmail);
        }
    }//GEN-LAST:event_textEmailFocusLost

    private void textEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textEmailFocusGained
        if(textEmail.getText().equals("Email"))
        textEmail.setText("");
        removePlaceHolder(textEmail);
    }//GEN-LAST:event_textEmailFocusGained

    private void textTelefoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textTelefoneFocusLost
        if(textTelefone.getText().equals("")) {
            textTelefone.setText("Número de Telefone");
            singlePlaceHolder(textTelefone);
        }
    }//GEN-LAST:event_textTelefoneFocusLost

    private void textTelefoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textTelefoneFocusGained
        if(textTelefone.getText().equals("Número de Telefone"))
        textTelefone.setText("");
        removePlaceHolder(textTelefone);
    }//GEN-LAST:event_textTelefoneFocusGained

    private void textEnderecoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textEnderecoFocusLost
        if(textEndereco.getText().equals("")) {
            textEndereco.setText("Endereço");
            singlePlaceHolder(textEndereco);
        }
    }//GEN-LAST:event_textEnderecoFocusLost

    private void textEnderecoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textEnderecoFocusGained
        if(textEndereco.getText().equals("Endereço"))
        textEndereco.setText("");
        removePlaceHolder(textEndereco);
    }//GEN-LAST:event_textEnderecoFocusGained

    private void textData_nascaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textData_nascaKeyPressed

    }//GEN-LAST:event_textData_nascaKeyPressed

    private void textData_nascaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textData_nascaFocusLost
        if(textData_nasca.getText().equals("")) {
            textData_nasca.setText("dd/MM/yyyy");
            singlePlaceHolder(textData_nasca);
        }
    }//GEN-LAST:event_textData_nascaFocusLost

    private void textData_nascaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textData_nascaFocusGained
        if(textData_nasca.getText().equals("dd/MM/yyyy"))
        textData_nasca.setText("");
        removePlaceHolder(textData_nasca);
    }//GEN-LAST:event_textData_nascaFocusGained

    private void textLoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textLoginFocusLost
        if(textLogin.getText().equals("")) {
            textLogin.setText("Login");
            singlePlaceHolder(textLogin);
        }
    }//GEN-LAST:event_textLoginFocusLost

    private void textLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textLoginFocusGained
        if(textLogin.getText().equals("Login"))
        textLogin.setText("");
        removePlaceHolder(textLogin);
    }//GEN-LAST:event_textLoginFocusGained

    private void textNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNomeFocusLost
        if(textNome.getText().equals("")) {
            textNome.setText("Nome do Usuário");
            singlePlaceHolder(textNome);
        }
    }//GEN-LAST:event_textNomeFocusLost

// PLACEHOLDERS
    
    private void textNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNomeFocusGained
        if(textNome.getText().equals("Nome do Usuário"))
        textNome.setText("");
        removePlaceHolder(textNome);
    }//GEN-LAST:event_textNomeFocusGained

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
    private javax.swing.JButton btnVoltar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private com.toedter.components.JSpinField jSpinField1;
    private javax.swing.JTextField textData_nasca;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textEndereco;
    private javax.swing.JTextField textLogin;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextField textSenha;
    private javax.swing.JTextField textSenhaConfirm;
    private javax.swing.JTextField textTelefone;
    // End of variables declaration//GEN-END:variables
}
