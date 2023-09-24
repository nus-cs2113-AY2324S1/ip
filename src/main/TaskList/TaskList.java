package TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.ToDo;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the size of the task list.
     * 
     * @return Size of ArrayList.
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * Marks the task as completed or incompleted in the task list.
     * 
     * @param index Index of the task in the task list.
     * @param status True if mark as completed or False if mark as uncompleted.
     * @return Task that is marked or unmarked.
     * @throws IndexOutOfBoundsException If index is out of the size of the ArrayList.
     */
    public Task markList(int index, boolean status) throws IndexOutOfBoundsException {
        Task task = taskList.get(index);
        task.setCompleted(status);
        taskList.set(index, task);
        return task;
    }

    /**
     * Creates a Deadline object and stores into the task list.
     * 
     * @param description The description variable of the Deadline object.
     * @param by The by variable of the Deadline object.
     * @return Deadline object that was created.
     */
    public Deadline addDeadlineToList(String description, LocalDate by) {
            Deadline deadline = new Deadline(description, by);
            taskList.add(deadline);
            return deadline;
    }

    /**
     * Creates a Event object and stores into the task list.
     * 
     * @param description The description variable of the Event object.
     * @param from The from variable of the Event object.
     * @param to The to variable of the Event object.
     * @return Event object that was created.
     */
    public Event addEventToList(String description, LocalDate from, LocalDate to) {
        Event event = new Event(description, from, to);
        taskList.add(event);
        return event;
    }

    /**
     * Creates a ToDo object and stores into the task list.
     * 
     * @param description The description variable of the ToDo object.
     * @return ToDo object that was created.
     */
    public ToDo addToDoToList(String description) {
        ToDo todo = new ToDo(description);
        taskList.add(todo);
        return todo;
    }
    
    /**
     * Deletes the Task from the task list.
     * 
     * @param index Index of the task list.
     * @return The object that was Deleted.
     * @throws IndexOutOfBoundsException
     */
    public Task deleteTaskFromList(int index) throws IndexOutOfBoundsException {
        Task deletedTask = taskList.get(index);
        taskList.remove(index);
        return deletedTask;
    }
}