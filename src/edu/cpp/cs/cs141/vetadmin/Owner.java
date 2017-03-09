package edu.cpp.cs.cs141.vetadmin;

import java.util.ArrayList;

/**
 * This class represents the a {@link Pet} owner. It contains the
 * {@link #name}, {@link #address}, and {@link #phone} number of
 * the owner.
 */
public class Owner {

    /**
     * The name of the owner as a {@code String}
     */
    private String name;

    /**
     * The address of the owner as a {@code String}
     */
    private String address;

    /**
     * The phone number of the owner as a {@code String}
     */
    private String phone;

    /**
     * This {@code ArrayList} will contain the pets that
     * the owner has registered.
     */
    private ArrayList<Pet> pets = new ArrayList<>();

    /**
     * This is the default constructor for the {@link Owner} class.
     * All of the class fields are initialized to empty {@code String}s.
     */
    public Owner(){
        name = "";
        address = "";
        phone = "";
    }

    /**
     * This is a constructor for the {@link Owner} class. It
     * initializes all three of the class fields.
     *
     * @param name The name to be set
     * @param address The address to be set
     * @param phone The phone number to be set
     */
    public Owner(String name, String address, String phone){
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    /**
     * This is a constructor for the {@link Owner} class. It initializes
     * the three fields and {@code ArrayList} for this class.
     *
     * @param name The name to be set
     * @param address The address to be set
     * @param phone The phone number to be set
     * @param pet The {@link Pet} to be assigned to this user
     */
    public Owner(String name, String address, String phone, Pet pet){
        this.name = name;
        this.address = address;
        this.phone = phone;
        pets.add(pet);
    }

    /**
     * @return The {@link #name} of {@code this} {@link Owner}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to be set for {@code this} {@link Owner}
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return The {@link #address} of {@code this} {@link Owner}
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address to be set for {@code this} {@link Owner}
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The {@link #phone} number of {@code this} {@link Owner}
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The phone number to be set for {@code this} {@link Owner}
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return The {@link #pets} registered to {@code this} {@link Owner}
     */
    public ArrayList<Pet> getPets() {
        return pets;
    }

    /**
     * @param pet The pet to be registered to {@code this} {@link Owner}
     */
    public void addPet(Pet pet) {
        pets.add(pet);
    }

    /**
     * @param index The index of the {@link Pet} to be deregistered from
     *              {@code this} {@link Owner}
     */
    public void removePet(int index){
        pets.remove(index);
    }

}
