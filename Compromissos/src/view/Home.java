package view;


import com.toedter.calendar.JDayChooser;
import compromissos2.Compromisso;
import compromissos2.Grupo;
import compromissos2.connections.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.Connection;
import compromissos2.connections.UserConnection;
import compromissos2.Usuario;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;




public class Home extends javax.swing.JFrame {

    static Usuario usuario;
    
    // Lista
    static ArrayList<Compromisso> listaCompromissos = new ArrayList<>();
    static ArrayList<Usuario> listaContatos = new ArrayList<>();
    static ArrayList<Grupo> listaGrupos = new ArrayList<>();
    
    boolean Choosed = false;
    int dateChoosed;
   
    // Conexão 
    ConnectionFactory cf = new ConnectionFactory();
    Connection conn;
    
    
    public Home(Usuario usuario) {
        
        initComponents();        
        this.usuario = usuario; 
        displayWindow();
        
    }    
                     
    private void displayWindow() {
        
        this.setLocationRelativeTo(null);         
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        labelHello.setText("Olá, " + usuario.getNome());
     
        this.getContentPane().setBackground(Color.decode("#CBF1F5"));
        UIManager.put( contatos, BorderFactory.createEmptyBorder());

        //iconPhoto.setBackground(Color.red);
        
        btnCriaCadastro.setOpaque(true);
        btnCriaGrupo.setOpaque(true);
        btnSair.setOpaque(true);

        btnCriaCadastro.setBorderPainted(false);
        btnCriaGrupo.setBorderPainted(false);
        btnSair.setBorderPainted(false);
            
        // Load JLists
            exibeCompromissos();
            exibeContatos();
            exibeGrupos();
            
        // OnClick Calendar 
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
                     
            // Select Date
                JDayChooser dayChooser = Calendario.getDayChooser();            
                dayChooser.setAlwaysFireDayProperty(true); // here is the key     
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                dayChooser.addPropertyChangeListener("day", (evt) -> {    
                    
                    try {                    
                        Date todaysDate = sdf.parse(sdf.format(date));
                        Date CalendarDate = sdf.parse(sdf.format(Calendario.getDate()));
                
                        if( dateChoosed == dayChooser.getDay() )                         
                            if( Calendario.getDate().compareTo(date) >= 0 || CalendarDate.equals(todaysDate) )         
                                marcarCompromisso();                                         
                        dateChoosed = dayChooser.getDay();
                        
                    } catch(ParseException pe) {
                        System.out.println("Erro ao converter data em HOME: " + pe);
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
        btnCriaCadastro = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grupos = new javax.swing.JList<>();
        btnCriaGrupo = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        iconPhoto = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Calendario.setBackground(new java.awt.Color(113, 201, 206));
        Calendario.setForeground(new java.awt.Color(0, 0, 0));
        Calendario.setDecorationBackgroundColor(new java.awt.Color(113, 201, 206));
        Calendario.setSundayForeground(new java.awt.Color(204, 204, 255));
        Calendario.setWeekOfYearVisible(false);

        labelHello.setFont(new java.awt.Font("Microsoft YaHei", 0, 36)); // NOI18N
        labelHello.setText("Olá, ");

        Lista.setBackground(new java.awt.Color(113, 201, 206));
        Lista.setForeground(new java.awt.Color(0, 0, 0));
        Lista.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(227, 253, 253));

        JPanelLista.setBackground(new java.awt.Color(203, 241, 245));

        compromissos.setBackground(new java.awt.Color(227, 253, 253));
        compromissos.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        compromissos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        compromissos.setSelectionBackground(new java.awt.Color(203, 241, 245));
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
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("", JPanelLista);

        Lista.addTab("Compromissos", jTabbedPane1);

        jPanel3.setBackground(new java.awt.Color(203, 241, 245));

        contatos.setBackground(new java.awt.Color(227, 253, 253));
        contatos.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        contatos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"a"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        contatos.setSelectionBackground(new java.awt.Color(227, 253, 253));
        contatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(contatos);

        btnCriaCadastro.setBackground(new java.awt.Color(113, 201, 206));
        btnCriaCadastro.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        btnCriaCadastro.setForeground(new java.awt.Color(255, 255, 255));
        btnCriaCadastro.setText("Adicionar novo Contato");
        btnCriaCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriaCadastroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(btnCriaCadastro)
                .addContainerGap(120, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCriaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("", jPanel3);

        Lista.addTab("Contatos", jTabbedPane2);

        jPanel2.setBackground(new java.awt.Color(203, 241, 245));

        grupos.setBackground(new java.awt.Color(227, 253, 253));
        grupos.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
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

        btnCriaGrupo.setBackground(new java.awt.Color(113, 201, 206));
        btnCriaGrupo.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        btnCriaGrupo.setForeground(new java.awt.Color(255, 255, 255));
        btnCriaGrupo.setText("Criar Grupo");
        btnCriaGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriaGrupoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(btnCriaGrupo)
                .addContainerGap(160, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCriaGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("", jPanel2);

        Lista.addTab("Grupos", jTabbedPane3);

        btnSair.setBackground(new java.awt.Color(113, 201, 206));
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        iconPhoto.setForeground(new java.awt.Color(204, 0, 0));
        iconPhoto.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Calendario, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                        .addGap(22, 22, 22)
                        .addComponent(Lista, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHello, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iconPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnSair)
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHello, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Calendario, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Lista, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCriaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriaCadastroActionPerformed
        this.setVisible(false);
        new AddContato(usuario).setVisible(true);
    }//GEN-LAST:event_btnCriaCadastroActionPerformed

    private void btnCriaGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriaGrupoActionPerformed
        this.setVisible(false);
        new AddGrupo(usuario).setVisible(true);
    }//GEN-LAST:event_btnCriaGrupoActionPerformed

    private void contatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contatosMouseClicked
        if( contatos.getSelectedIndex() >= 0) {
            this.setVisible(false);    

            VerContato vercontato = new VerContato(listaContatos.get(contatos.getAnchorSelectionIndex()), usuario);
            vercontato.setVisible(true);
        }   
     
    }//GEN-LAST:event_contatosMouseClicked

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        
        int dialogResult = JOptionPane.showConfirmDialog(this, "Desabilitar Login Automático?", "Fazer Logoff", JOptionPane.YES_NO_OPTION);
       
        if(dialogResult == 0) {
              new Login().pref.putBoolean("rememberMe", false);
      
            this.dispose(); 
            this.setVisible(false);     
            
            JOptionPane.showConfirmDialog(this, "Próximo login, a conta não será lembrada", "Avisso", JOptionPane.OK_OPTION);
        }                             
    }//GEN-LAST:event_btnSairActionPerformed

    private void compromissosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compromissosMouseClicked
        if( compromissos.getSelectedIndex() >= 0) {
        
        this.setVisible(false);    
        
        VerCompromisso vercompromisso = new VerCompromisso(listaCompromissos.get(compromissos.getAnchorSelectionIndex()), usuario);
        vercompromisso.setVisible(true);
        }
    }//GEN-LAST:event_compromissosMouseClicked

    private void gruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gruposMouseClicked
        if( grupos.getSelectedIndex() >= 0) {
            this.setVisible(false);    

            VerGrupos vergrupos = new VerGrupos(listaGrupos.get(grupos.getAnchorSelectionIndex()), usuario);
            vergrupos.setVisible(true);
        }
    }//GEN-LAST:event_gruposMouseClicked

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
    private javax.swing.JPanel JPanelLista;
    private javax.swing.JTabbedPane Lista;
    private javax.swing.JButton btnCriaCadastro;
    private javax.swing.JButton btnCriaGrupo;
    private javax.swing.JButton btnSair;
    private javax.swing.JList<String> compromissos;
    private javax.swing.JList<String> contatos;
    private javax.swing.JList<String> grupos;
    private javax.swing.JLabel iconPhoto;
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
