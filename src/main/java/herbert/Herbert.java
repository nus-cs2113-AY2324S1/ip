package herbert;

import task.*;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * The main chatbot class which contains methods to add, delete, update, and load tasks from file.
 */
public class Herbert {

    private final HerbertSaver reader;
    private final TaskList taskList;

    /**
     * Constructor for the chatbot class.
     * Automatically attempts to find a save file on disk at "./data/HerbertTasks.txt" and parse all tasks from it.
     * Prints welcome message on success.
     */
    public Herbert() {
        this.taskList = new TaskList();

        this.reader = new HerbertSaver("data", "HerbertTasks.txt");
        this.reader.loadFromSaveFile(this);

        HerbertUI.sayHello();
    }

    /**
     * Main chatbot loop which constantly reads in new input from the user until the user inputs "bye".
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            System.out.println();

            int process = this.processLine(line);
            if (process == 1) {
                // User has inputted "bye"
                break;
            }
        }
    }

    /**
     * Parses each line of user input.
     * @param line The raw input string from the user.
     * @return -1 if no input is entered, 1 if user enters "bye", and 0 otherwise.
     */
    public int processLine(String line) {
        line = line.strip();
        if (line.isEmpty()) {
            HerbertUI.printMessageInvalidInput("Please enter a command!");
            return -1;
        }

        String lowerLine = line.toLowerCase();

        if (lowerLine.equals("bye")) {
            HerbertUI.sayGoodbye();
            return 1;
        } else if (lowerLine.equals("list")) {
            HerbertUI.listTasks(this.taskList);
        } else if (lowerLine.equals("help")) {
            HerbertUI.displayHelp();
        } else if (lowerLine.startsWith("todo") || lowerLine.startsWith("deadline") || lowerLine.startsWith("event")) {
            addTask(line);
        } else if (lowerLine.startsWith("delete")) {
            deleteTask(line);
        } else if (lowerLine.startsWith("mark")) {
            markTask(line, true);
        } else if (lowerLine.startsWith("unmark")) {
            markTask(line, false);
        } else {
            HerbertUI.printMessageUnknownCommand(line);
        }

        return 0;
    }

    /**
     * Marks a task as either complete or incomplete depending on given user input.
     * @param line The raw input string from the user.
     * @param completed Describes whether to mark the task as complete or incomplete.
     */
    private void markTask(String line, boolean completed) {
        // Check for valid user input
        if (HerbertParser.checkInputTaskIndex(line) == -1) {
            return;
        }

        // Extract task index and mark the task as completed
        int taskIndex = HerbertParser.extractTaskIndex(line);
        if (taskIndex == -1) {
            return;
        }
        int verify = HerbertParser.verifyTaskIndex(taskIndex, this.taskList);
        if (verify == -1) {
            return;
        }
        Task task = taskList.get(taskIndex);
        task.setCompleted(completed);

        // Print result message to user
        HerbertUI.printMessageMarkTask(task, completed);
    }

    /**
     * Parses user input to decode either an event, deadline or todo, and then adds the task to the Herbert TaskList.
     * Also saves the new task to file.
     * @param line The raw input string from the user.
     */
    private void addTask(String line) {
        if (HerbertParser.checkInputAddTask(line) == -1) {
            return;
        }

        String[] words = line.split(" ");
        switch (words[0]) {
        case "todo": {
            // Get details
            String description = line.substring(line.indexOf(" ") + 1);

            // Create and add task
            Todo td = new Todo(description);
            this.taskList.add(td);
            this.reader.addTaskToSaveFile(td);

            // Print success message
            HerbertUI.printMessageAddTask(td, this.taskList);

            break;
        }
        case "deadline": {
            // Get details
            String[] dlDetails = HerbertParser.getDeadlineDetails(line);
            if (dlDetails == null) {
                return;
            }

            String description = dlDetails[0];
            LocalDate dueDate = HerbertParser.parseDate(dlDetails[1]);
            if (dueDate == null) {
                return;
            }

            // Create and add task
            Deadline dl = new Deadline(description, dueDate);
            this.taskList.add(dl);
            this.reader.addTaskToSaveFile(dl);

            // Print success message
            HerbertUI.printMessageAddTask(dl, this.taskList);
            break;
        }
        case "event":
            // Get details
            String[] evDetails = HerbertParser.getEventDetails(line);
            if (evDetails == null) {
                return;
            }

            String description = evDetails[0];
            LocalDate fromDate = HerbertParser.parseDate(evDetails[1]);
            LocalDate toDate = HerbertParser.parseDate(evDetails[2]);
            if (fromDate == null || toDate == null) {
                return;
            }

            // Create and add task
            Event ev = new Event(description, fromDate, toDate);
            this.taskList.add(ev);
            this.reader.addTaskToSaveFile(ev);

            // Print success message
            HerbertUI.printMessageAddTask(ev, this.taskList);
            break;
        }
    }

    /**
     * Adds a given task to the Herbert TaskList.
     * @param t The task to add to the TaskList.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Parses user input to delete a specific task from the Herbert TaskList using the task index.
     * @param line The raw input string from the user.
     */
    private void deleteTask(String line) {
        if (HerbertParser.checkInputTaskIndex(line) == -1) {
            return;
        }

        int taskIndex = HerbertParser.extractTaskIndex(line);
        if (taskIndex == -1) {
            return;
        }
        int verify = HerbertParser.verifyTaskIndex(taskIndex, this.taskList);
        if (verify == -1) {
            return;
        }

        Task taskCopy = taskList.get(taskIndex);
        this.taskList.remove(taskIndex);
        HerbertUI.printMessageDeleteTask(taskCopy, this.taskList);
    }

}



