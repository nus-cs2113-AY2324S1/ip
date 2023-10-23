package bob.tasklist;

import bob.BobException;
import bob.deadline.Deadline;
import bob.event.Event;
import bob.task.Task;
import bob.todo.Todo;

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
     * @param markIdx index of task to be marked as complete.
     * @return formatted message declaring that the task has been marked as complete.
     */
    public String markItem(int markIdx) throws BobException {
        try {
            taskItems.get(markIdx).setIsDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("Index outside acceptable realm.");
        }

        return String.format("Nice! I've marked this task as done: \n\t  "
                + taskItems.get(markIdx).getTask());
    }

    /**
     * Marks item as incomplete. Returns message confirming that task has been marked as
     * incomplete.
     *
     * @param markIdx index of task to be marked as incomplete as a String.
     * @return formatted message declaring that the task has been marked as incomplete.
     */
    public String unmarkItem(int markIdx) throws BobException {
        try {
            taskItems.get(markIdx).setIsDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("Index outside acceptable realm.");
        }

        return String.format("Nice! I've marked this task as undone: \n\t  " +
                taskItems.get(markIdx).getTask());
    }

    /**
     * Handler for creating a new Todo.
     *
     * @param Todo todo to add.
     * @return Message declaring that todo has been added.
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
     * @param event Event to add into list of tasks.
     * @return Message indicating that event has been added.
     */
    public String handleCreateEvent(Event event) {
        taskItems.add(event);

        return taskItems.get(taskItems.size()-1).getTaskAdded(taskItems.size());
    }

    /**
     * Handler for deleting a task.
     *
     * @param deleteIdx index of task to delete.
     * @return message confirming task has been deleted.
     */
    public String handleDeleteTask(int deleteIdx) throws BobException {
        try {
            String deleteMessage = taskItems.get(deleteIdx).getTaskDeleted(taskItems.size()-1);
            taskItems.remove(deleteIdx);
            return deleteMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("Index outside acceptable realm.");
        }
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
