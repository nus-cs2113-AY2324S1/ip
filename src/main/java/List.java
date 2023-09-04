import java.util.ArrayList;

class List {
    ArrayList<Task> tasks;

    public List() {
        this.tasks = new ArrayList<Task>(100);
    }

    public void add(String task) {
        this.tasks.add(Task.createTask(task));
    }

    public void mark(int taskID) {
        this.tasks.get(taskID - 1).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.tasks.get(taskID - 1));
    }

    public void unmark(int taskID) {
        this.tasks.get(taskID - 1).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tasks.get(taskID - 1));
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 1; i <= tasks.size(); i++) {
            output.append(i).append(".").append(this.tasks.get(i - 1)).append("\n");
        }
        return output.toString();
    }
}
