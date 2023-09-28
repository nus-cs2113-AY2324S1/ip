package Task;

import java.util.ArrayList;
import Storage.Storage;

public class TaskList {

    protected ArrayList<Task> itemList;

    protected int i;

    public TaskList() {
        itemList = Storage.load();
        i = itemList.size();
    }

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

    public int size() {
        return i;
    }

    public Task get(int j) {
        return itemList.get(j);
    }

    public void addTodo(String in) {
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

        i += 1;
    }

    public void addEvent(String in) {
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

        i += 1;
    }

    public void addDeadline(String in) {
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

        i += 1;
    }

    public void delete(int j) {
        try {
            Task temp = itemList.get(j - 1);

            System.out.println(LINE_DIVIDER + "\n"
                    + "Alright, I've deleted this task:\n"
                    + "    " + temp.toString() + "\n"
                    + "You have " + (itemList.size() - 1) + " tasks left now. "
                    + ((itemList.size() == 1) ? "Congrats!" : "Get on it!") +"\n"
                    + LINE_DIVIDER);

            itemList.remove(j - 1);
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

        this.i -= 1;
    }

    public void markTask(int j) {

        itemList.get(j - 1).setDone(true);

        System.out.println(LINE_DIVIDER + "\n"
                + "    Nice, I've marked this task as done:\n"
                + "    " + itemList.get(j - 1).toString() + "\n"
                + LINE_DIVIDER);
    }

    public void unmarkTask(int j) {
        itemList.get(j - 1).setDone(false);

        System.out.println(LINE_DIVIDER + "\n"
                + "    Alright, I've unmarked this task as done:\n"
                + "    " + itemList.get(j - 1).toString() + "\n"
                + LINE_DIVIDER);
    }

    public void listItems() {
        System.out.println(LINE_DIVIDER);
        for(int j = 0; j < i; j += 1) {
            System.out.println((j + 1)
                    + ". " + itemList.get(j).toString());
        }
        System.out.println(LINE_DIVIDER);
    }
}
