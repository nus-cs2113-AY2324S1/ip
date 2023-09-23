import java.util.ArrayList;
import neo.task.Task;

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
