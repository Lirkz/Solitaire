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


    public GUI(Blackjack game) {
        this.game = game;
        dealer = new Dealer(this.game);
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
        GridBagConstraints c = new GridBagConstraints();
        JPanel back = new JPanel();
        c.ipadx = 800;
        c.ipady = 400;
 

        c.gridx = 0;
        c.gridy = 0;
        back.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        back.setBackground(new Color(0, 0, 0, 0));
        add(back, c);
 

        JPanel top = new JPanel();
        c.anchor = GridBagConstraints.PAGE_START;
        c.ipadx = 800;
        c.ipady = 110;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 3;
        top.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        top.setBackground(new Color(0, 0, 0, 0));
        add(top, c);
 

        JPanel bottom = new JPanel();
        c.anchor = GridBagConstraints.PAGE_END;
        c.ipadx = 800;
        c.ipady = 110;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 3;
        bottom.setBorder(BorderFactory.createLineBorder(Color.red, 5));
        bottom.setBackground(new Color(0, 0, 0, 0));
        add(bottom, c);
 

        JPanel middle = new JPanel();
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 800;
        c.ipady = 120;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 3;
        middle.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
        //middle.setBackground(new Color(0, 0, 0, 0));
        middle.setLayout(new FlowLayout());
        middle.setOpaque(false);
        add(middle, c);

        //I want a way to show if the buttons are allowed to use, if its the player's turn. idk if i should use an if statement

		Icon tempIcon = new ImageIcon("stand.png");
		JButton standButton = new JButton(tempIcon);
		standButton.addActionListener(new ActionListener(){
			@Override
				public void actionPerformed(ActionEvent e) {
					//if(/*player turn*/){
                        /* switch to dealer's turn */
                        //standButton.setVisible(false);
                    //}
				}
		});
		
		
 

        /*******
         * This is just a test to make sure images are being read correctly on your
         * machine. Please replace
         * once you have confirmed that the card shows up properly. The code below
         * should allow you to play the solitare
         * game once it's fully created.
         */
        // Card card = new Card(2, Card.Suit.Diamonds);
        // System.out.println(card);
        // this.add(card);
 

        this.setVisible(true);
    }
 

//retruns a j layered pane with the cards inside it
 

    public JLayeredPane drawPile(Stack<Card> stackIn) {
 

        Object cards[];
       
        cards = stackIn.toArray();
        ArrayList<Card> pile = new ArrayList<>();
        int offset = 50;
        JLayeredPane pane = new JLayeredPane();
        pane.setLayout(null);
        for(int i = 0; i < cards.length; i++){
            System.out.println("here");
        Card temp = (Card)cards[i];
        //temp.setSize(20,20);
        temp.setBounds(0, offset*i, 20, 20);
        pane.add(temp);
 

        }
        pane.setPreferredSize(new Dimension(200, 80));
        pane.setBorder(BorderFactory.createMatteBorder(2, 2, 2,2, Color.black));
        return pane;
        // please note we convert this stack to an array so that we can iterate through
                                    // it backwards while drawing. You’ll need to cast each element inside cards to
                                    // a <Card> in order to use the methods to adjust their position
 

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
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}