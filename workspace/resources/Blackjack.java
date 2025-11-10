package resources;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.*;

public class Blackjack {
	Stack<Card> deck = new Stack<Card>();
	Queue<Card> discard = new LinkedList<Card>();
	Dealer dealer;
	GUI gui;
	boolean playerTurn = true;
	boolean busted = false;
	int score = 0;
	int aces = 0;
	int usedAces=0;
    ArrayList<Card> cards = new ArrayList<>();
	State gameState = State.Playing;
	
	public Blackjack() throws InterruptedException{
		for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 14; j++) {
                Card card = new Card(1, Card.Suit.Clubs);
                if (i == 0) {
                    card = new Card(j, Card.Suit.Clubs);
                    deck.push(card);
                }
                if (i == 1) {
                    card = new Card(j, Card.Suit.Diamonds);
                    deck.push(card);
                }
                if (i == 2) {
                    card = new Card(j, Card.Suit.Hearts);
                    deck.push(card);
                }
                if (i == 3) {
                    card = new Card(j, Card.Suit.Spades);
                    deck.push(card);
                }
                System.out.println(card);
            }
        }

		Collections.shuffle(deck);

		playerHit();
		playerHit();
	}

	//the part of your program that's in charge of game rules goes here.
	public Card hit(){
		if (deck.empty())
		Collections.shuffle(deck);
		
		Card card = deck.pop();
		return card;
	}

	public void playerHit() throws InterruptedException{
		Card card = hit();
		cards.add(card);
		int newVal = card.value;
		if (newVal>10){
			newVal=10;
		}
		score +=newVal;
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
				stand();
			}
            aces = 0;
		}
	}

	public void stand() throws InterruptedException{
		if (playerTurn){
			playerTurn = false;
			dealer.play();
		}
	}

	public void resetGame() throws InterruptedException{
		for(int i = 0; i < cards.size(); i++){
			discard.add(cards.get(i));
		}
		for(int i = 0; i < dealer.cards.size(); i++){
			discard.add(dealer.cards.get(i));
		}
		Dealer newDealer = new Dealer(this);
		dealer = newDealer;
		gui.dealer = newDealer;
		playerHit();
		Thread.sleep(3000);
		playerHit();
	}
	
	public void gameOver() throws InterruptedException{
		if(score == dealer.score){
			gameState = State.Tie;
			gui.update();
		}

		if(score > dealer.score || dealer.busted){
			gameState = State.Win;
			gui.update();
		}

		if(score < dealer.score || busted){
			gameState = State.Lose;
			gui.update();
		}

	}

	enum State{
		Playing, Win, Lose, Tie;
	}
}
