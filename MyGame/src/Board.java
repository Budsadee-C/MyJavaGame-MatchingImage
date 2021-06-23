import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Board extends JFrame{


    private List<Card> cards; // keep card click already
    private Card card1;
    private Card card2;
    private Timer t;

    public Board(){ // constructor
    	
        int pairs = 20;
        List<Card> cardsList = new ArrayList<Card>();
        List<Integer> cardVals = new ArrayList<Integer>();

        for (int i = 2; i <= pairs; i+=2){ // initial value in list
            cardVals.add(i);
            cardVals.add(i);
        } 
        
        Collections.shuffle(cardVals); // random position in list

        for (int val : cardVals){
            Card c = new Card();
            c.setId(val);
            c.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    CheckOpenCard(c);// same type can give vale
                }
            });
            cardsList.add(c);
        }
        this.cards = cardsList;
        //set up the timer
        t = new Timer(650, new ActionListener(){ // timer
            public void actionPerformed(ActionEvent ae){
                checkCards();
            }
        });

        t.setRepeats(false);

        //set up the board itself
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(4, 5));
        for (Card c : cards){
            pane.add(c);
        }
        setTitle("Matching Game");

    }

    public void CheckOpenCard(Card selectedCard){
        if (card1 == null && card2 == null){ // first time
            card1 = selectedCard;
            card1.setText(String.valueOf(card1.getId())); // change another type to String
            card1.setFont(new Font("Tahoma", Font.PLAIN, 42));
        }

        if (card1 != null && card1 != selectedCard && card2 == null){ // second time
            card2 = selectedCard;
            card2.setText(String.valueOf(card2.getId()));
            card2.setFont(new Font("Tahoma", Font.PLAIN, 42));
            t.start();

        }
    }

    public void checkCards(){ // check match with 2 card
        if (card1.getId() == card2.getId()){//match condition
            card1.setEnabled(false); //disables the button
            card2.setEnabled(false);
            card1.setMatched(true); //flags the button as having been matched
            card2.setMatched(true);
            if (this.isGameWon()){
                JOptionPane.showMessageDialog(this, "You win!","Wow!! The Winner",JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        }

        else{
            card1.setText(""); //'hides' text
            card2.setText("");
        }
        card1 = null; //reset c1 and c2
        card2 = null;
    }

    public boolean isGameWon(){
        for(Card c: this.cards){
            if (c.getMatched() == false){
                return false;
            }
        }
        return true;
    }

}