package cn.yfshadaow.cs2113.ip.task;

public abstract class Task {
    protected boolean isDone = false;
    protected String name;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toStringWithIsDone() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }
}
