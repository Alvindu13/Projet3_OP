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


    public BaseGame(int nbCases, int nbTry, boolean devMode) {
        this.devMode = devMode;
        this.nbCases = nbCases;
        this.nbTry = nbTry;
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

    protected void proposition(int user, String combination){
        System.out.print("Merci de faire votre proposition ( ");
        System.out.print("il vous reste encore " + (nbTry) + " tentatives) : ");
        myAnswer = sc.nextLine();
        System.out.print("Votre proposition : " + myAnswer + " -> réponse : ");
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
