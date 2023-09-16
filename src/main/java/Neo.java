import neo.exception.NeoException;
import neo.task.Deadline;
import neo.task.Event;
import neo.task.Task;
import neo.task.Todo;
import neo.type.CommandType;
import neo.type.ErrorType;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Neo {

    public static void printList(Task[] list) {
        int listIndex = 1;

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.print((listIndex) + ". ");
            System.out.println(list[i]);
            listIndex++;
        }
    }

    public static void markTask(String line, Task[] list) {
        try {
            String[] words = line.split(" ");
            int listIndex = Integer.parseInt(words[1]);
            int listArrayIndex = listIndex - 1;

            list[listArrayIndex].setDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("    " + list[listArrayIndex]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please give the index of which task to mark.");
        }
    }

    public static void unmarkTask(String line, Task[] list) {
        try {
            String[] words = line.split(" ");
            int listIndex = Integer.parseInt(words[1]);
            int listArrayIndex = listIndex - 1;

            list[listArrayIndex].setDone(false);
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println("    " + list[listArrayIndex]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please give the index of which task to unmark.");
        }
    }

    public static void catchFormatError(CommandType type, String line) throws NeoException {
        switch (type) {
        case TODO:
            if (line.contains("/from") || (line.contains("/to"))) {
                throw new NeoException("/from", ErrorType.MISUSE);
            }
            if (line.contains("/by")) {
                throw new NeoException("/by", ErrorType.MISUSE);
            }
            break;
        case DEADLINE:
            if (line.contains("/from") || (line.contains("/to"))) {
                throw new NeoException("/from", ErrorType.MISUSE);
            }
            if (!line.contains("/by")) {
                throw new NeoException("/by", ErrorType.FORMAT);
            }
            break;
        case EVENT:
            if (line.contains("/by")) {
                throw new NeoException("/by", ErrorType.MISUSE);
            }
            if (!line.contains("/from")) {
                throw new NeoException("/from", ErrorType.FORMAT);
            }
            if (!line.contains("/to")) {
                throw new NeoException("/to", ErrorType.FORMAT);
            }
            break;
        }
    }

    public static void catchEmptyDescription(String field, String description) throws NeoException {
        if (description.isBlank()) {
            throw new NeoException(field, ErrorType.EMPTY);
        }
    }

    public static void handleEvent(String line, Task[] list) {
        try {
            addEvent(line, list);
        } catch (NeoException e) {
            e.printException();
        }
    }

    public static void addEvent(String line, Task[] list) throws NeoException{
        catchFormatError(CommandType.EVENT, line);

        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        int fromStringLength = 5;
        int toStringLength = 3;
        int eventStringLength = 5;

        String description = line.substring(eventStringLength, fromIndex).trim();
        String from = line.substring(fromIndex + fromStringLength, toIndex).trim();
        String to = line.substring(toIndex + toStringLength).trim();

        catchEmptyDescription("description", description);
        catchEmptyDescription("/from", from);
        catchEmptyDescription("/to", to);

        int taskArrayIndex = Task.getTotalTasks();

        list[taskArrayIndex] = new Event(description, from, to);
        list[taskArrayIndex].printAddedTask();
    }

    public static void handleDeadline(String line, Task[] list) {
        try {
            addDeadline(line, list);
        } catch (NeoException e) {
            e.printException();
        }
    }

    public static void addDeadline(String line, Task[] list) throws NeoException {
        catchFormatError(CommandType.DEADLINE, line);

        int byIndex = line.indexOf("/by");
        int byStringLength = 3;
        int deadlineStringLength = 8;

        String description = line.substring(deadlineStringLength, byIndex).trim();
        String by = line.substring(byIndex + byStringLength).trim();

        catchEmptyDescription("description", description);
        catchEmptyDescription("/by", by);

        int taskArrayIndex = Task.getTotalTasks();

        list[taskArrayIndex] = new Deadline(description, by);
        list[taskArrayIndex].printAddedTask();
    }

    public static void handleTodo(String line, Task[] list) {
        try {
            addTodo(line,list);
        } catch (NeoException e) {
            e.printException();
        }
    }
    public static void addTodo(String line, Task[] list) throws NeoException {
        catchFormatError(CommandType.TODO, line);

        int todoStringLength = 4;

        String description = line.substring(todoStringLength).trim();

        catchEmptyDescription("description", description);

        int taskArrayIndex = Task.getTotalTasks();

        list[taskArrayIndex] = new Todo(description);
        list[taskArrayIndex].printAddedTask();
    }
    public static boolean isMarked(int mark) {
        return (mark == 1);
    }
    public static void readFromFile(String filePath, Task[] list) throws IOException {
        File directory = new File("data");
        if (directory.mkdir()) {
            System.out.println("Creating new data folder...");
        }
        File f = new File(filePath);
        if (f.createNewFile()) {
            System.out.println("Creating new data.txt file...");
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] task = line.split(" / ");
            String taskType = task[0];
            int mark = Integer.parseInt(task[1]);
            int taskArrayIndex = Task.getTotalTasks();
            switch (taskType) {
            case "T":
                list[taskArrayIndex] = new Todo(task[2]);
                break;
            case "D":
                list[taskArrayIndex] = new Deadline(task[2], task[3]);
                break;
            case "E":
                list[taskArrayIndex] = new Event(task[2], task[3], task[4]);
                break;
            }
            list[taskArrayIndex].setDone(isMarked(mark));
        }
    }
    public static void writeToFile(String filePath, Task[] list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            String formatTask = list[i].formatTask();
            fw.write(formatTask + System.lineSeparator());
        }
        fw.close();
    }
    public static void main(String[] args) {
        Task[] list = new Task[100];
        try {
            readFromFile("data/data.txt", list);
        } catch (IOException e) {
            System.out.println("Error with data.txt file");
        }
        System.out.println("Hello! I'm Neo.");
        System.out.println("What can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(list);
            } else if (line.startsWith("mark")) {
                markTask(line, list);
            } else if (line.startsWith("unmark")) {
                unmarkTask(line, list);
            } else if (line.startsWith("event")) {
                handleEvent(line, list);
            } else if (line.startsWith("deadline")) {
                handleDeadline(line, list);
            } else if (line.startsWith("todo")){
                handleTodo(line, list);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            try {
                writeToFile("data/data.txt", list);
            } catch (IOException e) {
                System.out.println("Error with data.txt file.");
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
