package fr.umlv.hanabi.models;

/**
 *
 */
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

    public Card(String color, int number)
    {
        this.color = color;
        this.number = number;
    }


    /**
     * Display the card.
     * If back is false, the front of the card is display.
     * If back is true, the back of the card is display with the information.
     *
     * @param back draw back or not
     */
    public String display(boolean back)
    {
    	return this.toString();
    }
    
    /*
     * Return the color of the card
     */
    public String getColor() {
    	return color;
    }
    
    /*
     * Return the number of the card
     */
    public int getNumber() {
    	return number;
    }
    
    public String toString() {
    	return "| " + this.number + " " + this.color.charAt(0) + " |-";
    }
}
