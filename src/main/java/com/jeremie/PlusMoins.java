package com.jeremie;


public class PlusMoins extends BaseGame {

    private boolean find;
    private boolean[] equal;
    private boolean[] more;
    private boolean[] less;

    /**
     * @param nbSize size of the combination.
     * @param nbTry maximum try/turn to find combination.
     * @param devMode enable display combination when the game at started in dev mode.
     */
    public PlusMoins(int nbSize, int nbTry, boolean devMode) {
        super(nbSize, nbTry, devMode);
        this.find = false;
        this.equal = new boolean[nbSize];
        this.more = new boolean[nbSize];
        this.less = new boolean[nbSize];
    }

    /**
     * Calculation of a random number.
     * @return the random number.
     */
    public void combinationRandom() { //revoir cette méthode
            int bMin = (int) Math.pow(10, nbSize - 1);
            int bMax = (int) Math.pow(10, nbSize);
            randomNumber = (int) (Math.random() * (bMax - bMin)) + bMin;
    }

    /**
     * Start challenge mode, The user propose a secret combination that the computer have to find.
     */
    @Override
    public void challengeMode() {
        this.combinationRandom();
        combination = String.valueOf(randomNumber);
        displaySolutionForDev(combination); // if mode dev then display solution
        do {
            displayProposal(gameTypes.MORELESS, gameModes.CHALLENGE, userTypes.HUMAN, 0, null);
            compareAndDisplayIndicatorsPlacement(myAnswer, combination);
            System.out.println();
            nbTry--;
            find = result(combination, myAnswer,"human");
        } while (!find && nbTry != 0);
    }

    /**
     * Start defense mode. The computer have to search a random combination generated by you.
     */
    @Override
    public void defenseMode() {
        choiceCombinationToComputer("numCombiToComputer");
        this.combinationRandom();
        computerAnswer = String.valueOf(randomNumber);
        while(!find && tentative <= nbTry) {
            displayProposal(gameTypes.MORELESS, gameModes.DEFENSE, userTypes.COMPUTER, 0, computerAnswer);
            compareAndDisplayIndicatorsPlacement(computerAnswer, myCombinationThatComputerFind);
            find = result(myCombinationThatComputerFind, computerAnswer,"computer");
            comparePlacement(computerAnswer, myCombinationThatComputerFind);
            computerAnswer = computerReflexion(equal, more, less, myCombinationThatComputerFind);
            tentative++;
        }
    }

    /**
     * Start duel mode. Switch between user and computer to search a random combination.
     */
    @Override
    public void duelMode() {
        int nombre = 0;
        this.combinationRandom();
        combination = String.valueOf(randomNumber);
        this.combinationRandom();
        computerAnswer = String.valueOf(randomNumber);
        displaySolutionForDev(combination);

        do {
            if (nombre % 2 == 0) {
                displayProposal(gameTypes.MORELESS, gameModes.DUAL, userTypes.HUMAN, counter1, null);
                counter1++;
                compareAndDisplayIndicatorsPlacement(myAnswer, combination);
                find = result(combination, myAnswer, "human");
            } else {
                displayProposal(gameTypes.MORELESS, gameModes.DUAL, userTypes.COMPUTER, counter2, computerAnswer);
                counter2++;
                compareAndDisplayIndicatorsPlacement(computerAnswer, combination);
                find = result(combination, computerAnswer,"computer");
                comparePlacement(computerAnswer, combination);
                computerAnswer = computerReflexion(equal, more, less, combination);
            }
            System.out.println();
            nombre++;
        } while (!find);
    }

    /**
     * Comparison between answer and combination.
     * @param answer your answer or computer answer.
     * @param combination secret combination.
     */
    private void compareAndDisplayIndicatorsPlacement(String answer, String combination) {
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == combination.charAt(i)) {
                System.out.print("=");
            } else if (answer.charAt(i) < combination.charAt(i)) {
                System.out.print("+");
            } else if (answer.charAt(i) > combination.charAt(i)) {
                System.out.print("-");
            }
        }
        System.out.println();
    }

    /**
     * Comparison between answer and combination.
     * @param answer your answer or computer answer.
     * @param combination secret combination.
     */
    private void comparePlacement(String answer, String combination) {
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == combination.charAt(i)) {
                equal[i] = true;
            } else if (answer.charAt(i) < combination.charAt(i)) {
                more[i] = true;
            } else if (answer.charAt(i) > combination.charAt(i)) {
                less[i] = true;
            }
        }
    }

    /**
     * The computer is thinking about proposing an answer based on the indicators.
     * @param equal Array which catch with a boolean equal values between combination and answer
     * @param more  Array which catch with a boolean for greater number values between combination and answer
     * @param less  Array which catch with a boolean for smaller number values between combination and answer
     */

    private String computerReflexion(boolean[] equal, boolean[] more, boolean[] less, String yourResponseThatOrdiFind){
        char[] computerAnswers = new char[nbSize];
        String answer = "";
        for(int index = 0; index < nbSize; index++){
            computerAnswers[index] = computerAnswer.charAt(index);
        }
        for(int index = 0; index < nbSize; index++){
            int entier = 0;
            int newEntier = 0;
            if(equal[index] == true){
                computerAnswers[index] = yourResponseThatOrdiFind.charAt(index);
            }
            else if(more[index] == true){
                entier = Character.getNumericValue(computerAnswer.charAt(index));
                newEntier = entier + 1;
                computerAnswers[index] = Character.forDigit(newEntier,10);
            }
            else if(less[index] == true){
                entier = Character.getNumericValue(computerAnswer.charAt(index));
                newEntier = entier - 1;
                computerAnswers[index] = Character.forDigit(newEntier,10);
            }
            answer += computerAnswers[index];
        }
        return answer;
    }
}

