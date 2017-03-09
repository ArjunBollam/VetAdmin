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
