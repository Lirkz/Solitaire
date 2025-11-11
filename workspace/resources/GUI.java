package resources;

import javax.imageio.ImageIO;
import javax.swing.*;

import resources.Card.Suit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

        
    
public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    Blackjack game;
    Dealer dealer;
    GridBagConstraints c = new GridBagConstraints();
    JPanel back = new JPanel();
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    JPanel middle = new JPanel();
    Icon tempIcon;
    JButton standButton;
    JButton hitButton;
    Boolean paused=false;

    public GUI(Blackjack game) throws InterruptedException{

        this.game = game;
        
        dealer = new Dealer(game,this);
        this.game.gui = this;
        this.game.dealer = dealer;
        // Create and set up the window.
        setTitle("Blackjack");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // this supplies the background
        try {
            System.out.println(getClass().toString());
            Image blackImg = ImageIO.read(getClass().getResource("background.jpg"));
            setContentPane(new ImagePanel(blackImg));

        } catch (IOException e) {
            e.printStackTrace();
        }

        getContentPane().setLayout(new GridBagLayout());
       
        c.ipadx = 800;
        c.ipady = 400;

        c.gridx = 0;
        c.gridy = 0;
        back.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        back.setBackground(new Color(0, 0, 0, 0));
        add(back, c);

        
        c.anchor = GridBagConstraints.PAGE_START;
        c.ipadx = 800;
        c.ipady = 110;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 3;
        top.setLayout(new FlowLayout());
        top.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        top.setBackground(new Color(0, 0, 0, 0));
        add(top, c);

        
        c.anchor = GridBagConstraints.PAGE_END;
        c.ipadx = 800;
        c.ipady = 110;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 3;
        bottom.setLayout(new FlowLayout());
        bottom.setBorder(BorderFactory.createLineBorder(Color.red, 5));
        bottom.setBackground(new Color(0, 0, 0, 0));
        add(bottom, c);

        
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 800;
        c.ipady = 120;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 3;
        middle.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
        // middle.setBackground(new Color(0, 0, 0, 0));
        middle.setLayout(null);
        middle.setOpaque(false);
        
        add(middle, c);

        // I want a way to show if the buttons are allowed to use, if its the player's
        // turn. idk if i should use an if statement

        
        

        /*******
         * This is just a test to make sure images are being read correctly on your
         * machine. Please replace
         * once you have confirmed that the card shows up properly. The code below
         * should allow you to play the solitare
         * game once it's fully created.
         */
        
        // System.out.println(card);
        // this.add(card);

        this.setVisible(true);
        game.playerHit();
		game.playerHit();
        update();
    }

    // retruns a j layered pane with the cards inside it

    public JLayeredPane drawPile(Stack<Card> stackIn) {

        Object cards[];

        cards = stackIn.toArray();
        ArrayList<Card> pile = new ArrayList<>();
        int offset = 50;
        JLayeredPane pane = new JLayeredPane();
        pane.setLayout(null);
        for (int i = 0; i < cards.length; i++) {
            System.out.println("here");
            Card temp = (Card) cards[i];
            // temp.setSize(20,20);
            temp.setBounds(0, offset * i, 20, 20);
            pane.add(temp);

        }
        pane.setPreferredSize(new Dimension(200, 80));
        pane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        return pane;
        // please note we convert this stack to an array so that we can iterate through
        // it backwards while drawing. You’ll need to cast each element inside cards to
        // a <Card> in order to use the methods to adjust their position

    }

    public void update() throws InterruptedException {
        
        top.removeAll();
        bottom.removeAll();
        middle.removeAll();
        if(!game.discard.isEmpty()){
        Card card = new Card(2, Card.Suit.Diamonds);
        card.isReversed=true;
        card.setPreferredSize(new Dimension(40, 60));
        card.setLocation(150, 150);
        middle.add(card);
        
        }

        // tempIcon = new ImageIcon("stand.png");
        standButton = new JButton("Stand");
        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    game.stand();
                } catch (InterruptedException e1) {
                    
                    e1.printStackTrace();
                }
                try {
                    update();
                } catch (InterruptedException e1) {
                    
                    e1.printStackTrace();
                }
            }
        });
        
        

        // tempIcon = new ImageIcon("/workspace/resources/hit.png");
        hitButton = new JButton("Hit");
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    game.playerHit();
                    update();
                } catch (InterruptedException e1) {
                    
                    e1.printStackTrace();
                }
            }
            
        });
        hitButton.setSize(100, 50);
        hitButton.setLocation(100, 25);
        middle.add(hitButton);
        standButton.setSize(100, 50);
        standButton.setLocation(600, 25);
        middle.add(standButton);

        if (!game.discard.isEmpty()){
        Card disCardGeddit = new Card(2, Card.Suit.Diamonds);
        disCardGeddit.isReversed=true;
        disCardGeddit.setSize(80, 120);
        disCardGeddit.setLocation(475, 0);
        middle.add(disCardGeddit);
        }

        if (!game.deck.isEmpty()){
        Card drawCard = new Card(2, Card.Suit.Diamonds);
        drawCard.isReversed=true;
        drawCard.setSize(80, 120);
        drawCard.setLocation(225, 0);
        middle.add(drawCard);
        }

        if(game.playerTurn){
            standButton.setEnabled(true);
            hitButton.setEnabled(true);
        }
        else{
            standButton.setEnabled(false);
            hitButton.setEnabled(false);
        }
        
        if(!dealer.cards.isEmpty()){
            for (Card card : dealer.cards){
                card.setPreferredSize(new Dimension(80, 120));
                top.add(card);
                if (!paused&& game.gameState != Blackjack.State.Playing){
                    paused=true;
                }
            }
            
        }
        if(!game.cards.isEmpty()){
            for (Card card : game.cards){
                card.setPreferredSize(new Dimension(80, 120));
                bottom.add(card);
            }
        }

      
        JLabel endState = new JLabel("Playing", JLabel.CENTER);
        endState.setLocation(380,50);
        // endState.setPreferredSize(new Dimension(60,30));
        endState.setSize(100,50);
        
        middle.add(endState);
        
        if (game.gameState==Blackjack.State.Tie){
            endState.setText("You Tied");
            
            game.gameState=Blackjack.State.Playing;
        }
        
        else if (game.gameState==Blackjack.State.Lose){
            endState.setText("You Lose");
            
            game.gameState=Blackjack.State.Playing;
        }

        else if (game.gameState == Blackjack.State.Win){
            endState.setText("You Win");

            game.gameState=Blackjack.State.Playing;
        }
    

        

        this.revalidate();

        this.repaint();
        if (game.gameState == Blackjack.State.Playing && paused){
            Thread.sleep(3000);
            endState.setText("Playing");
            game.resetGame();
        }
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
        //

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
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}