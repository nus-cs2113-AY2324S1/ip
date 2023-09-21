package duke;

import duke.exceptions.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String NULL_INPUT_EXCEPTION = "____________________________________________________________"
        + "At least say something! :D"
        + "____________________________________________________________";

    public static final String UNDEFINED_TASKS = "____________________________________________________________" + System.lineSeparator()
        + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + System.lineSeparator()
        + "     You should tell me which kind of Tasks (todo, deadline, event) you would like to add" + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator();

    public static final String NULL_DESCRIPTION_EXCEPTION_FOR_TODO = "____________________________________________________________" + System.lineSeparator()
        + "     ☹ OOPS!!! The description of a todo cannot be empty." + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator();

    public static final String NULL_DESCRIPTION_EXCEPTION_FOR_DEADLINE = "____________________________________________________________" + System.lineSeparator()
        + "     ☹ OOPS!!! The description of a deadline cannot be empty." + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator();

    public static final String NULL_DESCRIPTION_EXCEPTION_FOR_EVENT = "____________________________________________________________" + System.lineSeparator()
        + "     ☹ OOPS!!! The description of an event cannot be empty." + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator();

    public static final String NULL_DESCRIPTION_EXCEPTION_FOR_DELETE = "____________________________________________________________" + System.lineSeparator()
        + "     ☹ OOPS!!! You can't delete nothing." + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator();

    public static final String INDEX_OUT_OF_BOUND = "____________________________________________________________" + System.lineSeparator()
        + "     There are not so many tasks!" + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator();

    public static final String FILE_PATH = "duke.txt";

    public static void main(String[] args) {
        List<Task> taskList = new LinkedList<>();
        greetToUsers();

        String filePath = FILE_PATH;
        File file = new File(filePath);

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Check if the file doesn't exist, then create it
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        try {
            if (line.length() == 0) {
                throw new NullInputException(NULL_INPUT_EXCEPTION);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            line = in.nextLine();
        }

        while (!line.equals("bye")) {

            if (line.equals("list")) {

                showList(taskList);

            } else if (line.startsWith("mark") || line.startsWith("unmark")) {

                markTheTask(line, taskList);

            } else {

                try {

                    handleTheUserInput(taskList, line, fileWriter);
                    if (!line.startsWith("delete")) {
                        feedbackOfTheExecution(taskList);

                    }

                } catch (DukeException e) {

                    System.out.println(e.getMessage());

                }

            }

            line = in.nextLine();

        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byeToUsers();
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private static void handleTheUserInput(List<Task> taskList, String line, FileWriter fileWriter) throws DukeException {

        String[] words = line.split(" ");
        String firstWord = words[0];
        boolean nullDescription = words.length == 1;
        if (nullDescription) {
            throw new NullDescriptionInputException(NULL_DESCRIPTION_EXCEPTION_FOR_DEADLINE);
        }
        switch (firstWord) {

        case "deadline":
            if (nullDescription) {
                throw new NullDescriptionInputException(NULL_DESCRIPTION_EXCEPTION_FOR_DEADLINE);
            }
            handleDeadline(taskList, line, fileWriter);
            break;
        case "todo":
            if (nullDescription) {
                throw new NullDescriptionInputException(NULL_DESCRIPTION_EXCEPTION_FOR_TODO);
            }
            handleTodo(taskList, words, fileWriter);
            break;
        case "event":
            if (nullDescription) {
                throw new NullDescriptionInputException(NULL_DESCRIPTION_EXCEPTION_FOR_EVENT);
            }
            handleEvent(taskList, line, fileWriter);
            break;
        case "delete":
            if (nullDescription) {
                throw new NullDescriptionInputException(NULL_DESCRIPTION_EXCEPTION_FOR_DELETE);
            }
            deleteTask(taskList, Integer.parseInt(words[1]));
            break;
        default: {
            throw new UndefinedTaskException(UNDEFINED_TASKS);
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
        try {
            File file = new File(FILE_PATH);

            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            // Loop through the list and write each element to the file
            for (Task item : taskList) {
                writer.write(item.toString());
                writer.newLine(); // Add a newline to separate each item
            }

            // Close the writer to release resources
            writer.close();

            System.out.println("List has been saved and has overwritten the existing file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to save the list to the file.");
        }
    }

    private static void feedbackOfTheExecution(List<Task> taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + taskList.get(taskList.size() - 1).toString());
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void handleEvent(List<Task> taskList, String line, FileWriter fileWriter) throws NullValidInputException {
        String[] userfulInfo = Event.handleInputForEvent(line);
        Event event = new Event(userfulInfo[0], userfulInfo[1]);
        taskList.add(event);
        try {
            fileWriter.write(event + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleTodo(List<Task> taskList, String[] words, FileWriter fileWriter) {
        String task = "";
        for (int i = 1; i < words.length; i++) {
            task += words[i] + " ";
        }
        Todo todo = new Todo(task.trim());
        taskList.add(todo);
        try {
            fileWriter.write(todo + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void handleDeadline(List<Task> taskList, String line, FileWriter fileWriter) {
        String by = line.split("/")[1];
        String description = line.split("/")[0].replace("deadline", "").trim();
        Deadline deadline = new Deadline(description, by.replace("by", "").trim());
        try {
            fileWriter.write(deadline + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        taskList.add(deadline);
    }

    public static void greetToUsers() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm TUM");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void byeToUsers() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void showList(List taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i).toString());
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
