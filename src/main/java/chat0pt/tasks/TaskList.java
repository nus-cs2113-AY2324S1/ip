package chat0pt.tasks;

import chat0pt.helper.DukeException;
import chat0pt.helper.FormatCheck;
import chat0pt.helper.FormatString;
import chat0pt.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(Ui ui, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void addTodo(String[] tokens) {
        try {
            if (tokens.length > 1) {
                String[] todo = FormatString.format("todo", tokens);
                if (todo != null) {
                    Todo task = new Todo(todo[0]);
                    tasks.add(task);
                    ui.successfulTask(task, tasks.size());
                } else {
                    throw new DukeException("Failed to add todo");
                }
            } else {
                ui.invalidMessage("todo");
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
            if (FormatCheck.deadlineFormat(tokens)) {
                String[] deadline = FormatString.format("deadline", tokens);
                if (deadline != null) {
                    Deadline task = new Deadline(deadline[0], deadline[1]);
                    tasks.add(task);
                    ui.successfulTask(task, tasks.size());
                } else {
                    throw new DukeException("Failed to add deadline");
                }
            } else {
                ui.invalidMessage("deadline");
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
            if (FormatCheck.eventFormat(tokens)) {
                String[] event = FormatString.format("event", tokens);
                if (event != null) {
                    Event task = new Event(event[0], event[1], event[2]);
                    tasks.add(task);
                    ui.successfulTask(task, tasks.size());
                } else {
                    throw new DukeException("Failed to add event");
                }
            } else {
                ui.invalidMessage("event");
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

    public ArrayList<Task> returnTaskList() {
        return tasks;
    }
}
