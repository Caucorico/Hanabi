package fr.umlv.hanabi.controllers;

import fr.umlv.hanabi.models.Board;
import fr.umlv.hanabi.models.Card;
import fr.umlv.hanabi.models.Deck;
import fr.umlv.hanabi.views.BoardView;

import java.util.Map;

public class BoardController
{
    private Board board;

    private BoardView view;

    public BoardController(int numberPlayer)
    {
        this.board = new Board(numberPlayer, this);
        this.view = new BoardView(this.board);
    }

    /**
     * Start of the game.
     */
    public void start()
    {
        this.board.createDeck();
        this.board.shuffleDeck();
        this.board.initDistribute();
        this.board.initFireworks();
        this.board.setDiscard(new Deck("discard"));
    }

    /**
     * run game.
     */
    public void loop()
    {
        int nbPlayers = this.board.getPlayers().size();
        while ( !this.board.checkVictory() ) {
            this.view.display();
            this.board.getPlayers().get(this.board.getTurn()).turn();

            this.board.setTurn((this.board.getTurn() + 1) % nbPlayers);

            if ( this.board.getMainDeck().isEmpty() && this.board.getLastTurn() == -1 ) {
                this.board.setLastTurn(this.board.getPlayers().size());
            }
            else if ( this.board.getMainDeck().isEmpty() && this.board.getLastTurn() != -1 )
            {
                this.board.setLastTurn(this.board.getLastTurn()-1);
            }
        }
    }

    /**
     * End of the game.
     */
    public void end()
    {
        int score = 0;
        for ( Map.Entry<String, Deck> entry : this.board.getFireworks().entrySet() ) {
            score += entry.getValue().getDeckSize();
        }
        System.out.println("Your score : " + score);
    }

    /**
     * Play a card on the board.
     * @param card The card.
     */
    public void playCard(Card card)
    {
        String color = card.getColor();
        int size = this.board.getFireworks().get(color).getDeckSize();
        if ( size + 1 == card.getNumber() ) {
            this.board.getFireworks().get(color).addCard(card);
        }
        else {
            this.discardCard(card);
            this.board.setRedTokenPlayed(this.board.getRedTokenPlayed()+1);
        }
    }

    /**
     * Put a card on the board discard.
     * @param card The card to discard
     */
    public void discardCard( Card card )
    {
        this.board.getDiscard().addCard(card);
    }

    /*
     * Draw a card from the main deck
     */
    public Card drawCard() {
        return this.board.getMainDeck().getCard(0);
    }

    /**
     * Give an information to another player
     * @param action color or number
     * @param possibility which cards
     * @return return false if the action is not allowed
     */
    public boolean giveInfo( int action, int possibility )
    {
        return true;
    }
}
