package fr.umlv.hanabi.test;

import fr.umlv.hanabi.Board;

public class Main {
    public static void main(String[] args) {
        Board b = new Board(3);
        b.start();
        b.loop();
        System.out.println("End of game");
        b.end();
    }
}