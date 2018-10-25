package com.jeremie;

import org.apache.log4j.Logger;
import java.util.Scanner;

public abstract class BaseGame implements GameMode{
    private boolean devMode;
    private char[] formatColoursGameS;
    protected int nbSize;
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
    protected char[] randomCombinationColors;
    protected int counter1;
    protected int counter2;


    public BaseGame(int nbCases, int nbTry, boolean devMode) {
        this.devMode = devMode;
        this.number = 0;
        this.myCombinationThatComputerFind = myCombinationThatComputerFind;
        this.randomNumber = randomNumber;
        this.tentative = 1;
        this.nbSize = nbCases;
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

    public enum gameTypes {
        MORELESS,
        MASTERMIND,
    }

    public enum gameModes {
        CHALLENGE,
        DEFENSE,
        DUAL,
    }

    public enum userTypes {
        HUMAN,
        COMPUTER,
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
     * This method enabled to choice the combination that ordi have to find for the defense mode.
     */
    protected void choiceCombinationToComputer(String cases){
        System.out.print("Merci de choisir la combinaison à 4 chiffres que l'ordinateur doit trouver : ");
        myCombinationThatComputerFind = sc.nextLine();
        switch(cases) {
            case "numCombiToComputer":
                testAnswer("numCombiToComputer", userTypes.COMPUTER);
                break;
            case "stringCombiToComputer":
                testAnswer("stringCombiToComputer", userTypes.COMPUTER);
                break;
        }
        System.out.println();
        System.out.println("L'ordinateur doit retrouver la réponse suivante : " + myCombinationThatComputerFind);
        System.out.println();
    }

    /**
     * Display a sentence with your answer every turn.
     * @param counter parameter only in duel mode for count every turn up to find the combination.
     * @param answer your answer or computer answer.
     * @param cases possibles cases.
     */
    public void displayResultSentence(int counter, String answer, int cases){
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
     * you or computer.
     * to count turns.
     * computer answer.
     */
    protected void displayProposal(gameTypes game, gameModes mode, userTypes user, int counter, String combinationComputer) {
        int cases = 0;
        switch (user) {
            case HUMAN:
                if (mode.equals(gameModes.DUAL)) {
                    System.out.print("C'est à votre tour : ");
                    myAnswer = sc.nextLine();
                    if (game.equals(gameTypes.MORELESS))
                        testAnswer("numCombi", userTypes.HUMAN);
                    else if (game.equals(gameTypes.MASTERMIND))
                        testAnswer("stringCombi", userTypes.HUMAN);
                    cases = 1;
                    displayResultSentence(counter, myAnswer, cases);
                } else if (mode.equals(gameModes.CHALLENGE)) {
                    System.out.print("Merci de faire votre proposition (");
                    System.out.print("il vous reste encore " + (nbTry) + " tentatives) : ");
                    myAnswer = sc.nextLine();
                    if (game.equals(gameTypes.MORELESS))
                        testAnswer("numCombi", userTypes.HUMAN);
                    else if (game.equals(gameTypes.MASTERMIND))
                        testAnswer("stringCombi", userTypes.HUMAN);
                    cases = 2;
                    displayResultSentence(counter, myAnswer, cases);
                }
                break;
            case COMPUTER:
                if (mode.equals(gameModes.DEFENSE)) {
                    cases = 3;
                    displayResultSentence(counter, combinationComputer, cases);
                } else if (mode.equals(gameModes.DUAL)) {
                    System.out.print("C'est au tour de l'ordinateur ! \n");
                    cases = 4;
                    displayResultSentence(counter, combinationComputer, cases);
                }
                break;
        }
    }

    /**
     * Regex to check if the answer format is correct.
     * @param cases possibles cases (number or string combi).
     */
    protected void testAnswer(String cases, userTypes user) {
        switch(user) {
            case HUMAN:
                switch (cases) {
                    case "numCombi":
                        while (myAnswer.length() != nbSize || !myAnswer.matches("^\\d+$")) {
                            System.out.println("Ce n'est pas bon, la taille ou le format n'est pas bon.");
                            System.out.println("Merci de saisir un nombre à " + nbSize + " chiffres : ");
                            myAnswer = sc.nextLine();
                        }
                        break;
                    case "stringCombi":
                        while (myAnswer.length() != nbSize || !myAnswer.matches("^[A-Z]+$")) {
                            System.out.println("Ce n'est pas bon, la taille ou le format n'est pas bon.");
                            System.out.println("Merci de saisir un une combinaison à " + nbSize + " lettres MAJ (ex : RJBM).");
                            myAnswer = sc.nextLine();
                        }
                        break;
                }
                break;
            case COMPUTER:
                switch(cases) {
                    case "numCombiToComputer":
                        while (myCombinationThatComputerFind.length() != nbSize || !myCombinationThatComputerFind.matches("^\\d+$")) {
                            System.out.println("Ce n'est pas bon, la taille ou le format n'est pas bon.");
                            System.out.println("Merci de saisir un nombre à " + nbSize + " chiffres : ");
                            myCombinationThatComputerFind = sc.nextLine();
                        }
                        break;
                    case "stringCombiToComputer":
                        while (myCombinationThatComputerFind.length() != nbSize || !myCombinationThatComputerFind.matches("^[A-Z]+$")) {
                            System.out.println("Ce n'est pas bon, la taille ou le format n'est pas bon.");
                            System.out.println("Merci de saisir un une combinaison à " + nbSize + " lettres MAJ (ex : RJBM).");
                            myCombinationThatComputerFind = sc.nextLine();
                        }
                        break;
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
                else if (!find && nbTry == 0 || !find && tentative == nbTry ){
                    System.out.println();
                    System.out.println("Malheureusement pour l'ordinateur, il n'a pas pu trouver la bonne réponse... La réponse était :  " + combination);
                }
                break;
        }
        return find;
    }
}



