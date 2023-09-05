public class Task {
    private String[] tasks;
    private int taskCount;
    private boolean[] isDone;
    public Task() {
        this.tasks = new String[100];
        this.taskCount = 0;
        this.isDone = new boolean[100]; // False by default
    }

    public void print_tasks() {
        if (this.taskCount == 0) {
            System.out.println("No Entry");
            return;
        }
        for (int i = 0; i<this.taskCount; i++) {
            System.out.print((i+1) + ".");
            if (isDone[i]) {
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }
            System.out.println(this.tasks[i]);
        }
    }

    public void add_to_tasks(String details) {
        this.tasks[this.taskCount] = details;
        this.taskCount++;
        System.out.println("added: " + details);
    }
    public void markAsDone(int number) {
        isDone[number-1] = true;
        System.out.println("Nice! I've market this task as done:");
        System.out.println("[X] " + tasks[number-1]);
    }

    public void markAsUndone(int number) {
        isDone[number-1] = false;
        System.out.println("Nice! I've market this task as not done yet:");
        System.out.println("[ ] " + tasks[number-1]);
    }
}
