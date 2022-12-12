package view;

import compromissos2.connections.ConnectionFactory;
import compromissos2.connections.UserConnection;
import compromissos2.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class AddContato extends javax.swing.JFrame {

    static Usuario usuario;
    
    public AddContato(Usuario usuario) {
        
        initComponents();
        
        this.usuario = usuario; 
        System.out.println(this.usuario + " " + usuario);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        // Foto Padrão 
        ArrayList<JTextField> campos = new ArrayList<>(Arrays.asList(
                textNome,
                textData_nasc,
                textEndereco,
                textEmail,
                textTelefone
                
        ));
       
        // Iniciando os placeholders
        startPlaceHolders(campos);
        
        btnAddContato.setOpaque(true);
        btnVoltar.setOpaque(true);
        
        btnAddContato.setBorderPainted(false);
        btnVoltar.setBorderPainted(false);
            
    }
    
    public void startPlaceHolders(ArrayList<JTextField> campos) {
               
        // Texto Padrão
            for (int i = 0; i < campos.size(); i++) {
                
                Font font = campos.get(i).getFont();
                font = font.deriveFont(Font.ITALIC);
                campos.get(i).setFont(font);
                campos.get(i).setForeground(Color.gray);
            }
        
        // Imagem Padrão
            ImageIcon iconDefault = new ImageIcon("default-image.jpg");
            Image imageDefault = iconDefault.getImage().getScaledInstance(labelFoto.getWidth(), labelFoto.getHeight(), java.awt.Image.SCALE_SMOOTH);
            ImageIcon photoDefault = new ImageIcon(imageDefault);
            labelFoto.setIcon(photoDefault);    
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

        jPanel1 = new javax.swing.JPanel();
        textData_nasc = new javax.swing.JTextField("dd/MM/yyyy");
        textEndereco = new javax.swing.JTextField("Endereço");
        textEmail = new javax.swing.JTextField();
        textTelefone = new javax.swing.JTextField();
        labelFoto = new javax.swing.JLabel();
        btnAddContato = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        textNome = new javax.swing.JTextField("Nome do Contato");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(227, 253, 253));
        jPanel1.setPreferredSize(new java.awt.Dimension(664, 540));

        textData_nasc.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textData_nasc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textData_nascFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textData_nascFocusLost(evt);
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
        textEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textEnderecoActionPerformed(evt);
            }
        });

        textEmail.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textEmailFocusLost(evt);
            }
        });
        textEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textEmailActionPerformed(evt);
            }
        });

        textTelefone.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textTelefone.setVerifyInputWhenFocusTarget(false);
        textTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textTelefoneFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textTelefoneFocusLost(evt);
            }
        });

        labelFoto.setText(" ");
        labelFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelFotoMouseClicked(evt);
            }
        });

        btnAddContato.setBackground(new java.awt.Color(67, 154, 151));
        btnAddContato.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btnAddContato.setForeground(new java.awt.Color(255, 255, 255));
        btnAddContato.setText("Add Contato");
        btnAddContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddContatoActionPerformed(evt);
            }
        });

        btnVoltar.setBackground(new java.awt.Color(67, 154, 151));
        btnVoltar.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        textNome.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textNomeFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textData_nasc, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnVoltar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddContato)))))
                .addGap(56, 56, 56))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(textEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(textData_nasc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(135, 135, 135)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddContato, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection conn;
    
    private void textEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textEnderecoActionPerformed

    private void textEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textEmailActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.dispose();
        new Home(usuario).setVisible(true);
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void labelFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFotoMouseClicked
        
        // Escolher arquivo
            JFileChooser chooser = new JFileChooser();
            
            File file;
            
        // Evitando erros caso não selecione uma imagem*
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
            
        try {
            // Abre selecionar Imagem
            
                BufferedImage img = ImageIO.read(file);
                ImageIcon icon = new ImageIcon(img); // Adicionou
           
            // Encaixar Imagem na Label
                Image imagem = icon.getImage().getScaledInstance(labelFoto.getWidth(), labelFoto.getHeight(), java.awt.Image.SCALE_SMOOTH);
                ImageIcon foto = new ImageIcon(imagem);
                labelFoto.setIcon(foto);
        }
        catch(IOException e) {
            e.printStackTrace();
        }                  
    }//GEN-LAST:event_labelFotoMouseClicked
}
    
    private void textNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNomeFocusGained
        if(textNome.getText().equals("Nome do Contato"))
            textNome.setText("");
        removePlaceHolder(textNome);
    }//GEN-LAST:event_textNomeFocusGained

    private void textNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNomeFocusLost
         if(textNome.getText().equals("")) {
            textNome.setText("Nome do Contato");
            singlePlaceHolder(textNome);
        }
    }//GEN-LAST:event_textNomeFocusLost

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

    
// Adicionar Contato
    private void btnAddContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddContatoActionPerformed
               
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            
            String nome, data_nascS, /*login*/ endereco, telefone, email /*senha, senhaConfirm*/;
                   
            nome = textNome.getText();   
            data_nascS = textData_nasc.getText();
            endereco = textEndereco.getText();
            telefone = textTelefone.getText();
            email = textEmail.getText();
                         
            Date data_nasc = formatter.parse(data_nascS);    
            
            Usuario contato = new Usuario(
                    nome,
                    data_nasc,
                    endereco,
                    telefone,
                    email
            );
              
           
            InsereContatoBanco(contato);             
            
            // Fecha Janela              
            this.setVisible(false);
            
            Home home = new Home(usuario);
            home.setVisible(true);
                                 
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnAddContatoActionPerformed

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
            textTelefone.setText("Telefone");
            singlePlaceHolder(textTelefone);
        }
    }//GEN-LAST:event_textTelefoneFocusLost

    private void textTelefoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textTelefoneFocusGained
       if(textTelefone.getText().equals("Telefone"))
            textEmail.setText("");
         removePlaceHolder(textTelefone);
    }//GEN-LAST:event_textTelefoneFocusGained
   
    private void InsereContatoBanco(Usuario contato) {
               
        String query = "insert into pessoa() values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;

        try {        
            
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
         
            // String dataSQL = ConvertData(contato.getData_nasc());        
                       
            java.sql.Date sqlDate = new java.sql.Date(contato.getData_nasc().getTime());
                 
            ps = conn.prepareStatement(query);
            
            ps.setString(1, null);
            ps.setString(2, contato.getNome());
            ps.setDate(3, sqlDate);
            ps.setString(4, contato.getEndereco());
            ps.setString(5, contato.getTelefone());
            ps.setString(6, contato.getEmail());
            ps.setString(7, null);
            ps.setString(8, null);
            ps.setString(9, null);
             
            ps.execute();
            conn.commit();
            ps.close();
            
            ConectaContato(contato);
                      
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em InsereContatoBanco: " + ex.getMessage());
        }
    }   
    
    
    public String ConvertData(Date data) {

        
        String FORMATO_ANTIGO = "dd/MM/yyyy";
        // String SQL_FORMATO = "yyyy-MM-dd";

        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_ANTIGO);

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(data);

        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        
        String mesS = mes <= 9 ? "0" + String.valueOf(mes) : String.valueOf(mes);
        String diaS = mes <= 9 ? "0" + String.valueOf(dia) : String.valueOf(dia);

        String dataSQL = ano + "-" + mesS + "-" + diaS;
        
        return dataSQL;
    }
    
    
      
    public void ConectaContato(Usuario contato) {
          
        try {   
            
            // Conexão com usuário desta conta
           
            UserConnection connection_usuario = new UserConnection();   
            
            System.out.println("Usuario: " + usuario );
            System.out.println("Contato: " + contato);
            
            
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
             
            JOptionPane.showMessageDialog(null, "Contato adicionado com sucesso!");
             
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
            java.util.logging.Logger.getLogger(AddContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddContato(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddContato;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JTextField textData_nasc;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textEndereco;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextField textTelefone;
    // End of variables declaration//GEN-END:variables
}
