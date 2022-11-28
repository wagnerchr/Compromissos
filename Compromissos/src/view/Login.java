package view;

import compromissos2.Hash;
import compromissos2.connections.UserConnection;
import compromissos2.Usuario;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;


public class Login extends javax.swing.JFrame {
     
    Preferences pref;
    boolean rememberPref;
      
    public Login() {
        
        
        initComponents();   
       
        try {
            
        loginScreen();
        
        TimeUnit.SECONDS.sleep(1);
        
        rememberMe();
        } catch (Exception e) {
            System.out.println(e);
        }

}

// INICIA TELA LOGIN
    public void loginScreen() {
                           
        this.setVisible(true);
        
        icon2.setVisible(false);
   
        labelLogin.setFont(new Font("Comic Sans", Font.BOLD, 25));
        labelLogin.setAlignmentX(500);
        labelLogin.setAlignmentY(500);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    
        loginFundo.setBackground(Color.decode("#CDF0EA"));
    }
    
    
// REMEMBER ME - AUTOLOGIN
    public void rememberMe() {
        
        pref = Preferences.userNodeForPackage(this.getClass());   
        rememberPref = pref.getBoolean("rememberMe", Boolean.valueOf(""));
        
        if (rememberPref) {              
            String login = pref.get("User", "");
            String senha = pref.get("Password", "");           
            checkLogin.setSelected(rememberPref);
              
            Connection(login, senha);
                           
        }                  
    }
    
// CONEXÃO COM BD - LOGAR NA APLICAÇÃO
    public void Connection(String login, String senha) {
          
        try {
            
            Hash hash = new Hash();
            
            //String login = textLogin.getText();               
            //String senha = hash.hashSenha(textSenha.getText());
            
            senha = hash.hashSenha(senha);
                     
            Usuario usuario = new Usuario(login, senha);               
            UserConnection connection = new UserConnection();           
            ResultSet rs = connection.autenticacao(usuario); 
            
            // Conexão parâmetros batem
            if(rs.next()) {
                          
            // ---------------------------------------------------------- 
            /*
                if(checkLogin.isSelected() && !rememberPref) {
                    pref.put("User", textLogin.getText());
                    pref.put("Password", textSenha.getText());
                    pref.putBoolean("rememberMe", true);

                } else if(!checkLogin.isSelected() && rememberPref){
                    pref.put("User", "");
                    pref.put("Password", "");
                    pref.putBoolean("rememberMe", false);
                }
            */    
                             
                String nome, endereco, telefone, email, data;
                Date data_nasc;
                
                nome = rs.getString("nome");                         
                data = rs.getString("data_nasc");
                LocalDate localdata = LocalDate.parse(data);  
                data_nasc = Date.from(localdata.atStartOfDay(ZoneId.systemDefault()).toInstant());
                
                endereco = rs.getString("endereco");
                telefone = rs.getString("telefone");
                email = rs.getString("email");
                // login;
                senha = rs.getString("senha");                                        
                
                Usuario user = new Usuario(nome, login, data_nasc, endereco, telefone, email, senha, senha);
                   
                new Home(user).setVisible(true); 
                
                this.dispose();
                                                                        
            // Mensagem incorreta!
            } else {
                
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
            }
                      
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, err);
        }
    
    }
 
// VER SENHA   
    public void showPassWord(boolean showSenha) {
        if(showSenha) {
                textSenha.setEchoChar((char) 0);
                showSenha = false;
                icon1.setVisible(false);
                icon2.setVisible(true);

            } else {
                textSenha.setEchoChar('*');
                showSenha = true;
                icon1.setVisible(true);
                icon2.setVisible(false);
            }
    } 
    
// GOOD TEXT
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jToggleButton1 = new javax.swing.JToggleButton();
        loginFundo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textLogin = new javax.swing.JTextField();
        buttonLogin = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        labelLogin = new javax.swing.JLabel();
        textSenha = new javax.swing.JPasswordField();
        icon1 = new javax.swing.JLabel();
        checkLogin = new javax.swing.JCheckBox();
        labelUsuario = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        icon2 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loginFundo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText(" Nãso possui Conta?");
        loginFundo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 110, 20));
        loginFundo.add(textLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 300, 30));

        buttonLogin.setText("Fazer Login");
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });
        loginFundo.add(buttonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, 30));

        jButton2.setText("Cadastre-se");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        loginFundo.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 110, 30));

        labelLogin.setText("Fazer Login");
        labelLogin.setMaximumSize(new java.awt.Dimension(500, 500));
        loginFundo.add(labelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 240, 50));
        loginFundo.add(textSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 300, 30));

        icon1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/eye-12109 (2).png"))); // NOI18N
        icon1.setMaximumSize(new java.awt.Dimension(50, 50));
        icon1.setMinimumSize(new java.awt.Dimension(50, 50));
        icon1.setPreferredSize(new java.awt.Dimension(50, 50));
        icon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon1MouseClicked(evt);
            }
        });
        loginFundo.add(icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 40, 30));

        checkLogin.setText("Lembrar de mim");
        loginFundo.add(checkLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        labelUsuario.setText("Usuario");
        loginFundo.add(labelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        labelSenha.setText("Senha");
        loginFundo.add(labelSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/hidden-12115 (3).png"))); // NOI18N
        icon2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon2MouseClicked(evt);
            }
        });
        loginFundo.add(icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 40, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginFundo, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(loginFundo, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

// EVENTS
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Cadastro cadastro = new Cadastro();
        cadastro.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
         Connection(textLogin.getText(), textSenha.getText());
    }//GEN-LAST:event_buttonLoginActionPerformed

// VER SENHA
    private void icon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon1MouseClicked
        showPassWord(true);
    }//GEN-LAST:event_icon1MouseClicked

    private void icon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon2MouseClicked
        showPassWord(false);
    }//GEN-LAST:event_icon2MouseClicked
// FIM VER SENHA    
    
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogin;
    private javax.swing.JCheckBox checkLogin;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon2;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel loginFundo;
    private javax.swing.JTextField textLogin;
    private javax.swing.JPasswordField textSenha;
    // End of variables declaration//GEN-END:variables
}
