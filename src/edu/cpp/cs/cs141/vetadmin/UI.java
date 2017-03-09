package edu.cpp.cs.cs141.vetadmin;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class contains all of the methods that make up the User Interface.
 * All input and output occurs here.
 */
public class UI {

    private boolean first;

    /**
     * The {@link Registry} the program will run on
     */
    private Registry registry;

    /**
     * The {@link Scanner} used for user input
     */
    private Scanner in;

    /**
     * The constructor for the {@link UI} class.
     *
     * @param regisrty The regisrty to be used
     */
    public UI(Registry regisrty){
        this.registry = regisrty;
        in = new Scanner(System.in);
        first = true;
    }

    /**
     * This method runs the program //in a loop
     */
    public void runProgram(){
        if(first){
            System.out.println("--------WELCOME--------");
            first = false;
        }

        mainMenu();
    }

    /**
     * This method is the main menu for the program
     */
    public void mainMenu(){
        System.out.println("--------MAIN MENU--------" +
                "\nPlease make a selection: " +
                "\n1. Registry - View or search for pets and owners" +
                "\n2. Appointments - View, edit, and create appointments" +
                "\n3. Save/Load - Save and load registries");
        System.out.print("> ");

        try{
            int choice = in.nextInt();

            switch(choice){
                case 1: registryDialogue();
                        break;
                case 2: appointmentsDialogue();
                        break;
                case 3: saveLoadDialogue();
                        break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    mainMenu();
            }
        } catch(InputMismatchException e){
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            mainMenu();
        }
    }

    private void registryDialogue(){
        System.out.println("--------Now Viewing Registry--------");
        System.out.println("Please make a selection:" +
                "\n1. Pets - View all pets" +
                "\n2. Owners - View all owners" +
                "\n3. Search - Search the registry for pets and owners" +
                "\n4. Back to Main Menu");
        System.out.print("> ");

        try{
            int choice = in.nextInt();

            switch(choice){
                case 1: viewPets();
                    break;
                case 2: viewOwners();
                    break;
                case 3: searchRegistry();
                    break;
                case 4: mainMenu();
                        break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    registryDialogue();
            }
        } catch(InputMismatchException e){
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            registryDialogue();
        }
    }

    private void viewPets(){
        if(registry.getPets().isEmpty()){
            System.out.println("No pets registered.");
            registryDialogue();
        } else {
            System.out.println(registry);
        }

        System.out.println("Please make a selection:" +
                "\n1. Pets - View all pets" +
                "\n2. Owners - View all owners" +
                "\n3. Search - Search the registry for pets and owners" +
                "\n4. Back to Main Menu");
        System.out.print("> ");

        try{
            int choice = in.nextInt();

            switch(choice){
                case 1: viewPets();
                    break;
                case 2: viewOwners();
                    break;
                case 3: searchRegistry();
                    break;
                case 4: mainMenu();
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    registryDialogue();
            }
        } catch(InputMismatchException e){
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            registryDialogue();
        }
    }

    private void viewOwners(){

    }

    private void searchRegistry(){

    }

    private void appointmentsDialogue(){

    }

    private void saveLoadDialogue(){

    }
}
