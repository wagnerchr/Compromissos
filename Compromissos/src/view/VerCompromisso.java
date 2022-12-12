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
import static view.AddGrupo.listaContatosAdd;
import static view.VerContato.campos;


public class VerCompromisso extends javax.swing.JFrame {

    static Compromisso compromisso;
    static Usuario usuario;
    ConnectionFactory cf = new ConnectionFactory();
    DefaultListModel demoList = new DefaultListModel();

    
    ArrayList<Usuario> listaContatosParaInserir = new ArrayList<>();    
    ArrayList<Usuario> listaContatosInseridos = new ArrayList<>();
    int itemscomboBox = listaContatosParaInserir.size();
    
    static Boolean edit = false;
    
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
        exibeContatos();           
        carregaContatos(compromisso, usuario);
        
        
    }
    
 // LISTA DE CONTATOS COMBOBOX   
    private void exibeContatos() {
    
         DefaultListModel model = new DefaultListModel();  
         
        listaContatosParaInserir.clear();
        listaContatosAdd.clear();          
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
    
// CONTATOS NA COMBOBOX PARA SEREM ADICIONADOS
    public ArrayList<Usuario> carregaContatos() {

        try {
            //ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);

            String query = 
            "SELECT * FROM pessoa p WHERE p.login is null AND p.id IN ( SELECT id_contato FROM pessoacontato pc WHERE pc.id_pessoa = ? )"
                    + " AND p.id NOT IN ( SELECT id_pessoa FROM pessoacompromisso );";

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
    
    private void displayWindow() {
     

        textNome.setText(compromisso.getNome());
        textNome.setFont(new Font("Roboto", Font.BOLD, 32));
        textDescricao.setText(compromisso.getDescricao());
        textLocal.setText(compromisso.getLocalc());
    
        String inicio = compromisso.getInicio().toString();
        String fim = compromisso.getFim().toString();
        
        textInicio.setText(inicio.substring(0, 10) + " " + inicio.substring(11));
        textFim.setText(fim.substring(0, 10) + " " + fim.substring(11));
        
        btnAddContact.setOpaque(true);
        btnEdit.setOpaque(true);
        btnRemoveContact.setOpaque(true);
        btnEdit.setOpaque(true);
        btnExcluir.setOpaque(true);
        btnVoltar.setOpaque(true);
        
        btnAddContact.setBorderPainted(false);
        btnEdit.setBorderPainted(false);
        btnRemoveContact.setBorderPainted(false);
        btnExcluir.setBorderPainted(false);
        btnVoltar.setBorderPainted(false);
        
        btnAddContact.setEnabled(false);
        btnRemoveContact.setEnabled(false);
    
    }

    public void carregaTexto(ArrayList<JTextField> campos, Boolean e) {             
        for(int i = 0; i < campos.size(); i++) 
            campos.get(i).setEnabled(e);                
    }
    
    
// CONTATOS QUE FAZEM PARTE DO COMPROMISSO
    private void carregaContatos(Compromisso compromisso, Usuario usuario) {
           
        try {      
            
            // Reset JList
            demoList.removeAllElements();
            jListContatos.setModel(demoList);
                             
            conn = cf.getConnection();
            conn.setAutoCommit(false);
                     
            String query = "SELECT * FROM pessoa p WHERE login is null AND p.id IN ( SELECT id_pessoa FROM pessoacompromisso pc WHERE pc.id_compromisso = ?);";
                            
            PreparedStatement ps = conn.prepareStatement(query);  
            ps.setInt(1, compromisso.getId());                    
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
            InsereContatosCompromisso();

            
            
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
            
            InsereContatosCompromisso();
                     
            //ConectaContato(contato);
            JOptionPane.showMessageDialog(null, "Compromisso editado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em InsereContatoBanco: " + ex.getMessage());
        }
    
    }
       
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnVoltar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListContatos = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField();
        btnAddContact = new javax.swing.JButton();
        textDescricao = new javax.swing.JTextField();
        btnRemoveContact = new javax.swing.JButton();
        textLocal = new javax.swing.JTextField();
        comboboxContatos = new javax.swing.JComboBox<>();
        textInicio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textFim = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(203, 241, 245));

        jPanel1.setBackground(new java.awt.Color(203, 241, 245));
        jPanel1.setForeground(new java.awt.Color(67, 154, 151));
        jPanel1.setPreferredSize(new java.awt.Dimension(636, 540));

        btnVoltar.setBackground(new java.awt.Color(67, 154, 151));
        btnVoltar.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(67, 154, 151));
        btnExcluir.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setText("Excluir Compromisso");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jListContatos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListContatos);

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        jLabel1.setText("Contatos adicionados: ");

        textNome.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N

        btnAddContact.setBackground(new java.awt.Color(67, 154, 151));
        btnAddContact.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        btnAddContact.setForeground(new java.awt.Color(255, 255, 255));
        btnAddContact.setText("Add");
        btnAddContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddContactActionPerformed(evt);
            }
        });

        textDescricao.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N

        btnRemoveContact.setBackground(new java.awt.Color(67, 154, 151));
        btnRemoveContact.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        btnRemoveContact.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoveContact.setText("Remover");
        btnRemoveContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveContactActionPerformed(evt);
            }
        });

        textLocal.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        textLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textLocalActionPerformed(evt);
            }
        });

        comboboxContatos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        comboboxContatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxContatosActionPerformed(evt);
            }
        });

        textInicio.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        textInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textInicioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel2.setText("Local do Compromisso: ");

        textFim.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        textFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFimActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel3.setText("Inicio do Evento");

        btnEdit.setBackground(new java.awt.Color(67, 154, 151));
        btnEdit.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel4.setText("Fim do Evento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(textLocal)
                                    .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(textInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnVoltar))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(textFim, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnExcluir)
                                        .addGap(7, 7, 7)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnRemoveContact)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddContact))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(65, 65, 65))
                                .addComponent(comboboxContatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(30, 30, 30))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboboxContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFim, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddContact)
                    .addComponent(btnRemoveContact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                btnAddContact.setEnabled(true);
                btnRemoveContact.setEnabled(true);
                
                btnEdit.setText("Confirmar");
        } else {
            
            edit = false;
            carregaTexto(campos, edit);
            btnEdit.setText("Editar");           
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        try {
            edit = false;
            this.dispose();
            conn.close();
            new Home(usuario).setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao voltar para Home " + ex.getMessage());
        }
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        
        int dialogResult = JOptionPane.showConfirmDialog(this, "Quer mesmo excluir este compromisso?", "Excluir compromisso", JOptionPane.YES_NO_OPTION);
       
        if(dialogResult == 0) 
            excluirCompromisso(compromisso);
        else 
             System.exit(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void comboboxContatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxContatosActionPerformed

    }//GEN-LAST:event_comboboxContatosActionPerformed

    private void btnRemoveContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveContactActionPerformed
        removeContato();
    }//GEN-LAST:event_btnRemoveContactActionPerformed

    private void btnAddContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddContactActionPerformed
       
        demoList.addElement( comboboxContatos.getSelectedItem() );
        listaContatosInseridos.add( listaContatosParaInserir.get( comboboxContatos.getSelectedIndex()) );
        listaContatosParaInserir.remove(comboboxContatos.getSelectedIndex());
        
        comboboxContatos.removeItem( comboboxContatos.getSelectedItem() );
        
        jListContatos.setModel(demoList);     
    }//GEN-LAST:event_btnAddContactActionPerformed

    
    
    
    private void InsereContatosCompromisso() {
    
        
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
            
            String query = "INSERT INTO pessoacompromisso() VALUES(?, ?)";
            
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            PreparedStatement ps;

            ps = conn.prepareStatement(query);
            
            System.out.println("VEJA AQUI : " + listaContatosInseridos);
            System.out.println("contatos IDS: " +contatosIds);
            
            for(int i = 0; i < contatosIds.size(); i++) {
                             
                ps.setInt(1, contatosIds.get(i) );
                ps.setInt(2, compromisso.getId());
                
                ps.execute();
  
            }
            
            conn.commit();
            ps.close();
            
    }catch(SQLException ex) {
            System.out.println(ex);
    }
    
    }
    
    
    private void removeContato() {
    
        String query = "DELETE FROM pessoacompromisso WHERE id_pessoa = ?";
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
                        
            carregaContatos(compromisso, usuario);
            exibeContatos();
                    
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao removerContato " + ex.getMessage());     
        }
      
    
    }
    
    
    
    
    
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
    private javax.swing.JButton btnAddContact;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnRemoveContact;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> comboboxContatos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jListContatos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField textDescricao;
    private javax.swing.JTextField textFim;
    private javax.swing.JTextField textInicio;
    private javax.swing.JTextField textLocal;
    private javax.swing.JTextField textNome;
    // End of variables declaration//GEN-END:variables
}
