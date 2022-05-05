package gameLogic;

import java.io.Console;

import inputHandling.InputHandler;

public class Game {
    private int mode; // Playing against friend (0) or AI (1)
    private String playerName;
    private String enemyName;
    private Deck playerDeck;
    private Deck enemyDeck;
    private int playerMoney;
    private int enemyMoney;

    public Game(int modeIn,
                String playerNameIn,
                String enemyNameIn,
                int playerMoneyIn,
                int enemyMoneyIn) {
        mode = modeIn;
        playerName = playerNameIn;
        enemyName = enemyNameIn;
        playerMoney = playerMoneyIn;
        enemyMoney = enemyMoneyIn;
}

public int launch() {
        int pot = 0; // Set center pot
        
        Poker poker = new Poker();

        boolean roundFinished;
        boolean gameFinished = false;
        int roundsWon = 0;
        int roundsLost = 0;

        Console console = System.console();
        InputHandler ih = new InputHandler();
        String dummyInput;
        
        while (!gameFinished) {
            // Initialize round
            int winner = 0;
            roundFinished = false;
            playerDeck = new Deck();
            enemyDeck = new Deck();
            // Take entry fee
            pot = 20;
            playerMoney -= 10;
            enemyMoney -= 10;

            while (!roundFinished) {
                printLineBreak();
                printBoard(roundsWon, roundsLost, pot);
                dummyInput = console.readLine("Press [enter] to continue.");

                // First bidding
                printLineBreak();
                System.out.println(playerName + "\'s turn.\n");
                System.out.println("1. Wait"
                                + "\n2. Rise by 5"
                                + "\n3. Rise by 10"
                                + "\n4. Rise by 20\n");
                boolean correctInput1 = false;
                int bid = 0;
                do {
                    String input1 = console.readLine("Enter choice: ");
                    if (ih.isInteger(1, 4, input1)) {
                        switch(input1) {
                            case "2":
                                bid = 5;
                                break;
                            case "3":
                                bid = 10;
                                break;
                            case "4":
                                bid = 20;
                                break;
                        }
                        correctInput1 = true;
                    }
                    else {
                        System.out.println("! Value must be between 1 and 4. !");
                    }
                } while (!correctInput1);
                
                // Second bidding
                printLineBreak();
                System.out.println(enemyName + "\'s turn.\n");
                if (bid == 20) {
                    System.out.println("1. Accept (20)"
                                    + "\n2. Pass\n");
                }
                else if (bid == 10) {
                    System.out.println("1. Accept (10)"
                                    + "\n2. Rise by 10 (to 20)"
                                    + "\n3. Pass\n");
                }
                else if (bid == 5) {
                    System.out.println("1. Accept (5)"
                                    + "\n2. Rise by 5 (to 10)"
                                    + "\n3. Rise by 15 (to 20)"
                                    + "\n4. Pass\n");
                }
                else {
                    System.out.println("1. Wait"
                                    + "\n2. Rise by 5"
                                    + "\n3. Rise by 10"
                                    + "\n4. Rise by 20"
                                    + "\n5. Pass\n");
                }
                boolean correctInpu2 = false;
                boolean enemyRise = false;
                int finalBid = -1;
                do {
                    String input2 = console.readLine("Enter choice: ");
                    if (bid == 5 && ih.isInteger(1, 4, input2)) {
                        switch (input2) {
                            case "1":
                                finalBid = bid;
                                break;
                            case "2":
                                finalBid = 10;
                                enemyRise = true;
                                break;
                            case "3":
                                finalBid = 20;
                                enemyRise = true;
                                break;
                            case "4":
                                roundFinished = true;
                                winner = 1;
                                playerMoney += pot; // Player wins the pot
                        }
                        correctInpu2 = true;
                    }
                    else if (bid == 10 && ih.isInteger(1, 3, input2)) {
                        switch (input2) {
                            case "1":
                                finalBid = bid;
                                break;
                            case "2":
                                enemyRise = true;
                                finalBid = 20;
                                break;
                            case "3":
                                roundFinished = true;
                                winner = 1;
                                playerMoney += pot; // Player wins the pot
                        }
                        correctInpu2 = true;
                    }
                    else if (bid == 20 && ih.isInteger(1, 2, input2)) {
                        switch (input2) {
                            case "1":
                                finalBid = bid;
                                break;
                            case "2":
                                roundFinished = true;
                                winner = 1;
                                playerMoney += pot; // Player wins the pot
                        }
                        correctInpu2 = true;
                    }
                    else if (bid == 0 && ih.isInteger(1, 5, input2)) {
                        switch (input2) {
                            case "1":
                                finalBid = bid;
                                break;
                            case "2":
                                enemyRise = true;
                                finalBid = 5;
                                break;
                            case "3":
                                enemyRise = true;
                                finalBid = 10;
                                break;
                            case "4":
                                enemyRise = true;
                                finalBid = 20;
                                break;
                            case "5":
                                roundFinished = true;
                                winner = 1;
                                playerMoney += pot; // Player wins the pot
                        }
                        correctInpu2 = true;
                    }
                    else {
                        System.out.println("! Incorrect input. !");
                    }
                } while (!correctInpu2);

                // Third bidding
                boolean continueRound = true;
                if (roundFinished != true) { // Enemy decided to play
                    printLineBreak();
                    if (enemyRise == true) { // Enemy rised
                        System.out.println(playerName + "\'s turn.\n");
                        System.out.println(enemyName + " rised to " + String.valueOf(finalBid));
                        System.out.println("\n1. Accept"
                                        + "\n2. Pass\n");
                        boolean correctInpu3 = false;
                        do {
                            String input3 = console.readLine("Enter choice: ");
                            if (ih.isInteger(1, 2, input3)) {
                                if (input3.equals("2")) {
                                    continueRound = false;
                                }
                                correctInpu3 = true;
                            }
                            else {
                                System.out.println("! Value must be between 1 and 2. !");
                            }
                        } while (!correctInpu3);
                    }

                    if (continueRound != true) {
                        roundFinished = true;
                        winner = -1;
                        enemyMoney += pot; // Enemy wins the pot
                    }
                    else {
                        pot += finalBid*2;
                        playerMoney -= finalBid;
                        enemyMoney -= finalBid;
                        printLineBreak();
                        printBoard(roundsWon, roundsLost, pot);
                        
                        // Rerolling
                        System.out.println("\n" + playerName + " can select dices to reroll.\n");
                        int playerDicesToReroll[] = new int[5];
                        for (int i=0; i<5; i++) {
                            playerDicesToReroll[i] = 0; // Initialize all to 0
                        }
                        // Player chooses dices to reroll
                        boolean playerFinished = false;
                        do {
                            String playerInput = console.readLine("Enter dice number [1-5] or \'end\' to finish: ");
                            if (ih.isInteger(1, 5, playerInput)) {
                                switch (playerInput) {
                                    case "1":
                                        if (playerDicesToReroll[0] == 0) {
                                            playerDicesToReroll[0] = 1;
                                            System.out.println("1st dice selected to reroll.\n");
                                        } else {
                                            playerDicesToReroll[0] = 0;
                                            System.out.println("1st dice unselected.\n");
                                        }
                                        break;
                                    case "2":
                                        if (playerDicesToReroll[1] == 0) {
                                            playerDicesToReroll[1] = 1;
                                            System.out.println("2nd dice selected to reroll.\n");
                                        } else {
                                            playerDicesToReroll[1] = 0;
                                            System.out.println("2nd dice unselected.\n");
                                        }
                                        break;
                                    case "3":
                                        if (playerDicesToReroll[2] == 0) {
                                            playerDicesToReroll[2] = 1;
                                            System.out.println("3rd dice selected to reroll.\n");
                                        } else {
                                            playerDicesToReroll[2] = 0;
                                            System.out.println("3rd dice unselected.\n");
                                        }
                                        break;
                                    case "4":
                                        if (playerDicesToReroll[3] == 0) {
                                            playerDicesToReroll[3] = 1;
                                            System.out.println("4th dice selected to reroll.\n");
                                        } else {
                                            playerDicesToReroll[3] = 0;
                                            System.out.println("4th dice unselected.\n");
                                        }
                                        break;
                                    case "5":
                                        if (playerDicesToReroll[4] == 0) {
                                            playerDicesToReroll[4] = 1;
                                            System.out.println("5th dice selected to reroll.\n");
                                        } else {
                                            playerDicesToReroll[4] = 0;
                                            System.out.println("5th dice unselected.\n");
                                        }
                                        break;
                                }
                            }
                            else if (playerInput.equals("end")) {
                                playerFinished = true;
                            }
                            else {
                                System.out.println("! Incorrect input. Try again. !\n");
                            }

                        } while (!playerFinished);

                        printLineBreak();
                        printBoard(roundsWon, roundsLost, pot);
                        //Repeat for enemy
                        System.out.println("\n" + enemyName + " can select dices to reroll.\n");
                        int enemyDicesToReroll[] = new int[5];
                        for (int i=0; i<5; i++) {
                            enemyDicesToReroll[i] = 0; // Initialize all to 0
                        }
                        // Enemy chooses dices to reroll
                        boolean enemyFinished = false;
                        do {
                            String enemyInput = console.readLine("Enter dice number [1-5] or \'end\' to finish: ");
                            if (ih.isInteger(1, 5, enemyInput)) {
                                switch (enemyInput) {
                                    case "1":
                                        if (enemyDicesToReroll[0] == 0) {
                                            enemyDicesToReroll[0] = 1;
                                            System.out.println("1st dice selected to reroll.\n");
                                        } else {
                                            enemyDicesToReroll[0] = 0;
                                            System.out.println("1st dice unselected.\n");
                                        }
                                        break;
                                    case "2":
                                        if (enemyDicesToReroll[1] == 0) {
                                            enemyDicesToReroll[1] = 1;
                                            System.out.println("2nd dice selected to reroll.\n");
                                        } else {
                                            enemyDicesToReroll[1] = 0;
                                            System.out.println("2nd dice unselected.\n");
                                        }
                                        break;
                                    case "3":
                                        if (enemyDicesToReroll[2] == 0) {
                                            enemyDicesToReroll[2] = 1;
                                            System.out.println("3rd dice selected to reroll.\n");
                                        } else {
                                            enemyDicesToReroll[2] = 0;
                                            System.out.println("3rd dice unselected.\n");
                                        }
                                        break;
                                    case "4":
                                        if (enemyDicesToReroll[3] == 0) {
                                            enemyDicesToReroll[3] = 1;
                                            System.out.println("4th dice selected to reroll.\n");
                                        } else {
                                            enemyDicesToReroll[3] = 0;
                                            System.out.println("4th dice unselected.\n");
                                        }
                                        break;
                                    case "5":
                                        if (enemyDicesToReroll[4] == 0) {
                                            enemyDicesToReroll[4] = 1;
                                            System.out.println("5th dice selected to reroll.\n");
                                        } else {
                                            enemyDicesToReroll[4] = 0;
                                            System.out.println("5th dice unselected.\n");
                                        }
                                        break;
                                }
                            }
                            else if (enemyInput.equals("end")) {
                                enemyFinished = true;
                            }
                            else {
                                System.out.println("! Incorrect input. Try again. !\n");
                            }

                        } while (!enemyFinished);

                        // Reroll and print board
                        for (int i=0; i<5; i++) {
                            if (playerDicesToReroll[i] == 1) {
                                playerDeck.reroll(i);
                            }
                            if (enemyDicesToReroll[i] == 1) {
                                enemyDeck.reroll(i);
                            }
                        }
                        printLineBreak();
                        printBoard(roundsWon, roundsLost, pot);
                        winner = poker.compareDeck(playerDeck, enemyDeck);
                    }
                }

                // Round summary
                System.out.println("\nROUND SUMMARY\n");
                if (winner == 1) {
                    System.out.println(playerName + " WON!");
                    roundsWon ++;
                    roundFinished = true;
                    dummyInput = console.readLine("\nPress [enter] to continue.");
                }
                else if (winner  == -1) {
                    System.out.println(enemyName + " WON!");
                    roundsLost ++;
                    roundFinished = true;
                    dummyInput = console.readLine("\nPress [enter] to continue.");
                }
                else {
                    System.out.println("DRAW");
                    roundsWon ++;
                    roundsLost ++;
                    roundFinished = true;
                    dummyInput = console.readLine("\nPress [enter] to continue.");
                }
            }


            // Finish the game
            if (roundsWon == 2 || roundsLost == 2) {
                gameFinished = true;
            }
        }
        // Prints the summary of the game
        printLineBreak();
        System.out.println("GAME SUMMARY\n");
        if (roundsWon == 2 && roundsLost != 2) {
            System.out.println(playerName + " WON!");
            dummyInput = console.readLine("\nPress [enter] to continue.");
            return 1;
        }
        else if (roundsLost == 2 && roundsWon != 2) {
            System.out.println(playerName + " WON!");
            dummyInput = console.readLine("\nPress [enter] to continue.");
            return -1;
        }
        else {
            System.out.println("DRAW!");
            dummyInput = console.readLine("\nPress [enter] to continue.");
            return 0;
        }
    }

    /**
     * Prints line break.
     */
    private static void printLineBreak() {
        String line = "";
        for (int i=0; i<25; i++) {
            line = String.join("", line, "--");
        }
        System.out.println("\n" + line + "\n");
    }

    /**
     * Prints the board.
     * @param roundsWon Rounds won by the player.
     * @param roundsLost Rounds won by the enemy.
     * @param pot The total money to win.
     */
    private void printBoard(int roundsWon, int roundsLost, int pot) {
        System.out.println(enemyName);
        System.out.println("WINS: " + String.valueOf(roundsLost));
        System.out.println("MONEY: " + String.valueOf(enemyMoney));
        enemyDeck.printDeck();
        System.out.println("\n\t POT: " + String.valueOf(pot) + "\n");
        playerDeck.printDeck();
        System.out.println(playerName);
        System.out.println("WINS: " + String.valueOf(roundsWon));
        System.out.println("MONEY: " + String.valueOf(playerMoney));
    }

    public static void main(String args[]) {
        Game g = new Game(0, "player", "enemy", 100, 100);
        g.launch();
    }
}
