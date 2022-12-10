package view;

import java.sql.Connection;
import compromissos2.Compromisso;
import compromissos2.Usuario;
import compromissos2.connections.ConnectionFactory;
import compromissos2.connections.UserConnection;
import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static view.AddCompromisso.usuario;
import static view.AddGrupo.listaContatosAdd;
import static view.VerContato.campos;


public class VerCompromisso extends javax.swing.JFrame {

    static Compromisso compromisso;
    static Usuario usuario;
    ConnectionFactory cf = new ConnectionFactory();

    
    ArrayList<Usuario> lista = new ArrayList<>();

    
    static Boolean edit = false;
    
    //int reply = JOptionPane.showConfirmDialog(null, "123", "231", JOptionPane.YES_NO_OPTION);
    public VerCompromisso(Compromisso compromisso, Usuario usuario) {
        
        initComponents();
        
        this.setLocationRelativeTo(null);  
        this.setResizable(false);
        this.setDefaultCloseOperation(0);
        
       
        this.getContentPane().setBackground(Color.decode("#CBEDD5"));

        this.compromisso = compromisso;
        this.usuario = usuario;
        
        displayWindow();

        
        campos.addAll(Arrays.asList(      
                textNome,
                textDescricao,
                textLocal,
                textInicio,
                textFim      
        ));
           
        carregaTexto(campos, edit);
        // Para adicionar mais contatos na Edição
            exibeContatos();
        carregaContatos(compromisso, usuario);
        
        
    }
    
    
    private void exibeContatos() {
    
         DefaultListModel model = new DefaultListModel();  
            lista.clear();
            listaContatosAdd.clear();
            ArrayList<Usuario> lista = carregaContatos(); 
          
            try {            
                int count = 0;
                while(lista.size() > count) {       
                    
                    listaContatosAdd.add(lista.get(count));
                    comboboxContatos.addItem(lista.get(count).getNome());               
                    count++;
                }                         
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "Erro em exibeContatos: " + ex);
            }
    
    
    }
    
// Contatos na comboBox para serem adicionados
    public ArrayList<Usuario> carregaContatos() {

        try {
            //ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);

            String query = 
            "SELECT * FROM pessoa p WHERE p.login is null AND p.id IN ( SELECT id_contato FROM pessoacontato pc WHERE pc.id_pessoa = ? ) AND p.id NOT IN ( SELECT id_pessoa FROM pessoacompromisso );";

            int userId = Integer.valueOf(getIdUsuario(usuario));
            PreparedStatement ps = conn.prepareStatement(query);  
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();            
            // SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            while(rs.next()) {

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                Date date = formato.parse(rs.getString("data_nasc")); 

                Usuario contato = new Usuario(
                        rs.getString("nome"),
                        rs.getInt("id"),                 
                        date,
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("email")                      
                );
  
                lista.add(contato);      
            }
            
            conn.close();

            
        } catch (SQLException ex) {           
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        } catch (ParseException ex) {        
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        }   
        
        return lista;
    };
    
    
    public String getIdUsuario(Usuario usuario) throws SQLException {

       UserConnection connection_usuario = new UserConnection(); 

       ResultSet rsUsuario = connection_usuario.autenticacao(usuario);           
       String idUsuario = "";

       if(rsUsuario.next()) 
           idUsuario = rsUsuario.getString("id");
       else 
           JOptionPane.showMessageDialog(null, "Problema ao estabelecer conexão com o usuário");

       return idUsuario;
    } 
    
    
    private void displayWindow() {
     
        ArrayList<JTextField> campos = new ArrayList<JTextField>( Arrays.asList(
                textNome,
                textDescricao,
                textLocal,
                textInicio
        
        ));
                
                
        
        
        
        textNome.setText(compromisso.getNome());
        textNome.setFont(new Font("Roboto", Font.BOLD, 32));
        textDescricao.setText(compromisso.getDescricao());
        textLocal.setText(compromisso.getLocalc());
    
        String inicio = compromisso.getInicio().toString();
        String fim = compromisso.getFim().toString();
        
        textInicio.setText(inicio.substring(0, 10) + " " + inicio.substring(11));
        textFim.setText(fim.substring(0, 10) + " " + fim.substring(11));
    
    }

    public void carregaTexto(ArrayList<JTextField> campos, Boolean e) {
        
        for(int i = 0; i < campos.size(); i++) 
            campos.get(i).setEnabled(e);                
    }
    
    
