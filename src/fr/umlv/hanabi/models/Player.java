package fr.umlv.hanabi.models;

import fr.umlv.hanabi.controllers.BoardController;
import fr.umlv.hanabi.models.Board;
import fr.umlv.hanabi.models.Card;
import fr.umlv.hanabi.models.Deck;

import java.util.Scanner;

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
    public Player(int number, int nbCards)
    {
        this.number = number;
        this.numberOfCards = nbCards;
        this.hand = new Deck("hand");
    }
    
    /**
     * @return maximum number of cards in the player's hand
     */
    public int getNumberOfCards()
    {
        return this.numberOfCards;
    }

    public int getNumber()
    {
        return this.number;
    }

    public Deck getHand()
    {
        return hand;
    }
}
