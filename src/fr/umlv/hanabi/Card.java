package fr.umlv.hanabi;

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
     * If back is true, the back of the card is display with the information(s).
     *
     * @param back draw back or not
     */
    public String display(boolean back)
    {
    	if ( back ) {
    		if ( this.colorRevealed && this.numberRevealed ) {
    			return this.toString();
    		}
    		else if ( this.colorRevealed ) {
    			return "|  " + this.color.charAt(0) + "  |-";
    		}
    		else if ( this.numberRevealed ) {
    			return "|  " + this.number  + "  |-";
    		}
    		else {
    			return "|  X  |-";
    		}
    	}
    	return this.toString();
    	
    }
    
    /**
     * @return the color of the card
     */
    public String getColor() {
    	return color;
    }
    
    /**
     * @return the number of the card
     */
    public int getNumber() {
    	return number;
    }
    /**
     * Set the color of the card as revealed
     * @param colorRevealed true if the color has to be revealed, false otherwise
     * @return the card in its new state.
     */
    public Card setColorRevealed(boolean colorRevealed) {
    	this.colorRevealed = colorRevealed;
    	return this;
    }
    /**
     * Set the number of the card as revealed
     * @param colorRevealed true if the number has to be revealed, false otherwise
     * @return the card in its new state.
     */
    public Card setNumberRevealed(boolean numberRevealed) {
    	this.numberRevealed = numberRevealed;
    	return this;
    }
    /**
     * @returns a stringified version of a card
     */
    @Override
    public String toString() {
    	return "| " + this.number + " " + this.color.charAt(0) + " |-";
    }
}
