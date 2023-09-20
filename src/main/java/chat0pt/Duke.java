package chat0pt;

import chat0pt.commands.Task;
import chat0pt.helper.CommandProcessor;
import chat0pt.helper.FileHandler;
import chat0pt.helper.Printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        Printer.welcomeMessage();
        Scanner in = new Scanner(System.in);
        boolean endProg = true;
        ArrayList<Task> savedTasks = FileHandler.onStart();
        while (endProg) {
            String input = in.nextLine();
            endProg = CommandProcessor.runCommand(input,savedTasks);
        }
    }
}
