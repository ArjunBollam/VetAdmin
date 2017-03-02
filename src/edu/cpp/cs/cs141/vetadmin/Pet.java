package edu.cpp.cs.cs141.vetadmin;

import java.util.ArrayList;

/**
 * Created by chocolatecharme on 3/2/17.
 */
public abstract class Pet {

    private String breed;
    private Owner owner;
    private String name;
    private int age;
    private ArrayList<String> medHist;
    private ArrayList<String> vaccinations;
    private ArrayList<Appointmet> appointments;

    public Pet(){
        owner = new Owner();
        name = "";
        age = 0;
        medHist = new ArrayList<>();
        vaccinations = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    public Pet(Owner owner){
        this.owner = owner;
        name = "";
        age = 0;
        medHist = new ArrayList<>();
        vaccinations = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    public abstract String getBreed();

    public abstract void setBreed(String breed);

    public Owner getOwner(){
        return owner;
    }

    public void setOwner(Owner owner){
        this.owner = owner;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public ArrayList<String> getMedHist(){
        return medHist;
    }

    public void addMedHist(String disease, String status){
        medHist.add(disease + " " + status);
    }

    public void removeMedHist(int index){
        medHist.remove(index);
    }

    public ArrayList<String> getVaccinations(){
        return vaccinations;
    }

    public void addVaccination(String vaccine, String date){
        vaccinations.add(vaccine + " " + date);
    }

    public void removeVaccination(int index){
        vaccinations.remove(index);
    }

    public ArrayList<Appointmet> getAppointments(){
        return appointments;
    }

    public void addAppointment(Appointment app){
        appointments.add(app);
    }

    public void removeAppointment(int index){
        appointments.remove(index);
    }

}
