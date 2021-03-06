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

/**
 * This class represents the record for a {@link Pet}. It contains
 * all of the information for one specific {@link Pet}.
 */
public class Record implements Comparable<Record>, java.io.Serializable {

    /**
     * The {@link Pet} that the record is for
     */
    private Pet pet;

    /**
     * This is the constructor for the {@link Record} class
     *
     * @param pet The {@link Pet} to be set
     */
    public Record(Pet pet) {
        this.pet = pet;
    }

    /**
     * @return The {@code String} representation of
     * {@code this} {@link Record}
     */
    public String toString() {
        String breed = "";
        switch (pet.getType()) {
            case CAT:
                breed = ((Cat) pet).getBreed().toString();
                break;
            case DOG:
                breed = ((Dog) pet).getBreed().toString();
                break;
            case FISH:
                breed = ((Fish) pet).getBreed().toString();
        }

        return  "Name:  " + pet.getName() +
                "\nOwner: " + pet.getOwner().getName() +
                "\nAge:   " + pet.getAge() +
                "\nBreed: " + breed +
                "\nMedical History\n" + pet.stringMedHist() +
                "\nVaccinations\n" + pet.stringVaccinations() +
                "\nCurrent Appointments\n" + pet.stringAppointments();
    }

    /**
     * @param rec The {@link Record} to be compared to {@code this}
     *            {@link Record}.
     * @return The int returned by the compareTo method
     */
    @Override
    public int compareTo(Record rec){
        return pet.getName().compareToIgnoreCase(rec.pet.getName());
    }
}
