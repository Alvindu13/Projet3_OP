package com.jeremie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mastermind extends BaseGame {
    private int nbAvailableColors;
    private List<Character> formatColoursGame;
    private String combinationFixeMastermind;
    private char[] combinationFixeMastermindArrayHu;
    private char[] combinationFixeMastermindArrayCompu;

    /**
     * @param nbCases size of the combination.
     * @param nbTry maximum try/turn to find combination.
     * @param nbAvailableColors size of available colors.
     * @param devMode enable display combination when the game at started in dev mode.
     */

    public Mastermind(int nbCases, int nbTry, int nbAvailableColors, boolean devMode) {
        super(nbCases, nbTry, devMode);
        this.nbAvailableColors = nbAvailableColors;
        this.formatColoursGame = Arrays.asList('R', 'J', 'B', 'I', 'M', 'V', 'G', 'N', 'O', 'P');
        this.combinationFixeMastermind = "";
        this.combinationFixeMastermindArrayHu = new char[nbCases];
        this.combinationFixeMastermindArrayCompu = new char[nbCases];
        displayAvailableColors();
    }

    /**
     * Display available colors and the input format for the game.
     */
    private void displayAvailableColors(){
        List<String> coloursAvailable = Arrays.asList("Rouge", "Jaune", "Bleu", "Indigo", "Marron", "Vert", "Gris", "Noir", "Orange", "Pourpre");
        List<String> stockColorsAvailable = new ArrayList();
        System.out.println(String.format("La taille des combinaisons est de  " + nbSize + " et vous avez le droit à " + nbTry + " tentatives \n"));
        System.out.print("Les couleurs disponibles sont : ");
        stockColorsAvailable = coloursAvailable;
            for (int index = 1; index <= nbAvailableColors; index++) {
                if (index < nbAvailableColors)
                    System.out.print(stockColorsAvailable.get(index - 1) + ", ");
                else
                    System.out.println(stockColorsAvailable.get(index - 1) + ".");
            }
            System.out.print("Pour le jeu, il faudra utiliser le format suivant pour proposer une combinaison : ");
            for (int index = 1; index <= nbAvailableColors; index++) {
                if (index < nbAvailableColors)
                    System.out.print(formatColoursGame.get(index - 1) + ", ");
                else
                    System.out.println(formatColoursGame.get(index - 1) + ".");
            }
            System.out.println("Exemple d'une proposition valide : RJBJ (équivaut à Rouge, Jaune, Bleu, Jaune). \n");
    }

    /**
     * Calculation of a random number.
     * @return the random number.
     */
    protected void combinationRandom() {

        for (int indexColour = 0; indexColour < nbSize; indexColour++) { //génère une série de 4 couleurs aléatoire pour la réponse de l'ordi
                int bMin = 0;
                int bMax = nbAvailableColors;
                int numRandom = (int) (Math.random() * (bMax - bMin)) + bMin;
                randomCombinationColors[indexColour] = formatColoursGame.get(numRandom);
            }
        }

    /**
     * Compare the answer of YOU or the COMPUTER with the combination.
     * @param myAnswer answer YOU or COMPUT according who have to play this turn.
     * @param combinaisonSecrete combination at find.
     * @return find = true if the answer is equal with the combination.
     */
    public boolean compare(String myAnswer, char[] combinaisonSecrete) {
        int[] result = new int[2];
        boolean [] checkDuplicate = new boolean[nbSize]; //Le tableau checkDuplicate permet de marquer les éléments correctement devinés et placés pour qu'ils ne soient pas considérés plusieurs fois.
        int PMP = 0; // Présent(s) Mal Placé(s)
        int findGoodPlace = 0;
        char[] answer = new char[nbSize];
        find = true;
        answer = castMethodStringToArray(answer, myAnswer);
            // permet de marquer les éléments bien devinés bien placés.
            for (int index = 0; index < nbSize; index++) {
                if (combinaisonSecrete[index] == answer[index]) {
                    findGoodPlace++;
                    checkDuplicate[index] = true;
                } else {
                    find = false;
                    checkDuplicate[index] = false;
                }
            }
            // permet d'identifier les éléments bien devinés mais mal placés.
            for (int index = 0; index < nbSize; index++) {
                if (combinaisonSecrete[index] != answer[index]) {
                    int j = 0;
                    boolean trouveMalPlace = false;
                    while ((j < nbSize) && !trouveMalPlace) {
                        if (!checkDuplicate[j] && (combinaisonSecrete[index] == answer[j])) {
                            PMP++;
                            checkDuplicate[j] = true;
                            trouveMalPlace = true;
                        }
                        j++;
                    }
                }
            }
            result[0] = findGoodPlace;
            result[1] = PMP;
            System.out.print(findGoodPlace + " bien placé(s), ");
            System.out.println(PMP + " présent(s). \n");
        return find;
    }

    /**
     * Cast method.
     * @param stringVariable
     * @param charArray
     * @return stringVariable with the contents of array.
     */
    public String castMethodArrayToString(String stringVariable, char[] charArray){
        for(int index = 0; index < nbSize; index++)
            stringVariable += charArray[index];
        return stringVariable;
    }

    /**
     * Cast method.
     * @param charArray
     * @param stringVariable
     * @return charArray with the contents of string.
     */
    public char[] castMethodStringToArray (char[] charArray, String stringVariable){
        for(int index = 0; index < nbSize; index++)
            charArray[index] = stringVariable.charAt(index);
        return charArray;
    }

    /**
     * Start challenge mode. You have to search a random combination generated by the computer.
     */
    @Override
    public void challengeMode() {
        this.combinationRandom();
        combination = castMethodArrayToString(combination, randomCombinationColors);
        displaySolutionForDev(combination);
        while(nbTry > 0 && !find) {
            displayProposal(GameModes.CHALLENGE, PlayerTypes.HUMAN, 0, null);
            find = compare(myAnswer, randomCombinationColors);
            nbTry--;
        }
        result(combination, myAnswer, null,PlayerTypes.HUMAN, GameModes.CHALLENGE);
    }

    /**
     * Start defense mode. The computer have to search a random combination generated by you.
     */
    @Override
    public void defenseMode() {
        choiceCombinationToComputer(GameModes.DEFENSE);
        combinationFixeMastermindArrayCompu = castMethodStringToArray(combinationFixeMastermindArrayCompu, myCombinationThatComputerFind);
        while(nbTry > 0 && !find) {
            computerAnswer = "";
            this.combinationRandom();
            computerAnswer = castMethodArrayToString(computerAnswer, randomCombinationColors);
            displayProposal(GameModes.DEFENSE, PlayerTypes.COMPUTER, 0, computerAnswer);
            find = compare(computerAnswer, combinationFixeMastermindArrayCompu);
            tentative++;
            nbTry--;
        }
        System.out.println();
        result(myCombinationThatComputerFind, computerAnswer, null,PlayerTypes.COMPUTER, GameModes.CHALLENGE);
    }

    /**
     * Start duel mode. Switch between user and computer to search a random combination.
     */
    @Override
    public void duelMode() {
        this.combinationRandom();
        choiceCombinationToComputer(GameModes.DUAL);
        combinationFixeMastermind = castMethodArrayToString(combinationFixeMastermind, randomCombinationColors);
        combinationFixeMastermindArrayHu = castMethodStringToArray(combinationFixeMastermindArrayHu, combinationFixeMastermind);
        combinationFixeMastermindArrayCompu = castMethodStringToArray(combinationFixeMastermindArrayCompu, myCombinationThatComputerFind);
        displaySolutionForDev(combinationFixeMastermind);
        while(!find){
            if (number % 2 == 0) {
                displayProposal(GameModes.DUAL, PlayerTypes.HUMAN, counter1, null);
                counter1++;
                find = compare(myAnswer, combinationFixeMastermindArrayHu);
                result(combinationFixeMastermind, myAnswer, myCombinationThatComputerFind, PlayerTypes.HUMAN, GameModes.DUAL);
            } else {
                computerAnswer = "";
                this.combinationRandom();
                computerAnswer = castMethodArrayToString(computerAnswer, randomCombinationColors);
                displayProposal(GameModes.DUAL, PlayerTypes.COMPUTER, counter2, computerAnswer);
                counter2++;
                find = compare(computerAnswer, combinationFixeMastermindArrayCompu);
                result(myCombinationThatComputerFind, computerAnswer, combinationFixeMastermind, PlayerTypes.COMPUTER, GameModes.DUAL);
            }
            number++;
        }
    }

    @Override
    protected boolean testMyAnswer(String answer) {
        boolean answerCorrect = true;
        if (answer.length() != nbSize || !answer.matches("^[A-Z]+$")) {
            System.out.println("Ce n'est pas bon, la taille ou le format n'est pas bon.");
            System.out.println("Merci de saisir un une combinaison à " + nbSize + " lettres MAJ (ex : RJBM).");
            answerCorrect = false;
        }
        return answerCorrect;
    }
}

