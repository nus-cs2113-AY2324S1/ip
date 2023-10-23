package duke.commands;

import duke.inputProcess.TaskList;

public class PrintList {
    TaskList tasks;
    public PrintList(TaskList tasks){
        this.tasks = tasks;
    }

    public void print() {
        for (int i = 0; i < tasks.getList().size(); ++i) {
            System.out.print("\t" + (i+1) + ".");
            System.out.println(tasks.getList().get(i));
        }
        System.out.println("\tNow you have " + tasks.getSize() + " in the list");
    }
}
