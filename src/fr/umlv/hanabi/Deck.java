package fr.umlv.hanabi;

import java.util.ArrayList;
import java.util.Iterator;

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


    /**
     * Add a card to the deck.
     * @param card The card to add to the deck.
     */
    public void addCard(Card card)
    {

    }

    /**
     * Return a card of the deck.
     * @param id The rank of the card in the deck.
     * @return The card.
     */
    public Card getCard(int id)
    {
        return null;
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
