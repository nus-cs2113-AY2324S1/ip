import java.util.ArrayList;
import neo.task.Task;
import neo.util.Parser;
import neo.util.Storage;
import neo.task.TaskList;
import neo.util.Ui;

/**
 * This is the main class of Neo chatbot.
 */
public class Neo {

    /**
     * Main method where data.txt file is loaded or generated, along with creating
     * the list for storing the tasks.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Storage.findFile(TaskList.getList());
        Ui.welcomeMessage();

        String input = Ui.readInput();
        ArrayList<Task> list = TaskList.getList();

        Parser.handleInput(input, list);
        Ui.byeMessage();
    }
}
