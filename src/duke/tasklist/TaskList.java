package duke.tasklist;

import duke.DukeException;
import duke.deadline.Deadline;
import duke.event.Event;
import duke.task.Task;
import duke.todo.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskItems;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> tasks) {
        taskItems = tasks;
    }

    public String markItem(String line) {
        int markIdx = Integer.parseInt(line);
        taskItems.get(markIdx).setIsDone(true);

        return String.format("Nice! I've marked this task as done: \n"
                + taskItems.get(markIdx).getTask());
    }

    public String unmarkItem(String line) {
        int markIdx = Integer.parseInt(line);
        taskItems.get(markIdx).setIsDone(false);

        return String.format("Nice! I've marked this task as undone: \n" +
                taskItems.get(markIdx).getTask());
    }

    public String handleCreateTodo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty");
        }

        if (description.trim().length() == 0) {
            throw new DukeException("The description of a todo cannot be empty");
        }

        taskItems.add(new Todo(description));

        return taskItems.get(taskItems.size()-1).getTaskAdded(taskItems.size());
    }

    public String handleCreateDeadline(String line) throws DukeException {
        int byIdx = line.indexOf("/by");
        if (byIdx == -1) {
            throw new DukeException("The /by of a deadline must be specified");
        }

        // Extract task description and deadline from user input
        if (byIdx == 0) {
            throw new DukeException("The description of a deadline cannot be empty");
        }

        String description = line.substring(0,byIdx-1);
        if (description.trim().length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty");
        }

        int deadlineIdx = byIdx+ "/by ".length();
        if (deadlineIdx >= line.length()) {
            throw new DukeException("The /by of a deadline cannot be empty");
        }

        LocalDate deadline = LocalDate.parse(line.substring(deadlineIdx));

        taskItems.add(new Deadline(description, deadline));

        return taskItems.get(taskItems.size()-1).getTaskAdded(taskItems.size());
    }

    public String handleCreateEvent(String line) {
        int fromIdx = line.indexOf("/from");
        int toIdx = line.indexOf("/to");

        // Extract task description, start time and end time from user input
        String description = line.substring(0, fromIdx-1);
        String start = line.substring(fromIdx+ "/from ".length(), toIdx-1);
        String end = line.substring(toIdx+ "/to ".length());

        taskItems.add(new Event(description, start, end));

        return taskItems.get(taskItems.size()-1).getTaskAdded(taskItems.size());
    }

    public String handleDeleteTask(String line) {
        int deleteIdx = Integer.parseInt(line);

        String deleteMessage = taskItems.get(deleteIdx).getTaskDeleted(taskItems.size()-1);
        taskItems.remove(deleteIdx);

        return deleteMessage;
    }

    public String handleFindTask(String keyword) {
        String result = "";
        for (int i = 0; i < taskItems.size(); i++) {
            if (taskItems.get(i).getTask().contains(keyword)) {
                result += String.format("%d. %s\n\t", i+1, taskItems.get(i).getTask()) ;
            }
        }

        return result.trim();
    }

    public String handleGetList() {
        String result = "";
        for (int i = 0; i < taskItems.size(); i++) {
            result += String.format("%d. %s\n\t", i+1, taskItems.get(i).getTask()) ;
        }

        return result.trim();
    }

    public String handleWriteList() {
        String tasksToWrite = "";
        for (Task task : taskItems) {
            tasksToWrite += task.getTaskForFile() + '\n';
        }

        return tasksToWrite;
    }
}
