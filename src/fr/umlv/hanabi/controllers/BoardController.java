package fr.umlv.hanabi.controllers;

import fr.umlv.hanabi.models.Board;
import fr.umlv.hanabi.models.Card;
import fr.umlv.hanabi.models.Deck;
import fr.umlv.hanabi.models.Player;
import fr.umlv.hanabi.views.BoardView;

import java.util.Map;

public class BoardController
{

    public void runGame(int nbrPlayers)
    {
        Board board = new Board( nbrPlayers );
        BoardController.start( board );
        BoardController.loop( board );
        BoardController.end( board );
    }

    /**
     * Start of the game.
     */
    public static void start(Board board)
    {
        board.createDeck();
        board.shuffleDeck();
        BoardController.initDistribute(board);
        board.initFireworks();
        board.setDiscard(new Deck("discard"));
    }

    /**
     * Distribute the cards to the players at the beginning of the game.
     */
    public static void initDistribute(Board board)
    {
        for ( Player player : board.getPlayers() )
        {
            for ( int i = 0 ; i < player.getNumberOfCards() ; i++ )
            {
                PlayerController.giveCard(player, board.getMainDeck().getCard(0));
            }
        }
    }

    /**
     * run game.
     */
    public static void loop(Board board)
    {
        int nbPlayers = board.getPlayers().size();
        while ( !board.checkVictory() ) {
            BoardView.display(board);
            PlayerController.turn(board, board.getPlayers().get(board.getTurn()));

            board.setTurn((board.getTurn() + 1) % nbPlayers);

            if ( board.getMainDeck().isEmpty() && board.getLastTurn() == -1 ) {
                board.setLastTurn(board.getPlayers().size());
            }
            else if ( board.getMainDeck().isEmpty() && board.getLastTurn() != -1 )
            {
                board.setLastTurn(board.getLastTurn()-1);
            }
        }
    }

    /**
     * End of the game.
     */
    public static void end(Board board)
    {
        int score = 0;
        for ( Map.Entry<String, Deck> entry : board.getFireworks().entrySet() ) {
            score += entry.getValue().getDeckSize();
        }
        System.out.println("Your score : " + score);
    }

    /**
     * Play a card on the board.
     * @param card The card.
     */
    public static void playCard(Board board, Card card)
    {
        String color = card.getColor();
        int size = board.getFireworks().get(color).getDeckSize();
        if ( size + 1 == card.getNumber() ) {
            board.getFireworks().get(color).addCard(card);
        }
        else {
            BoardController.discardCard(board, card);
            board.setRedTokenPlayed(board.getRedTokenPlayed()+1);
        }
    }

    /**
     * Put a card on the board discard.
     * @param card The card to discard
     */
    public static void discardCard( Board board, Card card )
    {
        board.getDiscard().addCard(card);
    }

    /*
     * Draw a card from the main deck
     */
    public static Card drawCard(Board board)
    {
        return board.getMainDeck().getCard(0);
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
