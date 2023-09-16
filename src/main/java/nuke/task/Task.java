package nuke.task;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        setName(name);
        setDone(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public abstract String getType();

    @Override
    public String toString() {
        String mark = isDone()? "X" : " ";
        return String.format("[%s][%s] %s", getType(), mark, getName());
    }

    public String formatData() {
        String mark = isDone()? "1" : "0";
        return String.format("%s / %s / %s", getType(), mark, getName());
    }
}
