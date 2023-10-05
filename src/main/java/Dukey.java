import Tasks.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Dukey {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected String filePath = "docs/dukey.txt";

    public Dukey(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> array1 = new ArrayList<>();
            storage.fileToTaskArray(filePath, array1);
            tasks = new TaskList(array1); // Assigning the TaskList to the class-level variable
        } catch (Exception e) {
            ui.showLoadingError();
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

<<<<<<< HEAD
    public void run() throws IOException {
        ui.showWelcomeMessage();
=======
    private static void deleteTask(String line, ArrayList<Task> tasks) {
        try {
            String[] words = line.split(" ");
            int index  = Integer.parseInt(words[1]) - 1;
            Task delete = tasks.get(index);
            tasks.get(index).printDeleteTask();
            tasks.remove(index);
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("_____________________________________________________");
            System.out.println(DukeyException.todoDescriptionError());
            System.out.println("_____________________________________________________");
        }
    }

    private static void addEvent(String line, ArrayList<Task> tasks) {
        int startIndexOfFrom = line.indexOf("/from");
        int startIndexOfTo = line.indexOf("/to");
        final int beginIndex = 6;
        try {
        String from = line.substring(startIndexOfFrom + 6, startIndexOfTo);
        String to = line.substring(startIndexOfTo + 4);
        String description = line.substring(beginIndex, startIndexOfFrom);
        tasks.add(new Event(from, to, description));
        tasks.get(tasks.size() - 1).printNewTask();
        } catch(StringIndexOutOfBoundsException e) {
            System.out.println("_____________________________________________________");
            System.out.println(DukeyException.EventFormatError());
            System.out.println("_____________________________________________________");
        }
    }

    private static void addDeadline(String line, ArrayList<Task> tasks) {
        String[] words = line.split("/by");
        String[] words2 = line.split(" ");
    //    int indexOfDescriptionEnd = line.indexOf("/by");
        try {
            String description = words2[1];
            String by = words[1];
            tasks.add(new Deadline(description, by));
            tasks.get(tasks.size() - 1).printNewTask();
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("_____________________________________________________");
            System.out.println(DukeyException.deadlineDescriptionError());
            System.out.println("_____________________________________________________");
        }
    }

    private static void unmarkTask(String line, ArrayList<Task> tasks) {
        String[] words = line.split(" ");
        int taskNum = Integer.parseInt(words[1]) - 1;
        tasks.get(taskNum).unmarkTask();
    }

    private static void markTask(String line, ArrayList<Task> tasks) {
        String[] words = line.split(" ");
        int taskNum = Integer.parseInt(words[1]) - 1;
        tasks.get(taskNum).setDone();
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("_____________________________________________________");
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println((index++) + "." + task);
        }
        System.out.println("_____________________________________________________");
    }


    // appends the last index of the task array into the file
    private static void taskArrayToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        int index = tasks.size() - 1;
        Task lastTask = tasks.get(tasks.size() - 1);
        fw.write((index + 1) + "." + lastTask);
        fw.write("\n");
        fw.close();
    }
    private static void fileToTaskArray(String filePath, ArrayList<Task> tasks) throws IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner list = new Scanner(f); // create a Scanner using the File as the source
        while (list.hasNext()) {
            String line = list.nextLine();
            char type = line.charAt(3);
            switch (type) {
                case 'T':
                    addTodoFromFile(line, tasks);
                    break;
                case 'D':
                    addDeadlineFromFile(line, tasks);
                    break;
                case 'E':
                    addEventFromFile(line, tasks);
                    break;
                default:
                    break;
            }
        }
    }

    private static void addTodoFromFile(String line, ArrayList<Task> tasks) throws IOException {
        Task element = new Todo(line.substring(7));
        if (line.charAt(6) == 'X') {
            element.setDone();
        }
        tasks.add(element);
    }

    private static void addDeadlineFromFile(String line, ArrayList<Task> tasks) throws IOException {
        int endOfDescriptionIndex = line.indexOf("(by");
        String description = line.substring(8, endOfDescriptionIndex);
        int startOfDueIndex = line.indexOf(':');
        int endOfDueIndex = line.indexOf(')');
        String due = line.substring(startOfDueIndex + 1, endOfDueIndex);
        Task element = new Deadline(description, due);
        tasks.add(element);
    }

    private static void addEventFromFile(String line, ArrayList<Task> tasks) throws IOException {
        int endOfDescriptionIndex = line.indexOf("(from:");
        String description = line.substring(8, endOfDescriptionIndex);
        int startOfFromIndex = endOfDescriptionIndex + 7;
        int endOfFromIndex = line.indexOf("to:");
        String from = line.substring(startOfFromIndex, endOfFromIndex);
        String to = line.substring(endOfFromIndex + 4, line.indexOf(')'));
        Task element = new Event(from, to, description);
        tasks.add(element);
    }

    private static void searchKeyword(String line, ArrayList<Task> tasks) {
        ArrayList<Task> searchResults = new ArrayList<>();
        String keyword = line.substring(4);
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                searchResults.add(task);
            }
        }
        outputHeader();
        for (Task task : searchResults) {
            System.out.println(task);
        }
        printLine();
    }

    public static void outputHeader() {
        printLine();
        System.out.println("Here are the matching tasks in your list:\n");
    }

    public static void printLine() {
        System.out.println("_____________________________________________________");
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hey! I'm Dukey, your virtual assistant!\nWhat can I do for you?\n");
>>>>>>> 7a801e3f285379044c697da06845cf1a552655d1
        Scanner in = new Scanner(System.in);
        String line;
    //    ArrayList<Task> tasks = new ArrayList<>();
    //    File file = new File(filePath);
        // Check if the file or directory exists, and create it if it doesn't
        storage.checkFileExists(filePath);
    /*    if (file.createNewFile()) {
            System.out.println("File created");
        } else {
            System.out.println("File already exists");
        } */
  /*      try {
            storage.fileToTaskArray(filePath, tasks); // Changed Storage to storage
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } */
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
                    TaskList.markTask(line, tasks.tasks);
                    break;
                case "unmark":
                    TaskList.unmarkTask(line, tasks.tasks);
                    break;
                case "deadline":
                    String[] deadline = Parser.parseCommandInput(command, input[1]);
                    TaskList.addDeadline(deadline[0], deadline[1], tasks.tasks);
                    storage.taskArrayToFile(filePath, tasks.tasks);
                    break;
                case "event":
                    String[] event = Parser.parseCommandInput(command, input[1]);
                    TaskList.addEvent(event[0], event[1], event[2], tasks.tasks);
                    storage.taskArrayToFile(filePath, tasks.tasks);
                    break;
                case "todo":
                    String[] todo = Parser.parseCommandInput(command, input[1]);
                    TaskList.addTodo(todo[0], tasks.tasks);
                    storage.taskArrayToFile(filePath, tasks.tasks);
                    break;
                case "delete":
                    TaskList.deleteTask(line, tasks.tasks);
                    break;
                case "find":
                    searchKeyword(line, tasks);
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
            new Dukey("docs/dukey.txt").run();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}



