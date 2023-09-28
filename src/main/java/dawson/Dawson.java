package dawson;

import java.util.Scanner;

import dawson.command.Command;
import dawson.command.ExitCommand;

public class Dawson {
    /**
     * Print given input text together with a line as separator
     * 
     * @param text
     */
    public static void printText(String text) {
        String line = "\t_______________________________________________________" + System.lineSeparator();

        System.out.println(line);
        System.out.println("\t " + text);
        System.out.println(line);
    }

    public static void printText(String[] texts) {
        String line = "\t_______________________________________________________" + System.lineSeparator();

        System.out.println(line);
        for (String text : texts) {
            System.out.println("\t " + text);
        }
        System.out.println(line);
    }

    public static void main(String[] args) {

        String introText = "Hello! My name is: ";
        String welcomeText = "What can I do for you?";
        String[] dawsonText = {
                introText,
                " _____     ______     __     __     ______     ______     __   __    ",
                "/\\  __-.  /\\  __ \\   /\\ \\  _ \\ \\   /\\  ___\\   /\\  __ \\   /\\ \"-.\\ \\   ",
                "\\ \\ \\/\\ \\ \\ \\  __ \\  \\ \\ \\/ \".\\ \\  \\ \\___  \\  \\ \\ \\/\\ \\  \\ \\ \\-.  \\  ",
                " \\ \\____-  \\ \\_\\ \\_\\  \\ \\__/\".~\\_\\  \\/\\_____\\  \\ \\_____\\  \\ \\_\\\\\"\\_\\ ",
                "  \\/____/   \\/_/\\/_/   \\/_/   \\/_/   \\/_____/   \\/_____/   \\/_/ \\/_/ ",
                "",
                welcomeText
        };
        printText(dawsonText);

        Storage storage = new Storage("data/test.txt");
        
        TaskList taskList;
        try {
            taskList = new TaskList(storage.load());
        } catch (DawsonException e) {
            printText(e.getMessage());
            taskList = new TaskList();
        }

        Command newCommand;
        Scanner scanner = new Scanner(System.in);
        do {
            String nextLineString = scanner.nextLine().trim();
            newCommand = Command.getCommand(nextLineString, taskList);

            try {
                newCommand.execute();
                storage.save(taskList);
            } catch (DawsonException e) {
                printText(e.getMessage());
            }
        } while (!(newCommand instanceof ExitCommand));

        scanner.close();
    }
}
