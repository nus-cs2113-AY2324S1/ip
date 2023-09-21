import Task.Task;
import Task.Task.ToDo;
import Task.Task.Deadline;
import Task.Task.Event;
import Task.EmptyDescriptionException;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static final String LINE_DIVIDER = "    _____________________________________";

    public static String RemoveCommandWord(String in) throws EmptyDescriptionException {
        int firstSpaceIndex = in.indexOf(" ") + 1;

        String out = in.substring(firstSpaceIndex);

        if(firstSpaceIndex == 0 || firstSpaceIndex >= in.length()){
            // Throws exception when name of task is left empty
            throw new EmptyDescriptionException();
        }

        return out;
    }

    public static void addTodo(String in, Task[] itemList, int i) {
        try {
            itemList[i] = new ToDo(RemoveCommandWord(in));
        } catch (EmptyDescriptionException e) {
            e.printErrorMessage();
            return;
        }

        System.out.println(LINE_DIVIDER + "\n"
                + "Sure, I've added this task:\n"
                + "    " + itemList[i].toString()
                + "\n" + LINE_DIVIDER);
    }

    public static void addEvent(String in, Task[] itemList, int i) {
        try {
            String[] vals = in.split(" /");
            String name = RemoveCommandWord(vals[0]);
            String from = RemoveCommandWord(vals[1]);
            String to = RemoveCommandWord(vals[2]);
            itemList[i] = new Event(name, from, to);
        } catch (IndexOutOfBoundsException e) {
            System.out.println( LINE_DIVIDER +
                    "\n    Oop, looks like you forgot to add something!\n" +
                    LINE_DIVIDER
            );
            return;
        } catch (EmptyDescriptionException e) {
            e.printErrorMessage();
            return;
        }

        System.out.println(LINE_DIVIDER + "\n"
                + "Sure, I've added this task:\n"
                + "    " + itemList[i].toString()
                + "\n" + LINE_DIVIDER);
    }

    public static void addDeadline(String in, Task[] itemList, int i) {
        try {
            String[] vals = in.split(" /");
            String name = RemoveCommandWord(vals[0]);
            String by = RemoveCommandWord(vals[1]);

            itemList[i] = new Deadline(name, by);
        } catch (IndexOutOfBoundsException e) {
            System.out.println( LINE_DIVIDER +
                    "\n    Oop, looks like you forgot to add something!\n" +
                    LINE_DIVIDER
            );
            return;
        } catch (EmptyDescriptionException e) {
            e.printErrorMessage();
            return;
        }

        System.out.println(LINE_DIVIDER + "\n"
                + "Sure, I've added this task:\n"
                + "    " + itemList[i].toString()
                + "\n" + LINE_DIVIDER);
    }

    public static void markTask(Task[] itemList, int i) {
        itemList[i - 1].setDone(true);

        System.out.println(LINE_DIVIDER + "\n"
                + "    Nice, I've marked this task as done:\n"
                + "    " + itemList[i - 1].toString() + "\n"
                + LINE_DIVIDER);
    }

    public static void unmarkTask(Task[] itemList, int i) {
        itemList[i - 1].setDone(false);

        System.out.println(LINE_DIVIDER + "\n"
                + "    Alright, I've unmarked this task as done:\n"
                + "    " + itemList[i - 1].toString() + "\n"
                + LINE_DIVIDER);
    }

    public static void listItems(Task[] itemList, int i) {
        System.out.println(LINE_DIVIDER);
        for(int j = 0; j < i; j += 1) {
            System.out.println((j + 1)
                    + ". " + itemList[j].toString());
        }
        System.out.println(LINE_DIVIDER);
    }
    public static void main(String[] args) {

        Task[] itemList = IO.load();

        System.out.println("Hello! I'm Axel!\n"
                + "What can I do for you?\n"
                + LINE_DIVIDER);

        Scanner in = new Scanner(System.in);
        String buf = in.nextLine();
        int i = 0; // current index in itemList
        //set i
        while (itemList[i] != null) {
            i++;
        }

        while(!buf.equalsIgnoreCase("bye")){
            switch(buf.toLowerCase().split(" ")[0]) {

            case "list":
                listItems(itemList, i);
                break;

            case "mark":
                markTask(itemList,
                        Integer.parseInt(buf.split(" ")[1]));
                break;

            case "unmark":
                unmarkTask(itemList,
                        Integer.parseInt(buf.split(" ")[1]));
                break;

            case "todo":
                addTodo(buf, itemList, i);
                i += 1;
                break;

            case "deadline":
                addDeadline(buf, itemList, i);
                i += 1;
                break;

            case "event":
                addEvent(buf, itemList, i);
                i += 1;
                break;

            default:
                System.out.println(LINE_DIVIDER +
                        "\n    I couldn't find a command word, try again!\n"
                        + LINE_DIVIDER);
                break;
            }
            buf = in.nextLine();

            try {
                IO.save(itemList, i);
            } catch (IOException e) {
                System.out.println("    I couldn't save your file, try again?\n"
                        + LINE_DIVIDER);
            }
        }

        System.out.println(LINE_DIVIDER +
                "\n    Bye. Hope to see you again soon!\n"
                + LINE_DIVIDER);
    }
}

