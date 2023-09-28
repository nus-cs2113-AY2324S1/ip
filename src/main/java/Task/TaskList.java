package Task;

import java.util.ArrayList;
import Storage.Storage;
import Ui.Ui;
import Parser.Parser;

public class TaskList {

    protected ArrayList<Task> itemList;

    public TaskList() {
        itemList = Storage.load();
    }

    public static final String LINE_DIVIDER = "    _____________________________________";

    public int size() {
        return itemList.size();
    }

    public Task get(int j) {
        return itemList.get(j);
    }

    public void addTodo(String in) {
        try {
            itemList.add(new ToDo(Parser.removeCommandWord(in)));
        } catch (EmptyDescriptionException e) {
            Ui.reportMissingTaskInfo();
            return;
        }

        Ui.reportTaskAdded(itemList, itemList.size() - 1);
    }

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

    public void markTask(int j) {

        itemList.get(j - 1).setDone(true);

        Ui.reportTaskMarked(itemList, j);
    }

    public void unmarkTask(int j) {
        itemList.get(j - 1).setDone(false);

        Ui.reportTaskUnmarked(itemList, j);
    }

    public void listItems() {
        System.out.println(LINE_DIVIDER);
        for(int j = 0; j < itemList.size(); j += 1) {
            System.out.println((j + 1)
                    + ". " + itemList.get(j).toString());
        }
        System.out.println(LINE_DIVIDER);
    }
}
