import listWhisper.exceptions.DescriptionFormatException;

import java.io.IOException;
import java.lang.String;
import java.lang.System;
import java.util.ArrayList;

public class ListWhisper {
    public static void main(String[] args) throws DescriptionFormatException, IOException {
        ListWhisper.startProgram();
    }

    private static void startProgram() throws DescriptionFormatException, IOException {
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