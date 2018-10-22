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
    private int gameMode;
    private int numberGame;
    private int nbTry;
    private int choice;
    private int nbAvailableColors;
    private boolean numberchoiceIsGood;
    private boolean devMode;
    private Scanner sc;
    private Logger logger;

    /**
     * @param dev dev mode (args[0] = true, args[1] = false)
     */
    public GameSelector(boolean dev) {
        this.devMode = dev; //switch for dev mode true or false
        this.gameMode = 0;
        this.numberGame = 0;
        this.choice = 0;
        this.numberchoiceIsGood = false;
        this.sc = new Scanner(System.in);
        this.logger = Logger.getLogger(GameSelector.class);
    }

    /**
     * Run all methods of the games.
     */
    public void numberRun() {

        logger.info("--------Le jeu a démarré------");
        gamechoice();
        gameMode();
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
        System.out.println();
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

    public void gamechoice()  {
        System.out.println("Veuillez choisir le jeu que vous voulez lancer : ");
        String[] gameCh = {"Recherche d'une combinaison de chiffre avec indicateurs +/-", "Recherche d'une combinaison de couleurs avec indicateurs de placement - Mastermind"};
        for (int i = 0; i < 2; i++)
            System.out.println(i + 1 + " - " + gameCh[i]);
        numberGame = manageException(numberGame, 2);
        logger.info("Le joueur a choisi le mode : " + numberGame);
    }

    /**
     * Display different game modes.
     * @return number choose of mode.
     */
    public void gameMode() {

        String[] arrayMode = {"1 - Mode Challenger : vous devez trouver la combinaison secrète de l'ordinateur", "2 - Mode Défenseur : où c'est à l'ordinateur de trouver votre combinaison secrète ", "3 - Mode duel : où l'ordinateur et vous jouez tour à tour, le premier à trouver la combinaison secrète de l'autre a gagné"};
        System.out.println("Veuillez choisir le mode de jeu :");
        for (int index = 0; index < arrayMode.length; index++)
            System.out.println(arrayMode[index]);
        gameMode = manageException(gameMode, 3);
        logger.info("Le joueur a choisi le mode : " + gameMode);
        System.out.println(" ");
    }


    /**
     * Function wich enable to manage exceptions
     * @param value
     * @return
     */
    private int manageException(int value, int numberChoice){
        do {
            try {
                value = sc.nextInt();
                if (value >= 1 && value <= numberChoice) {
                    numberchoiceIsGood = true;
                } else {
                    logger.error("Le joueur n'a pas entré un numéro valide lors de la sélection du jeu ou du mode. Valeur :  " + value);
                    numberchoiceIsGood = false;
                    System.out.println("Vous devez saisir un nombre valide.");
                }

            } catch (RuntimeException e) {
                logger.error("Une exception est survenue lors du choix du jeu.");
                sc.next();
                System.out.println("Vous devez saisir un nombre valide parmis la liste ci-dessus.");
                numberchoiceIsGood = false;

            }
        } while (!numberchoiceIsGood);
        return value;
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








