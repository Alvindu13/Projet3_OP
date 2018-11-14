package com.jeremie;

import org.apache.log4j.Logger;
import java.util.Scanner;

public abstract class BaseGame implements GameMode {
    private boolean devMode;
    protected int nbSize;
    protected int number;
    protected int nbTry;
    protected Scanner sc;
    protected Logger logger;
    protected String myAnswer;
    protected boolean find;
    protected String computerAnswer;
    protected String combination;
    protected String myCombinationThatComputerFind;
    protected int essay;
    protected int counter1;
    protected int counter2;


    public BaseGame(int nbSize, int nbTry, boolean devMode) {
        this.devMode = devMode;
        this.number = 0;
        this.myCombinationThatComputerFind = "";
        this.essay = 1;
        this.nbSize = nbSize;
        this.nbTry = nbTry;
        this.combination = "";
        this.logger = Logger.getLogger(BaseGame.class);
        this.sc = new Scanner(System.in);
        this.find = false;
        this.counter1 = 1;
        this.counter2 = 1;
    }

    protected enum GameMode {
        CHALLENGE,
        DEFENSE,
        DUAL,
    }

    protected enum PlayerType {
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
            System.out.println("Mode Développeur activé. Voici la combinaison secrète que l'utilisateur doit trouver : " + combination + "\n");
        }
    }

    /**
     * This method enabled to choice the combination that the computer have to find for the defense mode.
     */
    protected void choiceCombinationToComputer(GameMode mode){
        if(mode.equals(GameMode.DUAL)){
            System.out.println("L'ordinateur a généré une combinaison que vous devez trouver. Attention, vous devez chercher des combinaisons différentes. \n");
            System.out.print("C'est maintenant à vous de proposer une combinaison pour l'ordinateur : ");
        }
        else if ((mode.equals(GameMode.DEFENSE)))
            System.out.print("Merci de proposer une combinaison de " + nbSize + " valeurs que l'ordinateur doit retrouver : ");
        myCombinationThatComputerFind = this.getAnswerFromUser();
        System.out.println("=> L'ordinateur doit retrouver la réponse suivante : " + myCombinationThatComputerFind);
        System.out.println();
    }

    /**
     * Regex to check if the answer format is correct.
     * @return a check boolean
     */
    protected abstract boolean testMyAnswer(String answer);

    /**
     * Display some propositions possibles according combination, user type and mode selected.
     * @param mode game mode selected.
     * @param user you or computer.
     * @param counter to count turns.
     * @param combinationComputer computer answer.
     */
    protected void displayProposal(GameMode mode, PlayerType user, int counter, String combinationComputer) {
        int cases = 0;
        switch (user) {
            case HUMAN:
                if (mode.equals(GameMode.DUAL)) {
                    System.out.print("C'est à votre tour : ");
                    myAnswer = this.getAnswerFromUser();
                    cases = 1;
                    this.displayResultSentence(counter, myAnswer, cases);
                } else if (mode.equals(GameMode.CHALLENGE)) {
                    System.out.print("Merci de faire votre proposition (");
                    System.out.print("il vous reste encore " + (nbTry) + " tentatives) : ");
                    myAnswer = this.getAnswerFromUser();
                    cases = 2;
                    this.displayResultSentence(counter, myAnswer, cases);
                }
                break;
            case COMPUTER:
                if (mode.equals(GameMode.DEFENSE)) {
                    cases = 3;
                    this.displayResultSentence(counter, combinationComputer, cases);
                } else if (mode.equals(GameMode.DUAL)) {
                    System.out.print("C'est au tour de l'ordinateur ! \n");
                    cases = 4;
                    this.displayResultSentence(counter, combinationComputer, cases);
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
    protected boolean result(String combination, String answer, String answerOfOther, PlayerType user, GameMode mode){
        if (answer.contains(combination))
            find = true;
        else
            find = false;
        switch(user) {
            case HUMAN:
                if(find){
                    System.out.print("Bravo ! Vous avez trouvé la bonne combinaison (" + answer + ") ! ");
                    if(mode.equals(GameMode.DUAL))
                        System.out.print(("La combinaison que l'ordinateur devait trouver était : " + answerOfOther));
                    System.out.println("");
                }
                else if (!find && nbTry == 0 )
                    System.out.println("Malheureusement vous n'avez pas trouvé la bonne combinaison qui était :  " + combination);
                break;
            case COMPUTER:
                if(find) {
                    System.out.print("L'ordinateur a trouvé la bonne combinaison (" + answer + "). Vous pouvez le féliciter ! ");
                    if(mode.equals(GameMode.DUAL))
                        System.out.println("La combinaison que vous deviez trouver était :  " + answerOfOther);
                    System.out.println("");
                }
                else if (!find && nbTry == 0 || !find && essay == nbTry ){
                    System.out.println("Malheureusement pour l'ordinateur, il n'a pas pu trouver la bonne réponse... La réponse était :  " + combination);
                }
                break;
        }
        return find;
    }

    /**
     * Check if the format of your answer is correct.
     * @return correct answer.
     */
    private String getAnswerFromUser() {
        String answer = sc.nextLine();
        while (this.testMyAnswer(answer) == false) {
            answer = sc.nextLine();
        }
        return answer;
    }

    /**
     * Display a sentence with your answer every turn.
     * @param counter parameter only in duel mode for count every turn up to find the combination.
     * @param answer your answer or computer answer.
     * @param cases possibles cases.
     */
    private void displayResultSentence(int counter, String answer, int cases){
        switch(cases) {
            case 1:
                System.out.print("Votre proposition pour le tour " + counter +   " : " + answer + " => réponse : ");
                break;
            case 2:
                System.out.print("Votre proposition : " + answer + " -> réponse : ");
                break;
            case 3:
                System.out.print("Proposition " + essay + " : " + answer + " vérification des placements : ");
                break;
            case 4:
                System.out.print("L'ordinateur propose pour le tour " + counter +   " : " + answer + " => réponse : ");
                break;
        }
    }

}

