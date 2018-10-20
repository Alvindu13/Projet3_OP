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
    protected int randomNumber;
    protected String myCombinationThatComputerFind;
    protected int tentative = 1;


    public BaseGame(int nbCases, int nbTry, boolean devMode) {
        this.devMode = devMode;
        this.myCombinationThatComputerFind = myCombinationThatComputerFind;
        this.randomNumber = randomNumber;
        this.tentative = 1;
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
    private int randomNumberAndSelectedNumber(String game) { //revoir cette méthode
        int bMin = (int) Math.pow(10, nbCases - 1);
        int bMax = (int) Math.pow(10, nbCases);
        randomNumber = (int) (Math.random() * (bMax - bMin)) + bMin;
        return randomNumber;
    }

    protected void combinationRandom(String game, int mode) {
        switch (game) {
            case "moreLess":
                combination = String.valueOf(randomNumberAndSelectedNumber("moreLess"));
                switch (mode) {
                    case 1:
                        this.combination = combination;
                        break;
                    case 2:
                        computerAnswer = String.valueOf(randomNumberAndSelectedNumber("moreLess"));
                        break;
                    case 3:
                        computerAnswer = String.valueOf(randomNumberAndSelectedNumber("moreLess"));
                        break;
                }

        }
    }

    protected void choiceCombinationToComputer(){
        System.out.print("Merci de choisir le nombre à 4 chiffres que l'ordinateur doit trouver : ");
        myCombinationThatComputerFind = sc.nextLine();
        System.out.println("L'ordinateur doit retrouver la réponse suivante : " + myCombinationThatComputerFind);
    }

    protected String proposition(String user, String combinationComputer, String mode){

        switch (user) {
            case "human" :
                if(!mode.contains("defense")) {
                    System.out.print("Merci de faire votre proposition ( ");
                    System.out.print("il vous reste encore " + (nbTry) + " tentatives) : ");
                    myAnswer = sc.nextLine();
                    System.out.print("Votre proposition : " + myAnswer + " -> réponse : ");
                    return myAnswer;
                }
                if(mode.contains("defense")){

                    System.out.print("Proposition " + tentative + " : " + combinationComputer + " vérification des placements : ");
                    return myCombinationThatComputerFind;
                }




                    case "computer":
                System.out.print("Merci de proposer une combinaison que l'ordinateur va devoir retrouver : ");
                randomNumberAndSelectedNumber("moreLess");
                break;
        }
        return "0";
    }

    //while(!find && tentative < nbTry){
    //            computerAnswer = computerReflexion(equal, more, less, myCombinationThatComputerFind, computerAnswer);
    //            System.out.print("Proposition " + (tentative+1) + " : " + computerAnswer + " vérification des placements : ");
    //            compareAndDisplayIndicatorsPlacement(computerAnswer, myCombinationThatComputerFind);
    //            comparePlacement(computerAnswer, myCombinationThatComputerFind);
    //            System.out.println();
    //            if (computerAnswer.contains(myCombinationThatComputerFind)) {
    //                find = true;
    //                System.out.print("\n" + "L'ordi a trouvé la bonne combinaison : " + computerAnswer);
    //            }
    //            tentative++;
    //        }
    //        if(!find)
    //            System.out.print("\n" + "L'ordi n'a pas trouvé la bonne combinaison, qui est : " + myCombinationThatComputerFind);
    //    }



    protected boolean result(String combination, String answer){

        if (answer.contains(combination)) {
            find = true;
            System.out.print("\n" + "Bravo ! Vous avez trouvé la bonne combinaison : " + answer);
        }
        if(!find && nbTry == 0)
            System.out.println("Malheureusement vous n'avez pas trouvé la bonne combinaison qui était :  " + combination);

        return find;

    }
}
