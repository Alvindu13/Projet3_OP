package com.jeremie;


import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;



public class GameSelector {
    private Mastermind mastermindGame;
    private PlusMoins plusMoinsGame;
    private int nbCases;
    private int nbTry;
    private int nbAvailableColors;
    private boolean devMode;
    private Scanner sc = new Scanner(System.in);
    private Logger logger = Logger.getLogger(GameSelector.class);

    /**
     * @param dev dev mode (args[0] = true, args[1] = false)
     */
    public GameSelector(boolean dev) {
        this.devMode = dev; //switch for dev mode true or false
    }

    /**
     * Run all methods of the games.
     */
    public void numberRun() {

        logger.info("--------Le jeu a démarré------");
        int numberGame = 0;
        int gameMode = 0;
        numberGame = gamechoice();
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
            logger.info("--------Le joueur vient de relancer le jeu------");
            this.numberRun();
        }
        System.out.println("Je vous remercie d'avoir joué. À bientôt ! ");
        logger.info("--------Le joueur vient de quitter le jeu------");
    }

    /**
     * Display available games and selected a game.
     */

    public int gamechoice()  {
        int numberGame = 0;
        boolean numberchoiceIsGood;
        System.out.println("Veuillez choisir le jeu que vous voulez lancer : ");
        String[] gameCh = {"Recherche d'une combinaison de chiffre avec indicateurs +/-", "Recherche d'une combinaison de couleurs avec indicateurs de placement - Mastermind"};
        for (int i = 0; i < 2; i++)
            System.out.println(i + 1 + " - " + gameCh[i]);
        do {
            try {
                numberGame = sc.nextInt();
                logger.info("Le joueur a choisi le jeu : " + numberGame);
                if (numberGame >= 1 && numberGame <= 2) {
                    numberchoiceIsGood = true;
                } else {
                    logger.error("Le joueur n'a pas entré un numéro valide pour le choix du jeu :  " + numberGame);
                    numberchoiceIsGood = false;
                    System.out.println("Vous devez saisir un nombre valide");
                }

            } catch (InputMismatchException e) {
                logger.error("Une exception est survenue lors du choix du jeu.");
                sc.next();
                System.out.println("Vous devez saisir un nombre, correspondant au jeu choisi");
                numberchoiceIsGood = false;

            }
        } while (!numberchoiceIsGood);
        return numberGame;
    }

    /**
     * Display different game modes.
     * @return number choose of mode.
     */
    public int gameMode() {

        int choice;
        String[] arrayMode = {"1 - Mode Challenger : vous devez trouver la combinaison secrète de l'ordinateur", "2 - Mode Défenseur : où c'est à l'ordinateur de trouver votre combinaison secrète ", "3 - Mode duel : où l'ordinateur et vous jouez tour à tour, le premier à trouver la combinaison secrète de l'autre a gagné"};
        System.out.println("Veuillez choisir le mode de jeu :");
        for (int index = 0; index < arrayMode.length; index++)
            System.out.println(arrayMode[index]);
        choice = sc.nextInt();
        logger.info("Le joueur a choisi le mode de jeu :  " + choice);
        while (choice < 0 || choice > 3) {
            System.out.println("Merci de rentrer un nombre valide parmis la liste ci-dessus :  ");
            choice = sc.nextInt();
        }
        System.out.println(" ");
        return choice;
    }


    /**
     * Run different games and modes depending on parameters.
     * @param numberGame Selected the game.
     * @param gameMode Selected game mode.
     */
    public void gameRun(int numberGame, int gameMode){

            this.readParameters();
            if (numberGame == 1){
                plusMoinsGame = new PlusMoins(nbCases, nbTry, devMode);
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
                mastermindGame = new Mastermind(nbCases, nbTry, nbAvailableColors, devMode);
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
        logger.info("--------Le jeu est terminé------");
        this.retry();
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
            this.nbAvailableColors = Integer.parseInt(prop.getProperty("mastermind.nombre.couleurs"));
        } catch (FileNotFoundException e) {
            logger.fatal("Le fichier config.properties a levé une exception.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








