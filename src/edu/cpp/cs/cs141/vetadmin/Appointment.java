package edu.cpp.cs.cs141.vetadmin;

/**
 * Created by chocolatecharme on 3/2/17.
 */
public class Appointment {

    private String date;

    private String time;

    private Pet client;

    private String status;

    public Appointment(Pet client, String date, String time, String status){
        this.client = client;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Pet getClient() {
        return client;
    }

    public void setClient(Pet client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString(){
        return "Name: " + client.getName() +
                "\nDate: " + date +
                "\nTime: " + time +
                "\nStatus: " + status;
    }
}
