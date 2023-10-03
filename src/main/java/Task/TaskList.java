package Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Storage.Storage;
import Ui.Ui;
import Parser.Parser;

/**
 * Stores a list of tasks entered into the chatbot.
 * Contains methods for manipulating and accessing the tasks inside the list.
 */
public class TaskList {

    protected ArrayList<Task> itemList;

    public TaskList() {
        itemList = Storage.load();
    }

    public static final String LINE_DIVIDER = "    _____________________________________";

    /**
     * Returns the number of elements currently stored in itemList.
     *
     * @return The size of the list of tasks
     */
    public int size() {
        return itemList.size();
    }

    /**
     * Returns the task at a specified index in itemList.
     *
     * @param j Index of item to be retrieved
     * @return Task at index j in itemList
     */
    public Task get(int j) {
        return itemList.get(j);
    }

    /**
     * Adds a todo object into itemList
     *
     * @param in The Todo task to be added
     */
    public void addTodo(String in) {
        try {
            itemList.add(new ToDo(Parser.removeCommandWord(in)));
        } catch (EmptyDescriptionException e) {
            Ui.reportMissingTaskInfo();
            return;
        }

        Ui.reportTaskAdded(itemList, itemList.size() - 1);
    }

    /**
     * Adds an event object into itemList
     *
     * @param in The Event task to be added
     */
    public void addEvent(String in) {
        try {
            String[] vals = in.split(" /");
            String name = Parser.removeCommandWord(vals[0]);
            String from = Parser.removeCommandWord(vals[1]);
            String to = Parser.removeCommandWord(vals[2]);
            itemList.add(new Event(name, from, to));
        } catch (IndexOutOfBoundsException e) {
            Ui.reportMissingTaskInfo();
            return;
        } catch (EmptyDescriptionException e) {
            Ui.reportMissingTaskInfo();
            return;
        }

        Ui.reportTaskAdded(itemList, itemList.size() - 1);
    }

    /**
     * Adds a deadline object into itemList
     *
     * @param in The Deadline task to be added
     */
    public void addDeadline(String in) {
        try {
            String[] vals = in.split(" /");
            String name = Parser.removeCommandWord(vals[0]);
            String by = Parser.removeCommandWord(vals[1]);

            itemList.add(new Deadline(name, by));
        } catch (IndexOutOfBoundsException e) {
            Ui.reportMissingTaskInfo();
            return;
        } catch (EmptyDescriptionException e) {
            Ui.reportMissingTaskInfo();
            return;
        }

        Ui.reportTaskAdded(itemList, itemList.size() - 1);
    }

    /**
     * Deletes the item at a specified index.
     * Shows an error message if an invalid index is chosen, or if the list is empty.
     *
     * @param j Index of item to be deleted
     */
    public void delete(int j) {
        try {
            Task temp = itemList.get(j - 1);

            Ui.reportTaskDeleted(temp, itemList);

            itemList.remove(j - 1);
        } catch(IndexOutOfBoundsException e) {
            if(itemList.isEmpty()) {
                Ui.reportListEmpty();
            } else {
                Ui.reportOutOfBounds();
            }
        }
    }

    /**
     * Marks task at a specified index as done.
     *
     * @param j Index of task. Index starts from 1.
     */
    public void markTask(int j) {

        itemList.get(j - 1).setDone(true);

        Ui.reportTaskMarked(itemList, j);
    }

    /**
     * Unmarks task at a specified index as done.
     *
     * @param j Index of task. Index starts from 1.
     */
    public void unmarkTask(int j) {
        itemList.get(j - 1).setDone(false);

        Ui.reportTaskUnmarked(itemList, j);
    }

    /**
     * Prints a list of all the current items in itemList.
     */
    public void listItems() {
        System.out.println(LINE_DIVIDER);
        for(int j = 0; j < itemList.size(); j += 1) {
            System.out.println((j + 1)
                    + ". " + itemList.get(j).toString());
        }
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Finds a task whaich has a name containing a word which matches the input string
     * This function only supports single words (i.e. no spaces)
     *
     * @param in
     */
    public void findItem(String in) {
        List<Task> temp = itemList.stream()
                .filter(task -> Arrays.stream(
                        task.name.split(" "))
                        .anyMatch(word -> word.equalsIgnoreCase(in)))
                .collect(Collectors.toList());

        if(temp.isEmpty()) {
            System.out.println(LINE_DIVIDER
            + "\n    Oops, I couldn't find the task you're looking for!\n"
            + LINE_DIVIDER);
        } else {
            System.out.println(LINE_DIVIDER
            + "\nI found these tasks which match your search:");
            for (int j = 0; j < temp.size(); j += 1) {
                System.out.println((j + 1)
                        + ". " + temp.get(j).toString());
            }
            System.out.println(LINE_DIVIDER);
        }
    }

}
