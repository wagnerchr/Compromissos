/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import com.toedter.calendar.JDayChooser;
import compromissos2.Grupo;
import compromissos2.connections.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.Connection;
import compromissos2.connections.UserConnection;
import compromissos2.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane; 


public class Home extends javax.swing.JFrame {

    static Usuario usuario;
    static ArrayList<Usuario> lista = new ArrayList<>();
    Connection conn;
    
    public Home(Usuario usuario) {
        
        initComponents();
        
        this.usuario = usuario;
        
        exibeContatos();
        exibeGrupos();
        
        this.setLocationRelativeTo(null);             
        labelHello.setText("Hello, " + usuario.getNome());
        
        // Onclick Calendário
        JDayChooser dayChooser = Calendario.getDayChooser();
        dayChooser.setAlwaysFireDayProperty(true); // here is the key
        
        dayChooser.addPropertyChangeListener("day", (evt) -> {           
               marcarCompromisso();   
        });
        
        
    }
    
     
    public String getId(Usuario usuario) throws SQLException {
               
        // Pegar id 
            UserConnection connection_usuario = new UserConnection(); 

            ResultSet rsUsuario = connection_usuario.autenticacao(usuario);           
            String idUsuario = "";

            if(rsUsuario.next()) 
                idUsuario = rsUsuario.getString("id");
               
            else 
                JOptionPane.showMessageDialog(null, "Problema ao estabelecer conexão com o usuário");
        return idUsuario;
    }
    
    // ResultSet para trazer info do banco
   
    public ArrayList<Usuario> carregaContatos() {
        
        
       // ArrayList<Usuario> lista = new ArrayList<>();
        
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
                                  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        }
        
        return lista;
    };
      
    
    private void exibeContatos() {
        
        DefaultListModel model = new DefaultListModel();
        ArrayList<Usuario> lista = carregaContatos();  
            
        try {
             
            int count = 0;
            while(lista.size() > count) {
                model.addElement(lista.get(count).getNome());  
                count++;
            }
            
            System.out.println("Essa é a lista: " + lista);
            contatos.setModel(model);
                         
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Erro em exibeContatos: " + ex);
        }
    
    }
    
     private void exibeGrupos() {
        
        DefaultListModel model = new DefaultListModel();
        ArrayList<Grupo> lista = carregaGrupos();  
            
        try {
             
            int count = 0;
            while(lista.size() > count) {
                model.addElement(lista.get(count).getNome());  
                count++;
            }
            
            System.out.println("Essa é a lista de Grupos : " + lista);
            grupos.setModel(model);
                         
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Erro em exibeContatos: " + ex);
        }
    
    }

// Carregar lista de grupos do usuário
     public ArrayList<Grupo> carregaGrupos() {
        
        
        ArrayList<Grupo> lista = new ArrayList<>();
        
        try {
            
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
                     
            String query = "SELECT * FROM grupo g WHERE g.id IN ( SELECT id_grupo FROM pessoagrupo pc WHERE pc.id_pessoa = ?)";
            
            
            int userId = Integer.valueOf(getId(usuario));
                  
           
            PreparedStatement ps = conn.prepareStatement(query);  
            ps.setInt(1, userId);
          
             
            ResultSet rs = ps.executeQuery();            
           
            while(rs.next()) {
                                                 
                Grupo grupo = new Grupo(
                    rs.getString("nome"),
                    rs.getString("descricao")                                                         
                );
                
                lista.add(grupo);      
            }
                                  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        }
        
        return lista;
    };
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    public void marcarCompromisso() {       
      new MarcarCompromisso(Calendario.getDate()).setVisible(true);  
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Calendario = new com.toedter.calendar.JCalendar();
        labelHello = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        Lista = new javax.swing.JTabbedPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contatos = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grupos = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelHello.setText("Hello");

        jButton2.setText("Marcar Compromisso");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel1)
                .addContainerGap(251, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addContainerGap(437, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel4);

        Lista.addTab("Compromissos", jTabbedPane1);

        contatos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"a"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        contatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(contatos);

        jButton1.setText("Adicionar novo Contato");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jButton1)
                .addContainerGap(125, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        jTabbedPane2.addTab("", jPanel3);

        Lista.addTab("Contatos", jTabbedPane2);

        grupos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {" "};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(grupos);

        jButton3.setText("Criar Grupo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        jTabbedPane3.addTab("", jPanel2);

        Lista.addTab("Grupos", jTabbedPane3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(Lista, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHello)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(labelHello)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Calendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Lista, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new AddContato(usuario).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        new MarcarCompromisso(Calendario.getDate()).setVisible(true);
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new AddGrupo(usuario).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void contatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contatosMouseClicked
       new Contato(lista.get(contatos.getAnchorSelectionIndex())).setVisible(true);      
    }//GEN-LAST:event_contatosMouseClicked

    private void dayChooser(java.awt.event.ActionEvent evt) {
        System.out.println(Calendario.getDate());
    }
    
    public void selecionaData() {
        System.out.println(Calendario.getDate());
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home(usuario).setVisible(true);
            }
        });
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar Calendario;
    private javax.swing.JTabbedPane Lista;
    private javax.swing.JList<String> contatos;
    private javax.swing.JList<String> grupos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel labelHello;
    // End of variables declaration//GEN-END:variables
}
