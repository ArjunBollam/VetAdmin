package edu.cpp.cs.cs141.vetadmin;

import java.util.ArrayList;

/**
 * Created by chocolatecharme on 3/2/17.
 */
public abstract class Pet {

    private Owner owner;
    private String name;
    private AnimalType type;
    private int age;
    private ArrayList<String> medHist = new ArrayList<>();
    private ArrayList<String> vaccinations = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public Pet(String name, int age, AnimalType type){
        owner = new Owner();
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public Pet(Owner owner, String name, int age, AnimalType type){
        this.owner = owner;
        this.name = name;
        this.age = age;
        this.type = type;
    }

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

    public AnimalType getType(){
        return type;
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

    public String printMedHist(){
        String str = "";
        if(medHist.isEmpty()){
            return "No medical history.";
        }
        for(String line : medHist){
            str += (medHist.indexOf(line) + 1) + ". " + line + "\n";
        }
        return str;
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

    public String printVaccinations(){
        String str = "";
        if(vaccinations.isEmpty()){
            return "No vaccinations.";
        }
        for(String line : vaccinations){
            str += (vaccinations.indexOf(line) + 1) + ". " + line + "\n";
        }
        return str;
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

    public String printAppointments(){
            String str = "";
            if(appointments.isEmpty()){
                return "No current appointments.";
            }
            for(Appointment line : appointments){
                str += (appointments.indexOf(line) + 1) + ". " + line.toString() + "\n";
            }
            return str;
    }

    public void addAppointment(Appointment app){
        appointments.add(app);
    }

    public void removeAppointment(int index){
        appointments.remove(index);
    }

}
