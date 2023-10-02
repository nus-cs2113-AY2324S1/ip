package duke.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class Tasklist extends ArrayList<Task>{

    /**
     * Returns a string representation of the tasks in the Tasklist object.
     *
     * @return A string representation of the tasks in the Tasklist object.
     */
    public String tasksToString() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("There are no tasks in your list.\n");
        } else {
            for (int i = 0; i < size(); i++) {
                sb.append(i + 1).append(". ").append(get(i)).append("\n");
            }
        }
        return sb.toString();
    } 

}