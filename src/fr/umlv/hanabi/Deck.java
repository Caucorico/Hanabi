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

    /**
     * The type of the deck (hand, mainDeck ,discard ,etc).
     
    private String type;*/

    public Deck(String type)
    {
        /*this.type = type;*/
        this.cards = new ArrayList<>();
    }

    public Deck(String type, ArrayList<Card> cards)
    {
        this(type);
        this.cards = cards;
    }

    /**
     * Shuffle the deck
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
    
    public ArrayList<String> getColors() {
    	ArrayList<String> colors = new ArrayList<>();
    	for ( int i = 0 ; i < this.cards.size() ; ++i ) {
    		if ( ! colors.contains(this.cards.get(i).getColor()) ) {
    			colors.add(this.cards.get(i).getColor());
    		}
    	}
    	return colors;
    }
    
    public ArrayList<Integer> getNumbers() {
    	ArrayList<Integer> numbers = new ArrayList<>();
    	for ( int i = 0 ; i < this.cards.size() ; ++i ) {
    		if ( ! numbers.contains(this.cards.get(i).getNumber()) ) {
    			numbers.add(this.cards.get(i).getNumber());
    		}
    	}
    	return numbers;
    }

    public void updateVisibility(String color) {
    	for ( Card c : this.cards ) {
    		if ( c.getColor().equals(color)) {
    			c.setColorRevealed(true);
    		}
    	}
    }
    
    public void updateVisibility(int number) {
    	for ( Card c : this.cards ) {
    		if ( c.getNumber() == number ) {
    			c.setNumberRevealed(true);
    		}
    	}
    }
    /**
     * Return the number of cards in the deck
     */
    public int getDeckSize() {
    	return this.cards.size();
    }
    
    public boolean isEmpty() {
    	return this.cards.size() == 0;
    }
}
