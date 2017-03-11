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
 * This is the main class. It's purpose is to run the program.
 */
public class Main {
  public static void main(String[] args){
        Registry reg = new Registry();
        UI ui = new UI(reg);
        ui.runProgram();
    }
}
