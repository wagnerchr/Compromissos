/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compromissos2;

import java.util.ArrayList;


public class Grupo {
    
    String nome;
    ArrayList<String> nomes;
    
    public Grupo(String nome, ArrayList<String> nomes) {
        
        this.nome = nome;
        this.nomes = nomes;
    
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
