public class Task {

    private boolean isComplete;
    private String name;

    static int numberOfTasks = 0;

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

    public String addMessage() {
        return name;
    }
    public String listText() {
        return "[" + (getIsComplete() ? "X" : " ") + "] " + getName();
    }
}
