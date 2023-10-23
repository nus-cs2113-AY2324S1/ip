package duke.commands;

import duke.inputProcess.TaskList;

public class UnmarkTask {

    int taskNumToUnmark;
    String userInput;
    TaskList tasks;
    public UnmarkTask(String userInput, TaskList tasks){
        this.userInput = userInput;
        this.tasks = tasks;
    }

    public void unmark() {
        try {
            taskNumToUnmark = Integer.parseInt(userInput) - 1;
        } catch(NumberFormatException | NullPointerException e){
            System.out.println("\tOOPS!!! Need to enter the index of task want to unmark");
            return;
        }
        try {
            tasks.getList().get(taskNumToUnmark).unmark();
            System.out.print("\tOK, I've marked this task as not done yet:\n\t\t");
            System.out.println(tasks.getList().get(taskNumToUnmark));
        } catch(IndexOutOfBoundsException e){
            System.out.println("\tOOPS!!! Need to input an index from the list");
        }
    }
}
