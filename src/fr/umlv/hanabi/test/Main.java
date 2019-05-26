package fr.umlv.hanabi.test;

import fr.umlv.hanabi.controllers.BoardController;
import fr.umlv.hanabi.models.Board;

public class Main
{
    public static void main(String[] args) {
        BoardController b = new BoardController(3);
        b.start();
        b.loop();
        System.out.println("End of game");
        b.end();
    }
}
