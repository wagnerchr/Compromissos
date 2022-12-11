package view;

import compromissos2.Compromisso;
import compromissos2.Grupo;
import compromissos2.Usuario;
import compromissos2.connections.ConnectionFactory;
import compromissos2.connections.UserConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static view.VerCompromisso.compromisso;
import static view.VerCompromisso.usuario;
import static view.VerContato.campos;
import static view.VerContato.contato;
import static view.VerContato.edit;
import static view.VerContato.usuario;


public class VerGrupos extends javax.swing.JFrame {

    static Grupo grupo;
    static Usuario usuario;
    
    static Boolean edit = false;
    static ArrayList<JTextField> campos = new ArrayList<>();
    ConnectionFactory cf = new ConnectionFactory();
    DefaultListModel demoList = new DefaultListModel();
    
    ArrayList<Usuario> listaContatosParaInserir = new ArrayList<>();    
    ArrayList<Usuario> listaContatosInseridos = new ArrayList<>();

        
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
        btnAddContact1.setEnabled(false);
        btnRemoveContact1.setEnabled(false);
             
        corregaTexto(campos, edit); 
        exibeContatos();
        carregaContatos(grupo, usuario);  
        
    }   
    
    public void corregaTexto(ArrayList<JTextField> campos, Boolean e) {
        
        for(int i = 0; i < campos.size(); i++) 
            campos.get(i).setEnabled(e);                
    }


