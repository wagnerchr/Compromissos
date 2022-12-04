package compromissos2;

public class Grupo {
    
    String nome, descricao;
    
    
    public Grupo(String nome, String descricao) {
        
        this.nome = nome;
        this.descricao = descricao;
    
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
   
    
}
