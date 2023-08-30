public class Task {
    private boolean isComplete;
    private String name;

    public Task(String name) {
        this.name = name;
        this.isComplete = false;
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

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
