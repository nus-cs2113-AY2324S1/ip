package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    protected char type;
    protected static int numTasks = 0;

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public static int getNumTasks() {
        return numTasks;
    }
    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
        System.out.println("_____________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n\t  " + this);
        System.out.println("_____________________________________________________");
    }

    public void setType(char letter) {
        this.type = letter;
    }
    // defines constructor for the task class
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = 'T';
        numTasks++;
    }

    // made changes as an earlier comment mentioned it was better to have
    // mark and unmark as two different functions
    public void unmarkTask() {
        this.isDone = false;
        System.out.println("Nice! I've marked this task as done:\n\t  " + this);
    }

    // the superclass toString outputs the mark box and the description
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    //
    public void printNewTask() {
        System.out.print("Got it. I've added this task:\n  " + this + "\nNow you have " + getNumTasks());
        if (getNumTasks() > 1) {
            System.out.println(" tasks in the list.");
        } else {
            System.out.println(" task in the list.");
        }
    }

    public void printDeleteTask() {
        System.out.println("_____________________________________________________");
        int num_tasks = getNumTasks() - 1;
        System.out.print("Noted. I have removed this task:\n  " + this + "\nNow you have " + (num_tasks));
        if (num_tasks > 1) {
            System.out.println(" tasks in the list.");
        } else {
            System.out.println(" task in the list.");
        }
        System.out.println("_____________________________________________________");
    }

}
