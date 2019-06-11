package fr.umlv.hanabi.test;

import fr.umlv.hanabi.Board;

public class Main {
    public static void main(String[] args) {
    	int nb = Integer.parseInt(args[0]);
    	
    	if ( nb < 2 || nb > 5 ) {
    		throw new IllegalArgumentException("Number of player should be between 2 and 5.");
    	}
    	Board b = new Board(nb);
        b.start();
        b.loop();
        System.out.println("End of game");
        b.end();
    }
}