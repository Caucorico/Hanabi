package fr.umlv.hanabi.views;

import fr.umlv.hanabi.models.Board;

public class BoardView
{
    /**
     * Displays a visualization of the board in its current state
     */
    public static void display(Board board) {
        System.out.println("-------------------------Board-------------------------");
        System.out.println(board.getRedTokenPlayed() + " red tokens played");
        System.out.println(board.getMainDeck().getDeckSize() + " cards is the main deck");
        System.out.println(board.getDiscard().getDeckSize() + " cards in the discard");
        System.out.println("Fireworks : ");
        board.getFireworks().forEach((key, deck) -> {
            System.out.printf("%7s : ", key);
            deck.display(false);
        });
        System.out.println("--------------------------------------------------------");
    }
}
