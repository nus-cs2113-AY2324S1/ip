package elgin;

import elgin.exception.DukeException;
import elgin.task.TaskManager;

import java.util.Scanner;

import static elgin.utils.CommandHandler.handleCommand;
import static elgin.utils.FormatPrinter.formatPrint;
import static elgin.utils.FormatPrinter.sayGreeting;

public class Duke {

    static TaskManager tasks = new TaskManager();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        sayGreeting();
        String command;
        boolean isContinue = true;
        while (isContinue) {
            command = scanner.nextLine();
            try {
                isContinue = handleCommand(tasks, command);
            } catch (DukeException e) {
                formatPrint(e.getMessage());
            }
        }
    }
}