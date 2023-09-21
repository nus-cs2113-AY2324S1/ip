import kenergeticbot.task.Task;
import kenergeticbot.exceptionhandler.KenergeticBotException;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import static kenergeticbot.command.BooleanChecks.*;
import static kenergeticbot.command.CommandList.*;
import static kenergeticbot.command.CommonMessages.*;

import kenergeticbot.fileaccess.Save;

public class KenergeticBot extends Save {

    public static void main(String[] args) {
        printGreetingMessage();
        ArrayList<Task> taskList = new ArrayList<Task>();
        botDialogue(taskList);
    }

    //Main logic for the bot dialogue
    public static void botDialogue(ArrayList<Task> taskList) {
        Scanner input = new Scanner(System.in);
        String item = input.nextLine();
        while(!item.equals("bye")) {
            if (item.equals("list")) {
                list(taskList);
            } else if (checkTextForMark(item)) {
                String[] splitItem = item.split(" ");
                int listIndex = Integer.parseInt(splitItem[1]);
                mark(taskList, listIndex);
            } else if (checkTextForUnmark(item)) {
                String[] splitItem = item.split(" ");
                int listIndex = Integer.parseInt(splitItem[1]);
                unmark(taskList, listIndex);
            } else {
                try {
                    add(taskList, item);
                } catch (KenergeticBotException e) { //exception thrown when user inputs command outside the usual commands
                    System.out.println(e.getMessage()); 
                }
            }
            item = input.nextLine();
        }
        Save.checkFileExist();
        for (Task task : taskList) {
                Save.writeToFile(task.printTaskToSave());
        }
        printExitMessage();
    }
}
