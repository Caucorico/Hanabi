package fr.umlv.hanabi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 */
public class Deck implements Iterable
{
    /**
     * The cards of this deck.
     */
    private ArrayList<Card> cards;

    /**
     * The type of the deck (hand, mainDeck ,discard ,etc).
     */
    private String type;

    public Deck(String type)
    {
        this.type = type;
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

    }

    @Override
    public Iterator iterator()
    {
        return null;
    }
}
