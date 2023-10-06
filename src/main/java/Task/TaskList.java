package Task;

import java.util.ArrayList;

/**
 * class indicating an array list of all task objects
 */
public class TaskList {


    public static ArrayList<Task> list;

    /**
     * constructor of "TaskList"
     */
    public TaskList() {
        //creation of array list
        list = new ArrayList<>();
    }

    /**
     * adds an object of task type to the TaskList
     */
    public static void add(Task task) {
        list.add(task);
    }

    /**
     * removes an object of task type from TaskList
     */
    public static void remove(int index) {
        list.remove(index);
    }

    /**
     * gets an object of task type from TaskList given the index
     */
    public static Task get(int index) {
        return list.get(index);
    }

    /**
     * obtaining size of TaskList
     */
    public static int size() {
        return list.size();
    }

    //find tasks with keyword
    public static void findTasks(String keyword) {
        ArrayList<Task> foundList = new ArrayList<>();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String taskAsString = list.get(i).toString();
            if (taskAsString.contains(keyword)) {
                foundList.add(list.get(i));
                System.out.print((i + 1) + ". ");
                System.out.println(get(i));
            }
        }
        if (foundList.isEmpty()) {
            System.out.println("oops, there are none :(");
        }
    }
}