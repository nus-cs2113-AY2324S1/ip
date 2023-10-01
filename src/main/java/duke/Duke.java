package duke;

import duke.exceptions.*;
import duke.tasks.Task;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Duke {
    public static final String NULL_INPUT_EXCEPTION = "____________________________________________________________"
        + "At least say something! :D"
        + "____________________________________________________________";

    public static final String UNDEFINED_TASKS = "____________________________________________________________" + System.lineSeparator()
        + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + System.lineSeparator()
        + "     You should tell me which kind of Tasks (todo, deadline, event) you would like to add" + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator();

    public static final String NULL_DESCRIPTION_EXCEPTION_FOR_DEADLINE = "____________________________________________________________" + System.lineSeparator()
        + "     ☹ OOPS!!! The description cannot be empty." + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator();

    public static final String INDEX_OUT_OF_BOUND = "____________________________________________________________" + System.lineSeparator()
        + "     There are not so many tasks!" + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator();

    public static final String FILE_PATH = "duke.txt";

    private static final List<String> allValidInput = new ArrayList<>(List.of(new String[]{"todo", "deadline", "list", "bye", "event", "find", "delete"}));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH);
        duke.run();

    }

    private void run() {
        List<Task> taskList = this.tasks;

        ui.greetToUsers();

        String line = Ui.getUserInput();

        try {
            if (line.length() == 0) {
                throw new NullInputException(NULL_INPUT_EXCEPTION);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            line = Ui.getUserInput();
        }

        while (!line.equals("bye")) {

            if (line.equals("list")) {

                showList(taskList);

            } else if (line.startsWith("mark") || line.startsWith("unmark")) {

                markTheTask(line, taskList);

            } else {

                try {

                    handleTheUserInput(taskList, line);
                    if (!line.startsWith("delete") && !line.startsWith("find")) {
                        feedbackOfTheExecution(taskList);

                    }

                } catch (DukeException e) {

                    System.out.println(e.getMessage());

                }

            }

            line = Ui.getUserInput();

        }

        ui.byeToUsers();
    }


    private static void handleTheUserInput(List<Task> taskList, String line) throws DukeException {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] words = line.split(" ");
        String firstWord = words[0];
        if (!allValidInput.contains(firstWord)) {
            addToFile(taskList);
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            throw new NullValidInputException(UNDEFINED_TASKS);
        }
        boolean nullDescription = words.length == 1;
        if (nullDescription) {
            addToFile(taskList);
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            throw new NullDescriptionInputException(NULL_DESCRIPTION_EXCEPTION_FOR_DEADLINE);
        } else {
            switch (firstWord) {

            case "deadline":
                Parser.handleDeadline(taskList, line);
                break;
            case "todo":
                Parser.handleTodo(taskList, words);
                break;
            case "find":
                Parser.handleFind(taskList, words);
                break;
            case "event":
                Parser.handleEvent(taskList, line);
                break;
            case "delete":
                deleteTask(taskList, Integer.parseInt(words[1]));
                break;
            default: {
                throw new UndefinedTaskException(UNDEFINED_TASKS);
            }

            }

            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void deleteTask(List<Task> taskList, int index) throws IndexOutOfBoundOfTheList {
        if (index > taskList.size()) {
            throw new IndexOutOfBoundOfTheList(INDEX_OUT_OF_BOUND);
        }
        System.out.println("____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + taskList.get(index - 1).toString());
        taskList.remove(index - 1);
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
        addToFile(taskList);
    }

    private static void feedbackOfTheExecution(List<Task> taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + taskList.get(taskList.size() - 1).toString());
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }


    public static void addToFile(List<Task> taskList) {
        try {
            File file = new File(FILE_PATH);

            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            // Loop through the list and write each element to the file
            for (int i = 0; i < taskList.size(); i++) {
                writer.write((i+1) + ". " + taskList.get(i).toString());
                writer.newLine(); // Add a newline to separate each item
            }

            // Close the writer to release resources
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to save the list to the file.");
        }
    }

    public static void showList(List taskList) {
        System.out.println("____________________________________________________________");
        if (taskList.size() == 0) {
            System.out.println("     You haven't added anything yet!");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("     " + (i + 1) + "." + taskList.get(i).toString());
            }
        }
        System.out.println("____________________________________________________________");
    }

    public static void markTheTask(String line, List<Task> taskList) {
        System.out.println("____________________________________________________________");
        String[] words = line.split(" ");
        int index = Integer.parseInt(words[1]) - 1;
        if (line.startsWith("mark")) {
            taskList.get(index).setDone(true);
            System.out.println("     Nice! I've marked this task as done:");
        } else {
            taskList.get(index).setDone(false);
            System.out.println("     OK, I've marked this task as not done yet:");
        }
        System.out.println("       " + taskList.get(index).toString());
        System.out.println("____________________________________________________________");

    }
}
