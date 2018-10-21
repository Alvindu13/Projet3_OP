package com.jeremie;
import org.apache.log4j.Logger;

import java.util.Scanner;

public abstract class BaseGame implements GameMode{
    private boolean devMode;
    protected int nbCases;
    protected int number;
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
    protected int nbAvailableColors;
    protected char[] randomCombinationColors;
    protected char[] formatColoursGameS;
    protected int counter1;
    protected int counter2;


    public BaseGame(int nbCases, int nbTry, boolean devMode) {
        this.devMode = devMode;
        this.number = 0;
        this.nbAvailableColors = nbAvailableColors;
        this.myCombinationThatComputerFind = myCombinationThatComputerFind;
        this.randomNumber = randomNumber;
        this.tentative = 1;
        this.nbCases = nbCases;
        this.nbTry = nbTry;
        this.randomCombinationColors = new char[nbCases];
        this.combination = "";
        this.logger = Logger.getLogger(BaseGame.class);
        this.sc = new Scanner(System.in);
        this.find = false;
        this.formatColoursGameS = new char[]{'R', 'J', 'B', 'I', 'M', 'V', 'G', 'N', 'O', 'P'};
        this.counter1 = 1;
        this.counter2 = 1;

    }

    /**
     * Dislay the combination when the game at started in dev mode only.
     * @param combination combination secret.
     */
    protected void displaySolutionForDev(String combination) {
        if(devMode == true){
            logger.info("Le mode développeur est activé. L'utilisateur peut voir la combinaison secrète.");
            System.out.println("Mode Développeur activé. Voici la combinaison secrète : " + combination + "\n");
        }
    }

    /**
     * Calculation of a random number.
     * @return the random number.
     */
    protected void combinationRandom(String game, int nbAvailableColors) { //revoir cette méthode
        if(game.equals("moreLess")) {
            int bMin = (int) Math.pow(10, nbCases - 1);
            int bMax = (int) Math.pow(10, nbCases);
            randomNumber = (int) (Math.random() * (bMax - bMin)) + bMin;
        }
        else if(game.equals("mastermind")) {
            for (int indexColour = 0; indexColour < nbCases; indexColour++) { //génère une série de 4 couleurs aléatoire pour la réponse de l'ordi
                int bMin = 0;
                int bMax = nbAvailableColors;
                int numRandom = (int) (Math.random() * (bMax - bMin)) + bMin;
                randomCombinationColors[indexColour] = formatColoursGameS[numRandom];
            }
        }
    }

    /**
     * This method enabled to choice the combination that ordi have to find for the defense mode.
     */

    protected void choiceCombinationToComputer(){
        System.out.print("Merci de choisir la combinaison à 4 chiffres que l'ordinateur doit trouver : ");
        myCombinationThatComputerFind = sc.nextLine();
        System.out.println();
        System.out.println("L'ordinateur doit retrouver la réponse suivante : " + myCombinationThatComputerFind);
        System.out.println();
    }

    /**
     *
     * @param counter
     * @param answer
     * @param cases
     */
    public void displayYourResult(int counter, String answer, int cases){

        switch(cases) {
            case 1:
                System.out.print("Votre proposition pour le tour " + counter +   " : " + answer + " => réponse : ");
                break;
            case 2:
                System.out.print("Votre proposition : " + answer + " -> réponse : ");
                break;
            case 3:
                System.out.print("Proposition " + tentative + " : " + answer + " vérification des placements : ");
                break;
            case 4:
                System.out.print("L'ordinateur propose pour le tour " + counter +   " : " + answer + " => réponse : ");
                break;
        }
}

    /**
     * Display some propositions possibles according combination, user type and mode selected.
     * @param gameAndMode
     * @param user
     * @param counter
     * @param combinationComputer
     * @return
     */

    protected void displayProposal(String gameAndMode, String user, int counter, String combinationComputer){
        int cases = 0;
        switch(user) {
            case "human":
                if (gameAndMode.equals("moreLessAndDuel") || gameAndMode.equals("mastermindAndDuel")) {
                    System.out.print("C'est à votre tour : ");
                    myAnswer = sc.nextLine();
                    cases = 1;
                    displayYourResult(counter, myAnswer, cases);
                }
                else if (gameAndMode.equals("mastermindAndChallenge") || gameAndMode.equals("moreLessAndChallenge")) {
                    System.out.print("Merci de faire votre proposition (");
                    System.out.print("il vous reste encore " + (nbTry) + " tentatives) : ");
                    myAnswer = sc.nextLine();
                    cases = 2;
                    displayYourResult(counter, myAnswer, cases);
                }
                break;
            case "computer":
                if (gameAndMode.equals("moreLessAndDefense") || gameAndMode.equals("mastermindAndDefense")){
                    cases = 3;
                    displayYourResult(counter, combinationComputer, cases);
                }
                else if (gameAndMode.equals("moreLessAndDuel") || gameAndMode.equals("mastermindAndDuel")) {
                    System.out.print("C'est au tour de l'ordinateur ! \n");
                    cases = 4;
                    displayYourResult(counter, combinationComputer, cases);
                }
                break;
        }
    }


    /**
     * Display a sentance according result of the game.
     * @param combination secret combination.
     * @param answer human answer or computer answer.
     * @param user human or computer.
     * @return a boolean according the result.
     */
    protected boolean result(String combination, String answer, String user){
        if (answer.contains(combination))
            find = true;
        else
            find = false;
        switch(user) {
            case "human":
                if(find)
                    System.out.print("\n" + "Bravo ! Vous avez trouvé la bonne combinaison avant l'ordinateur ! La combinaison : " + answer);
                else if (!find && nbTry == 0 )
                    System.out.println("Malheureusement vous n'avez pas trouvé la bonne combinaison qui était :  " + combination);
                break;
            case "computer":
                if(find)
                    System.out.print("\n" + "L'ordinateur a trouvé la bonne réponse. Vous pouvez le féliciter ! La réponse :  " + answer);
                else if (!find && nbTry == 0 || !find && tentative == 5 ){
                    System.out.println();
                    System.out.println("Malheureusement pour l'ordinateur, il n'a pas pu trouver la bonne réponse... La réponse était :  " + combination);
                }
                break;
        }
        return find;
    }
}



