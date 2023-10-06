package duke;

import static duke.Duke.taskList;
/**
 * A utility class that will load the memory of previous instances of use, will load up the previous task list
 */
public class Storage {
    /**
     * Restores task list from the storage of previous uses
     */
    public static void restoreMemory() {
        taskList.initialiseToDoList();
    }
}
