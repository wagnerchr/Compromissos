package view;

import compromissos2.Compromisso;
import compromissos2.Usuario;
import compromissos2.connections.CompromissoConnection;
import compromissos2.connections.ConnectionFactory;
import compromissos2.connections.UserConnection;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
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
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddCompromisso extends javax.swing.JFrame {

    static Date date;
    static boolean firstClick = true;
    static Usuario usuario;
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    static DefaultListModel demoList = new DefaultListModel();
    static ArrayList<Usuario> lista = new ArrayList<>();
    static ConnectionFactory cf = new ConnectionFactory();
    static ArrayList<Usuario> listaContatosAdd = new ArrayList<>();
    
    
    public AddCompromisso(Usuario usuario, Date data) {
        
        initComponents();
      
        this.setBackground(Color.white);
        this.setResizable(false);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);         
        
        this.usuario = usuario;
        this.date = data;
  
        // Título da janela: 
        
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Brazil/West"));           
        cal.setTime(date);
        
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        
        String dayS = "";
        if(day <= 9) 
            dayS = "0" + day;
        else 
            dayS = "0" + day;
        
        this.setTitle("Compromisso para o dia: " + dayS + "/"  + month);
        
        

        ArrayList<JTextField> campos = new ArrayList<>(Arrays.asList(
                textNomeCompromisso,               
                textLocalCompromisso,
                textDataFim
                
        ));
        
        listaContatosAdd.clear();
        exibeContatos();
        timePicker.getSelectedTime();     
        startPlaceHolders(campos);
    
    }
 
    // PLACE HOLDERS    
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
    // FIM PLACEHOLDERS
    
    // MOSTRAR CONTATOS 
    private void exibeContatos() {

            DefaultListModel model = new DefaultListModel();
           
            ArrayList<Usuario> lista = carregaContatos(); 

            try {            
                int count = 0;
                while(lista.size() > count) {                           
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

            int userId = Integer.valueOf(getId(usuario));
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

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro em carregaContatos: " + ex);
        }

        return lista;
    };
    // FIM MOSTRA CONTATOS

    // VER ID
    public String getId(Usuario usuario) throws SQLException {

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

        timePicker = new com.raven.swing.TimePicker();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        textNomeCompromisso = new javax.swing.JTextField("Nome do Compromisso");
        canvas1 = new java.awt.Canvas();
        jScrollPane1 = new javax.swing.JScrollPane();
        textDescricao = new javax.swing.JTextPane();
        comboboxContatos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        textLocalCompromisso = new javax.swing.JTextField("Local do compromisso");
        textDataFim = new javax.swing.JTextField("Data do encerramento do compromisso");
        setHour = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        labelStart = new javax.swing.JLabel();
        labelFinish = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listContatos = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        timePicker.setForeground(new java.awt.Color(153, 153, 153));

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Confirmar ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        textNomeCompromisso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textNomeCompromissoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textNomeCompromissoFocusLost(evt);
            }
        });

        jScrollPane1.setViewportView(textDescricao);

        comboboxContatos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        comboboxContatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxContatosActionPerformed(evt);
            }
        });

        jLabel1.setText("Adicionar Participantes");

        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        textDataFim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textDataFimFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textDataFimFocusLost(evt);
            }
        });

        setHour.setText("Ok");
        setHour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setHourActionPerformed(evt);
            }
        });

        jLabel2.setText("Definir horário do comprimisso");

        labelStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelStartMouseClicked(evt);
            }
        });

        labelFinish.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelFinishMouseClicked(evt);
            }
        });

        listContatos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listContatos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textNomeCompromisso, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(labelStart, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(labelFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textLocalCompromisso, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboboxContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(setHour)
                        .addComponent(timePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(textNomeCompromisso, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(timePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setHour)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textLocalCompromisso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboboxContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelStart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection conn;

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
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
                
            InsereCompromissoBanco(compromisso);
           
        } catch(Exception e) {
            System.out.println(e);
        }
                  
    }//GEN-LAST:event_jButton2ActionPerformed

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
            if(rsUsuario.next()) 
                idUsuario = rsUsuario.getString("id");
            else 
                JOptionPane.showMessageDialog(null, "Problema ao estabelecer conexão com o usuário");
                        
            if(rsCompromisso.next()) 
                idCompromisso = rsCompromisso.getString("id");
            else 
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
            
            // CONECTAR CONTATOS
            conectaContatos(compromisso);
             
            JOptionPane.showMessageDialog(null, "Compromisso adicionado com sucesso!");
            
            this.dispose();
            new Home(usuario).setVisible(true);
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());          
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        new Home(usuario).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void setHourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setHourActionPerformed
         
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
    }//GEN-LAST:event_setHourActionPerformed
    
 
    private void labelStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelStartMouseClicked
        firstClick = true;
    }//GEN-LAST:event_labelStartMouseClicked

    private void labelFinishMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFinishMouseClicked
        firstClick = false;
    }//GEN-LAST:event_labelFinishMouseClicked

    private void comboboxContatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxContatosActionPerformed
    
        
    }//GEN-LAST:event_comboboxContatosActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        demoList.addElement(comboboxContatos.getSelectedItem());
        comboboxContatos.removeItem(comboboxContatos.getSelectedItem());
        listContatos.setModel(demoList);

        
        listaContatosAdd.add( lista.get(comboboxContatos.getSelectedIndex() ) );       
        //adionaContatoCompromisso(compromisso);
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
    
    }
    
    
    
    private void textNomeCompromissoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNomeCompromissoFocusGained
        if(textNomeCompromisso.getText().equals("Nome do Compromisso"))
            textNomeCompromisso.setText("");
         removePlaceHolder(textNomeCompromisso);
    }//GEN-LAST:event_textNomeCompromissoFocusGained

    private void textNomeCompromissoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNomeCompromissoFocusLost
        if(textNomeCompromisso.getText().equals("")) {
            textNomeCompromisso.setText("Nome do Compromisso");
            singlePlaceHolder(textNomeCompromisso);
        }
    }//GEN-LAST:event_textNomeCompromissoFocusLost

   
    private void textDataFimFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textDataFimFocusGained
       if(textDataFim.getText().equals("Data do encerramento do compromisso"))
            textDataFim.setText("");
         removePlaceHolder(textDataFim);
      
    }//GEN-LAST:event_textDataFimFocusGained

    private void textDataFimFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textDataFimFocusLost
         if(textDataFim.getText().equals("")) {
            textDataFim.setText("Data do encerramento do compromisso");
            singlePlaceHolder(textDataFim);
        }
    }//GEN-LAST:event_textDataFimFocusLost

    
    public String SqlData(String data) {
     
        try {
  
            int hora;
            String horaS;
            String horario;
              
        if(data.substring(data.length() - 2).equals("PM")) {
            
            hora = 12 + Integer.valueOf(data.substring(0, 2));
            hora = (hora == 24) ? 0 : hora;
            horaS = String.valueOf(hora);
            
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
    private java.awt.Canvas canvas1;
    private javax.swing.JComboBox<String> comboboxContatos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelFinish;
    private javax.swing.JLabel labelStart;
    private javax.swing.JList<String> listContatos;
    private javax.swing.JButton setHour;
    private javax.swing.JTextField textDataFim;
    private javax.swing.JTextPane textDescricao;
    private javax.swing.JTextField textLocalCompromisso;
    private javax.swing.JTextField textNomeCompromisso;
    private com.raven.swing.TimePicker timePicker;
    // End of variables declaration//GEN-END:variables
}
