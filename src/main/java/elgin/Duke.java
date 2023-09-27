package elgin;

import elgin.exception.DukeException;
import elgin.task.TaskList;

import java.util.Scanner;

import static elgin.ui.CommandHandler.handleCommand;
import static elgin.ui.Ui.formatPrint;
import static elgin.ui.Ui.sayGreeting;

public class Duke {

    private static TaskList tasks = new TaskList();

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