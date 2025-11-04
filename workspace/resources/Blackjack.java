package resources;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Blackjack {
	Stack<Card> deck = new Stack<Card>();
	ArrayList<Card> discard = new ArrayList<Card>();
	Dealer dealer;

	boolean playerTurn = true;
	boolean busted = false;
	int score = 0;
	int aces = 0;
	int usedAces=0;
    ArrayList<Card> cards = new ArrayList<>();
	
	public Blackjack(){
		for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 14; j++) {
                Card card = new Card(1, Card.Suit.Clubs);
                if (i == 0) {
                    card = new Card(j, Card.Suit.Clubs);
                    discard.add(card);
                }
                if (i == 1) {
                    card = new Card(j, Card.Suit.Diamonds);
                    discard.add(card);
                }
                if (i == 2) {
                    card = new Card(j, Card.Suit.Hearts);
                    discard.add(card);
                }
                if (i == 3) {
                    card = new Card(j, Card.Suit.Spades);
                    discard.add(card);
                }
                System.out.println(card);
            }
        }
        for (int i = 0; i < discard.size(); i++) {

            int num = (int) (Math.random() * discard.size());
            deck.push(discard.get(num));
            discard.remove(num);
        }
		playerHit();
		playerHit();
	}

	//the part of your program that's in charge of game rules goes here.
	public Card hit(){
		if (deck.empty()){
			for (int i = discard.size(); i>0; i++){
				int num = (int)(Math.random()*discard.size());
				deck.push(discard.get(num));
				discard.remove(num);
			}
		}
		Card card = deck.pop();
		return card;
	}

	public void playerHit(){
		Card card = hit();
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

// 	//public void resetGame(){
// 		//for(int i = 0; cards.length(); i++){
		//	discard.add(i);
// 		//}
// 	//}
}
