import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Game{
    public static void main(String[] args){
        Board b = new Board();
        b.setPreferredSize(new Dimension(700,700)); //need to use this instead of setSize
        b.setLocation(500, 250);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nice\\eclipse-workspace\\MyGame\\images\\maxresdefault.jpg"));
        b.pack();
        b.setVisible(true);
        
    }   
}