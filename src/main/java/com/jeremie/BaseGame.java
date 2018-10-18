package com.jeremie;
import org.apache.log4j.Logger;

import java.util.Scanner;

public abstract class BaseGame implements GameMode{
    private boolean devMode;
    protected int nbCases;
    protected int nbTry;
    protected Scanner sc;
    protected Logger logger;


    public BaseGame(int nbCases, int nbTry, boolean devMode) {
        this.devMode = devMode;
        this.nbCases = nbCases;
        this.nbTry = nbTry;
        this.logger = Logger.getLogger(BaseGame.class);
        this.sc = new Scanner(System.in);
    }

    /**
     * Dislay the combination when the game at started in dev mode only.
     * @param combination combination secret.
     */
    protected void displaySolutionForDev(String combination) {
        if(devMode == true){
            logger.info("Le mode développeur est activé. L'utilisateur peut voir la combinaison secrète.");
            System.out.println("Mode Développeur activé. Voici la combinaison secrète : " + combination);
        }
    }
}