// CONTATOS QUE FAZEM PARTE DO COMPROMISSO
     private void carregaContatos(Grupo grupo, Usuario usuario) {
           
        try {      
            
            // Reset JList
            demoList.removeAllElements();
            jListContatos.setModel(demoList);
                   
            conn = cf.getConnection();
            conn.setAutoCommit(false);
                     
            String query = "SELECT * FROM pessoa p WHERE login is null AND p.id IN ( SELECT id_pessoa FROM pessoagrupo pc WHERE pc.id_grupo = ?);";
                    
            
            PreparedStatement ps = conn.prepareStatement(query);  
            ps.setInt(1, grupo.getId());                    
            ResultSet rs = ps.executeQuery();            
                       
            while(rs.next()) {  
                
                 Usuario contato = new Usuario(
                        rs.getString("nome"),
                        rs.getInt("id"),
                        rs.getDate("data_nasc"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
                 
                demoList.addElement(contato.getNome()); 
                listaContatosInseridos.add(contato);  
            }
                       
            jListContatos.setModel(demoList);
                     
                                  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        }          
    }
     
// CONTATOS NA COMBOBOX PARA SEREM ADICIONADOS
    public ArrayList<Usuario> carregaContatos() {

        try {
            //ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);

            String query = 
            "SELECT * FROM pessoa p WHERE p.login is null AND p.id IN ( SELECT id_contato FROM pessoacontato pc WHERE pc.id_pessoa = ? )"
                    + " AND p.id NOT IN ( SELECT id_pessoa FROM pessoagrupo );";

            int userId = Integer.valueOf(getIdUsuario(usuario));
            PreparedStatement ps = conn.prepareStatement(query);  
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();                  

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
  
                listaContatosParaInserir.add(contato);      
            }
            
            conn.close();

            
        } catch (SQLException ex) {           
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        } catch (ParseException ex) {        
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        }   
        
        return listaContatosParaInserir;
    };
// 
     
    // PEGAR ID CONTATO  
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
// 
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAddContact = new javax.swing.JButton();
        btnRemoveContact = new javax.swing.JButton();
        textNome = new javax.swing.JTextField();
        textDescricao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListContatos = new javax.swing.JList<>();
        btnVoltar = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        comboboxContatos = new javax.swing.JComboBox<>();
        btnAddContact1 = new javax.swing.JButton();
        btnRemoveContact1 = new javax.swing.JButton();

        btnAddContact.setText("Add");
        btnAddContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddContactActionPerformed(evt);
            }
        });

        btnRemoveContact.setText("Remover");
        btnRemoveContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveContactActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textNome.setText("jTextField1");
        textNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNomeActionPerformed(evt);
            }
        });

        textDescricao.setText("jTextField1");
        textDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDescricaoActionPerformed(evt);
            }
        });

        jListContatos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListContatos);

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

        comboboxContatos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        comboboxContatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxContatosActionPerformed(evt);
            }
        });

        btnAddContact1.setText("Add");
        btnAddContact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddContact1ActionPerformed(evt);
            }
        });

        btnRemoveContact1.setText("Remover");
        btnRemoveContact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveContact1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                .addComponent(btnExcluir)
                .addGap(154, 154, 154)
                .addComponent(btnEdit)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRemoveContact1)
                                .addGap(92, 92, 92)
                                .addComponent(btnAddContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(comboboxContatos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboboxContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddContact1)
                    .addComponent(btnRemoveContact1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(btnEdit)
                    .addComponent(btnExcluir))
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
            btnAddContact1.setEnabled(true);
            btnRemoveContact1.setEnabled(true);
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

    private void comboboxContatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxContatosActionPerformed

    }//GEN-LAST:event_comboboxContatosActionPerformed

    private void textNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNomeActionPerformed

    private void textDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textDescricaoActionPerformed

    private void btnAddContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddContactActionPerformed

        demoList.addElement( comboboxContatos.getSelectedItem() );
        listaContatosInseridos.add( listaContatosParaInserir.get( comboboxContatos.getSelectedIndex()) );
        listaContatosParaInserir.remove(comboboxContatos.getSelectedIndex());

        comboboxContatos.removeItem( comboboxContatos.getSelectedItem() );

        jListContatos.setModel(demoList);

    }//GEN-LAST:event_btnAddContactActionPerformed

    private void btnAddContact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddContact1ActionPerformed

        demoList.addElement( comboboxContatos.getSelectedItem() );
        listaContatosInseridos.add( listaContatosParaInserir.get( comboboxContatos.getSelectedIndex()) );
        listaContatosParaInserir.remove(comboboxContatos.getSelectedIndex());

        comboboxContatos.removeItem( comboboxContatos.getSelectedItem() );

        jListContatos.setModel(demoList);

    }//GEN-LAST:event_btnAddContact1ActionPerformed

    private void btnRemoveContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveContactActionPerformed
        removeContato();
    }//GEN-LAST:event_btnRemoveContactActionPerformed

    private void btnRemoveContact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveContact1ActionPerformed
        removeContato();
    }//GEN-LAST:event_btnRemoveContact1ActionPerformed

    
     private void removeContato() {
    
        String query = "DELETE FROM pessoagrupo WHERE id_pessoa = ?";
        PreparedStatement ps;
         
        try {
                    
                    
            // Erro com lista vazia
            if( jListContatos.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "Selecione um usuário para removê-lo ");
                return;
            }
            
            conn = cf.getConnection();
            conn.setAutoCommit(false);
                                         
            ps = conn.prepareStatement(query);           
            ps.setInt(1, listaContatosInseridos.get(jListContatos.getSelectedIndex()).getId());

            ps.executeUpdate();
            conn.commit();
            ps.close();  
                        
            carregaContatos(grupo, usuario);
            exibeContatos();
                    
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao removerContato " + ex.getMessage());     
        }
      
    
    }
     
     private void exibeContatos() {
    
        DefaultListModel model = new DefaultListModel();  

        listaContatosParaInserir.clear();
        // listaContatosAdd.clear();          
        comboboxContatos.removeAllItems();
            
        ArrayList<Usuario> lista = carregaContatos(); 
            
            try {            
                int count = 0;
                while(lista.size() > count) {          
                    
                    //listaContatosAdd.add(lista.get(count));
                    comboboxContatos.addItem( lista.get(count).getNome() );               
                    count++;
                    
                }                         
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "Erro em exibeContatos: " + ex);
            }
    }
    
    
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
            InsereContatosGrupo();
            
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
    
    private void InsereContatosGrupo() {
    
        
        ArrayList<Integer> contatosids = new ArrayList<Integer>();
          
        for(int i = 0; i < listaContatosInseridos.size(); i++) {              
            for(int j = 0; j < jListContatos.getModel().getSize(); j++) {              
                if( ( listaContatosInseridos.get(i).getNome() ).equals( jListContatos.getModel().getElementAt(j)) )         
                    contatosids.add( (listaContatosInseridos.get(i).getId()) );           
            }              
        }
     
       InsereContatoBanco(contatosids);
    }
    
    private void InsereContatoBanco(ArrayList<Integer> contatosIds) {
         
        try {    
            
            String query = "INSERT INTO pessoagrupo() VALUES(?, ?)";
            
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            PreparedStatement ps;

            ps = conn.prepareStatement(query);
       
            
            for(int i = 0; i < contatosIds.size(); i++) {
                             
                ps.setInt(1, contatosIds.get(i) );
                ps.setInt(2, grupo.getId());
                
                ps.execute();
  
            }
            
            conn.commit();
            ps.close();
            
    }catch(SQLException ex) {
            System.out.println(ex);
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
    private javax.swing.JButton btnAddContact;
    private javax.swing.JButton btnAddContact1;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnRemoveContact;
    private javax.swing.JButton btnRemoveContact1;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> comboboxContatos;
    private javax.swing.JList<String> jListContatos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField textDescricao;
    private javax.swing.JTextField textNome;
    // End of variables declaration//GEN-END:variables
}
