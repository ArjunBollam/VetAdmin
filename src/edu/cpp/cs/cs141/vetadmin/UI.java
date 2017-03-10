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

    private int pets;

    /**
     * The {@link Scanner} used for user input
     */
    private Scanner in;

    /**
     * The constructor for the {@link UI} class.
     *
     * @param regisrty The regisrty to be used
     */
    public UI(Registry regisrty) {
        this.registry = regisrty;
        in = new Scanner(System.in);
        first = true;
        debug = false;
        pets = 0;

    }

    /**
     * This method runs the program
     */
    public void runProgram() {
        if (first) {
            System.out.println("--------WELCOME--------");
            first = false;
        }

        if (debug) {
            registry.newCat("Harold", 3, 2);
            registry.newDog("Lollipop", 2, 4);
            registry.newFish("Kai", 3, 1);
        }

        mainMenu();
    }

    /**
     * This method is the main menu for the program
     */
    private void mainMenu() {
        System.out.print("---------MAIN MENU---------" +
                "\nPlease make a selection: " +
                "\n1. Registry - View or search for pets and owners" +
                "\n// Appointments - View, edit, and create appointments" +
                "\n// Save/Load - Save and load registries" +
                "\n0. Quit" +
                "\n> ");

        try {
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    in.nextLine();
                    registryDialogue();
                    break;
                case 2:
                    in.nextLine();
                    appointmentsDialogue();
                    break;
                case 3:
                    in.nextLine();
                    saveLoadDialogue();
                    break;
                case 0:
                    in.nextLine();
                    exit();
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    mainMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            mainMenu();
        }
    }

    private void registryDialogue() {
        System.out.println("--------NOW VIEWING REGISTRY MENU--------");
        System.out.print("Please make a selection:" +
                "\n1. Pets - View all pets" +
                "\n2. New Pet - Register a new pet" +
                "\n3. Owners - View all owners" +
                "\n// Search - Search the registry for pets and owners" +
                "\n5. Back to Main Menu" +
                "\n0. Quit" +
                "\n> ");

        try {
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    in.nextLine();
                    viewPets();
                    break;
                case 2:
                    in.nextLine();
                    registerPet();
                    registryDialogue();
                    break;
                case 3:
                    in.nextLine();
                    viewOwners();
                    break;
                case 4:
                    in.nextLine();
                    searchRegistry();
                    break;
                case 5:
                    in.nextLine();
                    mainMenu();
                    break;
                case 0:
                    exit();
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    registryDialogue();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            registryDialogue();
        }
    }

    private void viewPets() {
        if (registry.getPets().isEmpty()) {
            System.out.println("No pets registered.");
            registryDialogue();
        } else {
            System.out.println(registry.stringPets());
        }

        System.out.print("Please make a selection:" +
                "\n1. Select - Display record and options for a specific pet" +
                "\n2. View Records - Display the records for all pets" +
                "\n3. Back" +
                "\n0. Quit" +
                "\n> ");

        try {
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Please enter the number of the pet:\n> ");
                    int pet = in.nextInt();
                    petOptions(registry.getPets().get(--pet));
                    in.nextLine();
                    break;
                case 2:
                    registry.printRegistry();
                    in.nextLine();
                    viewPets();
                    break;
                case 3:
                    in.nextLine();
                    registryDialogue();
                    break;
                case 0:
                    exit();
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    viewPets();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            viewPets();
        }
    }

    private void petOptions(Pet pet) {
        System.out.println("--------NOW VIEWING " + pet.getName().toUpperCase() + "'S RECORD--------");
        System.out.println(registry.getRecord(registry.getPets().indexOf(pet)));
        System.out.println("---------------------------------------");
        if (pet.getOwner().getName().equals("Not Registered")) {
            System.out.print("Please make a selection:" +
                    "\n1. Edit Info - Edit " + pet.getName() + "'s name, age, and more" +
                    "\n// New Appointment - Book a new appointment for " + pet.getName() +
                    "\n// Deregister - Remove " + pet.getName() + " from the registry" +
                    "\n4. Back" +
                    "\n5. Register Owner - Add " + pet.getName() + "'s owner to the registry" +
                    "\n0. Quit" +
                    "\n> ");
        } else {
            System.out.print("Please make a selection:" +
                    "\n1. Edit Info - Edit " + pet.getName() + "'s name, age, and more" +
                    "\n// New Appointment - Book a new appointment for " + pet.getName() +
                    "\n// Deregister - Remove " + pet.getName() + " from the registry" +
                    "\n4. Back" +
                    "\n0. Quit" +
                    "\n> ");
        }

        try {
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    in.nextLine();
                    editPet(pet);
                    break;
                case 2:
                    in.nextLine();
                    appointmentsDialogue();
                    break;
                case 3:
                    in.nextLine();
                    deregisterPet(pet);
                    break;
                case 4:
                    in.nextLine();
                    viewPets();
                    break;
                case 5:
                    in.nextLine();
                    registerOwner(pet);
                    break;
                case 0:
                    exit();
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    petOptions(pet);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            petOptions(pet);
        }
    }

    private void editPet(Pet pet) {
        System.out.println("--------NOW EDITING " + pet.getName().toUpperCase() +
                "'S RECORD--------");

        newPetName(pet);
        newPetAge(pet);
        addMedHistDialogue(pet, false);
        if (!pet.getMedHist().isEmpty())
            removeMedHistDialogue(pet, false);
        addVaccDialogue(pet, false);
        if (!pet.getVaccinations().isEmpty())
            removeVaccDialogue(pet, false);
        petOptions(pet);
    }

    private void newPetName(Pet pet) {
        System.out.println("Would you like to change " + pet.getName() +
                "'s name (Y/N)?");
        try {
            String choice = in.nextLine();

            switch (choice) {
                case "Y":
                case "y":
                    System.out.print(pet.getName() + "'s new name: ");
                    String name = in.nextLine();
                    pet.setName(name);
                    in.nextLine();
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    newPetName(pet);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newPetName(pet);
        }
    }

    private void newPetAge(Pet pet) {
        System.out.println("Would you like to change " + pet.getName() +
                "'s age (Y/N)?");
        try {
            String choice = in.nextLine();

            switch (choice) {
                case "Y":
                case "y":
                    System.out.print(pet.getName() + "'s new age: ");
                    int age = in.nextInt();
                    pet.setAge(age);
                    in.nextLine();
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    newPetAge(pet);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newPetAge(pet);
        }

    }

    private void addMedHistDialogue(Pet pet, boolean checked) {
        String choice;
        if (!checked) {
            System.out.println("Would you like to add medical history (Y/N)?");
            choice = in.nextLine();
        } else
            choice = "y";

        try {
            switch (choice) {
                case "Y":
                case "y":
                    System.out.print("Disease: ");
                    String disease = in.nextLine();
                    System.out.print("Status (Current or Treated?): ");
                    String status = in.nextLine();
                    pet.addMedHist(disease, status);
                    System.out.println("Add another (Y/N)?");
                    try {

                        choice = in.nextLine();
                        switch (choice) {
                            case "Y":
                            case "y":
                                addMedHistDialogue(pet, true);
                            case "N":
                            case "n":
                                break;
                            default:
                                System.out.println("Invalid entry! Please try again.\n");
                                in.nextLine();
                                addMedHistDialogue(pet, true);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry! Please try again.\n");
                        in.nextLine();
                        addMedHistDialogue(pet, true);
                    }
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    addMedHistDialogue(pet, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            addMedHistDialogue(pet, true);
        }

    }

    private void removeMedHistDialogue(Pet pet, boolean checked) {
        String choice;
        if (!checked) {
            System.out.println("Would you like to remove medical history (Y/N)?");
            choice = in.nextLine();
        } else
            choice = "y";

        try {
            switch (choice) {
                case "Y":
                case "y":
                    System.out.println(pet.stringMedHist());
                    System.out.print("Please enter the number of the line:\n> ");
                    int index = in.nextInt();
                    in.nextLine();
                    pet.removeMedHist(--index);
                    System.out.println("Remove another (Y/N)?");
                    try {
                        choice = in.nextLine();
                        switch (choice) {
                            case "Y":
                            case "y":
                                removeMedHistDialogue(pet, true);
                            case "N":
                            case "n":
                                break;
                            default:
                                System.out.println("Invalid entry! Please try again.\n");
                                in.nextLine();
                                removeMedHistDialogue(pet, true);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry! Please try again.\n");
                        in.nextLine();
                        removeMedHistDialogue(pet, true);
                    }
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    removeMedHistDialogue(pet, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            removeMedHistDialogue(pet, true);
        }
    }

    private void addVaccDialogue(Pet pet, boolean checked) {
        String choice;
        if (!checked) {
            System.out.println("Would you like to add vaccination history (Y/N)?");
            choice = in.nextLine();
        } else
            choice = "y";

        try {
            switch (choice) {
                case "Y":
                case "y":
                    System.out.print("Vaccine: ");
                    String vacc = in.nextLine();
                    System.out.print("Date: ");
                    String date = in.nextLine();
                    pet.addVaccination(vacc, date);
                    System.out.println("Add another (Y/N)?");
                    try {
                        choice = in.nextLine();
                        switch (choice) {
                            case "Y":
                            case "y":
                                addVaccDialogue(pet, true);
                            case "N":
                            case "n":
                                break;
                            default:
                                System.out.println("Invalid entry! Please try again.\n");
                                addVaccDialogue(pet, true);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry! Please try again.\n");
                        in.nextLine();
                        addVaccDialogue(pet, true);
                    }
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    addVaccDialogue(pet, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            addVaccDialogue(pet, true);
        }
    }

    private void removeVaccDialogue(Pet pet, boolean checked) {
        String choice;
        if (!checked) {
            System.out.println("Would you like to remove a vaccination from history (Y/N)?");
            choice = in.nextLine();
        } else
            choice = "y";

        try {
            switch (choice) {
                case "Y":
                case "y":
                    System.out.println(pet.stringVaccinations());
                    System.out.print("Please enter the number of the line:\n> ");
                    int index = in.nextInt();
                    pet.removeMedHist(--index);
                    System.out.println("Remove another (Y/N)?");
                    try {
                        choice = in.nextLine();
                        switch (choice) {
                            case "Y":
                            case "y":
                                removeVaccDialogue(pet, true);
                            case "N":
                            case "n":
                                break;
                            default:
                                System.out.println("Invalid entry! Please try again.\n");
                                in.nextLine();
                                removeVaccDialogue(pet, true);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry! Please try again.\n");
                        in.nextLine();
                        removeVaccDialogue(pet, true);
                    }
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    removeVaccDialogue(pet, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            removeVaccDialogue(pet, true);
        }
    }

    private void registerPet() {
        System.out.println("--------REGISTERING NEW PET--------");
        System.out.print("What kind of animal is this pet?" +
                "\n1. Cat" +
                "\n2. Dog" +
                "\n3. Fish" +
                "\n> ");

        try {
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    in.nextLine();
                    newCat();
                    break;
                case 2:
                    in.nextLine();
                    newDog();
                    break;
                case 3:
                    in.nextLine();
                    newFish();
                    break;
                case 0:
                    in.nextLine();
                    exit();
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    registerPet();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            registerPet();
        }
    }

    private void newCat() {
        System.out.print("Name: ");
        String name = in.nextLine();

        System.out.print("Age: ");
        double age = in.nextDouble();

        System.out.print("Choose a breed: " +
                "\n1. SIAMESE" +
                "\n2. PERSIAN" +
                "\n3. BURMESE" +
                "\n4. BOBTAIL" +
                "\n5. SIBERIAN" +
                "\n> ");
        try {
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    registry.newCat(name, age, 0);
                    in.nextLine();
                    break;
                case 2:
                    registry.newCat(name, age, 1);
                    in.nextLine();
                    break;
                case 3:
                    registry.newCat(name, age, 2);
                    in.nextLine();
                    break;
                case 4:
                    registry.newCat(name, age, 3);
                    in.nextLine();
                    break;
                case 5:
                    registry.newCat(name, age, 4);
                    in.nextLine();
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    newCat();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newCat();
        }
    }

    private void newDog() {
        System.out.print("Name: ");
        String name = in.nextLine();

        System.out.print("Age: ");
        double age = in.nextDouble();

        System.out.print("Choose a breed: " +
                "\n1. HUSKY" +
                "\n2. PUG" +
                "\n3. LAB" +
                "\n4. YORKIE" +
                "\n5. PEKINGESE" +
                "\n> ");
        try {
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    registry.newDog(name, age, 0);
                    in.nextLine();
                    break;
                case 2:
                    registry.newDog(name, age, 1);
                    in.nextLine();
                    break;
                case 3:
                    registry.newDog(name, age, 2);
                    in.nextLine();
                    break;
                case 4:
                    registry.newDog(name, age, 3);
                    in.nextLine();
                    break;
                case 5:
                    registry.newDog(name, age, 4);
                    in.nextLine();
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    newDog();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newDog();
        }
    }

    private void newFish() {
        System.out.print("Name: ");
        String name = in.nextLine();

        System.out.print("Age: ");
        double age = in.nextDouble();

        System.out.print("Choose a breed: " +
                "\n1. GOLDFISH" +
                "\n2. BETTA" +
                "\n3. ANGELFISH" +
                "\n4. CLOWNFISH" +
                "\n5. GUPPY" +
                "\n> ");
        try {
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    registry.newFish(name, age, 0);
                    in.nextLine();
                    break;
                case 2:
                    registry.newFish(name, age, 1);
                    in.nextLine();
                    break;
                case 3:
                    registry.newFish(name, age, 2);
                    in.nextLine();
                    break;
                case 4:
                    registry.newFish(name, age, 3);
                    in.nextLine();
                    break;
                case 5:
                    registry.newFish(name, age, 4);
                    in.nextLine();
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    newFish();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newFish();
        }
    }

    private void registerOwner(Pet pet) {
        System.out.println("--------REGISTERING " + pet.getName().toUpperCase() +
                "'S OWNER--------");
        System.out.print("Name: ");
        String name = in.nextLine();
        pet.getOwner().setName(name);

        System.out.print("Address: ");
        String address = in.nextLine();
        pet.getOwner().setAddress(address);

        System.out.print("Phone Number: ");
        String phone = in.nextLine();
        pet.getOwner().setPhone(phone);

        pet.getOwner().addPet(pet);
        registry.addOwner(pet.getOwner());
        registry.sort();

        petOptions(pet);
    }

    private void deregisterPet(Pet pet) {

    }

    private void viewOwners() {
        if (registry.getOwners().isEmpty()) {
            System.out.println("No owners registered.");
            registryDialogue();
        } else {
            System.out.println(registry.stringOwners());
        }

        System.out.print("Please make a selection:" +
                "\n1. Select - Display record and options for a specific owner" +
                "\n//. View All - Display the info for all owners" +
                "\n3. Back" +
                "\n0. Quit" +
                "\n> ");

        try {
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Please enter the number of the owner:\n> ");
                    int owner = in.nextInt();
                    ownerOptions(registry.getOwners().get(--owner));
                    in.nextLine();
                    break;
                case 2:
                    registry.printRegistry();
                    in.nextLine();
                    viewPets();
                    break;
                case 3:
                    in.nextLine();
                    registryDialogue();
                    break;
                case 0:
                    exit();
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    viewPets();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            viewPets();
        }
    }

    private void ownerOptions(Owner owner){
        System.out.println("--------NOW VIEWING " + owner.getName().toUpperCase() + "'S INFO--------");
        System.out.println(registry.getRecord(registry.getOwners().indexOf(owner)));
        System.out.println("---------------------------------------");
        if (owner.getPets().isEmpty()) {
            System.out.print("Please make a selection:" +
                    "\n1. Edit Info - Edit " + owner.getName() + "'s name, address, and more" +
                    "\n// New Appointment - Book a new appointment for " + owner.getName() +
                    "\n// Deregister - Remove " + owner.getName() + " from the registry" +
                    "\n4. Back" +
                    "\n//. Register Pet - Add " + owner.getName() + "'s pet to the registry" +
                    "\n0. Quit" +
                    "\n> ");
        } else {
            System.out.print("Please make a selection:" +
                    "\n1. Edit Info - Edit " + owner.getName() + "'s name, address, and more" +
                    "\n// New Appointment - Book a new appointment for " + owner.getName() +
                    "\n// Deregister - Remove " + owner.getName() + " from the registry" +
                    "\n4. Back" +
                    "\n0. Quit" +
                    "\n> ");
        }

        try {
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    in.nextLine();
                    editOwner(owner);
                    break;
                case 2:
                    in.nextLine();
                    appointmentsDialogue();
                    break;
                case 3:
                    in.nextLine();
                    deregisterowner(owner);
                    break;
                case 4:
                    in.nextLine();
                    viewowners();
                    break;
                case 5:
                    in.nextLine();
                    registerOwner(owner);
                    break;
                case 0:
                    exit();
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    ownerOptions(owner);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            ownerOptions(owner);
        }
    }

    private void editOwner(Owner owner){
        System.out.println("--------NOW EDITING " + owner.getName().toUpperCase() +
                "'S INFO--------");

        newOwnerName(owner);
        newOwnerAddress(owner);
        newOwnerPhone(owner);
        addPet(owner, true);
        if (!owner.getPets().isEmpty())
            removePet(owner, false);
        ownerOptions(owner);
    }

    private void newOwnerName(Owner owner){
        System.out.println("Would you like to change " + owner.getName() +
                "'s name (Y/N)?");
        try {
            String choice = in.nextLine();

            switch (choice) {
                case "Y":
                case "y":
                    System.out.print(owner.getName() + "'s new name: ");
                    String name = in.nextLine();
                    owner.setName(name);
                    in.nextLine();
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    newOwnerName(owner);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newOwnerName(owner);
        }
    }

    private void newOwnerAddress(Owner owner){
        System.out.println("Would you like to change " + owner.getName() +
                "'s address (Y/N)?");
        try {
            String choice = in.nextLine();

            switch (choice) {
                case "Y":
                case "y":
                    System.out.print(owner.getName() + "'s new address: ");
                    String address = in.nextLine();
                    owner.setName(address);
                    in.nextLine();
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    newOwnerAddress(owner);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newOwnerAddress(owner);
        }
    }

    private void newOwnerPhone(Owner owner){
        System.out.println("Would you like to change " + owner.getName() +
                "'s phone number (Y/N)?");
        try {
            String choice = in.nextLine();

            switch (choice) {
                case "Y":
                case "y":
                    System.out.print(owner.getName() + "'s new phone number: ");
                    String phone = in.nextLine();
                    owner.setName(phone);
                    in.nextLine();
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    newOwnerPhone(owner);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newOwnerPhone(owner);
        }
    }

    private void addPet(Owner owner, boolean checked){
        String choice;
        if (!checked) {
            System.out.println("Would you like to link this owner to a pet (Y/N)?");
            choice = in.nextLine();
        } else
            choice = "y";

        try {
            switch (choice) {
                case "Y":
                case "y":
                    System.out.print("Disease: ");
                    String disease = in.nextLine();
                    System.out.print("Status (Current or Treated?): ");
                    String status = in.nextLine();
                    pet.addMedHist(disease, status);
                    System.out.println("Add another (Y/N)?");
                    try {

                        choice = in.nextLine();
                        switch (choice) {
                            case "Y":
                            case "y":
                                addMedHistDialogue(pet, true);
                            case "N":
                            case "n":
                                break;
                            default:
                                System.out.println("Invalid entry! Please try again.\n");
                                in.nextLine();
                                addMedHistDialogue(pet, true);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry! Please try again.\n");
                        in.nextLine();
                        addMedHistDialogue(pet, true);
                    }
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    in.nextLine();
                    addMedHistDialogue(pet, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            addMedHistDialogue(pet, true);
        }

    }

    private void searchRegistry() {

    }

    private void appointmentsDialogue() {

    }

    private void saveLoadDialogue() {

    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void printLn() {
        System.out.println("");
    }

    private void exit() {
        //save
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
