import command.Command;
import task.TaskList;
import exception.FrankException;
import java.io.IOException;
import java.util.Objects;
import utility.Storage;
import static utility.Constants.*;

public class Frank {
    public static void main(String[] args) {

        // Welcome Message
        System.out.println(SOLIDLINE + LOGO + SOLIDLINE);
        System.out.println("Hello user, I'm FRANK! Nice to meet you!\n" + SOLIDLINE);

        // Recover from storage or create new TaskList
        Storage storage = null;
        TaskList tasks = null;
        try {
            storage = new Storage(FILEPATH);
        } catch (IOException | FrankException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        if(Objects.requireNonNull(storage).getTaskData() == null) {
            tasks = new TaskList();
        } else {
            tasks = new TaskList(storage.getTaskData());
        }

        // Take commands
        Command command;
        while(true){
            try {
                command = CommandParser.getCommand();
                command.execute(tasks);
                storage.setTaskData(tasks.getTaskData());
            } catch(FrankException | IOException e) {
                System.out.println(SOLIDLINE + e.getMessage() + SOLIDLINE);
            }
        }
    }
}
