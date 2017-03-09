package edu.cpp.cs.cs141.vetadmin;

/**
 * This class represents an appointment. It contains the
 * {@link #date} and {@link #time} of the appointment, the {@link Pet} object
 * {@link #client}, and the {@link #status} of the appointment.
 */
public class Appointment {

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
    private String status;

    /**
     * This is the constructor for the {@link Appointment} class.
     *
     * @param client The {@link Pet} to be set
     * @param date The date to be set
     * @param time The time to be set
     * @param status The status of to be set
     */
    public Appointment(Pet client, String date, String time, String status){
        this.client = client;
        this.date = date;
        this.time = time;
        this.status = status;
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
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status to be set for {@code this} {@link Appointment}
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The {@code String} representation of {@code this} {@link Appointment}
     */
    public String toString(){
        return "Name: " + client.getName() +
                "\nDate: " + date +
                "\nTime: " + time +
                "\nStatus: " + status;
    }

    public void print(){
        UI.print(this.toString());
    }
}
