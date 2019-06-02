package fr.umlv.hanabi.test;

import fr.umlv.hanabi.controllers.BoardController;

public class Main
{
    public static void main(String[] args) {
        BoardController b = new BoardController();
        b.runGame(3);

    }
}
