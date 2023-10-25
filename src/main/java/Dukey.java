import Commands.ListCommand;
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
                        TaskList.addDeadline(deadline[0], deadline[1], tasks.tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(dukey.DukeyException.EmptyInputError());
                    }
                    storage.taskArrayToFile(filePath, tasks.tasks);
                    break;
                case "event":
                    try {
                        String[] event = Parser.parseCommandInput(command, input[1]);
                        TaskList.addEvent(event[0], event[1], event[2], tasks.tasks);
                        storage.taskArrayToFile(filePath, tasks.tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(dukey.DukeyException.EmptyInputError());
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("if statement");
                        System.out.println(dukey.DukeyException.EmptyInputError());
                    }
                    break;
                case "todo":
                    try {
                        String[] todo = Parser.parseCommandInput(command, input[1]);
                        if (Parser.checkIfInputIsEmpty(input[1])) {
                            System.out.println(dukey.DukeyException.EmptyInputError());
                            System.out.println("if statement");
                            break;
                        }
                        TaskList.addTodo(todo[0], tasks.tasks);
                        storage.taskArrayToFile(filePath, tasks.tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(dukey.DukeyException.EmptyInputError());
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
                        Ui.printLine();
                        System.out.println(dukey.DukeyException.EmptyInputError());
                        Ui.printLine();
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



