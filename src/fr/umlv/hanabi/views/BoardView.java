package fr.umlv.hanabi.views;

import fr.umlv.hanabi.models.Board;

public class BoardView
{

    private Board board;

    public BoardView(Board board)
    {
        this.board = board;
    }

    /**
     * Displays a visualization of the board in its current state
     */
    public void display() {
        System.out.println("-------------------------Board-------------------------");
        System.out.println(this.board.getRedTokenPlayed() + " red tokens played");
        System.out.println(this.board.getMainDeck().getDeckSize() + " cards is the main deck");
        System.out.println(this.board.getDiscard().getDeckSize() + " cards in the discard");
        System.out.println("Fireworks : ");
        this.board.getFireworks().forEach((key, deck) -> {
            System.out.printf("%7s : ", key);
            deck.display(false);
        });
        System.out.println("--------------------------------------------------------");
    }
}
