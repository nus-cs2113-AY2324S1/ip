package torchie.ui;

import torchie.task.TaskList;

public class Ui {

    private TaskList taskList;
    public Ui(TaskList tl) {
        this.taskList = tl;
    }

    public void start() {
        System.out.println("Hello! I'm Torchie!");
        System.out.println("What can I do for you?");
        System.out.println("Let's play storetorchie today! You say something and I ll store it!");

        System.out.println("=================================");
        this.taskList.showTasks();
    }

    public void bye() {
        System.out.println("Awww bye :( Let's play again soon!");
    }
}
