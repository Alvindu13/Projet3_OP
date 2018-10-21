package com.jeremie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mastermind extends BaseGame {
    private int nbAvailableColors;
    private List<Character> formatColoursGame;
    private String combinationFixeMastermind; //combinaison fixée après avoir était générée
    private char[] combinationFixeMastermindArray;


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
        this.combinationFixeMastermindArray = new char[nbCases];
        displayAvailableColors();
    }

    /**
     * Display available colors and the input format for the game.
     */
    private void displayAvailableColors(){

        List<String> coloursAvailable = Arrays.asList("Rouge", "Jaune", "Bleu", "Indigo", "Marron", "Vert", "Gris", "Noir", "Orange", "Pourpre");
        List<String> stockColorsAvailable = new ArrayList();
        System.out.println(String.format("La taille des combinaisons est de  " + nbCases + " et vous avez le droit à " + nbTry + " tentatives \n"));
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
     * Create a random array char with colours.
     * @param nbCases size of the combination.
     */

    /**
     * Display secret combination.
     * @param combinaison array containing secret combination.
     * @param nbCases size of the combination.
     */


    /**
     * Compare the answer of the USER or the COMPUTER with the combination.
     * @param myAnswer answer USER or COMPUT according who have to play this turn.
     * @param combinaisonSecrete combination at find.
     * @return find = true if the answer is equal with the combination.
     */
    public boolean compare(String myAnswer, char[] combinaisonSecrete) {

        int[] result = new int[2];
        boolean [] checkDuplicate = new boolean[nbCases];
        int PMP = 0; // Présent(s) Mal Placé(s)
        int findGoodPlace = 0;
        char[] answer = new char[nbCases];
        boolean find = true;

        for (int index = 0; index < nbCases; index++) {
                answer[index] = myAnswer.charAt(index);
            }
            // &cette première boucle sert à trouver
            // les éléments bien devinés et correctement placés.
            // Le tableau marque permet de marquer de tels
            // éléments pour qu'ils ne soient pas considérés
            // plusieurs fois.
            for (int index = 0; index < nbCases; index++) {
                if (combinaisonSecrete[index] == answer[index]) {
                    findGoodPlace++;
                    checkDuplicate[index] = true;
                } else {
                    find = false;
                    checkDuplicate[index] = false;
                }
            }
            // la deuxième boucle suivante sert à identifier les
            // éléments bien devinés mais mal placés.
            for (int index = 0; index < nbCases; index++) {
                if (combinaisonSecrete[index] != answer[index]) {
                    int j = 0;
                    boolean trouveMalPlace = false;
                    while ((j < nbCases) && !trouveMalPlace) {
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
            nbTry--;


        return find;
    }

    public String castMethodArraytoString(String stringVariable, char[] charArray){
        for(int index = 0; index < nbCases; index++)
            stringVariable += charArray[index];
        return stringVariable;
    }

    /**
     * Start challenge mode. You have to search a random combination generated by the computer.
     */
    @Override
    public void challengeMode() {
        combinationRandom("mastermind", nbAvailableColors); //génère une combinaison aléatoire randomColors
        combination = castMethodArraytoString(combination, randomCombinationColors);
        displaySolutionForDev(combination);
        while(nbTry > 0 && !find) {
            //myAnswer = proposition("human", myAnswer, "challenge", "mastermind", 1);
            /////////
            displayProposal("moreLessAndDuel", "human", counter1, null);
            find = compare(myAnswer, randomCombinationColors);
        }
        result(combination, myAnswer, "human");
    }

    /**
     * Start defense mode. The computer have to search a random combination generated by you.
     */
    @Override
    public void defenseMode() {
        combinationFixeMastermind = castMethodArraytoString(combinationFixeMastermind, randomCombinationColors);
        choiceCombinationToComputer();
        for (int index = 0; index < nbCases; index++)
            combinationFixeMastermindArray[index] = myCombinationThatComputerFind.charAt(index);
        while(nbTry > 0 && !find) {
            computerAnswer = "";
            combinationRandom("mastermind", nbAvailableColors); //génère une combinaison aléatoire randomColors
            for (int index = 0; index < nbCases; index++)
                computerAnswer += randomCombinationColors[index];
            /////
            displayProposal("moreLessAndDuel", "human", counter1, null);
            find = compare(computerAnswer, combinationFixeMastermindArray);
            tentative++;
        }
       result(myCombinationThatComputerFind, computerAnswer, "computer");
    }

    /**
     * Start duel mode. Switch between user and computer to search a random combination.
     */
    @Override
    public void duelMode() {
        combinationFixeMastermind = "";

        combinationRandom("mastermind", nbAvailableColors);
        for (int index = 0; index < nbCases; index++) {
            combinationFixeMastermind += randomCombinationColors[index];
            combinationFixeMastermindArray[index] = combinationFixeMastermind.charAt(index);
        }
        displaySolutionForDev(combinationFixeMastermind);

        while(!find){
            if (number % 2 == 0) {
                ////
                displayProposal("moreLessAndDuel", "human", counter1, null);
                counter1++;
                find = compare(myAnswer, combinationFixeMastermindArray);
                result(combinationFixeMastermind, myAnswer, "human");
            } else {
                computerAnswer = "";
                combinationRandom("mastermind", nbAvailableColors);
                for (int index = 0; index < nbCases; index++)
                    computerAnswer += randomCombinationColors[index];
                ////
                displayProposal("moreLessAndDuel", "human", counter1, null);
                counter2++;
                find = compare(computerAnswer, combinationFixeMastermindArray);
                result(combinationFixeMastermind, computerAnswer, "computer");

            }
            number++;
        }
    }


}

