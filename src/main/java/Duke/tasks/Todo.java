/**
 * Todo is a kind of task with no time.
 * It is a subclass of the Task class and includes methods to
 * retrieve and format the Todo task.
 * <p>
 * Todo objects can be created with a description.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-09-30
 */

package duke.tasks;

import duke.Task;

public class Todo extends Task {
    public Todo(String description){
        super(description);
    }
    public String toString() {
        return "[T]" + super.toString();
    }
    public String getEventTime(){
        return "";
    }
}
