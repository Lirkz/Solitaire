//David Saiontz GUI for the blackjack game, steps 1 and 2 are just drawing the boxes and a card stack
package resources;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Stack;


public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

	Blackjack game;
	Dealer dealer;
   public GUI(Blackjack game){
	   this.game= game;
	   dealer = new Dealer(this.game);
	   this.game.dealer = dealer;
        //Create and set up the window.
       setTitle("Solitaire");
       setSize(900,700);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   

       
       // this supplies the background
       try {
		System.out.println(getClass().toString());
		Image blackImg = ImageIO.read(getClass().getResource("background.jpg"));
		setContentPane(new ImagePanel(blackImg));

       }catch(IOException e) {
    	   e.printStackTrace();
       }
       
       /*******
        * This is just a test to make sure images are being read correctly on your machine. Please replace
        * once you have confirmed that the card shows up properly. The code below should allow you to play the solitare
        * game once it's fully created.
        */
       Card card = new Card(2, Card.Suit.Diamonds);
	   Card card2  = new Card(3, Card.Suit.Spades);
	   Card card3 = new Card(4, Card.Suit.Hearts);
	   Card card4 = new Card(5, Card.Suit.Clubs);
	   Stack<Card> cards = new Stack<>();
	   cards.push(card);
	   cards.push(card2);
	   cards.push(card3);
	   cards.push(card4);
       System.out.println(card);
       ///this.add(card);    
	   GridBagConstraints oppC =  new GridBagConstraints();
	   GridBagConstraints deckC = new GridBagConstraints();
	   GridBagConstraints playC = new GridBagConstraints();
	   GridBagConstraints cardsC = new GridBagConstraints();
	   JPanel outer = new JPanel();
	   this.add(outer);
	   outer.setOpaque(false);
	   outer.setLayout(new GridBagLayout());
	   outer.setSize(new Dimension(750,500));
	   outer.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
	   JPanel opponent = new JPanel();
	   opponent.setLayout(new GridBagLayout());
	   oppC.gridheight =2;
	   oppC.gridwidth = 3;
	   oppC.anchor = GridBagConstraints.PAGE_START;
	   oppC.weighty = 1;
	   oppC.ipady = 175;
	   oppC.ipadx = 150;
	   opponent.setOpaque(false);
	   opponent.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.PINK));
	   JPanel deck = new JPanel();
	   deckC.gridx = 2;
	   deckC.gridy = 2;
	   deckC.weighty = 2;
	   deckC.ipadx = 75;
	   deckC.ipady = 100;
	   deck.setOpaque(false);
	   deck.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.YELLOW));
	   JPanel player= new JPanel();
	   playC.gridheight =2;
	   playC.gridwidth = 3;
	   playC.gridy =3;
	   playC.ipadx = 150;
	   playC.ipady = 175;
	   player.setOpaque(false);
	   player.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
	   outer.add(opponent,oppC);
	   outer.add(deck, deckC);
	   outer.add(player,playC);
	   outer.add(drawPile(cards));
	   cardsC.anchor = GridBagConstraints.FIRST_LINE_START;
	   cardsC.weighty=1;
	   cardsC.weightx =1;
		cardsC.gridheight = 1;
       cardsC.gridwidth = 1;
	   cardsC.ipadx = 84;
	   cardsC.ipady = 120;
	   opponent.add(drawPile(cards),cardsC);
    	this.setVisible(true);
    }
	public JLayeredPane drawPile(Stack<Card> stackIn) {
    	Object cards[];
    	cards = stackIn.toArray(); //please note we convert this stack to an array so that we can iterate through it backwards while drawing. You’ll need to cast each element inside cards to a <Card> in order to use the methods to adjust their position
		JLayeredPane pane = new JLayeredPane();
		for (int i = 0;i<cards.length;i++){

			((Card)cards[i]).setSize(new Dimension(49, 70));
			((Card)cards[i]).setBounds(i*8, i*14, 49, 70);
			pane.add((Card)cards[i],0);
		}
		
		return pane;
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
