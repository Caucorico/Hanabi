package fr.umlv.hanabi;

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

    }


    /**
     * Function call when the player decide to play a card.
     */
    public void playCard()
    {

    }


    /**
     * Function call when the player decide to discard a card.
     */
    public void discardCard()
    {

    }


    /**
     * Function call when the player decide to give an information to an other player.
     */
    public void giveInfo()
    {

    }


    /**
     * Receieve a card, put the card in the player's hand
     */
    public  void giveCard(Card card)
    {
        this.hand.addCard(card);
    }

    public int getNumberOfCards()
    {
        return this.numberOfCards;
    }
}
