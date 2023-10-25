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
                        System.out.println("if statement");
                    }
                    break;
                case "unmark":
                    TaskList.unmarkTask(line, tasks.tasks);
                    break;
                case "deadline":
                    try {
                        String[] deadline = Parser.parseCommandInput(command, input[1]);
                        if (deadline[0].trim().isEmpty() || deadline[1].trim().isEmpty()) {
                            Ui.deadlineEmptyInputError();
                            break;
                        }
                        TaskList.addDeadline(deadline[0], deadline[1], tasks.tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(dukey.DukeyException.EmptyInputError());
                        Ui.deadlineFormatError();
                    }
                    storage.taskArrayToFile(filePath, tasks.tasks);
                    break;
                case "event":
                    try {
                        String[] events = Parser.parseCommandInput(command, input[1]);
                        if (events[2].isEmpty() || events[1].isEmpty() || events[0].isEmpty()) {
                            Ui.eventEmptyInputError();
                            break;
                        }
                        TaskList.addEvent(events[0], events[1], events[2], tasks.tasks);
                        storage.taskArrayToFile(filePath, tasks.tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(dukey.DukeyException.EmptyInputError());
                        Ui.eventFormatError();
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.eventFormatError();
                    }
                    break;
                case "todo":
                    try {
                        String[] todo = Parser.parseCommandInput(command, input[1]);
                        if (input[1].trim().isEmpty()) {
                            Ui.todoEmptyInputError();
                            break;
                        }
                        TaskList.addTodo(todo[0], tasks.tasks);
                        storage.taskArrayToFile(filePath, tasks.tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Ui.todoFormatError();
                    }
                    break;
                case "delete":
                    TaskList.deleteTask(line, tasks.tasks);
                    storage.deleteLineFromFile(Integer.parseInt(input[1].trim()) - 1, filePath);
                    break;
                case "find":
                    storage.searchKeyword(line, tasks.tasks);
                    break;
                default:
                    if (line.trim().isEmpty()) {
                        Ui.printLine();
                        System.out.println(dukey.DukeyException.EmptyInputError());
                        Ui.printLine();
                    } else {
                        Ui.unrecognizedCommandError();
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



