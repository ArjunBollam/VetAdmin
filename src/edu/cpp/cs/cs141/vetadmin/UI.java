/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Homework 4: Vet Administration Program
 *
 * Create a text-based administration program for a vet's office.
 *
 * Mora Labisi
 */
package edu.cpp.cs.cs141.vetadmin;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class contains all of the methods that make up the User Interface.
 * All input and output occurs here.
 */
public class UI {

    /**
     * These {@code boolean} flags are used to enhance the UI by
     * preventing text from appearing more than it needs to.
     */
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
     * This {@code ArrayList} will contain the saves
     * from this session
     */
    private ArrayList<String> saves;

    /**
     * The constructor for the {@link UI} class.
     *
     * @param registry The registry to be used
     */
    public UI(Registry registry) {
        this.registry = registry;
        in = new Scanner(System.in);
        first = true;
        debug = false;
        saves = new ArrayList<>();

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
            registry.addOwner(new Owner("Tara", "6315 Ridgeglade Ct", "911"));
            registry.newCat("Harold", 3, 2);
            registry.newDog("Lollipop", 2, 4);
            registry.newFish("Kai", 3, 1);
            registry.getOwners().get(0).addPet(registry.getPets().get(2));
            registry.getPets().get(2).setOwner(registry.getOwners().get(0));
            registry.newAppointment(new Appointment(registry.getPets().get(2), "3/11/17", "11:00 AM"));
        }

