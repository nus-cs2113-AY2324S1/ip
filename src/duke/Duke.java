package duke;

import duke.todo.Todo;
import duke.task.Task;
import duke.event.Event;
import duke.deadline.Deadline;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;
import java.io.IOException;

public class Duke {

    private static final String LINE = "____________________________________________________________";
    private static ArrayList<Task> taskItems = new ArrayList<>();

    public static void println(String line) {
        System.out.println("\t" + line);
    }

    public static int getItemIdx(String line) {
        return Integer.parseInt(line.split(" ")[1]) - 1;
    }

    public static void markItem(String line) {
        int markIdx = getItemIdx(line);
        taskItems.get(markIdx).setIsDone(true);

        println("Nice! I've marked this task as done: ");
        println(taskItems.get(markIdx).getTask());
    }

    public static void unmarkItem(String line) {
        int markIdx = getItemIdx(line);
        taskItems.get(markIdx).setIsDone(false);

        println("Nice! I've marked this task as undone: ");
        println(taskItems.get(markIdx).getTask());
    }

    public static void handleCreateTodo(String line) throws DukeException {
        int spaceIdx = line.indexOf(" ");
        if (spaceIdx == -1) {
            throw new DukeException("The description of a todo cannot be empty");
        }

        String description = line.substring(spaceIdx+1);
        if (description.trim().length() == 0) {
            throw new DukeException("The description of a todo cannot be empty");
        }

        taskItems.add(new Todo(description));

        println(taskItems.get(taskItems.size()-1).getTaskAdded(taskItems.size()));
    }

    public static void handleCreateDeadline(String line) throws DukeException {
        int byIdx = line.indexOf("/by");
        if (byIdx == -1) {
            throw new DukeException("The /by of a deadline must be specified");
        }

        // Extract task description and deadline from user input
        int spaceIdx = line.indexOf(" ");
        if (spaceIdx == -1 || spaceIdx >= byIdx) {
            throw new DukeException("The description of a deadline cannot be empty");
        }

        String description = line.substring(spaceIdx+1, byIdx-1);
        if (description.trim().length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty");
        }

        int deadlineIdx = byIdx+ "/by ".length();
        if (deadlineIdx >= line.length()) {
            throw new DukeException("The /by of a deadline cannot be empty");
        }

        String deadline = line.substring(deadlineIdx);

        taskItems.add(new Deadline(description, deadline));

        println(taskItems.get(taskItems.size()-1).getTaskAdded(taskItems.size()));
    }

    public static void handleCreateEvent(String line) {
        int fromIdx = line.indexOf("/from");
        int toIdx = line.indexOf("/to");

        // Extract task description, start time and end time from user input
        String description = line.substring(line.indexOf(" ")+1, fromIdx-1);
        String start = line.substring(fromIdx+ "/from ".length(), toIdx-1);
        String end = line.substring(toIdx+ "/to ".length());

        taskItems.add(new Event(description, start, end));

        println(taskItems.get(taskItems.size()-1).getTaskAdded(taskItems.size()));
    }

    public static void handleDeleteTask(String line) {
        int deleteIdx = getItemIdx(line);

        String deleteMessage = taskItems.get(deleteIdx).getTaskDeleted(taskItems.size()-1);
        taskItems.remove(deleteIdx);

        println(deleteMessage);
    }

    public static void main(String[] args) {
        String line;
        Ui ui = new Ui();

        ui.printWelcome();

        Storage f = new Storage();
        try {
            taskItems = f.readTasksFromFile();
        } catch (IOException e) {
            println(String.valueOf(e));
        }

        line = ui.getCommand();
        while (line.equals("bye") == false) {
            println(LINE);

            if (line.startsWith("list")) {
                ui.printList(taskItems);
            } else if (line.startsWith("mark")) {
                markItem(line);
            } else if (line.startsWith("unmark")) {
                unmarkItem(line);
            } else if (line.startsWith("todo")) {
                try {
                    handleCreateTodo(line);
                } catch (DukeException e) {
                    println(String.valueOf(e));
                }
            } else if (line.startsWith("deadline")) {
                try {
                    handleCreateDeadline(line);
                } catch (DukeException e) {
                    println(String.valueOf(e));
                }
            } else if (line.startsWith("event")) {
                handleCreateEvent(line);
            } else if (line.startsWith("delete")) {
                handleDeleteTask(line);
            } else {
                println("I don't know that command");
            }

            println(LINE);

            line = ui.getCommand();
        }

        String tasksToWrite = "";
        for (Task task : taskItems) {
            tasksToWrite += task.getTaskForFile() + '\n';
        }

        try {
            f.writeTasksToFile(tasksToWrite);
        } catch (IOException e) {
            println(String.valueOf(e));
        }

        ui.printFarewell();
    }
}
