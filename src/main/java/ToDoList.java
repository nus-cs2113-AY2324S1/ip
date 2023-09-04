import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list;
    public ToDoList() {
        list = new ArrayList<>();
    }

    public void addToList(String task){
        list.add(new Task(task));
        Utils.printDivider();
        Utils.echo("added: " + task);
    }

    public void mark(int taskIndex) {
        Utils.printDivider();
        System.out.println("Nice! I've marked this task as done:\n");
        Task task = list.get(taskIndex - 1);
        task.markAsDone();
        printTask(task);
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
        if(!task.getIsDone()) {
            System.out.print("[ ] ");
        } else {
            System.out.print("[X] ");
        }
        System.out.println(task.getTaskName() + "\n");
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
