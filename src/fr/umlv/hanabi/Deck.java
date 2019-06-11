package fr.umlv.hanabi;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 */
public class Deck
{
    /**
     * The cards of this deck.
     */
    private ArrayList<Card> cards;

    /*
     * The type of the deck (hand, mainDeck ,discard ,etc).

    private String type;*/

    /**
     * Deck constructor.
     */
    public Deck()
    {
        /*this.type = type;*/
        this.cards = new ArrayList<>();
    }

    /**
     * Shuffle the deck.
     */
    public void shuffle()
    {
        Collections.shuffle(this.cards);
    }


    /**
     * Add a card to the deck.
     * @param card The card to add to the deck.
     */
    public void addCard(Card card)
    {
        this.cards.add(card);
    }

    /**
     * Return a card of the deck.
     * @param id The rank of the card in the deck.
     * @return The card.
     */
    public Card getCard(int id)
    {
        return this.cards.remove(id);
    }


    /**
     * Display the cards of the deck.
     * @param back If back is true, display the back of the cards.
     */
    public void display(boolean back)
    {
    	int size = getDeckSize();
    	StringBuilder s = new StringBuilder();
    	for ( int i = 0 ; i < size ; ++i ) {
    		s.append(this.cards.get(i).display(back));
    	}
    	System.out.println(s);
    }
    
    /**
     * Get the list of colors existing in the deck.
     * @return An array list of strings representing colors.
     */
    public ArrayList<String> getColors() {
    	ArrayList<String> colors = new ArrayList<>();
    	for ( int i = 0 ; i < this.cards.size() ; ++i ) {
    		if ( ! colors.contains(this.cards.get(i).getColor()) ) {
    			colors.add(this.cards.get(i).getColor());
    		}
    	}
    	return colors;
    }
    
    /**
     * Get the cards's numbers (in [1, 5]).
     * @return An array list of integers.
     */
    public ArrayList<Integer> getNumbers() {
    	ArrayList<Integer> numbers = new ArrayList<>();
    	for ( int i = 0 ; i < this.cards.size() ; ++i ) {
    		if ( ! numbers.contains(this.cards.get(i).getNumber()) ) {
    			numbers.add(this.cards.get(i).getNumber());
    		}
    	}
    	return numbers;
    }
    /**
     * Changes the visibility of the cards color in the deck.
     * It happens when a player give an information to another player.
     * When displayed, cards color will be revealed.
     * @param color The color to reveal in the deck.
     */
    public void updateVisibility(String color) {
    	for ( Card c : this.cards ) {
    		if ( c.getColor().equals(color)) {
    			c.setColorRevealed(true);
    		}
    	}
    }
    /**
     * Changes the visibility of the cards number in the deck.
     * It happens when a player give an information to another player.
     * When displayed, cards number will be revealed.
     * @param number The number to reveal in the deck.
     */
    public void updateVisibility(int number) {
    	for ( Card c : this.cards ) {
    		if ( c.getNumber() == number ) {
    			c.setNumberRevealed(true);
    		}
    	}
    }
    /**
     * Return the number of cards in the Deck.
     *
     * @return the number of cards in the deck.
     */
    public int getDeckSize() {
    	return this.cards.size();
    }
    /**
     * Checks if a deck is empty.
     * @return true if the deck is empty, false otherwise.
     */
    public boolean isEmpty() {
    	return this.cards.size() == 0;
    }
}
