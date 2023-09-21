package duke.task;

import duke.Utils;

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list;
    public void mark(int taskIndex) { //marking task as done
        Utils.printDivider();
        System.out.println("Nice! I've marked this task as done:\n");
        Task task = list.get(taskIndex - 1);
        task.markAsDone();
        printTask(task);
        Utils.printDivider();
    }

    public ToDoList() {
        list = new ArrayList<>();
    }

    int Number_Of_Task = 0;
    public void addToList(String taskType, Task task) {
        list.add(task);
        Number_Of_Task += 1;
        Utils.printDivider();
        if (taskType.equals("T") || taskType.equals("D") || taskType.equals("E") ) {
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
        } else {
            Utils.echo("added: " + task);
        }
        System.out.println("Now you have " + Number_Of_Task + " tasks in the list.");
        Utils.printDivider();
    }

    public void deleteTask(int taskNumber) {
        Task taskToDelete = list.get(taskNumber - 1);
        Utils.printDivider();
        list.remove(taskNumber - 1);
        Number_Of_Task -= 1;
        Utils.echo("Noted. I've removed this task:");
        printTask(taskToDelete);
        Utils.printDivider();
    }

    public void unmark(int taskIndex) {
        Utils.printDivider();
        System.out.println("OK, I've marked this task as not done yet:\n");
        Task task = list.get(taskIndex - 1);
        task.markAsUndone();
        printTask(task);
        Utils.printDivider();
    }

    private void printTask(Task task) {
        System.out.println(task.toString() + "\n");
    }
    public void printList() {
        Utils.printDivider();
        System.out.println("Here are the tasks in your list:\n");
        int counter = 1;
        for (Task task : list) {
            System.out.print(counter + ".");
            printTask(task);
            counter += 1;
        }
        Utils.printDivider();
    }
}
