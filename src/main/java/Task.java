public class Task {
    private static final String LINE = "____________________________________________________________\n";
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setStatusIcon(String status) {
        if (status.equals("mark")){
            isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        } else{
            isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + this.toString());
        System.out.println(LINE);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        String typeOfTask = "[T]";
        String statusOfTask = "[" + getStatusIcon() + "] ";
        return typeOfTask + statusOfTask + getDescription();
    }
}

