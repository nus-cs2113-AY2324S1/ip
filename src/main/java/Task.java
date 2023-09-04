public class Task {
    private static final String LINE = "____________________________________________________________\n";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void unmarkStatusIcon(){
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n[ ] " + getDescription() + "\n" + LINE);
    }

    public void markStatusIcon(){
        isDone = true;
        System.out.println("Nice! I've marked this task as done:\n[X] " + getDescription() + "\n" + LINE);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

