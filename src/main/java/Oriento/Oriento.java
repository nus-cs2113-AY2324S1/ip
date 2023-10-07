package Oriento;


import command.Command;
import commandFormat.CommandFormat;
import commandFormat.CommandType;

import task.*;
import message.Text;

import exception.OrientoException;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import fileIO.FileIO;

/**
 * Starting point of program
 */
public class Oriento {

    public static int taskCount = 0;
    public static Task[] list = new Task[100];

    public static void main(String[] args) throws IOException, OrientoException {
        Text.printWelcomeMessage();
        Scanner keyboard = new Scanner(System.in);
        FileIO.outputFileInitialization();
        executeCommand(keyboard);
    }

    private static void executeCommand(Scanner keyboard) {
        boolean isExit = false;

        while (!isExit) {
            String cmd = keyboard.nextLine();
            cmd = CommandFormat.formattedCommand(cmd);
            String[] commandSplits = cmd.split(" ");
            if (CommandFormat.missingOrExtraTaskDescription(commandSplits)){
                continue;
            }

            try {
                Command command = CommandType.parseCommand(cmd);
                command.executeCommand();
                isExit = command.isExit();
                FileIO.backupTaskFile();
                Text.printdottedline();
            } catch (NumberFormatException nfe) {
                System.out.println("Hey, please input your command with the correct task number.");
            } catch (NullPointerException npe){
                System.out.println("Your target task doesn't exist. Please input a correct task.");
            } catch (OrientoException e){
                e.incorrectFormatException(commandSplits[0]);
            } catch (FileNotFoundException fnf){
                System.out.println("Sorry, I cannot find the task source. Please check the task file.");
            } catch (IOException io){
                System.out.println("OMG! Something went wrong! Please check if the source files are available.");
            }
        }
    }


}
