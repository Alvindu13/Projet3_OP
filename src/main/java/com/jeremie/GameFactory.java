package com.jeremie;

public class GameFactory {

    private BaseGame game;
    private int numberGame;
    private int nbSize;
    private int nbTry;
    private boolean devMode;
    private int nbAvailableColors;

    public GameFactory(int numberGame, int nbSize, int nbTry, boolean devMode, int nbAvailableColors) {
        this.numberGame = numberGame;
        this.nbSize = nbSize;
        this.nbTry = nbTry;
        this.devMode = devMode;
        this.nbAvailableColors = nbAvailableColors;
    }

    public BaseGame gameObj () {

            if (numberGame == 1) {
                game = new MoreLess(nbSize, nbTry, devMode);
            } else if (numberGame == 2) {
                game = new Mastermind(nbSize, nbTry, nbAvailableColors, devMode);
            }
            return game;
        }
    }