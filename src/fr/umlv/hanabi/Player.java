package fr.umlv.hanabi;

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
    	System.out.println("Player " + 
    		(this.number+1) + ", it's your turn!\n What do you want to do ?\n"
    				+ "1) Play a card.\n2) Discard a card.\n3) Give an information."
    				+ "\nYour cards : ");
    	this.hand.display(false);
    	
    	int choice = 0;
    	while ( choice <= 0 || choice >= 4 ) {
    		choice = new Scanner(System.in).nextInt();
    		if ( choice == 3 ) {
    			System.out.println("This option is not implemented yet, sorry!");
    			choice = 0;
    		}
    	}
    	switch ( choice ) {
    		case 1 : playCard(); break;
    		/* To implement in phase 2 : dealing with blue tokens before calling discardCard()*/
    		case 2 : discardCard(); break;
    		case 3 : giveInfo(); break;
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
     * Function call when the player decide to give an information to an other player.
     */
    public void giveInfo()
    {

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
