package edu.cpp.cs.cs141.vetadmin;

/**
 * Created by chocolatecharme on 3/2/17.
 */
public class Record {

    private Pet pet;

    public Record(Pet pet) {
        this.pet = pet;
    }

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

        return "Name: " + pet.getName() +
                "\nOwner: " + pet.getOwner().getName() +
                "\nAge: " + pet.getAge() +
                "\nBreed: " + breed +
                "\nMedical History:\n" + pet.printMedHist() +
                "\nVaccinations:\n" + pet.printVaccinations() +
                "\nCurrent Appointments:\n" + pet.printAppointments();
    }
}
