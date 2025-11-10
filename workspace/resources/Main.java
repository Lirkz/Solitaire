package resources;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Blackjack game = new Blackjack();
		GUI gui = new GUI(game);
	}
}