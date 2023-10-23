package duke.commands;

import duke.inputProcess.TaskList;

public class AddTodo {

    String userInput;
    TaskList tasks;
    public AddTodo(String userInput, TaskList tasks){
        this.userInput = userInput;
        this.tasks = tasks;
    }

    public void AddTodoTask(){
        tasks.addTodo(userInput);
        System.out.println("\tGot it. I've added this task:\n\t\t" + tasks.getByIndex(tasks.getSize() - 1) + "\n\tNow you have "+ tasks.getSize() + " tasks in the list.");
    }
}
