package fr.umlv.hanabi.controllers;

import fr.umlv.hanabi.models.Board;
import fr.umlv.hanabi.models.Card;
import fr.umlv.hanabi.models.Player;

import java.util.Scanner;

public class PlayerController
{

    /**
     * Function call when it is the player turn.
     */
    public static void turn(Board board, Player player)
    {
        System.out.println("Player " +
                (player.getNumber()+1) + ", it's your turn!\n What do you want to do ?\n"
                + "1) Play a card.\n2) Discard a card.\n3) Give an information."
                + "\nYour cards : ");
        player.getHand().display(false);

        int choice = 0;
        while ( choice <= 0 || choice >= 4 ) {
            choice = new Scanner(System.in).nextInt();
            if ( choice == 3 ) {
                System.out.println("This option is not implemented yet, sorry!");
                choice = 0;
            }
        }
        switch ( choice ) {
            case 1 : PlayerController.playCard( board, player ); break;
            /* To implement in phase 2 : dealing with blue tokens before calling discardCard()*/
            case 2 : PlayerController.discardCard( board, player); break;
            case 3 : PlayerController.giveInfo(); break;
        }
    }

    /**
     * Function call when the player decide to play a card.
     */
    public static void playCard(Board board, Player player)
    {
        int choice = 0;

        System.out.println("Which card from your hand do you want to play?\n"
                + "Select a number between 1 and " + player.getHand().getDeckSize());

        while ( choice <= 0 || choice > player.getHand().getDeckSize() ) {
            choice = new Scanner(System.in).nextInt();
        }

        Card card = player.getHand().getCard(choice-1);
        BoardController.playCard(board, card);

        if ( !board.isMainDeckEmpty() ) {
            PlayerController.giveCard(player, BoardController.drawCard(board));
        }
    }

    /**
     * Function call when the player decide to discard a card.
     */
    public static void discardCard(Board board, Player player)
    {
        int choice = 0;

        System.out.println("Which card from your hand do you want to discard?\n"
                + "Select a number between 1 and " + player.getHand().getDeckSize());

        while ( choice <= 0 || choice > player.getHand().getDeckSize() ) {
            choice = new Scanner(System.in).nextInt();
        }

        Card card = player.getHand().getCard(choice-1);
        BoardController.discardCard(board, card);

        if ( !board.isMainDeckEmpty() ) {
            PlayerController.giveCard(player, BoardController.drawCard(board));
        }
    }

    /**
     * Function call when the player decide to give an information to an other player.
     */
    public static void giveInfo()
    {

    }

    /**
     * Put a card in the player's hand
     * @param card the card to add
     */
    public static void giveCard(Player player, Card card)
    {
        player.getHand().addCard(card);
    }
}