// CONTATOS QUE FAZEM PARTE DO COMPROMISSO
    private void carregaContatos(Compromisso compromisso, Usuario usuario) {
           
        try {      
            
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
                     
            String query = "SELECT * FROM pessoa p WHERE login is null AND p.id IN ( SELECT id_pessoa FROM pessoacompromisso pc WHERE pc.id_compromisso = ?);";
                    
            
            PreparedStatement ps = conn.prepareStatement(query);  
            ps.setInt(1, compromisso.getId());
                     
            ResultSet rs = ps.executeQuery();            
                       
            DefaultListModel demoList = new DefaultListModel();

            while(rs.next()) {               
                demoList.addElement(rs.getString("nome"));                               
            }
                       
            listContatos.setModel(demoList);
                     
                                  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        }    
        
    }
    
    /* LATTER LATTER LATTER LATTER LATTER LATTER 
    
    public void carregaContatos(Compromisso compromisso, Usuario usuario) {
         
        DefaultListModel model = new DefaultListModel();
   
        ArrayList<Usuario> lista = buscaContatos(); 
             
        try {
             
            int count = 0;
            while(lista.size() > count) {
                model.addElement(lista.get(count).getNome());  
                count++;
            }
                
            contatos.setModel(model);
           
                         
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Erro em exibeContatos: " + ex);
        }
        
    }
    */
    
    /* LATTER LATTER LATTER LATTER LATTER LATTER LATTER 
    
    
    private ArrayList<Usuario> buscaContatos() {
    
        try {    
            
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
                     
            String query = "SELECT * FROM pessoa p WHERE p.id IN ( SELECT id_contato FROM pessoacontato pc WHERE pc.id_pessoa = ?)";
                    
            int userId = Integer.valueOf(getId(usuario));
                           
            PreparedStatement ps = conn.prepareStatement(query);  
            ps.setInt(1, userId);
                     
            ResultSet rs = ps.executeQuery();            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                  
            while(rs.next()) {
                                            
                Usuario contato = new Usuario(
                        rs.getString("nome"),
                        rs.getInt("id"),                 
                        rs.getDate("data_nasc"),                        
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("email")                      
                );
                
                 System.out.println("A data é assim : :: :: " + contato.getData_nasc());
                
                if(listaContatos.contains(contato)) 
                    System.out.println("Já tem");
                else
                    listaContatos.add(contato);      
            }
                                  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        }
        
        return listaContatos;
    
    } */
    
    
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
        listContatos = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        comboboxContatos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

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
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Excluir Compromisso");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        listContatos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listContatos);

        jLabel1.setText("Contatos adicionados: ");

        jButton1.setText("Add");

        jButton4.setText("Remover");

        comboboxContatos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        comboboxContatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxContatosActionPerformed(evt);
            }
        });

        jLabel2.setText("Local do Compromisso: ");

        jLabel3.setText("Inicio do Evento");

        jLabel4.setText("Fim do Evento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(textInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(textFim, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(textLocal)
                            .addComponent(textDescricao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(comboboxContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jButton4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnEdit))
                                .addGap(20, 20, 20))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2)
                        .addGap(9, 9, 9)
                        .addComponent(textLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboboxContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFim, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(textInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            this.dispose();
            conn.close();
            new Home(usuario).setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao voltar para Home " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        int dialogResult = JOptionPane.showConfirmDialog(this, "Quer mesmo excluir este compromisso?", "Excluir compromisso", JOptionPane.YES_NO_OPTION);
       
        if(dialogResult == 0) 
            excluirCompromisso(compromisso);
        else 
             System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void comboboxContatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxContatosActionPerformed

    }//GEN-LAST:event_comboboxContatosActionPerformed

    private void excluirCompromisso(Compromisso compromisso) {
    
        String query = "DELETE FROM pessoacompromisso WHERE id_compromisso = ?";       
        PreparedStatement ps;
        
        try {
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            ps = conn.prepareStatement(query);
            
            ps.setInt(1, compromisso.getId());
         
            ps.executeUpdate();   
            conn.commit();
            ps.close();
            
            excluirLigacao(compromisso);
                      
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro em excluirCompromisso: " + ex.getMessage());
        
        }   
    }
    
    private void excluirLigacao(Compromisso compromisso) {
    
        String query = "DELETE FROM compromisso WHERE id = ?";
        PreparedStatement ps;
        
        try {
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            ps = conn.prepareStatement(query);
            
            ps.setInt(1, compromisso.getId());     
            
            ps.executeUpdate();               
            conn.commit();
            ps.close();
                        
            JOptionPane.showMessageDialog(null, "Compromisso Excluído!");
            this.dispose();
            new Home(usuario).setVisible(true);
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro em excluirLigacao: " + ex.getMessage());
        
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
    private javax.swing.JComboBox<String> comboboxContatos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listContatos;
    private javax.swing.JTextField textDescricao;
    private javax.swing.JTextField textFim;
    private javax.swing.JTextField textInicio;
    private javax.swing.JTextField textLocal;
    private javax.swing.JTextField textNome;
    // End of variables declaration//GEN-END:variables
}
