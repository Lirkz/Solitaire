package resources;
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
	boolean playerHasInputted = false;
	
	public Blackjack(){
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

		
	}

	//Precondition: if play in dealer is called
	//Postcondition:if deck is empty reshufle deck from discard pile otherwise draw a card from the deck
	public Card hit(){
		if (deck.empty()){
			for (int i = 0; i<discard.size();i++){
				deck.add(discard.poll());
			}
			Collections.shuffle(deck);
		}
		
		
		Card card = deck.pop();
		return card;
	}

	//Precondition: If the player presses the hit button
	//Postcondition: give the player a card and add the value to the score if the player 
	//has and ace and goes over 21 set the ace value to 1 if busts set bust equal to true
	public void playerHit(){
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
				gameState = State.Lose;
				gameOver();
			}
			System.out.print(busted);
            aces = 0;
		}
		gui.update();
	}

	//Precondition: if the player presses stay
	//Postcondition: set the playerturn to false and make the dealer play
	public void stand(){
		if (playerTurn){
			playerTurn = false;
			dealer.play();
		}
	}

	//Precondition: when reset game is called
	//Postcondition: add all cards on the board to discard, set score to 0, set busted to false,
	//instantiate a new dealer, set the gameState to playing, set to player turn, and hit twice
	public void resetGame(){
		for(int i = 0; i < cards.size(); i++){
			discard.add(cards.get(i));
		}
		for(int i = 0; i < dealer.cards.size(); i++){
			discard.add(dealer.cards.get(i));
		}
		score = 0;
		cards.clear();
		busted=false;
		Dealer newDealer = new Dealer(this,gui);
		dealer = newDealer;
		gui.dealer = newDealer;
		gameState= Blackjack.State.Playing;
		gui.endState.setText("Playing");
		playerTurn=true;
		playerHit();
		playerHit();
	}
	
	//Precondition: if gameover is called
	//Postcondition: depending on the score choose win, lose or tie, set the gamestate accordingly, update gui
	public void gameOver(){
		playerTurn=false;
		if(score == dealer.score){
			gameState = State.Tie;
			//System.out.println("Tie");
			gui.update();
		}

		if(dealer.busted || score > dealer.score || (score < dealer.score && dealer.busted)){
			gameState = State.Win;
			//System.out.println("Win");
			gui.update();
		}

		if((score < dealer.score && !dealer.busted) || busted){
			gameState = State.Lose;
			//System.out.println("Lose");
			gui.update();
		}

	}

	enum State{
		Playing, Win, Lose, Tie;
	}
}
