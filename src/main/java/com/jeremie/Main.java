package com.jeremie;

public class Main {

    /**
     * Run program.
     * @param args dev mode (args[0] = true, args[1] = false).
     */
    public static void main(String[] args) {
        GameSelector game = new GameSelector(args);
        game.numberRun();
    }
}
