package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

public class TextUi {

    private static final String NAME = "MudMud";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public void tellGreeting() {
        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\tOh hello! I'm " + NAME + ".");
        System.out.println("\tHow can I help you today?");
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    public void tellGoodbye() {
        System.out.println("\tGoodbye! I am going to sleep now.");
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    public void printNumOfTasks(int tasksCount) {
        System.out.println("\tI took a peak at the list and you have " + tasksCount
                + (tasksCount == 1 ? " task" : " tasks") + " currently.");
    }

    public void printRecentTask(Task task, int tasksCount) {
        System.out.println("\tI have added the following task into the list:");
        System.out.println("\t\t" + task);
        printNumOfTasks(tasksCount);
    }

    public void printTasks(ArrayList<Task> tasks, int tasksCount) {
        System.out.println("\tHere are your tasks you have inputted:");
        for (int i = 1; i <= tasksCount; i++) {
            System.out.println("\t" + i + "." + tasks.get(i - 1));
        }
    }
}
