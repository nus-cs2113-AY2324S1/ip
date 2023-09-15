import neo.exception.NeoException;
import neo.task.Deadline;
import neo.task.Event;
import neo.task.Task;
import neo.task.Todo;
import neo.type.CommandType;
import neo.type.ErrorType;
import java.util.Scanner;
import java.util.ArrayList;
public class Neo {

    public static void printList(ArrayList<Task> list) {
        int listIndex = 1;

        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.print((listIndex) + ". ");
            System.out.println(task);
            listIndex++;
        }
    }

    public static void printAddedTask(ArrayList<Task> list) {
        int listSize = list.size();
        int arrayListIndex = listSize - 1;

        System.out.println("Got it. I've added this task:");
        System.out.println("    " + list.get(arrayListIndex));
        System.out.print("Now you have " + listSize);
        if (listSize == 1) {
            System.out.println(" task in the list.");
        } else {
            System.out.println(" tasks in the list.");
        }
    }

    public static void markTask(String line, ArrayList<Task> list) {
        try {
            String[] words = line.split(" ");
            int listIndex = Integer.parseInt(words[1]);
            int arrayListIndex = listIndex - 1;

            Task toMark = list.get(arrayListIndex);
            toMark.setDone(true);
            list.set(arrayListIndex, toMark);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("    " + list.get(arrayListIndex));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please give the index of which task to mark.");
        }
    }

    public static void unmarkTask(String line, ArrayList<Task> list) {
        try {
            String[] words = line.split(" ");
            int listIndex = Integer.parseInt(words[1]);
            int arrayListIndex = listIndex - 1;

            Task toUnmark = list.get(arrayListIndex);
            toUnmark.setDone(false);
            list.set(arrayListIndex, toUnmark);
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println("    " + list.get(arrayListIndex));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please give the index of which task to unmark.");
        }
    }

    public static void deleteTask(String line, ArrayList<Task> list) {
        try {
            String[] words = line.split(" ");
            int listIndex = Integer.parseInt(words[1]);
            int arrayListIndex = listIndex - 1;

            Task toRemove = list.get(arrayListIndex);
            list.remove(arrayListIndex);
            int listSize = list.size();

            System.out.println("Noted. I've removed this task:");
            System.out.println("    " + toRemove);
            System.out.print("Now you have ");
            if (listSize == 1) {
                System.out.println(listSize + " task in the list.");
            } else {
                System.out.println(listSize + " tasks in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please give the index of which task to delete.");
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

    public static void handleEvent(String line, ArrayList<Task> list) {
        try {
            addEvent(line, list);
        } catch (NeoException e) {
            e.printException();
        }
    }

    public static void addEvent(String line, ArrayList<Task> list) throws NeoException {
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

        Event toAdd = new Event(description, from, to);
        list.add(toAdd);
        printAddedTask(list);
    }

    public static void handleDeadline(String line, ArrayList<Task> list) {
        try {
            addDeadline(line, list);
        } catch (NeoException e) {
            e.printException();
        }
    }

    public static void addDeadline(String line, ArrayList<Task> list) throws NeoException {
        catchFormatError(CommandType.DEADLINE, line);

        int byIndex = line.indexOf("/by");
        int byStringLength = 3;
        int deadlineStringLength = 8;

        String description = line.substring(deadlineStringLength, byIndex).trim();
        String by = line.substring(byIndex + byStringLength).trim();

        catchEmptyDescription("description", description);
        catchEmptyDescription("/by", by);

        Deadline toAdd = new Deadline(description, by);
        list.add(toAdd);
        printAddedTask(list);
    }

    public static void handleTodo(String line, ArrayList<Task> list) {
        try {
            addTodo(line,list);
        } catch (NeoException e) {
            e.printException();
        }
    }
    public static void addTodo(String line, ArrayList<Task> list) throws NeoException {
        catchFormatError(CommandType.TODO, line);

        int todoStringLength = 4;

        String description = line.substring(todoStringLength).trim();

        catchEmptyDescription("description", description);

        Todo toAdd = new Todo(description);
        list.add(toAdd);
        printAddedTask(list);
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Neo.");
        System.out.println("What can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        ArrayList<Task> list = new ArrayList<>();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(list);
            } else if (line.startsWith("mark")) {
                markTask(line, list);
            } else if (line.startsWith("unmark")) {
                unmarkTask(line, list);
            } else if (line.startsWith("delete")) {
                deleteTask(line, list);
            } else if (line.startsWith("event")) {
                handleEvent(line, list);
            } else if (line.startsWith("deadline")) {
                handleDeadline(line, list);
            } else if (line.startsWith("todo")){
                handleTodo(line, list);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
