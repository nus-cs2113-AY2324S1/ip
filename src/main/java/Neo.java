import java.util.ArrayList;
import neo.task.Task;
import neo.util.Parser;
import neo.util.Storage;
import neo.util.TaskList;
import neo.util.Ui;

public class Neo {
    public static void main(String[] args) {
        Storage.findFile(TaskList.getList());
        Ui.welcomeMessage();

        String input = Ui.readInput();
        ArrayList<Task> list = TaskList.getList();

        Parser.handleInput(input, list);
        Ui.byeMessage();
    }
}
