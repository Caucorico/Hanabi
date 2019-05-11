package fr.umlv.hanabi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Board
{
    /**
     * The player turn (2 if it is the turn of player 2).
     */
    private int turn;

    /**
     * Max number of played red token.
     */
    private int redTokenMax;

    /**
     * Number of red token played.
     */
    private int redTokenPlayed;

    /**
     * Max number of played blue token.
     */
    private int blueTokenMax;

    /**
     * Number of blue token played.
     */
    private int blueTokenPlayed;

    /**
     * Array list of the players.
     */
    private ArrayList<Player> players;

    /**
     * The main deck.
     */
    private Deck mainDeck;

    /* ########  find better structure ? ######## */

    /**
     * The blue fireworks.
     */
    //private Deck blueFireworks;

    /**
     * The red fireworks.
     */
    //private Deck redFireworks;

    /**
     * The yellow fireworks.
     */
    //private Deck yellowFireworks;

    /**
     * The white fireworks.
     */
    //private Deck whiteFireworks;

    /**
     * The green fireworks.
     */
    //private Deck greenFireworks;

    // Suggestion ?
    private Map<String, Deck> fireworks;
    /* ########################################### */

    /**
     * The discard.
     */
    private Deck discard;

    /**
     * Board constructor.
     * @param numberPlayer Number of players for this game.
     */
    public Board(int numberPlayer)
    {
        int numberCards;
        this.turn = 0;
        this.redTokenMax = 3;
        this.redTokenPlayed = 0;
        this.blueTokenMax = 8;
        this.blueTokenPlayed = 0;
        this.players = new ArrayList<>();

        if ( numberPlayer < 2 )
            throw new IllegalArgumentException("The game need at least 2 person to begin");
        else if ( numberPlayer < 4 )
            numberCards = 5;
        else if ( numberPlayer < 6 )
            numberCards = 4;
        else
            throw new IllegalArgumentException("The game can't be played with more than 5 players");

        for ( int i = 0 ; i < numberPlayer ;i++ )
        {
            this.players.add(new Player(i, numberCards, this));
        }
    }

    /**
     * Create the main deck
     */
    private void createDeck()
    {
        String[] colors = { "red", "blue", "yellow", "green", "white" };
        this.mainDeck = new Deck("mainDeck");

        for (String color : colors )
        {
            for ( int i = 0 ; i < 5 ; i++ )
            {
                this.mainDeck.addCard(new Card(color, i+1));
            }
        }
    }

    /**
     * Init the fireworks stack of cards.
     */
    private void initFireworks()
    {
    	this.fireworks = new HashMap<>(5);
    	this.fireworks.put("red", new Deck("fireworks"));
    	this.fireworks.put("blue", new Deck("fireworks"));
    	this.fireworks.put("yellow", new Deck("fireworks"));
    	this.fireworks.put("green", new Deck("fireworks"));
    	this.fireworks.put("white", new Deck("fireworks"));
    }


    /**
     * Shuffle the main deck.
     */
    private void shuffleDeck()
    {
        this.mainDeck.shuffle();
    }

    /**
     * Distribute the cards to the players at the beginning of the game.
     */
    private void initDistribute()
    {
        for ( Player player : this.players )
        {
            for ( int i = 0 ; i < player.getNumberOfCards() ; i++ )
            {
                player.giveCard(this.mainDeck.getCard(0));
            }
        }
    }

    /**
     * Start of the game.
     */
    public void start()
    {
        this.createDeck();
        this.shuffleDeck();
        this.initDistribute();
        this.initFireworks();
        this.discard = new Deck("discard");
    }

    /**
     * run game.
     */
    public void loop()
    {

    }

    /**
     * End of the game.
     */
    public void end()
    {

    }

    /**
     * Check if the game is terminate.
     * @return game terminate or not.
     */
    public boolean checkVictory()
    {
        return false;
    }

    /**
     * Play a card on the board.
     * @param color Color of the stack. //Le joueur n'a pas a choisir le feu d'artifice
     * @param card The card.
     */
    public void playCard(Card card)
    {
    	String color = card.getColor();
    	int size = this.fireworks.get(color).getDeckSize();
    	if ( size + 1 == card.getNumber() ) {
    		this.fireworks.get(color).addCard(card);
    	}
    	else {
    		this.discardCard(card);
    		this.redTokenPlayed++;
    	}
    }

    /**
     * Put a card on the board discard.
     * @param card The card to discard
     */
    public void discardCard( Card card )
    {
    	this.discard.addCard(card);
    }


    /**
     * Give an information to an other player
     * @param action color or number
     * @param possibility which cards
     * @return return false if the action is not allowed
     */
    public boolean giveInfo( int action, int possibility )
    {
        return true;
    }

    public static void main(String[] args) {
		Board b = new Board(3);
		b.start();
		for ( int i = 0 ; i < 3 ; ++i ) {
			b.players.get(b.turn).turn();
			b.turn++;
		}
	}
}