        mainMenu();
    }

    /**
     * This method is the main menu for the program
     */
    private void mainMenu() {
        System.out.print("---------MAIN MENU---------" +
                "\nPlease make a selection: " +
                "\n(1) Registry     - View or search for pets and owners" +
                "\n(2) Appointments - View, edit, and create appointments" +
                "\n(3) Save/Load    - Save and load registries" +
                "\n(0) Quit" +
                "\n> ");

        try {
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    registryDialogue();
                    break;
                case 2:
                    appointmentsDialogue();
                    break;
                case 3:
                    saveLoadDialogue();
                    break;
                case 0:
                    exit(false);
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    mainMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            mainMenu();
        }
    }

    /**
     * This method contains the options that pertain to the {@link Registry}
     */
    private void registryDialogue() {
        System.out.println("--------NOW VIEWING REGISTRY MENU--------");
        System.out.print("Please make a selection:" +
                "\n(1) Pets      - View all pets" +
                "\n(2) Owners    - View all owners" +
                "\n(3) View All  - View all registered pets and owners" +
                "\n(4) New Pet   - Register a new pet" +
                "\n(5) New Owner - Register a new owner" +
                "\n(/) Search    - Search the registry for pets and owners" +
                "\n(7) Back to Main Menu" +
                "\n(0) Quit" +
                "\n> ");

        try {
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    viewPets();
                    break;
                case 2:
                    viewOwners();
                    break;
                case 3:
                    System.out.println(registry);
                    registryDialogue();
                    break;
                case 4:
                    registerPet();
                    System.out.println(registry.stringPets());
                    registryDialogue();
                    break;
                case 5:
                    registerOwner();
                    registryDialogue();
                    break;
                case 6:
                    searchRegistry();
                    break;
                case 7:
                    mainMenu();
                    break;
                case 0:
                    exit(false);
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    registryDialogue();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            registryDialogue();
        }
    }

    /**
     * This method contains the options that pertain to the {@link Pet}s
     */
    private void viewPets() {
        if (registry.getPets().isEmpty()) {
            System.out.println("---------REGISTERED PETS---------" +
                    "\nNo pets registered.");
            registryDialogue();
        } else {
            System.out.println(registry.stringPets());
        }

        System.out.print("Please make a selection:" +
                "\n(1) Select       - Display record and options for a specific pet" +
                "\n(2) View Records - Display the records for all pets" +
                "\n(3) Back" +
                "\n(0) Quit" +
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
                    registry.allPets();
                    in.nextLine();
                    viewPets();
                    break;
                case 3:
                    in.nextLine();
                    registryDialogue();
                    break;
                case 0:
                    exit(false);
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    viewPets();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            viewPets();
        }
    }

    /**
     * This method contains the options that pertain to a specific {@link Pet}
     *
     * @param pet The selected {@link Pet}
     */
    private void petOptions(Pet pet) {
        System.out.println("--------NOW VIEWING " + pet.getName().toUpperCase() + "'S RECORD--------");
        System.out.println(registry.getRecord(registry.getPets().indexOf(pet)));
        System.out.println("---------------------------------------");
        if (pet.getOwner().getName().equals("Not Registered")) {
            System.out.print("Please make a selection:" +
                    "\n(1) Edit Info       - Edit " + pet.getName() + "'s age, appointments, and more" +
                    "\n(2) New Appointment - Book a new appointment for " + pet.getName() +
                    "\n(/) Deregister      - Remove " + pet.getName() + " from the registry" +
                    "\n(4) Back" +
                    "\n(5) Register Owner  - Add " + pet.getName() + "'s owner to the registry" +
                    "\n(0) Quit" +
                    "\n> ");
        } else {
            System.out.print("Please make a selection:" +
                    "\n(1) Edit Info       - Edit " + pet.getName() + "'s age, appointments, and more" +
                    "\n(2) New Appointment - Book a new appointment for " + pet.getName() +
                    "\n(/) Deregister      - Remove " + pet.getName() + " from the registry" +
                    "\n(4) Back" +
                    "\n(0) Quit" +
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
                    addAppDialogue(pet, false);
                    petOptions(pet);
                    break;
                case 3:
                    in.nextLine();
                    deregisterPet(pet);
                    viewPets();
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
                    exit(false);
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    petOptions(pet);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            petOptions(pet);
        }
    }

    /**
     * This method contains the options that pertain to editing
     * a specific {@link Pet}
     *
     * @param pet The selected {@link Pet}
     */
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
        addAppDialogue(pet, false);
        if (!pet.getAppointments().isEmpty())
            resolveAppDialogue(pet, false);
        petOptions(pet);
    }

    /**
     * This method contains the options that pertain to
     * editing a specific {@link Pet}'s name
     *
     * @param pet The selected {@link Pet}
     */
    private void newPetName(Pet pet) {
        System.out.print("Would you like to change " + pet.getName() +
                "'s name (Y/N)?\n> ");
        try {
            String choice = in.nextLine();

            switch (choice) {
                case "Y":
                case "y":
                    System.out.print(pet.getName() + "'s new name: ");
                    String name = in.nextLine();
                    pet.setName(name);
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    newPetName(pet);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newPetName(pet);
        }
    }

    /**
     * This method contains the options that pertain to
     * editing a specific {@link Pet}'s age
     *
     * @param pet The selected {@link Pet}
     */
    private void newPetAge(Pet pet) {
        System.out.print("Would you like to change " + pet.getName() +
                "'s age (Y/N)?\n> ");
        try {
            String choice = in.nextLine();

            switch (choice) {
                case "Y":
                case "y":
                    System.out.print(pet.getName() + "'s new age: ");
                    double age = in.nextDouble();
                    pet.setAge(age);
                    in.nextLine();
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    newPetAge(pet);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newPetAge(pet);
        }

    }

    /**
     * This method contains the options that pertain to editing
     * a specific {@link Pet}'s medical history
     *
     * @param pet The selected {@link Pet}
     * @param checked A {@code boolean} flag used to prevent redundant text
     */
    private void addMedHistDialogue(Pet pet, boolean checked) {
        String choice;
        if (!checked) {
            System.out.print("Would you like to add medical history (Y/N)?\n> ");
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
                    System.out.print("Add another (Y/N)?\n> ");
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
                    addMedHistDialogue(pet, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            addMedHistDialogue(pet, true);
        }

    }

    /**
     * This method contains the options that also pertain to editing
     * a specific {@link Pet}'s medical history
     *
     * @param pet The selected {@link Pet}
     * @param checked A {@code boolean} flag used to prevent redundant text
     */
    private void removeMedHistDialogue(Pet pet, boolean checked) {
        String choice;
        if (!checked) {
            System.out.print("Would you like to remove medical history (Y/N)?\n> ");
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
                    System.out.print("Remove another (Y/N)?\n> ");
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

    /**
     * This method contains the options that pertain to editing
     * a specific {@link Pet}'s vaccinations
     *
     * @param pet The selected {@link Pet}
     * @param checked A {@code boolean} flag used to prevent redundant text
     */
    private void addVaccDialogue(Pet pet, boolean checked) {
        String choice;
        if (!checked) {
            System.out.print("Would you like to add vaccination history (Y/N)?\n> ");
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
                    System.out.print("Add another (Y/N)?\n> ");
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
                    addVaccDialogue(pet, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            addVaccDialogue(pet, true);
        }
    }

    /**
     * This method contains the options that also pertain to editing
     * a specific {@link Pet}'s vaccinations
     *
     * @param pet The selected {@link Pet}
     * @param checked A {@code boolean} flag used to prevent redundant text
     */
    private void removeVaccDialogue(Pet pet, boolean checked) {
        String choice;
        if (!checked) {
            System.out.print("Would you like to remove a vaccination from history (Y/N)?\n> ");
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
                    System.out.print("Remove another (Y/N)?\n> ");
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
                    removeVaccDialogue(pet, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            removeVaccDialogue(pet, true);
        }
    }

    /**
     * This method contains the options that pertain to adding to
     * a specific {@link Pet}'s appointments
     *
     * @param pet The selected {@link Pet}
     * @param checked A {@code boolean} flag used to prevent redundant text
     */
    private void addAppDialogue(Pet pet, boolean checked) {
        String choice;
        if (!checked) {
            System.out.print("Would you like to schedule an appointment" +
                    " for " + pet.getName() + " (Y/N)?\n> ");
            choice = in.nextLine();
        } else
            choice = "y";

        try {
            switch (choice) {
                case "Y":
                case "y":
                    System.out.print("Date: ");
                    String date = in.nextLine();
                    System.out.print("Time: ");
                    String time = in.nextLine();
                    registry.newAppointment(new Appointment(pet, date, time));
                    Appointment app = registry.getAppointments().get(registry.getAppSize() - 1);
                    pet.addAppointment(app);
                    System.out.print("Add another (Y/N)?\n> ");
                    try {
                        choice = in.nextLine();
                        switch (choice) {
                            case "Y":
                            case "y":
                                addAppDialogue(pet, true);
                            case "N":
                            case "n":
                                break;
                            default:
                                System.out.println("Invalid entry! Please try again.\n");
                                addAppDialogue(pet, true);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry! Please try again.\n");
                        in.nextLine();
                        addAppDialogue(pet, true);
                    }
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    addAppDialogue(pet, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            addAppDialogue(pet, true);
        }
    }

    /**
     * This method contains the options that pertain to resolving
     * a specific {@link Pet}'s appointment(s)
     *
     * @param pet The selected {@link Pet}
     * @param checked A {@code boolean} flag used to prevent redundant text
     */
    private void resolveAppDialogue(Pet pet, boolean checked) {
        String choice;
        if (!checked) {
            System.out.print("Would you like to mark one of " + pet.getName() + "'s appointments " +
                    "as resolved? (Y/N)?\n> ");
            choice = in.nextLine();
        } else
            choice = "y";

        try {
            switch (choice) {
                case "Y":
                case "y":
                    System.out.println(pet.stringAppointments());
                    System.out.print("Please enter the number of the line:\n> ");
                    int index = in.nextInt();
                    in.nextLine();
                    Appointment app = registry.getAppointments().get(--index);
                    registry.resolveApp(app);
                    app.getClient().resolveApp(app);
                    System.out.print("Mark another (Y/N)?\n> ");
                    try {
                        choice = in.nextLine();
                        switch (choice) {
                            case "Y":
                            case "y":
                                resolveAppDialogue(pet, true);
                            case "N":
                            case "n":
                                break;
                            default:
                                System.out.println("Invalid entry! Please try again.\n");
                                resolveAppDialogue(pet, true);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry! Please try again.\n");
                        in.nextLine();
                        resolveAppDialogue(pet, true);
                    }
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    resolveAppDialogue(pet, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            resolveAppDialogue(pet, true);
        }
    }

    /**
     * This method contains the options that pertain to creating a {@link Pet}
     */
    private void registerPet() {
        System.out.println("--------REGISTERING NEW PET--------");
        System.out.print("What kind of animal is this pet?" +
                "\n(1) Cat" +
                "\n(2) Dog" +
                "\n(3) Fish" +
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
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    registerPet();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            registerPet();
        }
    }

    /**
     * This method contains the options that pertain to creating a {@link Cat}
     */
    private void newCat() {
        System.out.print("Name: ");
        String name = in.nextLine();

        System.out.print("Age: ");
        double age = in.nextDouble();

        System.out.print("Choose a breed: " +
                "\n(1) SIAMESE" +
                "\n(2) PERSIAN" +
                "\n(3) BURMESE" +
                "\n(4) BOBTAIL" +
                "\n(5) SIBERIAN" +
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
                    newCat();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newCat();
        }
    }

    /**
     * This method contains the options that pertain to creating a {@link Dog}
     */
    private void newDog() {
        System.out.print("Name: ");
        String name = in.nextLine();

        System.out.print("Age: ");
        double age = in.nextDouble();

        System.out.print("Choose a breed: " +
                "\n(1) HUSKY" +
                "\n(2) PUG" +
                "\n(3) LAB" +
                "\n(4) YORKIE" +
                "\n(5) PEKINGESE" +
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
                    newDog();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newDog();
        }
    }

    /**
     * This method contains the options that pertain to creating a {@link Fish}
     */
    private void newFish() {
        System.out.print("Name: ");
        String name = in.nextLine();

        System.out.print("Age: ");
        double age = in.nextDouble();

        System.out.print("Choose a breed: " +
                "\n(1) GOLDFISH" +
                "\n(2) BETTA" +
                "\n(3) ANGELFISH" +
                "\n(4) CLOWNFISH" +
                "\n(5) GUPPY" +
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
                    newFish();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newFish();
        }
    }

    /**
     * This method contains the options that pertain to creating an {@link Owner}
     *
     * @param pet The selected {@link Pet}
     */
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

    /**
     * This method contains the options that pertain to deregistering a {@link Pet}
     *
     * @param pet The selected {@link Pet}
     */
    private void deregisterPet(Pet pet) {

    }

    /**
     * This method contains the options that pertain to the {@link Owner}s
     */
    private void viewOwners() {
        if (registry.getOwners().isEmpty()) {
            System.out.println("---------REGISTERED OWNERS---------" +
                    "\nNo owners registered.");
            registryDialogue();
        } else {
            System.out.println(registry.stringOwners());
        }

        System.out.print("Please make a selection:" +
                "\n(1) Select   - Display info and options for a specific owner" +
                "\n(2) View All - Display the info for all owners" +
                "\n(3) Back" +
                "\n(0) Quit" +
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
                    registry.allOwners();
                    in.nextLine();
                    viewOwners();
                    break;
                case 3:
                    in.nextLine();
                    registryDialogue();
                    break;
                case 0:
                    exit(false);
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    viewOwners();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            viewOwners();
        }
    }

    /**
     * This method contains the options that pertain to a specific {@link Owner}
     *
     * @param owner The selected {@link Owner}
     */
    private void ownerOptions(Owner owner) {
        System.out.println("--------NOW VIEWING " + owner.getName().toUpperCase() + "'S INFO--------");
        System.out.println(owner);
        System.out.println("---------------------------------------");
        if (owner.getPets().isEmpty()) {
            System.out.print("Please make a selection:" +
                    "\n(1) Edit Info       - Edit " + owner.getName() + "'s name, address, and more" +
                    "\n(/) Deregister      - Remove " + owner.getName() + " from the registry" +
                    "\n(3) Back" +
                    "\n(4) Register Pet    - Add " + owner.getName() + "'s pet to the registry" +
                    "\n(0) Quit" +
                    "\n> ");
        } else {
            System.out.print("Please make a selection:" +
                    "\n(1) Edit Info       - Edit " + owner.getName() + "'s name, address, and more" +
                    "\n(/) Deregister      - Remove " + owner.getName() + " from the registry" +
                    "\n(3) Back" +
                    "\n(0) Quit" +
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
                    deregisterOwner(owner);
                    break;
                case 3:
                    in.nextLine();
                    viewOwners();
                    break;
                case 4:
                    in.nextLine();
                    if (registry.getPets().isEmpty())
                        registerPet();
                    addPet(owner, false);
                    break;
                case 0:
                    exit(false);
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    ownerOptions(owner);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            ownerOptions(owner);
        }
    }

    /**
     * This method contains the options that pertain to editing
     * a specific {@link Owner}
     *
     * @param owner The selected {@link Owner}
     */
    private void editOwner(Owner owner) {
        System.out.println("--------NOW EDITING " + owner.getName().toUpperCase() +
                "'S INFO--------");

        newOwnerName(owner);
        newOwnerAddress(owner);
        newOwnerPhone(owner);
        if (!owner.getPets().isEmpty())
            removePet(owner, false);
        addPet(owner, false);
        ownerOptions(owner);
    }

    /**
     * This method contains the options that pertain to
     * editing a specific {@link Owner}'s name
     *
     * @param owner The selected {@link Owner}
     */
    private void newOwnerName(Owner owner) {
        System.out.print("Would you like to change " + owner.getName() +
                "'s name (Y/N)?\n> ");
        try {
            String choice = in.nextLine();

            switch (choice) {
                case "Y":
                case "y":
                    System.out.print(owner.getName() + "'s new name: ");
                    String name = in.nextLine();
                    owner.setName(name);
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    newOwnerName(owner);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newOwnerName(owner);
        }
    }

    /**
     * This method contains the options that pertain to
     * editing a specific {@link Owner}'s address
     *
     * @param owner The selected {@link Owner}
     */
    private void newOwnerAddress(Owner owner) {
        System.out.print("Would you like to change " + owner.getName() +
                "'s address (Y/N)?\n> ");
        try {
            String choice = in.nextLine();

            switch (choice) {
                case "Y":
                case "y":
                    System.out.print(owner.getName() + "'s new address: ");
                    String address = in.nextLine();
                    owner.setAddress(address);
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    newOwnerAddress(owner);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newOwnerAddress(owner);
        }
    }

    /**
     * This method contains the options that pertain to
     * editing a specific {@link Owner}'s phone number
     *
     * @param owner The selected {@link Owner}
     */
    private void newOwnerPhone(Owner owner) {
        System.out.print("Would you like to change " + owner.getName() +
                "'s phone number (Y/N)?\n> ");
        try {
            String choice = in.nextLine();

            switch (choice) {
                case "Y":
                case "y":
                    System.out.print(owner.getName() + "'s new phone number: ");
                    String phone = in.nextLine();
                    owner.setPhone(phone);
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    newOwnerPhone(owner);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newOwnerPhone(owner);
        }
    }

    /**
     * This method contains the options that pertain to linking a {@link Pet}
     * to an {@link Owner}
     *
     * @param owner The selected {@link Owner}
     * @param checked A {@code boolean} flag used to prevent redundant text
     */
    private void addPet(Owner owner, boolean checked) {
        String choice;
        if (!checked) {
            System.out.print("Would you like to link " + owner.getName() + " to a pet (Y/N)?\n> ");
            choice = in.nextLine();
        } else
            choice = "y";

        try {
            switch (choice) {
                case "Y":
                case "y":
                    System.out.println(registry.stringPets());
                    System.out.print("Please enter the number of the pet you would like" +
                            " to link to " + owner.getName() + ":\n> ");
                    int pet = in.nextInt();
                    owner.addPet(registry.getPets().get(--pet));
                    owner.getPets().get(owner.getPetsSize() - 1).setOwner(owner);
                    System.out.print("Link another (Y/N)?\n> ");
                    in.nextLine();
                    try {

                        choice = in.nextLine();
                        switch (choice) {
                            case "Y":
                            case "y":
                                addPet(owner, true);
                            case "N":
                            case "n":
                                ownerOptions(owner);
                                break;
                            default:
                                System.out.println("Invalid entry! Please try again.\n");
                                addPet(owner, true);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry! Please try again.\n");
                        in.nextLine();
                        addPet(owner, true);
                    }
                    break;
                case "N":
                case "n":
                    ownerOptions(owner);
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    addPet(owner, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            addPet(owner, true);
        }

    }

    /**
     * This method contains the options that pertain to unlinking a {@link Pet}
     * from an {@link Owner}
     *
     * @param owner The selected {@link Owner}
     * @param checked A {@code boolean} flag used to prevent redundant text
     */
    private void removePet(Owner owner, boolean checked) {
        String choice;
        if (!checked) {
            System.out.print("Would you like to unlink a pet from " + owner.getName() + " (Y/N)?\n> ");
            choice = in.nextLine();
        } else
            choice = "y";

        try {
            switch (choice) {
                case "Y":
                case "y":
                    System.out.println(owner.stringPets());
                    System.out.print("Please enter the number of the pet you would like " +
                            " to unlink " + owner.getName() + ":\n> ");
                    int pet = in.nextInt();
                    owner.getPets().get(--pet).setOwner(new Owner());
                    owner.removePet(--pet);
                    System.out.print("Unlink another (Y/N)?\n> ");
                    try {
                        choice = in.nextLine();
                        switch (choice) {
                            case "Y":
                            case "y":
                                removePet(owner, true);
                            case "N":
                            case "n":
                                break;
                            default:
                                System.out.println("Invalid entry! Please try again.\n");
                                removePet(owner, true);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry! Please try again.\n");
                        in.nextLine();
                        removePet(owner, true);
                    }
                    break;
                case "N":
                case "n":
                    break;
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    removePet(owner, true);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            removePet(owner, true);
        }
    }

    /**
     * This method contains the options that pertain to deregistering a {@link Owner}
     *
     * @param owner The selected {@link Owner}
     */
    private void deregisterOwner(Owner owner) {

    }

    /**
     *  This method contains the options that pertain to registering a {@link Owner}
     */
    private void registerOwner() {
        System.out.println("--------REGISTERING NEW OWNER--------");

        System.out.print("Name: ");
        String name = in.nextLine();

        System.out.print("Address: ");
        String address = in.nextLine();

        System.out.print("Phone Number: ");
        String phone = in.nextLine();

        registry.addOwner(new Owner(name, address, phone));
        registry.sort();
    }

    /**
     * This method will be used to search the registry
     */
    private void searchRegistry() {

    }

    /**
     * This method will be used to search applications
     */
    private void searchApps() {

    }

    /**
     * This method will handle the options that pertain to
     * {@link Appointment}s
     */
    private void appointmentsDialogue() {
        System.out.println("--------NOW VIEWING APPOINTMENTS MENU--------");
        System.out.print("Please make a selection:" +
                "\n(1) View      - View all scheduled appointments" +
                "\n(/) Search    - Search from scheduled appointments" +
                "\n(3) New       - Create a new appointment" +
                "\n(4) Resolve   - Mark an appointment as resolved and remove from list" +
                "\n(5) Back to Main Menu" +
                "\n(0) Quit" +
                "\n> ");

        try {
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    in.nextLine();
                    System.out.println(registry.stringAppointments());
                    appointmentsDialogue();
                    break;
                case 2:
                    in.nextLine();
                    searchApps();
                    break;
                case 3:
                    in.nextLine();
                    newAppointment();
                    System.out.println(registry.stringAppointments());
                    appointmentsDialogue();
                    break;
                case 4:
                    in.nextLine();
                    System.out.println(registry.stringAppointments());
                    System.out.print("Please enter the number of the line:\n> ");
                    int index = in.nextInt();
                    Appointment app = registry.getAppointments().get(--index);
                    registry.resolveApp(app);
                    app.getClient().resolveApp(app);
                    System.out.println(registry.stringAppointments());
                    appointmentsDialogue();
                case 5:
                    mainMenu();
                    break;
                case 0:
                    exit(false);
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    appointmentsDialogue();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            appointmentsDialogue();
        }
    }

    /**
     * This method will handle options that pertain to creating
     * {@link Appointment}s
     */
    private void newAppointment() {
        System.out.println("--------NOW SCHEDULING NEW APPOINTMENT--------");
        if (registry.getPets().isEmpty()) {
            System.out.println("---------REGISTERED PETS---------" +
                    "\nNo pets registered. Please register a pet before attempting" +
                    " to schedule an appointment.");
            appointmentsDialogue();
        } else {
            System.out.println(registry.stringPets());
        }

        try {
            System.out.print("Please enter the number of the pet:\n> ");
            int pet = in.nextInt();
            in.nextLine();
            System.out.print("Please enter the date of the appointment: ");
            String date = in.nextLine();
            System.out.print("Please enter the time of the appointment: ");
            String time = in.nextLine();
            registry.newAppointment(new Appointment(registry.getPets().get(--pet), date, time));
            Appointment app = registry.getAppointments().get(registry.getAppSize() - 1);
            app.getClient().addAppointment(app);
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            newAppointment();
        }
    }

    /**
     * This method will handle options that pertain to
     * saving and loading the {@link Registry}.
     */
    private void saveLoadDialogue() {
        System.out.println("--------NOW IN SAVE/LOAD MENU--------");
        System.out.print("Please make a selection:" +
                "\n(1) Save   - Save the current registry" +
                "\n(2) Load   - Load a previously saved registry" +
                "\n(3) Back to Main Menu" +
                "\n(0) Quit" +
                "\n> ");

        try {
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    save();
                    mainMenu();
                    break;
                case 2:
                    load();
                    mainMenu();
                    break;
                case 3:
                    mainMenu();
                    break;
                case 0:
                    exit(false);
                default:
                    System.out.println("Invalid entry! Please try again.\n");
                    saveLoadDialogue();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            saveLoadDialogue();
        }
    }

    /**
     * This method runs the save dialogue
     */
    private void save(){
        System.out.println("--------NOW SAVING--------");
        if(saves.isEmpty())
            System.out.println("----------NO CURRENT SAVES FOUND----------");
        else
            displaySaves();

        System.out.print("Please enter the name you would like to save" +
                " this registry as \n(no file extension needed):\n> ");
        String name = in.nextLine();
        saves.add(name);
        try{
            FileOutputStream dest = new FileOutputStream(name + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(dest);

            out.writeObject(registry);

            out.close();
            dest.close();
            System.out.println();
            System.out.println("-----" + registry.getPets().size() +
                    " PETS, " + registry.getOwners().size() +
                    " OWNERS, AND " + registry.getAppointments().size() +
                    " APPOINTMENTS SAVED-----");
        }catch(FileNotFoundException e){
            System.out.println("This file does not exist! Please try again.");
            saveLoadDialogue();
        }catch (IOException e){
            System.out.println("An error occurred while attempting to save the registry." +
                    " (IO Exception)");
            saveLoadDialogue();
        }catch(InputMismatchException e){
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            saveLoadDialogue();
        }
    }

    /**
     * This method runs the load dialogue
     */
    private void load(){
        if(saves.isEmpty())
            System.out.println("----------NO SAVES FOUND----------");

        System.out.println("--------NOW LOADING--------");
        System.out.print("Please enter the name of the registry you would like to load" +
                " (no file extension needed):\n> ");

        try{
            String name = in.nextLine();

            FileInputStream source = new FileInputStream(name + ".ser");
            ObjectInputStream in = new ObjectInputStream(source);
            registry = (Registry)in.readObject();

            in.close();
            source.close();
            System.out.println();
            System.out.println("-----" + registry.getPets().size() +
                    " PETS, " + registry.getOwners().size() +
                    " OWNERS, AND " + registry.getAppointments().size() +
                    " APPOINTMENTS LOADED-----");
        }catch(ClassNotFoundException e) {
            System.out.println("An error occurred while attempting to load the registry." +
                    " (Class Not Found)");
            saveLoadDialogue();
        } catch(FileNotFoundException e){
            System.out.println("This file does not exist! Please try again.");
            saveLoadDialogue();
        }catch (IOException e){
            System.out.println("An error occurred while attempting to load the registry." +
                    " (IO Exception)");
            saveLoadDialogue();
        }catch(InputMismatchException e){
            System.out.println("Invalid entry! Please try again.\n");
            in.nextLine();
            saveLoadDialogue();
        }
    }

    /**
     * The {@code String} representation of {@link #saves}
     */
    private void displaySaves(){
        String str = "---------CURRENT SAVES---------";
        for (String save : saves) {
            int index = saves.indexOf(save) + 1;
            str += "\n" + index + ". " + save;
        }
        System.out.println(str);
    }

    /**
     * @param str The {@code String} to be printed
     */
    public static void print(String str) {
        System.out.println(str);
    }


    /**
     * This method is run when exiting the program
     *
     * @param checked A {@code boolean} flag used to prevent redundant text
     */
    private void exit(boolean checked) {
        if(!checked)
            System.out.print("Would you like to save before exiting (Y/N)?\n> ");
        
        try{
            String choice = in.nextLine();
            
            switch(choice){
                case "Y":case "y":
                    save();
                case "N":case "n":
                    break;
                default:
                    System.out.println("Invalid input! Please try again.");
                    exit(true);
            }
        }catch(InputMismatchException e){
            System.out.println("Invalid input! Please try again.");
            in.nextLine();
            exit(true);
        }

        System.out.println("Goodbye!");
        System.exit(0);
    }
}
