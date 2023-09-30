import command.Command;
import task.TaskList;
import exception.FrankException;
import java.io.IOException;
import utility.Storage;
import static utility.Constants.SOLIDLINE;
import static utility.Constants.LOGO;

public class Frank {
    public static void main(String[] args) {

        // Welcome Message
        System.out.println(SOLIDLINE + LOGO + SOLIDLINE);
        System.out.println("Hello user, I'm FRANK! Nice to meet you!\n" + SOLIDLINE);

        try {
            Storage storage = new Storage("data/tasklist.txt");
        } catch (IOException | FrankException e) {
            System.out.println(e.getMessage());
        }

        Command command;
        TaskList tasks = new TaskList();
        while(true){
            try {
                command = CommandParser.getCommand();
                command.execute(tasks);
            } catch(FrankException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
