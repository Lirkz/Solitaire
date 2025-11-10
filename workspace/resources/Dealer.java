 package resources;
import java.util.ArrayList;
public class Dealer {
    int score;
    int aces;
    boolean busted;
    ArrayList<Card> cards = new ArrayList<>();
    Blackjack game;

    public Dealer(Blackjack g){
        game = g;
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

    public void play() throws InterruptedException{
        while (score<17){
            score =0;
            cards.add(game.hit());
            for (Card card : cards){
                if (card.isAce){
                    aces++;
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
            
            Thread.sleep((int)(Math.random()*2000)+3000);
        }
        if (score>21){
            busted = true;
        }
        game.gameOver();
        
    }

    
}
