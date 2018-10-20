package com.jeremie;
import org.apache.log4j.Logger;

import java.util.Scanner;

public abstract class BaseGame implements GameMode{
    private boolean devMode;
    protected int nbCases;
    protected int nbTry;
    protected Scanner sc;
    protected Logger logger;
    protected String myAnswer;
    protected boolean find;
    protected String computerAnswer;
    protected String combination;


    public BaseGame(int nbCases, int nbTry, boolean devMode) {
        this.devMode = devMode;
        this.nbCases = nbCases;
        this.nbTry = nbTry;
        this.combination = combination;
        this.logger = Logger.getLogger(BaseGame.class);
        this.sc = new Scanner(System.in);
        this.find = false;
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

    /**
     * Calculation of a random number.
     * @return the random number.
     */
    private int randomNumberAndSelectedNumber() { //revoir cette méthode
        int bMin = (int) Math.pow(10, nbCases - 1);
        int bMax = (int) Math.pow(10, nbCases);
        int randomNumber = (int) (Math.random() * (bMax - bMin)) + bMin;
        return randomNumber;
    }

    protected void combination(){
        this.combination = String.valueOf(randomNumberAndSelectedNumber());
    }

    protected void proposition(String user, String combination){

        switch (user) {
            case "human" :
                System.out.print("Merci de faire votre proposition ( ");
                System.out.print("il vous reste encore " + (nbTry) + " tentatives) : ");
                myAnswer = sc.nextLine();
                System.out.print("Votre proposition : " + myAnswer + " -> réponse : ");
                break;
            case "computer":
                System.out.print("Merci de proposer une combinaison que l'ordinateur va devoir retrouver : ");
                computerAnswer = String.valueOf(randomNumberAndSelectedNumber());
                break;
        }
    }

    protected void result(String combination){

        nbTry--;
        if (myAnswer.contains(combination)) {
            find = true;
            System.out.print("\n" + "Bravo ! Vous avez trouvé la bonne combinaison : " + myAnswer);
        }
        if(!find && nbTry == 0)
            System.out.println("Malheureusement vous n'avez pas trouvé la bonne combinaison qui était :  " + combination);

    }
}
