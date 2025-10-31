package resources;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Blackjack {
	Stack<Card> deck;
	ArrayList<Card> discard;
	Dealer dealer;

	boolean playerTurn = false;
	boolean busted = false;
	int score = 0;
	int aces = 0;
    ArrayList<Card> cards = new ArrayList<>();
	
	//the part of your program that's in charge of game rules goes here.
	public Card hit(){
		Card card = deck.pop();
		return card;
	}

	public Card stand(){
		while (score<17){
            for (Card card : cards){
                if (card.isAce){ 
                    aces++;
                }
            }
            while (aces>0 && score >21){
                score-=10;
                aces--;
            }
            aces = 0;
        }
        if (score>21){
            busted = true;
        }
		//cards -> discard (visually as well)
	}
}
