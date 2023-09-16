package torchie.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done torchie.task with X
    }

    public void printTask(String s) {
        System.out.println(s);
    }

    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public void markItem() {
        this.isDone = true;
        System.out.println("Nice! I've marked this torchie.task as done: ");
        printTask(this.toString());
    }

    public void unmarkItem() {
        this.isDone = false;
        System.out.println("Ok, I've marked this torchie.task as not done yet: ");
        printTask(this.toString());
    }

    public void announceTaskAdd() {
        System.out.println("Got it. I've added this torchie.task: ");
        System.out.print("  ");
    }

}