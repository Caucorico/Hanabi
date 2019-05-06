package fr.umlv.hanabi;

public class Card
{
    /**
     * The color of the card.
     */
    private String color;

    /**
     * The number of the card.
     */
    private int number;

    /**
     * Return true if the player with this card has the information about the color.
     */
    private boolean colorRevealed;

    /**
     * Return true if the player with this card has the information about the number.
     */
    private boolean numberRevealed;


    /**
     * Display the card.
     * If back is false, the front of the card is display.
     * If back is true, the back of the card is display with the information.
     *
     * @param back draw back or not
     */
    public void display(boolean back)
    {

    }
}
