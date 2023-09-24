package neo.task;

import neo.exception.NeoTaskException;
import neo.exception.NeoTimeException;
import neo.util.ErrorCatcher;
import neo.type.CommandType;
import java.util.ArrayList;


public abstract class TaskList {
    private static final ArrayList<Task> list = new ArrayList<>();

    public static ArrayList<Task> getList() {
        return list;
    }
    public static void printList() {
        int listIndex = 1;

        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.print((listIndex) + ". ");
            System.out.println(task);
            listIndex++;
        }
    }

    public static void printAddedTask() {
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

    public static void markTask(String line) {
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
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please make sure the index given is within the current list.");
        }
    }

    public static void unmarkTask(String line) {
        try {
            String[] words = line.split(" ");
            int listIndex = Integer.parseInt(words[1]);
            int arrayListIndex = listIndex - 1;

            Task toUnmark = list.get(arrayListIndex);
            toUnmark.setDone(false);
            list.set(arrayListIndex, toUnmark);
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println("    " + list.get(arrayListIndex));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please give the index of which task to unmark.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please make sure the index given is within the current list.");
        }
    }

    public static void deleteTask(String line) {
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
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please make sure the index given is within the current list.");
        }
    }

    public static void handleEvent(String line) {
        try {
            addEvent(line);
        } catch (NeoTaskException e) {
            e.printException();
        } catch (NeoTimeException e) {
            e.printException();
        }
    }

    private static void addEvent(String line) throws NeoTaskException, NeoTimeException {
        ErrorCatcher.catchFormatError(CommandType.EVENT, line);


        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        int fromStringLength = 5;
        int toStringLength = 3;
        int eventStringLength = 5;

        String description = line.substring(eventStringLength, fromIndex).trim();
        String from = line.substring(fromIndex + fromStringLength, toIndex).trim();
        String to = line.substring(toIndex + toStringLength).trim();

        ErrorCatcher.catchEmptyDescription("description", description);
        ErrorCatcher.catchEmptyDescription("/from", from);
        ErrorCatcher.catchEmptyDescription("/to", to);
        ErrorCatcher.catchTimeFormatError(from);
        ErrorCatcher.catchTimeFormatError(to);


        Event toAdd = new Event(description, from, to);
        list.add(toAdd);
        printAddedTask();
    }

    public static void handleDeadline(String line) {
        try {
            addDeadline(line);
        } catch (NeoTaskException e) {
            e.printException();
        } catch (NeoTimeException e) {
            e.printException();
        }
    }

    private static void addDeadline(String line) throws NeoTaskException, NeoTimeException {
        ErrorCatcher.catchFormatError(CommandType.DEADLINE, line);


        int byIndex = line.indexOf("/by");
        int byStringLength = 3;
        int deadlineStringLength = 8;

        String description = line.substring(deadlineStringLength, byIndex).trim();
        String by = line.substring(byIndex + byStringLength).trim();

        ErrorCatcher.catchEmptyDescription("description", description);
        ErrorCatcher.catchEmptyDescription("/by", by);
        ErrorCatcher.catchTimeFormatError(by);

        Deadline toAdd = new Deadline(description, by);
        list.add(toAdd);
        printAddedTask();
    }

    public static void handleTodo(String line) {
        try {
            addTodo(line);
        } catch (NeoTaskException e) {
            e.printException();
        }
    }
    private static void addTodo(String line) throws NeoTaskException {
        ErrorCatcher.catchFormatError(CommandType.TODO, line);

        int todoStringLength = 4;

        String description = line.substring(todoStringLength).trim();

        ErrorCatcher.catchEmptyDescription("description", description);

        Todo toAdd = new Todo(description);
        list.add(toAdd);
        printAddedTask();
    }
}
