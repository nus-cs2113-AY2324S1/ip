package notGPT;

import notGPT.task.TaskList;
import notGPT.commands.CommandResponse;  
import notGPT.storage.Storage;
import notGPT.parser.Parser;
import notGPT.ui.Ui;

public class NotChatGPT {
    public static boolean isRunning;
    public static Storage storage;
    public static TaskList taskList;
    public static Parser parser;
    public static Ui ui = new Ui();

    public NotChatGPT(String filePath) {
        isRunning = true;
        storage = new Storage(filePath);
        taskList = new TaskList(storage);
        parser = new Parser();
        ui = new Ui();
    }

    public void run () {
        ui.displayIntroMessage();
        while (isRunning) {
            String userInput  = ui.getUserInput();
            String[] commandDetails = parser.parseCommand(userInput);
            ui.showLine();
            CommandResponse.respond(commandDetails);
            ui.showLine();
        }
        storage.saveTasks(taskList);
        System.exit(0);
    }
    public static void main(String[] args) {    
        NotChatGPT notChatGPT = new NotChatGPT("data/tasks.txt");
        notChatGPT.run();
    }
}
