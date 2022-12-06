package view;


import com.toedter.calendar.JDayChooser;
import compromissos2.Compromisso;
import compromissos2.Grupo;
import compromissos2.connections.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.Connection;
import compromissos2.connections.UserConnection;
import compromissos2.Usuario;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;



public class Home extends javax.swing.JFrame {

    static Usuario usuario;
    
    // Lista
    static ArrayList<Compromisso> listaCompromissos = new ArrayList<>();
    static ArrayList<Usuario> listaContatos = new ArrayList<>();
    static ArrayList<Grupo> listaGrupos = new ArrayList<>();
    
   
    // Conexão 
    ConnectionFactory cf = new ConnectionFactory();
    Connection conn;
    
    public Home(Usuario usuario) {
        
        initComponents();
        
        this.usuario = usuario;

        /// dasnjdasb njdasnj 
        
        displayWindow();
       
        /*
        URL iconURL = getClass().getResource("Compromissos\\images\\icon.png");
        ImageIcon img = new ImageIcon(iconURL);
        this.setIconImage(img.getImage());
*/
        // Window
           
        //
         
        
        
        
       
            
    }    
                     
    private void displayWindow() {
    
    
        this.setLocationRelativeTo(null);         
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        labelHello.setText("Hello, " + usuario.getNome());
            
        // Load JLists
            exibeCompromissos();
            exibeContatos();
            exibeGrupos();
            
        // OnClicl Calendar 
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            
           
        
           

            System.out.println(cal.get(Calendar.DAY_OF_MONTH));
            
            
            JDayChooser dayChooser = Calendario.getDayChooser();            
            dayChooser.setAlwaysFireDayProperty(true); // here is the key
            
            
            dayChooser.addPropertyChangeListener("day", (evt) -> {           
                  
                if( Calendario.getDate().compareTo(date) >= 0) {         
                    marcarCompromisso();   
                }          
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
 
// CARREGAR COMPROMISSOS
    private ArrayList<Compromisso> carregaCompromissos() {

        try {
                      
            conn = cf.getConnection();
            conn.setAutoCommit(false);
                     
            String query = "SELECT * FROM compromisso WHERE id IN ( SELECT id_compromisso FROM pessoacompromisso WHERE id_pessoa = ?);";            
            
            int userId = Integer.valueOf(getId(usuario));
                           
            PreparedStatement ps = conn.prepareStatement(query);  
            ps.setInt(1, userId);
                     
            ResultSet rs = ps.executeQuery();       
                                     
            while(rs.next()) {
                
                                      
                Compromisso compromisso = new Compromisso(
                        rs.getString("nome"),   
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getString("localc"),
                        rs.getTimestamp("data_inicio").toLocalDateTime(),
                        rs.getTimestamp("data_fim").toLocalDateTime()
                );
                              
                listaCompromissos.add(compromisso);      
            
            }
                    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaCompromissos: " + ex);             
        }
                  
        return listaCompromissos;
    }    
//
    
    
// CARREGAR CONTATOS 
    public ArrayList<Usuario> carregaContatos() {
        
        try {           
            
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
                         
                
                if(listaContatos.contains(contato)) 
                    System.out.println("Já tem");
                else
                    listaContatos.add(contato);      
            }
                                  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        }
        
        return listaContatos;
    };
//
    
// CARREGAR GRUPOS
     public ArrayList<Grupo> carregaGrupos() {
        
        
        ArrayList<Grupo> lista = new ArrayList<>();
        
        try {
            
            
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
                    rs.getInt("id"),
                    rs.getString("descricao")                                                         
                );
                
                listaGrupos.add(grupo);      
            }
                                  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        }
        
        return listaGrupos;
    };    
//
    
     
// -- EXIBIR --
  
     
// EXIBIR COPMPROMISSOS
    private void exibeCompromissos() {
    
        DefaultListModel model = new DefaultListModel();
        listaCompromissos.clear();
        
        ArrayList<Compromisso> lista = carregaCompromissos();
             
        try {
        
            int count = 0;
            while(lista.size() > count) {
                model.addElement(lista.get(count).getNome());
                count++;
            }
            
            
            compromissos.setModel(model);
            
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Erro em exibeCompromissos: " + ex);
        }
    
    
    }
//
    
    
// EXIBIR CONTATOS
    private void exibeContatos() {
        
        DefaultListModel model = new DefaultListModel();
        listaContatos.clear();
        
        ArrayList<Usuario> lista = carregaContatos(); 
             
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
//
    
    
// EXIBIR GRUPOS 
     private void exibeGrupos() {
        
        DefaultListModel model = new DefaultListModel();
        listaGrupos.clear();
        ArrayList<Grupo> lista = carregaGrupos();  
        
        try {
             
            int count = 0;
            while(lista.size() > count) {
                model.addElement(lista.get(count).getNome());  
                count++;
            }
                    
            grupos.setModel(model);
                         
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Erro em exibeContatos: " + ex);
        }
    
    }
//
   
  
    @SuppressWarnings("unchecked")
    
    public void marcarCompromisso() {       
        this.setVisible(false);
        new AddCompromisso(usuario, Calendario.getDate()).setVisible(true);  
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        Calendario = new com.toedter.calendar.JCalendar();
        labelHello = new javax.swing.JLabel();
        Lista = new javax.swing.JTabbedPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        JPanelLista = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        compromissos = new javax.swing.JList<>();
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
        jButton4 = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelHello.setText("Hello");

        compromissos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        compromissos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compromissosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(compromissos);

        javax.swing.GroupLayout JPanelListaLayout = new javax.swing.GroupLayout(JPanelLista);
        JPanelLista.setLayout(JPanelListaLayout);
        JPanelListaLayout.setHorizontalGroup(
            JPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
        );
        JPanelListaLayout.setVerticalGroup(
            JPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelListaLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("", JPanelLista);

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
        grupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gruposMouseClicked(evt);
            }
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

        jButton4.setText("Sair");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHello)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(Lista, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(30, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelHello)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Calendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(51, 51, 51))
                    .addComponent(Lista, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new AddContato(usuario).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        new AddGrupo(usuario).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void contatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contatosMouseClicked
        
        this.setVisible(false);    
        
        VerContato vercontato = new VerContato(listaContatos.get(contatos.getAnchorSelectionIndex()), usuario);
        vercontato.setVisible(true);
                
     
    }//GEN-LAST:event_contatosMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        int dialogResult = JOptionPane.showConfirmDialog(this, "Desabilitar Login Automático?", "Fazer Logoff", JOptionPane.YES_NO_OPTION);
       
        if(dialogResult == 0) {
              new Login().pref.putBoolean("rememberMe", false);
      
            this.dispose(); 
            this.setVisible(false);     
            
            JOptionPane.showConfirmDialog(this, "Próximo login, a conta não será lembrada", "Avisso", JOptionPane.OK_OPTION);
        }          
        
           
         //login.rememberMe();             
    }//GEN-LAST:event_jButton4ActionPerformed

