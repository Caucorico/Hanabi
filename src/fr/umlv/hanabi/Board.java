package fr.umlv.hanabi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
    private void createDeck()
    {
        String[] colors = { "red", "blue", "yellow", "green", "white" };
        this.mainDeck = new Deck();

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
    private void initFireworks()
    {
    	this.fireworks = new HashMap<>(5);
    	this.fireworks.put("red", new Deck());
    	this.fireworks.put("blue", new Deck());
    	this.fireworks.put("yellow", new Deck());
    	this.fireworks.put("green", new Deck());
    	this.fireworks.put("white", new Deck());
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
        this.discard = new Deck();
    }

    /**
     * run game.
     */
    public void loop()
    {
    	int nbPlayers = this.players.size();
    	while ( ! checkVictory() ) {
    	    for(int i = 0 ; i < 5000 ; i++ ) System.out.println();
    		System.out.println("Player " + (turn + 1) + " it's your turn ! Press enter to play");
    		new Scanner(System.in).nextLine();
    		this.display();
    		
    		this.players.get(turn).turn();

    		turn = (turn + 1) % nbPlayers;

            if ( mainDeck.isEmpty() && this.lastTurn == -1 ) {
                this.lastTurn = this.players.size();
            }
            else if ( this.mainDeck.isEmpty() && this.lastTurn != -1 )
            {
                this.lastTurn--;
            }
    	}
    }

    /**
     * End of the game.
     */
    public void end()
    {
    	int score = 0;
    	for ( Map.Entry<String, Deck> entry : fireworks.entrySet() ) {
    		score += entry.getValue().getDeckSize();
    	}
    	System.out.println("Your score : " + score);
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
     * Play a card on the board.
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

    /*
     * Draw a card from the main deck
     */
    public Card drawCard() {
    	return this.mainDeck.getCard(0);
    }
    
    /**
     * Check if the main deck is empty
     * @return true if the deck is empty, false otherwise
     */
    public boolean isMainDeckEmpty() {
    	return mainDeck.isEmpty();
    }
    
    /**
     * Displays a visualization of the board in its current state
     */
    public void display() {
    	System.out.println("-------------------------Board-------------------------");
    	System.out.println(this.redTokenPlayed + "/" + this.redTokenMax + " red tokens played");
    	System.out.println(this.blueTokenPlayed + "/" + this.blueTokenMax  + " blue tokens played");
		System.out.println(mainDeck.getDeckSize() + " cards is the main deck");
		System.out.println(discard.getDeckSize() + " cards in the discard");
		if ( discard.getDeckSize() > 0 ) {
			System.out.println("Discard's top card : " + this.discard.getCard(this.discard.getDeckSize()-1).toString());
		}
		System.out.println("Other players's cards : ");
		for ( int i = 0 ; i < this.players.size() ; ++i ) {
			if ( i == turn ) {
				System.out.print("My cards : ");
				this.players.get(i).showHand(true);
			}
			else {
				System.out.print("P" + (i+1) + " cards : ");
				this.players.get(i).showHand(false);
			}
		}
		System.out.println("Fireworks : ");
		this.fireworks.forEach((key, deck) -> {
			System.out.printf("%7s : ", key);
			deck.display(false);
		});
		System.out.println("--------------------------------------------------------");
    }
    /**
     * @return the number of player in the board
     */
    public int getNbPlayers() {
    	return this.players.size();
    }
    /**
     * When a player takes a blue token from the board
     * @return false is there is no more blue tocken, true otherwise
     */
    public boolean takeBlueToken() {
    	if ( blueTokenPlayed < blueTokenMax ) {
    		blueTokenPlayed++;
    		return true;
    	}
    	return false;
    }
    /**
     * When a player give a blue tocken to the board
     */
    public void addBlueToken() {
    	if ( blueTokenPlayed > 0 ) {
    		blueTokenPlayed--;
    	}
    }
    /**
     * Get the player at the index given the array of players
     * @param index index of the player
     * @return the player found at the index
     */
    public Player getPlayer(int index) {
    	return this.players.get(index);
    }
}
