package edu.cpp.cs.cs141.vetadmin;

import java.util.ArrayList;

/**
 * Created by chocolatecharme on 3/2/17.
 */
public class Owner {

    private String name;

    private String address;

    private String phone;

    private ArrayList<Pet> pets = new ArrayList<>();

    public Owner(){
        name = "";
        address = "";
        phone = "";
    }

    public Owner(Pet pet){
        name = "";
        address = "";
        phone = "";
        pets.add(pet);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public void removePet(int index){
        pets.remove(index);
    }

}
