package fr.umlv.hanabi.controllers;

import fr.umlv.hanabi.models.Card;
import fr.umlv.hanabi.models.Player;

import java.util.Scanner;

public class PlayerController
{
    private Player player;

    private BoardController boardController;

    public PlayerController(BoardController boardController)
    {

        this.boardController = boardController;
    }

    /**
     * Function call when it is the player turn.
     */
    public void turn()
    {
        System.out.println("Player " +
                (this.player.getNumber()+1) + ", it's your turn!\n What do you want to do ?\n"
                + "1) Play a card.\n2) Discard a card.\n3) Give an information."
                + "\nYour cards : ");
        this.player.getHand().display(false);

        int choice = 0;
        while ( choice <= 0 || choice >= 4 ) {
            choice = new Scanner(System.in).nextInt();
            if ( choice == 3 ) {
                System.out.println("This option is not implemented yet, sorry!");
                choice = 0;
            }
        }
        switch ( choice ) {
            case 1 : this.playCard(); break;
            /* To implement in phase 2 : dealing with blue tokens before calling discardCard()*/
            case 2 : this.discardCard(); break;
            case 3 : this.giveInfo(); break;
        }
    }

    /**
     * Function call when the player decide to play a card.
     */
    public void playCard()
    {
        int choice = 0;

        System.out.println("Which card from your hand do you want to play?\n"
                + "Select a number between 1 and " + this.player.getHand().getDeckSize());

        while ( choice <= 0 || choice > this.player.getHand().getDeckSize() ) {
            choice = new Scanner(System.in).nextInt();
        }

        Card card = this.player.getHand().getCard(choice-1);
        this.boardController.playCard(card);

        if ( ! this.boardController.isMainDeckEmpty() ) {
            this.giveCard(this.boardController.drawCard());
        }
    }

    /**
     * Function call when the player decide to discard a card.
     */
    public void discardCard()
    {
        int choice = 0;

        System.out.println("Which card from your hand do you want to discard?\n"
                + "Select a number between 1 and " + this.player.getHand().getDeckSize());

        while ( choice <= 0 || choice > this.player.getHand().getDeckSize() ) {
            choice = new Scanner(System.in).nextInt();
        }

        Card card = this.player.getHand().getCard(choice-1);
        this.boardController.discardCard(card);

        if ( ! this.boardController.isMainDeckEmpty() ) {
            this.giveCard(this.boardController.drawCard());
        }
    }

    /**
     * Function call when the player decide to give an information to an other player.
     */
    public void giveInfo()
    {

    }

    /**
     * Put a card in the player's hand
     * @param card the card to add
     */
    public  void giveCard(Card card)
    {
        this.player.getHand().addCard(card);
    }
}
