package compromissos2;

import java.security.Timestamp;
import java.time.LocalDateTime;


public class Compromisso {
    
    String nome, descricao ,localc;
    int id;
    LocalDateTime inicio, fim;  
    
    public Compromisso(String nome, String descricao, String localc, LocalDateTime inicio, LocalDateTime fim) {
    
        this.nome = nome;
        this.descricao = descricao;
        this.localc = localc;
        this.inicio = inicio;
        this.fim = fim;
    }
    
    public Compromisso(String nome, String descricao, String localc) {
    
        this.nome = nome;
        this.descricao = descricao;
        this.localc = localc;      
    }
    
    public Compromisso(String nome, int id, String descricao, String localc, LocalDateTime inicio, LocalDateTime fim) {
    
        this.nome = nome;
        this.id = id;
        this.descricao = descricao;
        this.localc = localc;
        this.inicio = inicio;
        this.fim = fim;
    
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalc() {
        return localc;
    }

    public void setLocalc(String localc) {
        this.localc = localc;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }
    
    
    
}
