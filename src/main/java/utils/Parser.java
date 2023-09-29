package utils;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Parser {

    public String getTaskName(String command, String message) throws DukeException {
        if (command.length() == message.trim().length()) {
            throw new DukeException("The description of " + command + " cannot be empty.");
        }
        return message.substring(command.length(),
                message.contains("/") ? message.indexOf("/") : message.length()).trim();
    }

    public String getTime(String message, String command) throws DukeException {
        if (!message.contains(command)) {
            throw new DukeException("Command " + command + " must be included in description.");
        } else if (command.equals("/from") && !message.contains("/to")) {
            throw new DukeException("Command /to must be included in description of events.");
        }
        String time = message.substring(message.indexOf(command) + command.length(),
                command.equals("/from") ? message.indexOf("/to") : message.length()).trim();
        if (time.isEmpty()) {
            throw new DukeException("Command " + command + " has no description.");
        }
        return time;
    }

    public boolean isExit(String in) {
        return in.equals("bye".trim());
    }

    public void parseAndExecute(String in, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (in.equals("bye")) {
            storage.save(tasks.getTasks());
            ui.echo("Bye. Hope to see you again soon!");
        }
        else if (in.equals("list")) {
            ui.printList(tasks.getTasks());
        } else if (in.startsWith("mark")) {
            String taskName = getTaskName("mark", in);
            int index = Integer.parseInt(taskName) - 1;
            tasks.updateTaskStatus(index, true);
            ui.echo("Nice! I've marked this task as done:\n" + tasks.getTask(index).getListText());
        } else if (in.startsWith("unmark")) {
            String taskName = getTaskName("unmark", in);
            int index = Integer.parseInt(taskName) - 1;
            tasks.updateTaskStatus(index, false);
            ui.echo("OK, I've marked this task as not done yet:\n" + tasks.getTask(index).getListText());
        } else if (in.startsWith("todo")) {
            String taskName = getTaskName("todo", in);
            Task newTask = new Todo(taskName);
            tasks.addTask(newTask);
            ui.echo("added: " + newTask.getAddMessage());
        } else if (in.startsWith("deadline")) {
            String taskName = getTaskName("deadline", in);
            String by = getTime(in, "/by");
            Task newTask = new Deadline(taskName, by);
            tasks.addTask(newTask);
            ui.echo("added: " + newTask.getAddMessage());
        } else if (in.startsWith("event")) {
            String taskName = getTaskName("event", in);
            String from = getTime(in, "/from");
            String to = getTime(in.substring(in.indexOf("/") + 1), "/to");
            Task newTask = new Event(taskName, from, to);
            tasks.addTask(newTask);
            ui.echo("added: " + newTask.getAddMessage());
        } else if (in.startsWith("clear")) {
//                    bot.clearList();
        } else if (in.startsWith("delete")) {
            String taskName = getTaskName("delete", in);
            Task deletedTask = tasks.deleteTask(Integer.parseInt(taskName) - 1);
            ui.echo(deletedTask.getDeleteMessage());
        } else if (in.startsWith("find")) {
            String keyword = getTaskName("find", in);
            ui.printListByKeyword(tasks.getTasks(), keyword);
        } else if (in.startsWith("due by")) {
            String date = getTaskName("due by", in);
            ui.printListByDate(tasks.getTasks(), date);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
