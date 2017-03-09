package edu.cpp.cs.cs141.vetadmin;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the registry for the vet clinic. It contains
 * all of the {@link Pet} {@link Record}s and {@link Owner}s registered
 * in the system. All {@link Pet}s and {@link Owner}s are registered and
 * deregistered through the registry.
 */
public class Registry implements Serializable{

    /**
     * This {@code ArrayList} will contain the registered {@link Pet}s
     */
    private ArrayList<Pet> pets;

    /**
     * This {@code ArrayList} will contain the registered {@link Owner}s
     */
    private ArrayList<Owner> owners;

    /**
     * This {@code ArrayList} will contain the {@link Record}s of the
     * registered {@link Pet}s
     */
    private ArrayList<Record> records;

    /**
     * This is the constructor for the {@link Registry} class. It
     * initializes the {@code ArrayList}s
     */
    public Registry(){
        pets = new ArrayList<>();
        owners = new ArrayList<>();
        records = new ArrayList<>();
    }

    /**
     * @return The {@link #pets} that are registered
     */
    public ArrayList<Pet> getPets(){
        return pets;
    }

    /**
     * @return The {@link #owners} that are registered
     */
    public ArrayList<Owner> getOwners(){
        return owners;
    }

    /**
     * @return The {@link #records} for the registered {@link Pet}s
     */
    public ArrayList<Record> getRecords(){
        return records;
    }

    /**
     * @param pet The {@link Pet} to be added
     */
    public void addPet(Pet pet){
        pets.add(pet);
    }

    /**
     * @param owner The {@link Owner} to be added
     */
    public void addOwner(Owner owner){
        owners.add(owner);
    }

    /**
     * @param pet The {@link Pet} to create a {@link Record}
     *            for
     */
    public void createRecord(Pet pet){
        records.add(new Record(pet));
    }

    /**
     * @param index The index of the pet to be removed
     */
    public void removePet(int index){
        pets.remove(index);
    }

    /**
     * @param index The index of the owner to be removed
     */
    public void removeOwner(int index){
        owners.remove(index);
    }

    /**
     * @param index The index of the record to be removed
     */
    public void removeRecord(int index){
        records.remove(index);
    }

    /**
     * @return The {@code String} representation of {@code this}
     * {@link Registry}.
     */
    public String toString(){
        String str = "------------------";
        for(Record pet : records){
            str +=  "\n" + (records.indexOf(pet) + 1) + " | \n" + pet;
        }

        return str;
    }

    public String printPets(){
        String str = "------------------";
        for(Pet pet : pets){
            str +=  "\n" + (records.indexOf(pet) + 1) + ". " + pet.getName();
        }

        return str;
    }

    /**
     * This method will create a new {@link Dog}
     *
     * @param name The name of the dog
     * @param age The age of the dog
     * @param breed The {@link edu.cpp.cs.cs141.vetadmin.Dog.DogBreed} of the dog
     */
    public void newDog(String name, int age, int breed){
        pets.add(new Dog(name, age, breed));
    }

    /**
     * This method will create a new {@link Cat}
     *
     * @param name The name of the cat
     * @param age The age of the cat
     * @param breed The {@link edu.cpp.cs.cs141.vetadmin.Cat.CatBreed} of the cat
     */
    public void newCat(String name, int age, int breed){
        pets.add(new Cat(name, age, breed));
    }

    /**
     * This method will create a new {@link Fish}
     *
     * @param name The name of the fish
     * @param age The age of the fish
     * @param breed The {@link edu.cpp.cs.cs141.vetadmin.Fish.FishBreed} of the fish
     */
    public void newFish(String name, int age, int breed){
        pets.add(new Fish(name, age, breed));
    }

    /**
     * This method will allow the {@link Pet}'s name to be edited.
     *
     * @param index The index of the pet
     * @param name Teh name to be set
     */
    public void editPetName(int index, String name){
        pets.get(index).setName(name);
    }

    /**
     * This method will allow the {@link Pet}'s age to be edited.
     *
     * @param index The index of the pet
     * @param age The age to be set
     */
    public void editPetAge(int index, int age){
        pets.get(index).setAge(age);
    }

    /**
     * This method will allow the {@link Owner}'s name to be edited.
     *
     * @param index The index of the owner
     * @param name The name to be set
     */
    public void editOwnerName(int index, String name){
        pets.get(index).getOwner().setName(name);
    }

    /**
     * This method will allow the {@link Owner}'s address to be edited.
     *
     * @param index The index of the owner
     * @param address The address to be set
     */
    public void editOwnerAddress(int index, String address){
        pets.get(index).getOwner().setAddress(address);
    }

    /**
     * This method will allow the {@link Owner}'s phone number to be edited.
     *
     * @param index The index of the owner
     * @param phone The phone number to be set
     */
    public void editOwnerPhone(int index, String phone){
        pets.get(index).getOwner().setPhone(phone);
    }
}
