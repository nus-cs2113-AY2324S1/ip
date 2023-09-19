import Task.Task;
import Task.Task.ToDo;
import Task.Task.Deadline;
import Task.Task.Event;
import Task.EmptyDescriptionException;

import java.util.ArrayList;
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

    public static void addTodo(String in, ArrayList<Task> itemList, int i) {
        try {
            itemList.add(new ToDo(RemoveCommandWord(in)));
        } catch (EmptyDescriptionException e) {
            e.printErrorMessage();
            return;
        }

        System.out.println(LINE_DIVIDER + "\n"
                + "Sure, I've added this task:\n"
                + "    " + itemList.get(i).toString()
                + "\n" + LINE_DIVIDER);
    }

    public static void addEvent(String in, ArrayList<Task> itemList, int i) {
        try {
            String[] vals = in.split(" /");
            String name = RemoveCommandWord(vals[0]);
            String from = RemoveCommandWord(vals[1]);
            String to = RemoveCommandWord(vals[2]);
            itemList.add(new Event(name, from, to));
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
                + "    " + itemList.get(i).toString()
                + "\n" + LINE_DIVIDER);
    }

    public static void addDeadline(String in, ArrayList<Task> itemList, int i) {
        try {
            String[] vals = in.split(" /");
            String name = RemoveCommandWord(vals[0]);
            String by = RemoveCommandWord(vals[1]);

            itemList.add(new Deadline(name, by));
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
                + "    " + itemList.get(i).toString()
                + "\n" + LINE_DIVIDER);
    }

    public static void delete(int i, ArrayList<Task> itemList) {
        try {
            Task temp = itemList.get(i - 1);

            System.out.println(LINE_DIVIDER + "\n"
                    + "Alright, I've deleted this task:\n"
                    + "    " + temp.toString() + "\n"
                    + "You have " + (itemList.size() - 1) + " tasks left now. "
                    + ((itemList.size() == 1) ? "Congrats!" : "Get on it!") +"\n"
                    + LINE_DIVIDER);

            itemList.remove(i - 1);
        } catch(IndexOutOfBoundsException e) {
            if(itemList.isEmpty()) {
                System.out.println(LINE_DIVIDER + "\n"
                        + "    The list is empty, yo!\n"
                        + LINE_DIVIDER);
            } else {
                System.out.println(LINE_DIVIDER + "\n"
                        + "    That's out of bounds, dude!\n"
                        + LINE_DIVIDER);
            }
        }
    }

    public static void markTask(ArrayList<Task> itemList, int i) {
        itemList.get(i - 1).setDone(true);

        System.out.println(LINE_DIVIDER + "\n"
                + "    Nice, I've marked this task as done:\n"
                + "    " + itemList.get(i - 1).toString() + "\n"
                + LINE_DIVIDER);
    }

    public static void unmarkTask(ArrayList<Task> itemList, int i) {
        itemList.get(i - 1).setDone(false);

        System.out.println(LINE_DIVIDER + "\n"
                + "    Alright, I've unmarked this task as done:\n"
                + "    " + itemList.get(i - 1).toString() + "\n"
                + LINE_DIVIDER);
    }

    public static void listItems(ArrayList<Task> itemList, int i) {
        System.out.println(LINE_DIVIDER);
        for(int j = 0; j < i; j += 1) {
            System.out.println((j + 1)
                    + ". " + itemList.get(j).toString());
        }
        System.out.println(LINE_DIVIDER);
    }
    public static void main(String[] args) {

        System.out.println("Hello! I'm Axel!\n"
                + "What can I do for you?\n"
                + LINE_DIVIDER);

        Scanner in = new Scanner(System.in);
        String buf = in.nextLine();
        ArrayList<Task> itemList = new ArrayList<Task>();
        int i = 0; // current index in itemList

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

            case "delete":
                delete(Integer.parseInt(buf.split(" ")[1]), itemList);
                break;

            default:
                System.out.println(LINE_DIVIDER +
                        "\n    I couldn't find a command word, try again!\n"
                        + LINE_DIVIDER);
                break;
            }
            buf = in.nextLine();
        }

        System.out.println(LINE_DIVIDER +
                "\n    Bye. Hope to see you again soon!\n"
                + LINE_DIVIDER);
    }
}

