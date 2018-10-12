package com.jeremie;


import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;



public class GameSelector {
    private MastermindGame mastermindGame;
    private PlusMoinsGame plusMoinsGame;
    private int nbCases;
    private int nbTry;
    private int nbAvailableColours;
    private boolean devMode;
    private Scanner sc = new Scanner(System.in);
    private static Logger logger = Logger.getLogger(GameSelector.class);

    /**
     * @param dev dev mode (args[0] = true, args[1] = false)
     */
    public GameSelector(String[] dev) {
        this.devMode = Boolean.parseBoolean(dev[1]); //switch for dev mode true or false
    }

    /**
     * Run all methods of the games.
     */
    public void numberRun() {

        logger.info("--------Le jeu a démarré------");
        int numberGame = 0;
        int gameMode = 0;
        numberGame = gameChoise();
        gameMode = gameMode();
        gameRun(numberGame, gameMode);
    }

    /**
     * Retry game.
     */
    public void retry() {

        System.out.println();
        System.out.println("Voulez-vous rejouer ? Si oui, veuillez entrer OK. Si non, appuyez sur n'importe quelle touche puis sur entrée");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (str.contains("OK")) {
            this.numberRun();
        }
        System.out.println("Je vous remercie d'avoir joué. À bientôt ! ");
    }

    /**
     * Display available games and selected a game.
     */

    public int gameChoise()  {
        int numberGame = 0;
        boolean numberChoiseIsGood;
        System.out.println("Veuillez choisir le jeu que vous voulez lancer : ");
        String[] gameCh = {"Recherche d'une combinaison de chiffre avec indicateurs +/-", "Recherche d'une combinaison de couleurs avec indicateurs de placement - Mastermind"};
        for (int i = 0; i < 2; i++)
            System.out.println(i + 1 + " - " + gameCh[i]);
        do {
            try {
                numberGame = sc.nextInt();
                logger.info("Le joueur a choisi le jeu numéro " + numberGame);
                if (numberGame >= 1 && numberGame <= 2) {
                    numberChoiseIsGood = true;
                } else {
                    numberChoiseIsGood = false;
                    System.out.println("Vous devez saisir un nombre valide");
                }

            } catch (InputMismatchException e) {
                logger.error("Une exception est survenue lors du choix du jeu.");
                sc.next();
                System.out.println("Vous devez saisir un nombre, correspondant au jeu choisi");
                numberChoiseIsGood = false;

            }
        } while (!numberChoiseIsGood);
        return numberGame;
    }

    /**
     * Run different games and modes depending on parameters.
     * @param numberGame Selected the game.
     * @param gameMode Selected game mode.
     */
    public void gameRun(int numberGame, int gameMode){

            this.readParameters();
            if (numberGame == 1){
                plusMoinsGame = new PlusMoinsGame(nbCases, nbTry, devMode);
                switch (gameMode){
                    case 1: plusMoinsGame.challengeMode();
                    break;
                    case 2: plusMoinsGame.defenseMode();
                    break;
                    case 3:plusMoinsGame.duelMode();
                    break;
                }
            }
            if (numberGame == 2) {
                mastermindGame = new MastermindGame(nbCases, nbTry, nbAvailableColours, devMode);
                switch(gameMode) {
                    case 1:
                        mastermindGame.challengeMode();
                        break;
                    case 2:
                        mastermindGame.defenseMode();
                        break;
                    case 3:
                        mastermindGame.duelMode();
                        break;
                }
            }
        this.retry();
    }

    /**
     * Display different game modes.
     * @return number choose of mode.
     */
    public int gameMode() {

        int choise;
        String[] arrayMode = {"1 - Mode Challenger : vous devez trouver la combinaison secrète de l'ordinateur", "2 - Mode Défenseur : où c'est à l'ordinateur de trouver votre combinaison secrète ", "3 - Mode duel : où l'ordinateur et vous jouez tour à tour, le premier à trouver la combinaison secrète de l'autre a gagné"};
        System.out.println("Veuillez choisir le mode de jeu :");
        for (int index = 0; index < arrayMode.length; index++)
            System.out.println(arrayMode[index]);
        choise = sc.nextInt();
        if (choise < 0 || choise > 3) {
            System.out.println("Merci de rentrer un nombre valide parmis la liste ci-dessous :  ");
            gameMode();
        }
        System.out.println(" ");
        return choise;
    }

    /**
     * Reading parameters of properties file.
     */
    public void readParameters() {
        try {
            Properties prop = new Properties();
            prop.load(new FileReader("src/main/resources/config.properties"));
            this.nbCases = Integer.parseInt(prop.getProperty("nombre.cases"));
            this.nbTry = Integer.parseInt(prop.getProperty("nombre.essai"));
            this.nbAvailableColours = Integer.parseInt(prop.getProperty("mastermind.nombre.couleurs"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








