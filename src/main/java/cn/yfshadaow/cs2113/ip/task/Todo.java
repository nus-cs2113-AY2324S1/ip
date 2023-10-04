package cn.yfshadaow.cs2113.ip.task;

import cn.yfshadaow.cs2113.ip.command.Command;

public class Todo extends Task {
    public Todo(String name) {
        this.name = name;
    }

    public static Todo parseTodo(Command cmd)  throws IllegalArgumentException{
        if (cmd.args.isEmpty()) {
            throw new IllegalArgumentException("Todo name cannot be empty!");
        }
        return new Todo(String.join(" ", cmd.args));
    }

    @Override
    public String toStringWithIsDone() {
        return "[T][" + (isDone ? "X" : " ") + "] " + name;
    }
}
