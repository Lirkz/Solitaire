 package resources;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Dealer{
    int score;
    int aces;
    boolean busted;
    ArrayList<Card> cards = new ArrayList<>();
    Blackjack game;
    GUI gui;
    Timer timer;

    public Dealer(Blackjack g, GUI gui){
        game = g;
        this.gui = gui;
        Card card1 = game.hit();
        card1.isReversed=true;
        cards.add(card1);
        cards.add(game.hit());
        for (Card card : cards){
            int newVal = card.value;
            if(newVal>10){
                newVal=10;
            }
            if (newVal == 1){
                newVal = 11;
            }
            score +=  newVal;
        }
    }

    public void play(){
        while (score<17){
            score =0;
            
            cards.add(game.hit());
            for (Card card : cards){
                if (card.isAce){
                    aces++;
                    score+=10;
                }
                int newVal = card.value;
		        if (newVal>10){
			        newVal=10;
		        }
                score+=newVal;
                
            }
            while (aces>0 && score >21){
                score-=10;
                aces--;
            }
            aces = 0;
            
            if (score>21){
                busted = true;
            }
            
            gui.update();
            //Thread.sleep((int)(Math.random()*2000)+3000);
            
        }
        
        game.gameOver();
        
    }

    
    

    
}
