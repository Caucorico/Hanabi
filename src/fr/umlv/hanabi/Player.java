package fr.umlv.hanabi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Player
{
    /**
     * The player number
     */
    private int number;

    /**
     * The board of the player.
     */
    private Board board;

    /**
     * The player's hand
     */
    private Deck hand;

    /**
     * The hand max cards number
     */
    private int numberOfCards;

    /**
     * Player object constructor.
     */
    public Player(int number, int nbCards, Board board)
    {
        this.number = number;
        this.board = board;
        this.numberOfCards = nbCards;
        this.hand = new Deck("hand");
    }

    /**
     * Function call when it is the player turn.
     */
    public void turn()
    {
    	System.out.println("What do you want to do ?\n"
    				+ "1) Play a card.\n2) Discard a card.\n3) Give an information.");
    	
    	int choice = 0;
    	while ( choice <= 0 || choice >= 4 ) {
    		choice = new Scanner(System.in).nextInt();
    	}
    	switch ( choice ) {
    		case 1 : playCard(); break;
    		case 2 : this.board.addBlueToken(); discardCard(); break;
    		case 3 : 
    			if ( this.board.takeBlueToken() ) {
    				giveInfo(); 
    			}
    			else {
    				System.out.println("There is no more blue token.");
    				turn();
    			}
    			break;
    	}
    }


    /**
     * Function call when the player decide to play a card.
     */
    public void playCard()
    {
    	int choice = 0;
    	
    	System.out.println("Which card from your hand do you want to play?\n"
    			+ "Select a number between 1 and " + hand.getDeckSize());    	
    	
    	while ( choice <= 0 || choice > hand.getDeckSize() ) {
    		choice = new Scanner(System.in).nextInt();
    	}
    	
    	Card card = this.hand.getCard(choice-1);
    	this.board.playCard(card);
    	
    	if ( ! this.board.isMainDeckEmpty() ) {
    		this.giveCard(this.board.drawCard());
        }
    }

    /**
     * Function call when the player decide to discard a card.
     */
    public void discardCard()
    {
    	int choice = 0;
    	
    	System.out.println("Which card from your hand do you want to discard?\n"
    			+ "Select a number between 1 and " + hand.getDeckSize());
    	
    	while ( choice <= 0 || choice > hand.getDeckSize() ) {
    		choice = new Scanner(System.in).nextInt();
    	}
    	
    	Card card = this.hand.getCard(choice-1);  
    	this.board.discardCard(card);
    	
    	if ( ! this.board.isMainDeckEmpty() ) {
    		this.giveCard(this.board.drawCard());
    	}
    }
    
    /**
	 * Get the hand deck of the player
     * @return the hand deck of the player
     */
    public Deck getHand() {
    	return this.hand;
    }
    
    /**
     * Display the player's hand deck
     * @param self false if it is the player's, true otherwise
     */
    public void showHand(boolean self)  {
    	this.hand.display(self);
    }
    
    /**
     * Scan choices of a player when giving an information.
     * @param type 1 if the information is about a color, 2 if it's about a number
     * @param player Id number of the player receiving the information
     */
    private void scanInfo(int type, int player) {
    	int size, choice = 0;
    	ArrayList<String> colors = null;
    	ArrayList<Integer> numbers = null;
    	if ( type == 1 ) {
    		System.out.println("Which color ?");
    		colors = this.board.getPlayer(player).getHand().getColors();
    		size = colors.size();
    		for ( int i = 0 ; i < size ; i++ ) {
        		System.out.println((i+1) + ")" + colors.get(i));	
        	}
    	}
    	else {
    		System.out.println("Which number ?");
    		numbers = this.board.getPlayer(player).getHand().getNumbers();
    		size = numbers.size();
    		for ( int i = 0 ; i < size ; i++ ) {
        		System.out.println((i+1) + ")" + numbers.get(i));	
        	}
    	}
    	while ( choice <= 0 || choice > size ) {
    		choice = new Scanner(System.in).nextInt();
    	}
    	if ( type == 1 ) {
    		this.board.getPlayer(player).getHand().updateVisibility(colors.get(choice - 1));
    	}
    	else {
    		this.board.getPlayer(player).getHand().updateVisibility(numbers.get(choice - 1));    		
    	}
    }
    /**
     * Function call when the player decide to give an information to an other player.
     */
    public void giveInfo()
    {
    	int player = -1, action = 0;
    	
    	System.out.println("To which player do you want to give an information ?");
    	while ( player == this.number || player < 0 || player >= this.board.getNbPlayers() ) {
    		player = new Scanner(System.in).nextInt() - 1;
    	}
    	
    	System.out.println("What kind of info do you want to give to P" + (player+1) + ": ");
    	System.out.println("1) Color\n2)Number");
    	
    	while ( action <= 0 || action > 2 ) {
    		action = new Scanner(System.in).nextInt();
    	}
    	scanInfo(action, player);
    }

    /**
     * Put a card in the player's hand
     * @param the card to add
     */
    public  void giveCard(Card card)
    {
        this.hand.addCard(card);
    }
    
    /**
     * @return maximum number of cards in the player's hand
     */
    public int getNumberOfCards()
    {
        return this.numberOfCards;
    }
}
