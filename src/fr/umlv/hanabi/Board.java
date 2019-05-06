package fr.umlv.hanabi;

import java.util.ArrayList;

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
    private Deck blueFireworks;

    /**
     * The red fireworks.
     */
    private Deck redFireworks;

    /**
     * The yellow fireworks.
     */
    private Deck yellowFireworks;

    /**
     * The white fireworks.
     */
    private Deck whiteFireworks;

    /**
     * The green fireworks.
     */
    private Deck greenFireworks;

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

    }

    /**
     * Create the main deck
     */
    public void createDeck()
    {

    }

    /**
     * Shuffle the main deck
     */
    public void shuffleDeck()
    {

    }

    /**
     * Start of the game
     */
    public void start()
    {

    }

    /**
     * run game
     */
    public void loop()
    {

    }

    /**
     * End of the game
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
     * @param color Color of the stack.
     * @param card The card.
     */
    public void playCard(String color, Card card )
    {

    }

    /**
     * Put a card on the board discard.
     * @param card The card to discard
     */
    public void discardCard( Card card )
    {

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

}
