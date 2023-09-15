package task;

public abstract class Task {

    private boolean isComplete;
    private String name;

    protected static int numberOfTasks = 0;

    public Task(String name) {
        this.name = name;
        this.isComplete = false;
        numberOfTasks++;
    }

    public String getName() {
        return name;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsComplete(boolean complete) {
        isComplete = complete;
    }

    public abstract String getAddMessage();
    public String getListText() {
        return "[" + (getIsComplete() ? "X" : " ") + "] " + getName();
    }

    public String getSaveString() {
        return " | " + (getIsComplete() ? "1" : "0") + " | " + getName();
    }

}
