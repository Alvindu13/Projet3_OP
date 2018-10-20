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
    protected int nbAvailableColors;
    protected char[] randomColors;
    protected char[] formatColoursGameS;


    public BaseGame(int nbCases, int nbTry, boolean devMode) {
        this.devMode = devMode;
        this.nbAvailableColors = nbAvailableColors;
        this.myCombinationThatComputerFind = myCombinationThatComputerFind;
        this.randomNumber = randomNumber;
        this.tentative = 1;
        this.nbCases = nbCases;
        this.nbTry = nbTry;
        this.randomColors = new char[nbCases];
        this.combination = combination;
        this.logger = Logger.getLogger(BaseGame.class);
        this.sc = new Scanner(System.in);
        this.find = false;
        formatColoursGameS = new char[]{'R', 'J', 'B', 'I', 'M', 'V', 'G', 'N', 'O', 'P'};

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
    protected int randomNumberAndSelectedNumber(String game, int nbAvailableColors) { //revoir cette méthode
        if(game.equals("moreless")) {
            int bMin = (int) Math.pow(10, nbCases - 1);
            int bMax = (int) Math.pow(10, nbCases);
            randomNumber = (int) (Math.random() * (bMax - bMin)) + bMin;
            return randomNumber;
        }
        if(game.equals("mastermind")) {
            for (int indexColour = 0; indexColour < nbCases; indexColour++) { //génère une série de 4 couleurs aléatoire pour la réponse de l'ordi
                int bMin = 0;
                int bMax = nbAvailableColors;
                int numRandom = (int) (Math.random() * (bMax - bMin)) + bMin;
                randomColors[indexColour] = formatColoursGameS[numRandom];
            }
        }
        return randomNumber;
    }

    protected void combinationRandom(String game, int mode) {
        switch (game) {
            case "moreLess":
                combination = String.valueOf(randomNumberAndSelectedNumber("moreLess", 0));
                switch (mode) {
                    case 1:
                        this.combination = combination;
                        break;
                    case 2:
                        computerAnswer = String.valueOf(randomNumberAndSelectedNumber("moreLess", 0));
                        break;
                    case 3:
                        computerAnswer = String.valueOf(randomNumberAndSelectedNumber("moreLess", 0));
                        break;
                }

        }
    }

    protected void choiceCombinationToComputer(){
        System.out.print("Merci de choisir la combinaison à 4 chiffres que l'ordinateur doit trouver : ");
        myCombinationThatComputerFind = sc.nextLine();
        System.out.println("L'ordinateur doit retrouver la réponse suivante : " + myCombinationThatComputerFind);
    }

    protected String displayProposal(int n, String user, int counter, String combinationComputer){
        switch(user) {
            case "human":
                if (n == 1 || n == 2) {
                    System.out.print("C'est à votre tour : ");
                    myAnswer = sc.nextLine();
                    if (n == 1)
                        System.out.print("Votre proposition : " + myAnswer + " -> réponse : ");
                    if (n == 2)
                        System.out.print("Votre proposition pour le tour " + counter +   " : " + myAnswer + " => ");
                    return myAnswer;
                }
                if (n == 3 || n == 4) {
                    System.out.print("Merci de faire votre proposition (");
                    System.out.print("il vous reste encore " + (nbTry) + " tentatives) : ");
                    myAnswer = sc.nextLine();
                    System.out.print("Votre proposition : " + myAnswer + " -> réponse : ");
                    return myAnswer;
                }
                break;
            case "computer":
                if (n == 5)
                    System.out.print("Proposition " + tentative + " : " + combinationComputer + " vérification des placements : ");
                if (n == 6 || n == 7) {
                    System.out.print("C'est au tour de l'ordinateur : \n");
                    if (n == 6)
                        System.out.print("L'ordinateur propose pour ce tour : " + computerAnswer + " -> réponse : ");
                    if (n == 7)
                        System.out.print("L'ordinateur propose pour ce tour : " + myAnswer + " => ");
                }
                break;
        }
        return "0";
    }

    /**
     * Display some propositions possibles according combination, user type and mode selected.
     * @param user human or computer.
     * @param combinationComputer random combination generated by computer.
     * @param mode mode of the game.
     * @return
     */
    protected String proposition(String user, String combinationComputer, String mode, String game, int counter) {
        switch (user) {
            case "human":
                switch (mode) {
                    case "dual":
                        if (game.equals("moreLess"))
                            myAnswer = displayProposal(1, "human", 0, null);
                        else if (game.equals("mastermind"))
                            myAnswer = displayProposal(2, "human", counter, null);
                        break;
                    case "challenge":
                        if (game.equals("mastermind"))
                            myAnswer = displayProposal(3, "human", 0, null);
                        else
                            myAnswer = displayProposal(4, "human", 0, null);
                        break;
                }
                return myAnswer;
            case "computer":
                switch (mode) {
                    case "defense":
                        displayProposal(5, "computer", 0, combinationComputer);
                        break;
                    case "dual":
                        if (game.equals("moreLess"))
                            displayProposal(6, "computer", 0, computerAnswer);
                        if(game.equals("mastermind"))
                            displayProposal(7, "computer", 0, myAnswer);
                        break;
                }
        }
        return "0";
    }

    /**
     * Display a sentance according result of the game.
     * @param combination secret combination.
     * @param answer human answer or computer answer.
     * @param user human or computer.
     * @return a boolean according the result.
     */
    protected boolean result(String combination, String answer, String user){
        if (answer.contains(combination)) {
            find = true;
        }
        else
            find = false;
        switch(user) {
            case "human":
                if(find)
                    System.out.print("\n" + "Bravo ! Vous avez trouvé la bonne combinaison : " + answer);
                if (!find && nbTry == 0)
                    System.out.println("Malheureusement vous n'avez pas trouvé la bonne combinaison qui était :  " + combination);
                break;
            case "computer":
                if(find)
                    System.out.print("\n" + "L'ordinateur a trouvé la bonne réponse. Vous pouvez le féliciter ! La réponse :  " + answer);
                if (!find && nbTry == 0)
                    System.out.println("Malheureusement pour l'ordinateur, il n'a pas pu trouver la bonne réponse... La réponse était :  " + combination);
                break;
        }
        return find;
    }
}
