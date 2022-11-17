
package compromissos2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Usuario {
    
    private String nome, login, endereco, telefone, email, senha, senhaConfirm;
    private Date data_nasc;

    public Usuario(String nome, String login, Date data_nasc, String endereco, String telefone, String email, String senha, String senhaConfirm) {
        this.nome = nome;
        this.login = login;
        this.data_nasc = data_nasc;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.senhaConfirm = senhaConfirm;
    }
    
    // Sobrecarga
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getSenhaConfirm() {
        return senhaConfirm;
    }

    public void setSenhaConfirm(String senhaConfirm) {
        this.senhaConfirm = senhaConfirm;
    }
   
    
    public Boolean senhaVerify(String senha, String senhaConfirm) {
        
        return senha == senhaConfirm;
   
    }
    
}
 
    
