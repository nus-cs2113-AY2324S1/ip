package cn.yfshadaow.cs2113.ip.task;

import cn.yfshadaow.cs2113.ip.command.Command;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Instantiates a new Todo.
     *
     * @param name the name of task
     */
    public Todo(String name) {
        this.name = name;
    }

    /**
     * Parse todo from command
     *
     * @param cmd the command
     * @return the todo task
     * @throws IllegalArgumentException the illegal argument exception
     */
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
