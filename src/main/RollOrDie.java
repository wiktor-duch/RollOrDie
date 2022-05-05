package main;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import fileHandling.Save;
import inputHandling.InputHandler;

public class RollOrDie {

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
     * Prints menus.
     * @param type Type of menu to be printed.
     */
    private static void printMenu() {
        // Print starting menu
        System.out.println("Welcome to ROLL OR DIE!\n\n");

        String menu = "    MAIN MENU\n"
                    + "\n1. New Game"
                    + "\n2. Load Game"
                    + "\n3. Game Info\n"
                    + "\n4. Quit\n";
        System.out.println(menu);
        
    }

    /**
     * Launches the game.
     */
    public RollOrDie() {
        // Print starting menu
        boolean quit = false;
        InputHandler ih = new InputHandler();
        File savesFolder;
        String saves[];
        Save save;
        while (!quit) {
            printMenu();

            // Get user input
            Console console = System.console();
            String input = "";
            String dummyInput = ""; // Input to allow the player see some information before continuing
            int choice = -1;
            boolean correctInput = false;
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
                    System.out.println("CREATING NEW GAME\n");
                    String playerName = console.readLine("Enter your nick: ");
                    
                    // Create new save
                    savesFolder = new File("./files/saves");
                    saves = savesFolder.list();
                    boolean saveNameCorrect = false;
                    String saveName;
                    do {
                        saveName = console.readLine("Enter save name: ");
                        if (ih.checkStringsOrIntegers(saveName)) {
                            if (saves == null) {
                                saveNameCorrect = true;
                            }
                            else {
                                // Check if save already exists
                                boolean saveNameInUse = false;
                                for (int i=0; i<saves.length; i++) {
                                    if (saves[i].equals(saveName)) {
                                        System.out.println("\n! Save with this name already exists. Try again. !\n");
                                        saveNameInUse = true;
                                    }
                                }
                                if (saveNameInUse == true) {
                                    saveNameCorrect = false;
                                } 
                                else {
                                    saveNameCorrect = true;
                                }
                            }
                        }
                        else {
                            System.out.println("\n! Save file name should contain characters and/or integers only. Try again. !\n");
                        }
                    } while (saveNameCorrect != true);
                    /* Creates new save
                    Player gets initially 100 coins
                    */
                    save = new Save(saveName, playerName, 0, 0, 100, 0);
                    System.out.println("\nYou have successfully created new save.\n");
                    dummyInput = console.readLine("Press [enter] to continue.");
                    printLineBreak();
                    MainMenu mainMenu = new MainMenu(save);
                    quit = mainMenu.launch();
                    break;
                
                case 2: // MAKE THIS WORK !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    printLineBreak();
                    System.out.println("LOADING SAVES\n");
                    savesFolder = new File("./files/saves");
                    saves = savesFolder.list();
                    if (saves == null) {
                        System.out.println("! You CANNOT load any game as you have no saves. !\n");
                    }
                    else {
                        for(int i=0; i<saves.length; i++) {
                            System.out.println(saves[i]);
                        }
                    }
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
                    }
                    dummyInput = console.readLine("\nPress [enter] to continue.");
                    printLineBreak();
                    break;
                
                case 4:
                    printLineBreak();
                    String quitInput = console.readLine("Do you want to quit the game?\n");
                    quit = ih.coverToBoolean(quitInput);
                    if (quit == true) {
                        System.out.println("\nGOODBYE");
                    }
                    printLineBreak();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        RollOrDie rod = new RollOrDie();
    }
}