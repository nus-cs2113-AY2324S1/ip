package neo.task;

import neo.exception.NeoException;
import neo.type.CommandType;
import neo.type.ErrorType;
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
    public static int findCounter(String toFind) {
        int count = 0;

        for (Task task : list) {
            if (task.description.contains(toFind) || toFind.contains(task.description)) {
                count ++;
            }
        }

        return count;
    }
    public static void find(String line) {
        try {
            String[] words = line.split(" ");
            String toFind = words[1];
            int count = findCounter(toFind);

            if (count == 0) {
                System.out.println("There are no matching tasks in your list.");
            } else {
                System.out.println("Here are the matching tasks in your list:");
                printMatchingTasks(toFind);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please give the keyword to find in the list.");
        }
    }

    private static void printMatchingTasks(String toFind) {
        int listIndex = 1;

        for (Task task : list) {
            if (task.description.contains(toFind) || toFind.contains(task.description)) {
                System.out.print(listIndex + ". ");
                System.out.println(task);
            }
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

    private static void catchFormatError(CommandType type, String line) throws NeoException {
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

    private static void catchEmptyDescription(String field, String description) throws NeoException {
        if (description.isBlank()) {
            throw new NeoException(field, ErrorType.EMPTY);
        }
    }

    public static void handleEvent(String line) {
        try {
            addEvent(line);
        } catch (NeoException e) {
            e.printException();
        }
    }

    private static void addEvent(String line) throws NeoException {
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
        printAddedTask();
    }

    public static void handleDeadline(String line) {
        try {
            addDeadline(line);
        } catch (NeoException e) {
            e.printException();
        }
    }

    private static void addDeadline(String line) throws NeoException {
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
        printAddedTask();
    }

    public static void handleTodo(String line) {
        try {
            addTodo(line);
        } catch (NeoException e) {
            e.printException();
        }
    }
    private static void addTodo(String line) throws NeoException {
        catchFormatError(CommandType.TODO, line);

        int todoStringLength = 4;

        String description = line.substring(todoStringLength).trim();

        catchEmptyDescription("description", description);

        Todo toAdd = new Todo(description);
        list.add(toAdd);
        printAddedTask();
    }
}
