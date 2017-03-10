package edu.cpp.cs.cs141.vetadmin;

import java.util.ArrayList;

/**
 * This abstract class represents a pet. It contains the pet's
 * {@link Owner}, {@link #name}, {@link AnimalType},
 * {@link #age}, medical history ({@link #medHist}),
 * {@link #vaccinations}, and {@link #appointments}.
 */
public abstract class Pet implements Comparable<Pet>{

    /**
     * The {@link Owner} that the {@code this} {@link Pet}
     * is registered to.
     */
    private Owner owner;

    /**
     * The name of the pet as a {@code String}
     */
    private String name;

    /**
     * The type of the pet with type {@link AnimalType}
     */
    private AnimalType type;

    /**
     * The age of the pet as an {@code int}
     */
    private int age;

    /**
     * This {@code ArrayList} will contain the medical
     * history of the pet
     */
    private ArrayList<String> medHist = new ArrayList<>();

    /**
     * This {@code ArrayList} will contain the vaccination
     * history of the pet
     */
    private ArrayList<String> vaccinations = new ArrayList<>();

    /**
     * This {@code ArrayList} will contain the appointments
     * for the pet
     */
    private ArrayList<Appointment> appointments = new ArrayList<>();

    /**
     * This is a constructor for the {@link Pet} class. It initializes
     * the class fields and creates a new unspecified {@link Owner}
     * for the pet.
     *
     * @param name The name to be set
     * @param age The age to be set
     * @param type The {@link AnimalType} to be set
     */
    public Pet(String name, int age, AnimalType type){
        owner = new Owner();
        owner.setName("Not Registered");
        this.name = name;
        this.age = age;
        this.type = type;
    }

    /**
     * This is a constructor for the {@link Pet} class. It initializes
     * the class fields and assigns the {@link Owner} for the pet.
     *
     * @param owner The pet's {@link Owner}
     * @param name The name to be set
     * @param age The age to be set
     * @param type The {@link AnimalType} to be set
     */
    public Pet(Owner owner, String name, int age, AnimalType type){
        this.owner = owner;
        this.name = name;
        this.age = age;
        this.type = type;
    }

    /**
     * @return The {@link #owner} of {@code this} {@link Pet}
     */
    public Owner getOwner(){
        return owner;
    }

    /**
     * @param owner The {@link Owner} to be set for {@code this}
     * {@link Pet}
     */
    public void setOwner(Owner owner){
        this.owner = owner;
    }

    /**
     * @return The {@link #name} of {@code this} {@link Pet}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to be set for {@code this} {@link Pet}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The {@link #type} of animal {@code this} {@link Pet} is
     */
    public AnimalType getType(){
        return type;
    }

    /**
     * @return The {@link #age} of {@code this} {@link Pet}
     */
    public int getAge(){
        return age;
    }

    /**
     * @param age The age to be set for {@code this} {@link Pet}
     */
    public void setAge(int age){
        this.age = age;
    }

    /**
     * @return The {@link #medHist} of {@code this} {@link Pet}
     */
    public ArrayList<String> getMedHist(){
        return medHist;
    }

    /**
     * @return The {@code String} representation of {@code this}
     * {@link Pet}'s {@link #medHist}.
     */
    public String stringMedHist(){
        String str = "";
        if(medHist.isEmpty()){
            return "No medical history.";
        }
        for(String line : medHist){
            int index = medHist.indexOf(line) + 1;
            str += index + ". " + line + "\n";
        }
        return str;
    }

    /**
     * This method will add to {@code this} {@link Pet}'s
     * {@link #medHist}
     *
     * @param disease The disease to be added
     * @param status The status of the disease
     */
    public void addMedHist(String disease, String status){
        medHist.add(disease + " | " + status);
    }

    /**
     * @param index The index of the line of medical history to be
     *              removed
     */
    public void removeMedHist(int index){
        medHist.remove(index);
    }

    /**
     * @return The vaccinations {@code this} {@link Pet} has had
     */
    public ArrayList<String> getVaccinations(){
        return vaccinations;
    }

    /**
     * @return The {@code String} representation of {@code this}
     * {@link Pet}'s {@link #vaccinations}
     */
    public String stringVaccinations(){
        String str = "";
        if(vaccinations.isEmpty()){
            return "No vaccinations.";
        }
        for(String line : vaccinations){
            int index = vaccinations.indexOf(line) + 1;
            str += index + ". " + line + "\n";
        }
        return str;
    }

    /**
     * This method will add to {@code this} {@link Pet}'s
     * {@link #vaccinations}
     *
     * @param vaccine The vaccine to be added
     * @param date The date of the vaccination
     */
    public void addVaccination(String vaccine, String date){
        vaccinations.add(vaccine + " " + date);
    }

    /**
     * @param index The index of the vaccination to be removed
     */
    public void removeVaccination(int index){
        vaccinations.remove(index);
    }

    /**
     * @return The {@link Appointment}s booked for {@code this}
     * {@link Pet}.
     */
    public ArrayList<Appointment> getAppointments(){
        return appointments;
    }

    /**
     * @return The {@code String} representation of {@code this} {@link Pet}'s
     * {@link #appointments}
     */
    public String stringAppointments(){
            if(appointments.isEmpty()){
                return "No current appointments.";
            }
            for(Appointment line : appointments){
                int place = appointments.indexOf(line) + 1;
                return place + ". " + line.toString();
            }
            return "";
    }

    public void printAppointments(){
        UI.print(this.stringAppointments());
    }

    /**
     * This method will add to {@code this} {@link Pet}'s
     * {@link #appointments}
     *
     * @param app The appointment to be added
     */
    public void addAppointment(Appointment app){
        appointments.add(app);
    }

    /**
     * @param index The index of the appointment to be removed
     */
    public void removeAppointment(int index){
        appointments.remove(index);
    }

    @Override
    public int compareTo(Pet pet){
        return this.name.compareToIgnoreCase(pet.getName());
    }

}
