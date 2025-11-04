package resources;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Blackjack {
	Stack<Card> deck;
	ArrayList<Card> discard;
	Dealer dealer;

	boolean playerTurn = true;
	boolean busted = false;
	int score = 0;
	int aces = 0;
	int usedAces=0;
    ArrayList<Card> cards = new ArrayList<>();
	
	//the part of your program that's in charge of game rules goes here.
	public Card backHit(){
		if (deck.peek() == null){
			for (int i = discard.size(); i>0; i++){
				int num = (int)(Math.random()*discard.size());
				deck.push(cards.get(num));
				cards.remove(num);
			}
		}
		Card card = deck.pop();
		return card;
	}

	public void hit(){
		Card card = backHit();
		cards.add(card);
		score +=card.value;
		if (card.value==1){
			score+=10;
		}
		if (score>21){
			for (Card card2 : cards){
                if (card2.isAce){ 
                    aces++;
                }
            }
			aces -= usedAces;
            while (aces>0 && score >21){
                score-=10;
                aces--;
				usedAces++;
            }
			if (score>21){
				busted = true;
			}
            aces = 0;
		}
	}

	public void stand(){
		if (playerTurn){
			playerTurn = false;
		}
	}
}
