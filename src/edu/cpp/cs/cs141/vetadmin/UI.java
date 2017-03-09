package edu.cpp.cs.cs141.vetadmin;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class contains all of the methods that make up the User Interface.
 * All input and output occurs here.
 */
public class UI {

    private boolean first, debug;

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
        debug = true;

    }

    /**
     * This method runs the program //in a loop
     */
    public void runProgram(){
        if(first){
            System.out.println("--------WELCOME--------");
            first = false;
        }

        if(debug){
            registry.newCat("Harold", 3, 2);
            registry.createRecord(registry.getPets().get(0));
            registry.newDog("Lollipop", 2, 4);
            registry.createRecord(registry.getPets().get(1));
            registry.newFish("Kai", 3, 1);
            registry.createRecord(registry.getPets().get(2));
        }

        mainMenu();
    }

    /**
     * This method is the main menu for the program
     */
    private void mainMenu(){
        System.out.println("--------MAIN MENU--------" +
                "\nPlease make a selection: " +
                "\n1. Registry - View or search for pets and owners" +
                "\n2. Appointments - View, edit, and create appointments" +
                "\n3. Save/Load - Save and load registries" +
                "\n0. Quit" +
                "\n> ");

        try{
            int choice = in.nextInt();

            switch(choice){
                case 1: registryDialogue();
                        break;
                case 2: appointmentsDialogue();
                        break;
                case 3: saveLoadDialogue();
                        break;
                case 0: exit();
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
                "\n4. Back to Main Menu" +
                "\n0. Quit" +
                "\n> ");

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
                case 0: exit();
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
            System.out.println(registry.stringPets());
        }

        System.out.print("Please make a selection:" +
                "\n1. Select - Display options for a specific pet" +
                "\n2. Back" +
                "\n0. Quit" +
                "\n> ");

        try{
            int choice = in.nextInt();

            switch(choice){
                case 1: System.out.print("Please enter the number of the pet:\n> ");
                        int pet = in.nextInt();
                        petOptions(registry.getPets().get(pet));
                        break;
                case 2: registryDialogue();
                        break;
                case 0: exit();
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    viewPets();
            }
        } catch(InputMismatchException e){
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            viewPets();
        }
    }

    private void petOptions(Pet pet){
        System.out.println("--------NOW VIEWING " + pet.getName().toUpperCase() + "'s RECORD--------");
        System.out.println("Please make a selection:" +
                "\n1. Edit Info - Edit " + pet.getName() + "'s name, age, and more" +
                "\n2. New Appointment - Book a new appointment for " + pet.getName() +
                "\n3. Deregister - Search the registry for pets and owners" +
                "\n4. Back" +
                "\n0. Quit" +
                "\n> ");

        try{
            int choice = in.nextInt();

            switch(choice){
                case 1: editPet(pet);
                        break;
                case 2: appointmentsDialogue();
                        break;
                case 3: deregisterPet(pet);
                        break;
                case 4: viewPets();
                        break;
                case 0: exit();
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

    public void editPet(Pet pet){
        System.out.println("--------NOW EDITING " + pet.getName().toUpperCase() +
                "'s RECORD--------" +
                "\nYou can either 1) Enter new info OR 2) Press [ENTER] to keep info the same.");

        newPetName(pet);
        newPetAge(pet);
        addMedHistDialogue(pet);
        removeMedHistDialogue(pet);
        vaccDialogue(pet);
    }

    private void newPetName(Pet pet){
        System.out.print(pet.getName() + "'s new name: ");
        String name = in.nextLine();
        if(!name.isEmpty())
            pet.setName(name);
    }

    private void newPetAge(Pet pet){
        System.out.print(pet.getName() + "'s new age: ");
        int age = in.nextInt();
        if(age != 0)
            pet.setAge(age);
    }

    private void addMedHistDialogue(Pet pet){
        System.out.println("Would you like to add medical history (Y/N)?");
        try{
            String choice = in.nextLine();

            switch(choice){
                case "Y": case "y":
                    System.out.print("Disease: ");
                    String disease = in.nextLine();
                    System.out.print("Status (Current or Treated?): ");
                    String status = in.nextLine();
                    pet.addMedHist(disease, status);
                    break;
                case "N": case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    addMedHistDialogue(pet);
            }
        } catch(InputMismatchException e){
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            addMedHistDialogue(pet);
        }
    }

    private void removeMedHistDialogue(Pet pet){
        System.out.println("Would you like to remove medical history (Y/N)?");
        try{
            String choice = in.nextLine();

            switch(choice){
                case "Y": case "y":
                    System.out.println(pet.stringMedHist());
                    System.out.println("Please enter the number of the line:\n> ");
                    int index = in.nextInt();
                    pet.removeMedHist(--index);
                    break;
                case "N": case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    removeMedHistDialogue(pet);
            }
        } catch(InputMismatchException e){
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            removeMedHistDialogue(pet);
        }
    }

    private void vaccDialogue(Pet pet){

    }

    private void deregisterPet(Pet pet){

    }

    private void viewOwners(){

    }

    private void searchRegistry(){

    }

    private void appointmentsDialogue(){

    }

    private void saveLoadDialogue(){

    }

    public static void print(String str){
        System.out.println(str);
    }

    public static void printLn(){
        System.out.println("");
    }

    private void exit(){
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
