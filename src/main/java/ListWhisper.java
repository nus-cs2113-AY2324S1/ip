import listWhisper.exceptions.DescriptionFormatException;

import java.lang.String;
import java.lang.System;

public class ListWhisper {
    public static void main(String[] args) throws DescriptionFormatException {
        ListWhisper.startProgram();
    }

    private static void startProgram() throws DescriptionFormatException {
        Manager manager = new Manager();
        greetUser();
        manager.readInput();
    }

    private static void greetUser() {
        ListWhisper.printStraightLine();
        System.out.println("Hello! I'm ListWhisper");
        System.out.println("What can I do for you?");
        ListWhisper.printStraightLine();
    }

    public static void printStraightLine() {
        System.out.println("-----------------------------------------------------");
    }

}