    private void compromissosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compromissosMouseClicked
        if( compromissos.getSelectedIndex() >= 0) {
        
        this.setVisible(false);    
        
        VerCompromisso vercompromisso = new VerCompromisso(listaCompromissos.get(compromissos.getAnchorSelectionIndex()), usuario);
        vercompromisso.setVisible(true);
        }
    }//GEN-LAST:event_compromissosMouseClicked

    private void gruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gruposMouseClicked
       
        this.setVisible(false);    
        
        VerGrupos vergrupos = new VerGrupos(listaGrupos.get(grupos.getAnchorSelectionIndex()), usuario);
        vergrupos.setVisible(true);
        
    }//GEN-LAST:event_gruposMouseClicked

    private void dayChooser(java.awt.event.ActionEvent evt) {
        System.out.println(Calendario.getDate());
    }
    
    public void selecionaData() {
        System.out.println(Calendario.getDate());
    }
    
    
    public void windowClosing(WindowEvent e) {
    
        int dialogResult = JOptionPane.showConfirmDialog(this, "Quer mesmo excluir este compromisso?", "Excluir compromisso", JOptionPane.YES_NO_OPTION);
       
        if(dialogResult == 0) 
            System.out.println("TÁ");
        else 
             System.exit(0);
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
    private javax.swing.JPanel JPanelLista;
    private javax.swing.JTabbedPane Lista;
    private javax.swing.JList<String> compromissos;
    private javax.swing.JList<String> contatos;
    private javax.swing.JList<String> grupos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel labelHello;
    // End of variables declaration//GEN-END:variables
}
