package compromissos2;

public class Grupo {
    
    String nome, descricao;
    int id;
    
    
    public Grupo(String nome, String descricao) {
        
        this.nome = nome;
        this.descricao = descricao;    
    }
    
    public Grupo(String nome, int id, String descricao) {
        
        this.nome = nome;
        this.id = id;
        this.descricao = descricao;  
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
   
    
}
