import Exceptions.DukeyErrorMessages;
import Tasks.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
public class Dukey {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected String filePath = "data/dukey.txt";

    public Dukey(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> array1 = new ArrayList<>();
            storage.fileToTaskArray(filePath, array1);
            tasks = new TaskList(array1); // Assigning the TaskList to the class-level variable
        } catch (Exception e) {
            Ui.showLoadingError();
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() throws IOException {
        ui.showWelcomeMessage();
        Scanner in = new Scanner(System.in);
        String line;
        storage.checkDirectoryExists("data");
        storage.checkFileExists(filePath);
        while (true) {
            line = in.nextLine();
            String[] input = Parser.parseUserInput(line);
            String command = input[0];
            switch (command) {
                case "bye":
                    Ui.showExitMessage();
                    return;
                case "list":
                    TaskList.printTaskList(tasks.tasks);
                    break;
                case "mark":
                    try {
                        TaskList.markTask(line, tasks.tasks);
                        storage.markTaskInFile(Integer.parseInt(input[1].trim()) - 1, filePath);
                    } catch (IndexOutOfBoundsException e) {
                        DukeyErrorMessages.markInvalidError();
                    } catch (NumberFormatException e) {
                        DukeyErrorMessages.markInvalidTypeError();
                    }
                    break;
                case "unmark":
                    try {
                        TaskList.unmarkTask(line, tasks.tasks);
                        storage.unmarkTaskInFile(Integer.parseInt(input[1].trim()) - 1, filePath);
                    } catch (IndexOutOfBoundsException e) {
                        DukeyErrorMessages.unmarkInvalidError();
                    } catch (NumberFormatException e) {
                        DukeyErrorMessages.unmarkInvalidTypeError();
                    }
                    break;
                case "deadline":
                    try {
                        String[] deadline = Parser.parseCommandInput(command, input[1]);
                        if (deadline[0].trim().isEmpty() || deadline[1].trim().isEmpty()) {
                            DukeyErrorMessages.deadlineEmptyInputError();
                            break;
                        }
                        TaskList.addDeadline(deadline[0], deadline[1], tasks.tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        DukeyErrorMessages.deadlineFormatError();
                    }
                    storage.taskArrayToFile(filePath, tasks.tasks);
                    break;
                case "event":
                    try {
                        String[] events = Parser.parseCommandInput(command, input[1]);
                        if (events[2].isEmpty() || events[1].isEmpty() || events[0].isEmpty()) {
                            DukeyErrorMessages.eventEmptyInputError();
                            break;
                        }
                        TaskList.addEvent(events[0], events[1], events[2], tasks.tasks);
                        storage.taskArrayToFile(filePath, tasks.tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(Exceptions.DukeyException.EmptyInputError());
                        DukeyErrorMessages.eventFormatError();
                    } catch (StringIndexOutOfBoundsException e) {
                        DukeyErrorMessages.eventFormatError();
                    }
                    break;
                case "todo":
                    try {
                        String[] todo = Parser.parseCommandInput(command, input[1]);
                        if (input[1].trim().isEmpty()) {
                            DukeyErrorMessages.todoEmptyInputError();
                            break;
                        }
                        TaskList.addTodo(todo[0], tasks.tasks);
                        storage.taskArrayToFile(filePath, tasks.tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        DukeyErrorMessages.todoFormatError();
                    }
                    break;
                case "delete":
                    try {
                        TaskList.deleteTask(line, tasks.tasks);
                        storage.deleteLineFromFile(Integer.parseInt(input[1].trim()) - 1, filePath);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        DukeyErrorMessages.deleteEmptyInputError();
                    } catch (NumberFormatException e) {
                        DukeyErrorMessages.deleteInvalidTypeError();
                    }
                    break;
                case "find":
                    TaskList.findKeyword(line, tasks.tasks);
                    break;
                default:
                    if (line.trim().isEmpty()) {
                        Ui.printLine();
                        System.out.println("huh");
                        Ui.printLine();
                    } else {
                        DukeyErrorMessages.unrecognizedCommandError();
                    }
            }
        }
    }
    public static void main(String[] args) {
        try {
            new Dukey("data/dukey.txt").run();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}



