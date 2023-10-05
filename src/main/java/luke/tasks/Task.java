package luke.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        if (done) {
            System.out.println("\tWoohoo! You have accomplished:");
        } else {
            System.out.println("\tHA! You still have to complete:");
        }
        isDone = done;
    }

    public void printGuide() {
        //nothing
    }
/*
    public String getType() {
        return "task";
    }

 */

    /*@Override
    public String toString() {
        return "";
    }
    */
    public String memoryString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[T]" + isDoneString + " task" + getDescription();
    }

}