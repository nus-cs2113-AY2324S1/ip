package bob.tasklist;

import bob.BobException;
import bob.deadline.Deadline;
import bob.event.Event;
import bob.task.Task;
import bob.todo.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskItems;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Constructs a TaskList. Populates TaskList with a given ArrayList of tasks.
     *
     * @param tasks list of tasks to add into task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        taskItems = tasks;
    }

    /**
     * Marks item in task as complete. Returns message confirming that task has been
     * marked as complete.
     *
     * @param line index of task to be marked as complete as a String.
     * @return formatted message declaring that the task has been marked as complete.
     */
    public String markItem(int markIdx) {

        taskItems.get(markIdx).setIsDone(true);

        return String.format("Nice! I've marked this task as done: \n\t  "
                + taskItems.get(markIdx).getTask());
    }

    /**
     * Marks item as incomplete. Returns message confirming that task has been marked as
     * incomplete.
     *
     * @param line index of task to be marked as incomplete as a String.
     * @return formatted message declaring that the task has been marked as incomplete.
     */
    public String unmarkItem(int markIdx) {
        taskItems.get(markIdx).setIsDone(false);

        return String.format("Nice! I've marked this task as undone: \n\t  " +
                taskItems.get(markIdx).getTask());
    }

    /**
     * Handler for creating a new Todo.
     *
     * @param description description of todo.
     * @return new Todo.
     * @throws BobException when description of todo is empty.
     */
    public String handleCreateTodo(Todo todo) {
        taskItems.add(todo);

        return taskItems.get(taskItems.size()-1).getTaskAdded(taskItems.size());
    }

    /**
     * Handler for adding a new deadline to the list of tasks.
     *
     * @param deadline Deadline to add into list of tasks.
     * @return Message indicating that deadline has been added.
     */
    public String handleCreateDeadline(Deadline deadline) {
        taskItems.add(deadline);

        return taskItems.get(taskItems.size()-1).getTaskAdded(taskItems.size());
    }

    /**
     * Handler for creating a new event.
     *
     * @param line containing a description, and the start and end times of the deadline in
     *             the form {@code /from start /to end}.
     * @return new Event.
     */
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

    /**
     * Handler for deleting a task.
     *
     * @param line index of task to delete as a String.
     * @return message confirming task has been deleted.
     */
    public String handleDeleteTask(String line) {
        int deleteIdx = Integer.parseInt(line) - 1;

        String deleteMessage = taskItems.get(deleteIdx).getTaskDeleted(taskItems.size()-1);
        taskItems.remove(deleteIdx);

        return deleteMessage;
    }

    /**
     * Handler to find a task containing a specific keyword.
     *
     * @param keyword used to locate tasks containing keyword in list.
     * @return formatted list of tasks containing the keyword.
     */
    public String handleFindTask(String keyword) {
        String result = "";
        for (int i = 0; i < taskItems.size(); i++) {
            if (taskItems.get(i).getTask().contains(keyword)) {
                result += String.format("%d. %s\n\t", i+1, taskItems.get(i).getTask()) ;
            }
        }

        return result.trim();
    }

    /**
     * Handler to get list of tasks when list command is entered.
     *
     * @return String containing a formatted list of all tasks.
     */
    public String handleGetList() {
        String result = "";
        for (int i = 0; i < taskItems.size(); i++) {
            result += String.format("%d. %s\n\t", i+1, taskItems.get(i).getTask()) ;
        }

        return result.trim();
    }

    /**
     * Handler to obtain a list of tasks to be saved into file as a formatted String.
     *
     * @return formatted list of tasks to be saved into a save file.
     */
    public String handleWriteList() {
        String tasksToWrite = "";
        for (Task task : taskItems) {
            tasksToWrite += task.getTaskForFile() + '\n';
        }

        return tasksToWrite;
    }
}
