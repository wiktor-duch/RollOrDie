package main;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

import fileHandling.Save;
import gameLogic.Game;
import inputHandling.InputHandler;

public class MainMenu {
    private Save save;

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

    public MainMenu(Save save) {
        this.save = save;
    }

    private void printMenu() {
        // Print game menu
        System.out.println("     ROLL OR DIE!\n");

        String welcomeMsg = String.join("", "Welcome, ", save.getPlayerName(),
                                    "\nYour money: ", String.valueOf(save.getMoney()), " credits\n");
        System.out.println(welcomeMsg);

        String menu = "    MAIN MENU\n"
                    + "\n1. Start Game"
                    + "\n2. Show Stats"
                    + "\n3. Game Info\n"
                    + "\n4. Save and Quit\n";
        System.out.println(menu);
    }

    public boolean launch() {
        boolean quit = false;
        InputHandler ih = new InputHandler();
        while (!quit) {
            printMenu();
        
            // Get user input
            Console console = System.console();
            String input = "";
            String gameMode = "";
            String dummyInput = ""; // Input to allow the player see some information before continuing
            int choice = -1;
            int mode = -1;
            boolean correctInput = false;
            boolean correctMode = false;
            do {
                input = console.readLine("Enter choice: ");
                if (ih.isInteger(1, 4, input)) {
                    switch (input) {
                        case "1":
                            choice = 1;
                            break;
                        case "2":
                            choice = 2;
                            break;
                        case "3":
                            choice = 3;
                            break;
                        case "4":
                            choice = 4;
                            break;
                    }
                    correctInput = true;
                }
                else {
                    printLineBreak();
                    printMenu();
                    System.out.println("! Incorrect input. Try again. !\n");
                }
            } while (correctInput != true);

            switch (choice) {
                case 1:
                    printLineBreak();
                    System.out.println("GAME OPTIONS\n");
                    System.out.println("1. Play against a friend." 
                                    + "\n2. Play against AI.\n");
                    do {
                        gameMode = console.readLine("Enter choice: ");
                        if (ih.isInteger(1, 2, gameMode)) {
                            switch (gameMode) {
                                case "1":
                                    mode = 1;
                                    break;
                                case "2":
                                    mode = 2;
                                    break;
                            }
                            correctMode = true;
                        }
                        else {
                            printLineBreak();
                            System.out.println("1. Play against a friend." 
                                            + "\n2. Play against AI.\n");
                            System.out.println("! Incorrect input. Try again. !\n");
                        }
                    } while (correctMode != true);
                    

                    // Play against a friend
                    if (mode == 1) {
                        String friendNick = console.readLine("Enter friend's nick: ");
                        Game g = new Game(0, save.getPlayerName(), friendNick, 100, 100);
                        g.launch();
                    }
                    // Play aagainst AI
                    else {
                        System.out.println("AI not implemented yet");
                        printLineBreak();
                    }
                    break;
                
                case 2:
                    printLineBreak();
                    System.out.println("YOUR STATISTICS\n");
                    System.out.println("Your name: " + save.getPlayerName()
                                    + "\nYou have: " + String.valueOf(save.getMoney()) + " credits"
                                    + "\nYou won: " + save.getGamesWon() + " game(s)"
                                    + "\nYou lost: " + save.getGamesLost() + " game(s)"
                                    + "\nYou earned " + save.getTotalMoneyEarned() + " credits in total");
                    dummyInput = console.readLine("\nPress [enter] to continue.");
                    printLineBreak();
                    break;

                case 3:
                    printLineBreak();
                    System.out.println("HOW TO PLAY\n");
  
                    BufferedReader br;
                    try {
                        File file = new File("./files/info.txt");
                        br = new BufferedReader(new FileReader(file));
                        String st;
                        while ((st = br.readLine()) != null) {
                            System.out.println(st);
                        }
                    } catch (IOException e) {
                        System.out.println("! An error occured while reading the instruction. !");
                        e.printStackTrace();
                    }
                    dummyInput = console.readLine("\nPress [enter] to continue.");
                    printLineBreak();
                    break;
                
                case 4:
                    printLineBreak();
                    String quitInput = console.readLine("Do you want to save and quit the game?\n");
                    quit = ih.coverToBoolean(quitInput);
                    if (quit == true) {
                        // Serialize
                        try {
                            FileOutputStream fos = new FileOutputStream("./files/saves/" + save.getSaveName());
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(save);
                            oos.close();
                            fos.close();
                            System.out.printf("\nGame saved as " + save.getSaveName());
                        } catch (IOException e) {
                            System.out.println("\nGame could not be saved.");
                        }
                        System.out.println("\nGOODBYE");
                    } 
                    printLineBreak();
                    break;
            }
        }
        return true;
    }
}
