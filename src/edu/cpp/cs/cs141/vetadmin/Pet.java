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
    private ArrayList<String> medHist = new ArrayList<>();
    private ArrayList<String> vaccinations = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public Pet(String name, int age){
        owner = new Owner();
        this.name = name;
        this.age = age;
    }

    public Pet(Owner owner, String name, int age){
        this.owner = owner;
        this.name = name;
        this.age = age;
    }

    public abstract String getBreed();

    public abstract void setBreed(String breed);

    public Owner getOwner(){
        return owner;
    }

    public void setOwner(Owner owner){
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<Appointment> getAppointments(){
        return appointments;
    }

    public void addAppointment(Appointment app){
        appointments.add(app);
    }

    public void removeAppointment(int index){
        appointments.remove(index);
    }

}
