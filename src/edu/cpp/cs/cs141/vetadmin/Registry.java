package edu.cpp.cs.cs141.vetadmin;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by chocolatecharme on 3/2/17.
 */
public class Registry implements Serializable{
    private ArrayList<Pet> pets;

    public Registry(){
        pets = new ArrayList<>();
    }

    public void addPet(Pet pet){
        pets.add(pet);
    }

    public void removePet(int index){
        pets.remove(index);
    }

    public String toString(){
        String str = "";
        for(Pet pet : pets){
            str += (pets.indexOf(pet) + 1) + ". " + pet.getName() + "\n";
        }

        return str;
    }
}
