/**
 * TaskList represents a list of tasks for the Duke Robot
 * It provides methods for adding different types of tasks (Todo, Event, Deadline)
 * into the ArrayList, instead of allowing the Duke program to modify the content in the list,
 * retrieve tasks by index, getting the size of the task list, printing the list,
 * marking tasks as done, unmarking tasks, and deleting tasks. It also supports
 * searching for tasks containing specific keywords.
 * <p>
 * TaskList is used to manage and manipulate tasks within the Duke application.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-09-30
 */

package duke.inputProcess;

import duke.Task;
import duke.tasks.Deadline;
import duke.tasks.Todo;
import duke.tasks.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list;

    public TaskList(){
        list = new ArrayList<>();
    }

    public void addTodo(String userInput){
        list.add(new Todo(userInput));
    }

    public void addEvent(String eventToAdd, LocalDateTime startTime, LocalDateTime endTime){
        list.add(new Event(eventToAdd, startTime, endTime));
    }

    public void addDeadline(String deadlineToAdd, LocalDateTime deadlineTime){
        list.add(new Deadline(deadlineToAdd, deadlineTime));
    }

    public Task getByIndex(int index){
        return list.get(index);
    }

    public int getSize(){
        return list.size();
    }

    public ArrayList<Task> getList(){
        return list;
    }
}
