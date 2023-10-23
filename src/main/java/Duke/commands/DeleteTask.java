package duke.commands;

import duke.inputProcess.TaskList;


public class DeleteTask {
    int taskNumToDelete;
    String userInput;
    TaskList tasks;
    public DeleteTask(String userInput, TaskList tasks){
        this.userInput = userInput;
        this.tasks = tasks;
    }
    public void delete() {
        try {
            taskNumToDelete = Integer.parseInt(userInput) - 1;
        } catch(NumberFormatException | NullPointerException e) {
            System.out.println("\tOOPS!!! Need to enter the index of task want to delete");
            return;
        }
        try {
            System.out.println("\tNoted. I've removed this task:\n\t\t" +
                    tasks.getList().get(taskNumToDelete));
            tasks.getList().remove(taskNumToDelete);
            System.out.println("\tNow you have " + tasks.getList().size() + " in the list");
        } catch(IndexOutOfBoundsException e){
            System.out.println("\tOOPS!!! Need to input an index from the list");
        }
    }
}
