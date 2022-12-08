package compromissos2;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextField;


public class PlaceHolders {
    
    ArrayList<JTextField> campos = new ArrayList<>();
    
    public PlaceHolders() {
        
    }
    
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
}
