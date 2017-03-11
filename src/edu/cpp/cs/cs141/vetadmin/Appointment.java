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
 * This class represents an appointment. It contains the
 * {@link #date} and {@link #time} of the appointment, the {@link Pet} object
 * {@link #client}, and the {@link #status} of the appointment.
 */
public class Appointment implements Comparable<Appointment>, java.io.Serializable {

    /**
     * The date of the appointment as a {@code String}
     */
    private String date;

    /**
     * The time of the appointment as a {@code String}
     */
    private String time;

    /**
     * The {@link Pet}
     */
    private Pet client;

    /**
     * The status of the appointment as a {@link String}
     */
    private int status;

    /**
     * This is the constructor for the {@link Appointment} class.
     *
     * @param client The {@link Pet} to be set
     * @param date   The date to be set
     * @param time   The time to be set
     */
    public Appointment(Pet client, String date, String time) {
        this.client = client;
        this.date = date;
        this.time = time;
        status = 0;
    }

    /**
     * @return The {@link #date} of {@code this} {@link Appointment}
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date to be set for {@code this} {@link Appointment}
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The {@link #time} of {@code this} {@link Appointment}
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time The time to be set for {@code this} {@link Appointment}
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return The {@link #client} of {@code this} {@link Appointment}
     */
    public Pet getClient() {
        return client;
    }

    /**
     * @param client The client to be set for {@code this} {@link Appointment}
     */
    public void setClient(Pet client) {
        this.client = client;
    }

    /**
     * @return The {@link #status} of {@code this} {@link Appointment}
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status The status to be set for {@code this} {@link Appointment}
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return The {@code String} representation of {@code this} {@link Appointment}
     */
    public String toString() {
        String stat;
        if (status == 0)
            stat = "Outstanding";
        else
            stat = "Resolved";

        return "Name: " + client.getName() +
                "\n   Date: " + date +
                "\n   Time: " + time +
                "\n   Status: " + stat;
    }

    /**
     * @param app The appointment to compare with {@code this} {@link Appointment}
     * @return The integer returned by the compareTo method
     */
    @Override
    public int compareTo(Appointment app) {
        return this.client.getOwner().getName().compareToIgnoreCase(
                app.getClient().getOwner().getName());
    }
}
