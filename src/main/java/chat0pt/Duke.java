package chat0pt;

import chat0pt.helper.CommandProcessor;
import chat0pt.helper.Printer;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Printer.welcomeMessage();
        Scanner in = new Scanner(System.in);
        boolean endProg = true;
        while (endProg) {
            String input = in.nextLine();
            endProg = CommandProcessor.runCommand(input);
        }
    }
}
