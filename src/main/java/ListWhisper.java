import java.lang.String;
import java.lang.System;

public class ListWhisper {
    public static void main(String[] args) {
        ListWhisper.startProgram();
    }

    private static void startProgram() {
        Manager manager = new Manager();
        greetUser();
        manager.readInput();
    }

    private static void greetUser() {
        System.out.println("-----------------------------------------------------");
        System.out.println(
                "Hello! I'm ListWhisper\n" +
                        "What can I do for you?"
        );
        System.out.println("-----------------------------------------------------");
    }

}