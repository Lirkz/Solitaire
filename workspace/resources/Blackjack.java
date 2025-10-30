package resources;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Blackjack {
	Stack<Card> deck;
	ArrayList<Card> discard;
	
	//the part of your program that's in charge of game rules goes here.
	public Card hit(){
		Card card = deck.pop();
		return card;
	}

	public Card stand(){
		
	}
}
