package fr.umlv.hanabi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Board
{
    /**
     * The player turn (starts at 0).
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

    /*
     * HashMap of Decks representing fireworks by color
     */
    private Map<String, Deck> fireworks;

    /**
     * The discard.
     */
    private Deck discard;

    /**
     * The lasts players who can play
     */
    private int lastTurn;

    /**
     * Board constructor.
     * @param numberPlayer Number of players for this game.
     */
    public Board(int numberPlayer)
    {
        int numberCards;
        this.turn = 0;
        this.lastTurn = -1;
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
    public void createDeck()
    {
        String[] colors = { "red", "blue", "yellow", "green", "white" };
        this.mainDeck = new Deck("mainDeck");

        for (String color : colors )
        {
        	this.mainDeck.addCard(new Card(color, 1));
        	this.mainDeck.addCard(new Card(color, 1));
        	this.mainDeck.addCard(new Card(color, 1));
            this.mainDeck.addCard(new Card(color, 2));
            this.mainDeck.addCard(new Card(color, 2));
            this.mainDeck.addCard(new Card(color, 3));
            this.mainDeck.addCard(new Card(color, 3));
            this.mainDeck.addCard(new Card(color, 4));
            this.mainDeck.addCard(new Card(color, 4));
            this.mainDeck.addCard(new Card(color, 5));
        }
    }

    /**
     * Init the fireworks stack of cards.
     */
    public void initFireworks()
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
    public void shuffleDeck()
    {
        this.mainDeck.shuffle();
    }

    /**
     * Distribute the cards to the players at the beginning of the game.
     */
    public void initDistribute()
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
     * Checks if each firework has been completed.
     * @return true if every firework has been completed, false otherwise
     */
    private boolean checkFireworks() {
    	for ( Map.Entry<String, Deck> entry : fireworks.entrySet() ) {
    		if ( entry.getValue().getDeckSize() != 5 ) {
    			return false;
    		}
    	}
    	return true;
    }

    /**
     * Check if the game is terminate.
     * @return game terminate or not.
     */
    public boolean checkVictory()
    {
        /* End of the game when too many errors has been played */
        if ( redTokenPlayed == redTokenMax ) {
        	return true;
        }
        /* End of the game when the last turn is over */
        if ( this.lastTurn == 0 ) {
            return true;
        }

        /* End of the game when all the fireworks are completed */
        if ( checkFireworks() ) {
        	return true;
        }

        return false;
    }

    
    /**
     * Check if the main deck is empty
     * @return true if the deck is empty, false otherwise
     */
    public boolean isMainDeckEmpty() {
    	return mainDeck.isEmpty();
    }

    public Deck getDiscard() {
        return discard;
    }

    public void setDiscard(Deck discard) {
        this.discard = discard;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public int getLastTurn() {
        return lastTurn;
    }

    public void setLastTurn(int lastTurn) {
        this.lastTurn = lastTurn;
    }

    public Map<String, Deck> getFireworks() {
        return fireworks;
    }

    public int getRedTokenPlayed() {
        return redTokenPlayed;
    }

    public void setRedTokenPlayed(int redTokenPlayed) {
        this.redTokenPlayed = redTokenPlayed;
    }
}
