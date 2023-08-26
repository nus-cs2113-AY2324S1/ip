package dawson;

import java.util.Scanner;

import dawson.commands.Command;
import dawson.commands.Echo;
import dawson.commands.Exit;

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

    public static void main(String[] args) {

        String dawsonText = 
            "\t ██████╗░░█████╗░░██╗░░░░░░░██╗░██████╗░█████╗░███╗░░██╗" + System.lineSeparator() +
            "\t ██╔══██╗██╔══██╗░██║░░██╗░░██║██╔════╝██╔══██╗████╗░██║" + System.lineSeparator() +
            "\t ██║░░██║███████║░╚██╗████╗██╔╝╚█████╗░██║░░██║██╔██╗██║" + System.lineSeparator() +
            "\t ██║░░██║██╔══██║░░████╔═████║░░╚═══██╗██║░░██║██║╚████║" + System.lineSeparator() +
            "\t ██████╔╝██║░░██║░░╚██╔╝░╚██╔╝░██████╔╝╚█████╔╝██║░╚███║" + System.lineSeparator() +
            "\t ╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░╚═════╝░░╚════╝░╚═╝░░╚══╝" + System.lineSeparator();
        String welcomeText =  System.lineSeparator() + "\t What can I do for you?";
        printText("Hello! My name is: " + System.lineSeparator() + dawsonText + welcomeText);

        Command newCommand = new Echo("");
        Scanner scanner = new Scanner(System.in);

        while (!(newCommand instanceof Exit)) {
            String nextLineString = scanner.nextLine().toLowerCase().trim();
            String[] split = nextLineString.split("\\s+");

            String commandString = split[0];
            newCommand = Command.commandChecker(commandString, nextLineString);
            newCommand.execute();
        }

        scanner.close();
    }
}
