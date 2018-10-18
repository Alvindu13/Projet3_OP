package com.jeremie;
import java.util.Scanner;

public abstract class BaseGame implements GameMode{
    private boolean devMode;
    protected int nbCases;
    protected int nbTry;
    protected Scanner sc;

    public BaseGame(int nbCases, int nbTry, boolean devMode) {
        this.devMode = devMode;
        this.nbCases = nbCases;
        this.nbTry = nbTry;
        sc = new Scanner(System.in);
    }

    /**
     * Dislay the combination when the game at started in dev mode only.
     * @param combination combination secret.
     */
    protected void displaySolutionForDev(String combination) {
        if(devMode == true){
            System.out.println("Mode Développeur activé. Voici la combinaison secrète : " + combination);
        }
    }
}
