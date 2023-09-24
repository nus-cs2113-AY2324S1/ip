package neo.task;

import neo.exception.NeoTaskException;
import neo.exception.NeoTimeException;
import neo.type.TimeType;
import neo.type.ErrorType;
import neo.type.CommandType;
import neo.type.TimeErrorType;
import neo.type.TimeValueType;
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

    private static void catchFormatError(CommandType type, String line) throws NeoTaskException {
        switch (type) {
        case TODO:
            if (line.contains("/from") || (line.contains("/to"))) {
                throw new NeoTaskException("/from", ErrorType.MISUSE);
            }
            if (line.contains("/by")) {
                throw new NeoTaskException("/by", ErrorType.MISUSE);
            }
            break;
        case DEADLINE:
            if (line.contains("/from") || (line.contains("/to"))) {
                throw new NeoTaskException("/from", ErrorType.MISUSE);
            }
            if (!line.contains("/by")) {
                throw new NeoTaskException("/by", ErrorType.FORMAT);
            }
            break;
        case EVENT:
            if (line.contains("/by")) {
                throw new NeoTaskException("/by", ErrorType.MISUSE);
            }
            if (!line.contains("/from")) {
                throw new NeoTaskException("/from", ErrorType.FORMAT);
            }
            if (!line.contains("/to")) {
                throw new NeoTaskException("/to", ErrorType.FORMAT);
            }
            break;
        }
    }
    private static void catchDateAndTimeError(String line) throws NeoTimeException {
        String[] dateTime = line.split(" ");
        if (!line.matches("(.*)/(.*)/(.*)") || dateTime[1].length() != 4) {
            throw new NeoTimeException(TimeType.DATE_AND_TIME, TimeValueType.DAY, TimeErrorType.FORMAT);
        }

        catchDateError(dateTime[0]);

        int hourStartIndex = 0;
        int minuteStartIndex = 2;

        String stringHour = dateTime[1].substring(hourStartIndex, minuteStartIndex);
        String stringMinute = dateTime[1].substring(minuteStartIndex);
        int hour;
        int minute;

        try {
            hour = Integer.parseInt(stringHour);
            minute = Integer.parseInt(stringMinute);
        } catch (NumberFormatException e) {
            throw new NeoTimeException(TimeType.DATE_AND_TIME, TimeValueType.HOUR, TimeErrorType.FORMAT);
        }

        if (hour > 23) {
            throw new NeoTimeException(TimeType.DATE_AND_TIME, TimeValueType.HOUR, TimeErrorType.VALUE);
        } else if (minute > 59) {
            throw new NeoTimeException(TimeType.DATE_AND_TIME, TimeValueType.MINUTE, TimeErrorType.VALUE);
        }
    }

    private static void catchDateError(String line) throws NeoTimeException {
        String[] date = line.split("/");

        if (!line.matches("(.*)/(.*)/(.*)")) {
            throw new NeoTimeException(TimeType.DATE, TimeValueType.HOUR, TimeErrorType.FORMAT);
        }

        String stringDay = date[0];
        String stringMonth = date[1];
        String year = date[2];

        if (stringDay.length() != 2 || stringMonth.length() != 2 || year.length() != 4) {
            throw new NeoTimeException(TimeType.DATE, TimeValueType.MINUTE, TimeErrorType.FORMAT);
        }

        int day;
        int month;

        try {
            day = Integer.parseInt(stringDay);
            month = Integer.parseInt(stringMonth);
        } catch (NumberFormatException e) {
            throw new NeoTimeException(TimeType.DATE, TimeValueType.HOUR, TimeErrorType.FORMAT);
        }

        if (day > 31) {
            throw new NeoTimeException(TimeType.DATE, TimeValueType.DAY, TimeErrorType.VALUE);
        } else if (month > 12) {
            throw new NeoTimeException(TimeType.DATE, TimeValueType.MONTH, TimeErrorType.VALUE);
        }
    }
    private static boolean hasTime(String line) {
        String[] dateAndTime = line.split(" ");
        return dateAndTime.length == 2;
    }
    private static void catchTimeFormatError(String line) throws NeoTimeException {
        if (hasTime(line)) {
            catchDateAndTimeError(line);
        } else {
            catchDateError(line);
        }
    }

    private static void catchEmptyDescription(String field, String description) throws NeoTaskException {
        if (description.isBlank()) {
            throw new NeoTaskException(field, ErrorType.EMPTY);
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
        catchTimeFormatError(from);
        catchTimeFormatError(to);


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
        catchFormatError(CommandType.DEADLINE, line);


        int byIndex = line.indexOf("/by");
        int byStringLength = 3;
        int deadlineStringLength = 8;

        String description = line.substring(deadlineStringLength, byIndex).trim();
        String by = line.substring(byIndex + byStringLength).trim();

        catchEmptyDescription("description", description);
        catchEmptyDescription("/by", by);
        catchTimeFormatError(by);

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
        catchFormatError(CommandType.TODO, line);

        int todoStringLength = 4;

        String description = line.substring(todoStringLength).trim();

        catchEmptyDescription("description", description);

        Todo toAdd = new Todo(description);
        list.add(toAdd);
        printAddedTask();
    }
}
