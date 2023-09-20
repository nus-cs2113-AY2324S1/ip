package cn.yfshadaow.cs2113.ip.task;

public class Todo extends Task {
    public Todo(String name) {
        this.name = name;
    }

    public static Todo parseTodo(String[] args) {
        return new Todo(String.join(" ", args));
    }

    @Override
    public String toStringWithIsDone() {
        return "[T][" + (isDone ? "X" : " ") + "] " + name;
    }
}
