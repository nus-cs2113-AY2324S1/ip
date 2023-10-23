package duke.commands;

import duke.inputProcess.TaskList;

public class MarkTask {
    int taskNumToMark;
    String userInput;
    TaskList tasks;
    public MarkTask(String userInput, TaskList tasks){
        this.userInput = userInput;
        this.tasks = tasks;
    }

    public void mark() {
        try {
            taskNumToMark = Integer.parseInt(userInput) - 1;
        } catch(NumberFormatException | NullPointerException e){
            System.out.println("\tOOPS!!! Need to enter the index of task want to mark");
            return;
        }
        try {
            tasks.getList().get(taskNumToMark).unmark();
            System.out.print("\tNice! I've marked this task as done:\n\t\t");
            System.out.println(tasks.getList().get(taskNumToMark));
        } catch(IndexOutOfBoundsException e){
            System.out.println("\tOOPS!!! Need to input an index from the list");
        }
    }
}
