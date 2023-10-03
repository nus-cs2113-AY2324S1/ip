package chat0pt.tasks;

import chat0pt.helper.DukeException;
import chat0pt.helper.FormatCheck;
import chat0pt.helper.FormatString;
import chat0pt.parser.Parser;
import chat0pt.ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringJoiner;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(Ui ui, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void addTodo(String[] tokens) {
        try {
            if (tokens.length < 2) {
                ui.invalidMessage("todo");
                return;
            }
            String[] todo = FormatString.format("todo", tokens);
            if (todo != null) {
                Todo task = new Todo(todo[0]);
                tasks.add(task);
                ui.successfulTask(task, tasks.size());
            } else {
                throw new DukeException("Failed to add todo");
            }
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            ui.invalidMessage("todo");
        } catch (DukeException e) {
            String[] toPrint = {e.toString()};
            ui.print(toPrint);
        }
    }

    public void addDeadline(String[] tokens) {
        try {
            if (!FormatCheck.deadlineFormat(tokens)) { // Checks if deadline is in the proper format
                ui.invalidMessage("deadline");
                return;
            }
            String[] deadline = FormatString.format("deadline", tokens);
            LocalDateTime deadlineTime = Parser.parseDateTime(deadline[1]);
            if (deadline.length == 2 && deadlineTime != null) {
                Deadline task = new Deadline(deadline[0], deadlineTime);
                tasks.add(task);
                ui.successfulTask(task, tasks.size());
            } else {
                throw new DukeException("Failed to add deadline! Ensure that your command is in the format deadline <event> /by <date and time in format YYYY-MM-DD HHMM>");
            }
        } catch (IndexOutOfBoundsException ioe) {
            ui.invalidMessage("deadline");
        } catch (DukeException e) {
            String[] toPrint = {e.toString()};
            ui.print(toPrint);
        }
    }

    public void addEvent(String[] tokens) {
        try {
            if (!FormatCheck.eventFormat(tokens)) { // Check if event is in the right format
                ui.invalidMessage("event");
                return;
            }
            String[] event = FormatString.format("event", tokens);
            if (event != null) {
                Event task = new Event(event[0], event[1], event[2]);
                tasks.add(task);
                ui.successfulTask(task, tasks.size());
            } else {
                throw new DukeException("Failed to add event");
            }
        } catch (IndexOutOfBoundsException ioe) {
            ui.invalidMessage("event");
        } catch (DukeException e) {
            String[] toPrint = {e.toString()};
            ui.print(toPrint);
        }
    }

    public void deleteTask(int taskNumber) {
        if (taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            ui.deleteMessage(task, tasks.size());
        } else {
            ui.invalidDelete();
        }
    }

    public void marker(int taskNumber, boolean mark) {
        if (taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.setMarked(mark);
            ui.mark(task, mark);
        } else {
            ui.invalidMark();
        }

    }

    public ArrayList<Task> findTasks(String[] input) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        if (input.length < 2) {
            return foundTasks;
        }
        StringJoiner searchString = new StringJoiner(" ");
        for (int i = 1; i < input.length; i++){
            searchString.add(input[i]);
        }
        String search = searchString.toString();
        for (Task t : tasks){
            if (t.getTask().contains(search)){
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }

    public ArrayList<Task> returnTaskList() {
        return tasks;
    }
}
