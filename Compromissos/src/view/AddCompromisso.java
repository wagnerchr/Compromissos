package view;

import compromissos2.Compromisso;
import compromissos2.PlaceHolders;
import compromissos2.Usuario;
import compromissos2.connections.CompromissoConnection;
import compromissos2.connections.ConnectionFactory;
import compromissos2.connections.UserConnection;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddCompromisso extends javax.swing.JFrame {

    static Date date;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    DefaultListModel demoList = new DefaultListModel();
    
    PlaceHolders placeHolders = new PlaceHolders();
    ConnectionFactory cf = new ConnectionFactory();
    
    ArrayList<Usuario> lista = new ArrayList<>();
    static Usuario usuario;
  
    ArrayList<Usuario> listaContatosAdd = new ArrayList<>();
    boolean firstClick = true;
    int IDZAO;
    int IDUSUARIO;

    public AddCompromisso(Usuario usuario, Date data) {
        
        initComponents();
             
        this.usuario = usuario;
        this.date = data;
        
                 
    // Título da janela: 
        displayWindow();
        
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Brazil/West"));           
        cal.setTime(date);
        
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        
        String dayS = "";
        if(day <= 9) 
            dayS = "0" + day;
        else 
            dayS = String.valueOf(day);
        
        this.setTitle("Compromisso para o dia: " + dayS + "/"  + month);
        
        

        ArrayList<JTextField> campos = new ArrayList<>(Arrays.asList(
                textNomeCompromisso,               
                textLocalCompromisso,
                textDataFim
          
        ));
        
        listaContatosAdd.clear();
        exibeContatos();
        timePicker.getSelectedTime();   
        
        placeHolders.startPlaceHolders(campos);
    }
    
    //  
    private void displayWindow() {
        
        this.setBackground(Color.white);
        this.setResizable(false);
        this.setLocationRelativeTo(null);    
        this.setDefaultCloseOperation(0);  
        
        btnSetHour.setOpaque(true);
        btnAddContact.setOpaque(true);
        btnCreate.setOpaque(true);
        btnVoltar.setOpaque(true);
        
        
        
        btnSetHour.setBorderPainted(false);
        btnAddContact.setBorderPainted(false);
        btnCreate.setBorderPainted(false);
        btnVoltar.setBorderPainted(false);
    
    
    }
    
    // MOSTRAR CONTATOS 
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

    public ArrayList<Usuario> carregaContatos() {

        try {
            //ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);

            String query = "SELECT * FROM pessoa p WHERE p.id IN ( SELECT id_contato FROM pessoacontato pc WHERE pc.id_pessoa = ?)";

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
    // FIM MOSTRA CONTATOS// FIM MOSTRA CONTATOS

    // VER ID
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

        canvas1 = new java.awt.Canvas();
        jPanel1 = new javax.swing.JPanel();
        comboboxContatos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnAddContact = new javax.swing.JButton();
        textLocalCompromisso = new javax.swing.JTextField("Local do compromisso");
        timePicker = new com.raven.swing.TimePicker();
        textDataFim = new javax.swing.JTextField("Data do encerramento do compromisso");
        btnVoltar = new javax.swing.JButton();
        btnSetHour = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        textNomeCompromisso = new javax.swing.JTextField("Nome do Compromisso");
        labelStart = new javax.swing.JLabel();
        labelFinish = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textDescricao = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        listContatos = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(203, 241, 245));
        jPanel1.setPreferredSize(new java.awt.Dimension(528, 868));
        jPanel1.setRequestFocusEnabled(false);

        comboboxContatos.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        comboboxContatos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        comboboxContatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxContatosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(67, 154, 151));
        jLabel1.setText("Adicionar Participantes");

        btnAddContact.setBackground(new java.awt.Color(67, 154, 151));
        btnAddContact.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btnAddContact.setForeground(new java.awt.Color(255, 255, 255));
        btnAddContact.setText("Add");
        btnAddContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddContactActionPerformed(evt);
            }
        });

        textLocalCompromisso.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textLocalCompromisso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textLocalCompromissoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textLocalCompromissoFocusLost(evt);
            }
        });

        timePicker.setBackground(new java.awt.Color(227, 253, 253));
        timePicker.setForeground(new java.awt.Color(153, 153, 153));

        textDataFim.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textDataFim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textDataFimFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textDataFimFocusLost(evt);
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

        btnSetHour.setBackground(new java.awt.Color(67, 154, 151));
        btnSetHour.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btnSetHour.setForeground(new java.awt.Color(255, 255, 255));
        btnSetHour.setText("Ok");
        btnSetHour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetHourActionPerformed(evt);
            }
        });

        btnCreate.setBackground(new java.awt.Color(67, 154, 151));
        btnCreate.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("Confirmar ");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(67, 154, 151));
        jLabel2.setText("Definir horário do comprimisso");

        textNomeCompromisso.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        textNomeCompromisso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textNomeCompromissoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textNomeCompromissoFocusLost(evt);
            }
        });

        labelStart.setBackground(new java.awt.Color(0, 0, 0));
        labelStart.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        labelStart.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        labelStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelStartMouseClicked(evt);
            }
        });

        labelFinish.setBackground(new java.awt.Color(0, 0, 0));
        labelFinish.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        labelFinish.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        labelFinish.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelFinishMouseClicked(evt);
            }
        });

        textDescricao.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(textDescricao);

        listContatos.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        listContatos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listContatos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(labelStart, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(labelFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSetHour)
                        .addComponent(timePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textLocalCompromisso, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboboxContatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddContact, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(textNomeCompromisso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                        .addComponent(textDataFim, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(textNomeCompromisso, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(timePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSetHour, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelStart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboboxContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textLocalCompromisso, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddContact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection conn;

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        
        DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      
        
        System.out.println( labelStart.getText() );
        
        try {
        
            String nome, descricao, localc;
            LocalDateTime dataInicio, dataFim;
              
            nome = textNomeCompromisso.getText();
            descricao = textDescricao.getText();
            localc = textNomeCompromisso.getText();
            
   
            dataInicio = LocalDateTime.parse( labelStart.getText(), formatterD);
            dataFim = LocalDateTime.parse(labelFinish.getText(), formatterD);
            
      
            Compromisso compromisso = new Compromisso(
                    nome,
                    descricao,
                    localc,
                    dataInicio,
                    dataFim                  
            );   
            
            checkCompromisso(compromisso);      
            
           
        } catch(Exception e) {
            System.out.println(e);
        }
                  
    }//GEN-LAST:event_btnCreateActionPerformed

    
    private void checkCompromisso(Compromisso compromisso) {
    
        String query =
            "SELECT * FROM compromisso c WHERE c.id IN ( SELECT id_compromisso FROM pessoacompromisso pc  WHERE pc.id_pessoa = ?)";
 
        try {

            conn = cf.getConnection();
            conn.setAutoCommit(false);
        
            PreparedStatement ps = conn.prepareStatement(query);        
            ps.setInt(1, Integer.parseInt(getIdUsuario(usuario)) );
            
            ResultSet rs = ps.executeQuery();      
            
            while(rs.next()) {
                
                LocalDateTime dateInsertI = rs.getTimestamp("data_inicio").toLocalDateTime();             
                LocalDateTime dateInsertF = rs.getTimestamp("data_fim").toLocalDateTime();  
                
                Instant instantI = dateInsertI.toInstant(ZoneOffset.UTC);
                Instant instantF = dateInsertF.toInstant(ZoneOffset.UTC);
                
                Instant instant2 = (compromisso.getInicio()).toInstant(ZoneOffset.UTC);
                Instant instant3 = (compromisso.getFim()).toInstant(ZoneOffset.UTC);
                  
                Date dateI = Date.from(instantI);
                Date dateF = Date.from(instantF);
                
                Date inicio = Date.from( instant2 );
                Date fim = Date.from( instant3 );
                
                if( !(dateI.before(inicio)) || (dateI.after(fim)) )  { 
                    JOptionPane.showMessageDialog(null, "Já existe um compromisso nesta data!");
                    return;   
                }
                if(!(dateF.before(inicio)  || (dateF.after(fim)) )) {
                    JOptionPane.showMessageDialog(null, "Já existe um compromisso nesta data!");
                    return;                              
                }         
            }
            
            rs.close();
           
        // Agora pode 
            InsereCompromissoBanco(compromisso);          
            InsereContatosCompromisso(compromisso);
           
        } catch (SQLException ex) {
        
            System.out.println("ero rekadkas kmdkasd kaskd amsk " + ex);
        }
    }
    
    
    
    private void InsereCompromissoBanco(Compromisso compromisso) {
               
        String query = "insert into compromisso() values(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            
        

        try {              
            
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
     
            ps = conn.prepareStatement(query);
            
            ps.setString(1, null);          
            ps.setString(2, compromisso.getNome());
            ps.setString(3, compromisso.getDescricao());
            ps.setString(4, compromisso.getLocalc());
            ps.setTimestamp(5, Timestamp.valueOf(compromisso.getInicio()));
            ps.setTimestamp(6, Timestamp.valueOf(compromisso.getFim()));
           
               
            ps.execute();
            conn.commit();
            ps.close();
            
            ConectaCompromisso(compromisso);
                      
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em InsereContatoBanco: " + ex.getMessage());
        }
    }
    
    public void ConectaCompromisso(Compromisso compromisso) {
          
        try {   
            
            // Conexão com usuário desta conta
           
            UserConnection connection_usuario = new UserConnection();   
            CompromissoConnection connection_compromisso = new CompromissoConnection();   
             
            ResultSet rsUsuario = connection_usuario.autenticacao(usuario);           
            ResultSet rsCompromisso = connection_compromisso.autenticacao(compromisso);           
            
             String idUsuario = "", idCompromisso = "";
            
             // Get Id's
            if(rsUsuario.next()) {
                idUsuario = rsUsuario.getString("id");
                IDUSUARIO = Integer.parseInt(idUsuario);
            } else 
                JOptionPane.showMessageDialog(null, "Problema ao estabelecer conexão com o usuário");
                        
            if(rsCompromisso.next())  {
                idCompromisso = rsCompromisso.getString("id");
                IDZAO = Integer.parseInt(idCompromisso);           
            } else 
                JOptionPane.showMessageDialog(null, "Problema ao estabelecer conexão com o contato adicionado");

            // ID USUÁRIO E ID CONTATO
            String query = "insert into pessoacompromisso() values(?, ?)";
            
              
        // Conexão com banco
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            PreparedStatement ps = conn.prepareStatement(query);  
                 
            ps.setInt(1, Integer.parseInt(idUsuario));
            ps.setInt(2, Integer.parseInt(idCompromisso));
            
     
            ps.execute();
            conn.commit();
            ps.close();
              
            JOptionPane.showMessageDialog(null, "Compromisso adicionado com sucesso!");
            
            this.dispose();
            new Home(usuario).setVisible(true);
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());          
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    

    
    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.dispose();
        new Home(usuario).setVisible(true);
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnSetHourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetHourActionPerformed
         
        String time = timePicker.getSelectedTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat datafimFormat = new SimpleDateFormat("dd/MM/yyyy");
                     
        if( firstClick ) {        
            labelStart.setText( dateFormat.format(date) + " " + SqlData(time) );
            firstClick = false;      
            
        } else {      
            try{
            textDataFim.getText();
            Date dataFim = datafimFormat.parse(textDataFim.getText());
 
            labelFinish.setText( dateFormat.format(dataFim) + " " + SqlData(time) ); 
            
        } catch (ParseException ex) {
                
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } 
    }//GEN-LAST:event_btnSetHourActionPerformed


    private void InsereContatosCompromisso(Compromisso compromisso) {
    
        ArrayList<Integer> contatosids = new ArrayList<Integer>();
        
        
                
        for(int i = 0; i < listaContatosAdd.size(); i++) {   
            
            for(int j = 0; j < listContatos.getModel().getSize(); j++) {
                
                if( ( listaContatosAdd.get(i).getNome() ).equals( listContatos.getModel().getElementAt(j)) )         
                    contatosids.add( (listaContatosAdd.get(i).getId()) );           
            }              
        }
        
        
       
             
       InsereContatoBanco(compromisso, contatosids);
    
    }
 
    private void InsereContatoBanco(Compromisso compromisso, ArrayList<Integer> contatosIds) {
    
        int[] arrayContatosIds = contatosIds.stream().mapToInt(i -> i).toArray();
        
        try {    
            
            String query = "INSERT INTO pessoacompromisso() VALUES(?, ?)";
            
            ConnectionFactory cf = new ConnectionFactory();
            conn = cf.getConnection();
            conn.setAutoCommit(false);
            
            PreparedStatement ps;

            ps = conn.prepareStatement(query);
            
            for(int i = 0; i < contatosIds.size(); i++) {
                
                
                ps.setInt(1, contatosIds.get(i));
                ps.setInt(2, IDZAO );
                
                ps.execute();
  
            }
            
            conn.commit();
            ps.close();
            
    }catch(SQLException ex) {
            System.out.println(ex);
    }
    
    }
 
    
    private void labelStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelStartMouseClicked
        firstClick = true;
    }//GEN-LAST:event_labelStartMouseClicked

    private void labelFinishMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFinishMouseClicked
        firstClick = false;
    }//GEN-LAST:event_labelFinishMouseClicked

    private void comboboxContatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxContatosActionPerformed
    
        
    }//GEN-LAST:event_comboboxContatosActionPerformed

    private void btnAddContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddContactActionPerformed
        
        demoList.addElement(comboboxContatos.getSelectedItem());
        comboboxContatos.removeItem(comboboxContatos.getSelectedItem());
        listContatos.setModel(demoList);
      
    }//GEN-LAST:event_btnAddContactActionPerformed

    /*
    private void conectaContatos(Compromisso compromisso) {
    
         StringBuilder usuarios = new StringBuilder();
         String comma="";
         
         for (Usuario u: listaContatosAdd) {
                usuarios.append(comma);
                usuarios.append(u.getId());
                comma = ", ";
        }

        try {
        String query = "INSERT INTO pessoacompromisso() VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, compromisso.getId());
        ps.setString(2, usuarios.toString() );

        ps.execute();
        conn.commit();
        ps.close();
         
         
         } catch(SQLException ex) {
              JOptionPane.showMessageDialog(null, "Erro em conectaContatos : " + ex.getMessage());
         }   
    
    }*/
    
    
    
    private void textNomeCompromissoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNomeCompromissoFocusGained
        if(textNomeCompromisso.getText().equals("Nome do Compromisso"))
            textNomeCompromisso.setText("");
         placeHolders.removePlaceHolder(textNomeCompromisso);
    }//GEN-LAST:event_textNomeCompromissoFocusGained

    private void textNomeCompromissoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNomeCompromissoFocusLost
        if(textNomeCompromisso.getText().equals("")) {
            textNomeCompromisso.setText("Nome do Compromisso");
            
        placeHolders.singlePlaceHolder(textNomeCompromisso);          
        }
    }//GEN-LAST:event_textNomeCompromissoFocusLost

   
    private void textDataFimFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textDataFimFocusGained
       if(textDataFim.getText().equals("Data do encerramento do compromisso"))
            textDataFim.setText("");
       
        placeHolders.removePlaceHolder(textDataFim);     
    }//GEN-LAST:event_textDataFimFocusGained

    private void textDataFimFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textDataFimFocusLost
         if(textDataFim.getText().equals("")) {
            textDataFim.setText("Data do encerramento do compromisso");               
            placeHolders.singlePlaceHolder(textDataFim);
        }
    }//GEN-LAST:event_textDataFimFocusLost

    private void textLocalCompromissoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textLocalCompromissoFocusGained
         if(textLocalCompromisso.getText().equals("Local do compromisso"))
            textLocalCompromisso.setText("");
        placeHolders.removePlaceHolder(textDataFim);
    }//GEN-LAST:event_textLocalCompromissoFocusGained

    private void textLocalCompromissoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textLocalCompromissoFocusLost
         if(textLocalCompromisso.getText().equals("")) {
            textLocalCompromisso.setText("Local do compromisso");
            placeHolders.singlePlaceHolder(textLocalCompromisso);
        }
    }//GEN-LAST:event_textLocalCompromissoFocusLost

    
    public String SqlData(String data) {
     
        try {
  
            int hora;
            String horaS;
            String horario;
              
        if(data.substring(data.length() - 2).equals("PM")) {
            
            hora = 12 + Integer.valueOf(data.substring(0, 2));
            horaS = (hora == 24) ? "00" : String.valueOf(hora);
            //horaS = String.valueOf(hora);
            
            horario = horaS + ":" + data.substring(3, 5);          
                  
        } else {          
            
            hora = Integer.valueOf(data.substring(0, 2));         
            horaS = hora < 9 
                    ? "0" + hora 
                    : String.valueOf(hora);    
            
             horario  =  horaS + ":" + data.substring(3, 5);          
        } 
        
        if(firstClick)
            labelStart.setText( ConvertData(date) + " " + horario);
        else {
            Date data_fim = formatter.parse( textDataFim.getText());  
            labelFinish.setText( ConvertData(data_fim) + " " + horario);
        }
        
        return horario;
        
        } catch (ParseException ex) {
            System.out.println(ex);
            return null;
        }
    
    }
    
    
    public String ConvertData(Date data) {

       String FORMATO_ANTIGO = "dd/MM/yyyy";

       SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_ANTIGO);

       Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
       cal.setTime(data);

       int ano = cal.get(Calendar.YEAR);
       int mes = cal.get(Calendar.MONTH) + 1;
       int dia = cal.get(Calendar.DAY_OF_MONTH);
       
       String diaS = dia <= 9 ? "0" + String.valueOf(dia) : String.valueOf(dia);
       String mesS = mes <= 9 ? "0" + String.valueOf(mes) : String.valueOf(mes);         
       String anos = String.valueOf(ano - 1);
       
       String dataSQL = anos + "-" + mesS + "-" + diaS;
       
       return dataSQL;
    }

    
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
            java.util.logging.Logger.getLogger(AddCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCompromisso(usuario, date).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddContact;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnSetHour;
    private javax.swing.JButton btnVoltar;
    private java.awt.Canvas canvas1;
    private javax.swing.JComboBox<String> comboboxContatos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelFinish;
    private javax.swing.JLabel labelStart;
    private javax.swing.JList<String> listContatos;
    private javax.swing.JTextField textDataFim;
    private javax.swing.JTextPane textDescricao;
    private javax.swing.JTextField textLocalCompromisso;
    private javax.swing.JTextField textNomeCompromisso;
    private com.raven.swing.TimePicker timePicker;
    // End of variables declaration//GEN-END:variables
}
