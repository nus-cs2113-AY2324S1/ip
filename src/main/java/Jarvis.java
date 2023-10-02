import jarvis.command.Command;
import jarvis.parser.Parser;
import jarvis.tasklist.TaskList;
import jarvis.storage.Storage;
import jarvis.ui.Ui;

public class Jarvis {
    private Ui ui;
    private TaskList taskList;
    private Storage dataStorage;
    private Parser parser;

    static String filePath = "tasklist.txt";
    public Jarvis(String filePath){
        taskList = new TaskList();
        ui = new Ui(taskList);
        dataStorage = new Storage(filePath);
        parser = new Parser();
    }

    public void runJarvis(){
        ui.displayWelcomeMessage();
        boolean toExit = false;
        while(!toExit){
            String userCommand = ui.readCommand();
            Command command = parser.parseCommand(userCommand);
            toExit = command.toExit();
            command.executeCommand(ui, dataStorage, taskList);
        }
        ui.displayGoodbyeMessage();
    }

    public static void main(String[] args){
        new Jarvis(filePath).runJarvis();
    }
}
