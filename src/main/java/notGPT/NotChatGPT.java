package notGPT;

import notGPT.task.TaskList;
import notGPT.commands.IntroMessage;
import notGPT.commands.CommandResponse;  
import notGPT.commands.UserInput;

public class NotChatGPT {
    public static boolean isRunning;
    public static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        isRunning = true;
        IntroMessage.displayIntroMessage();
        taskList.loadTasks();
        while (isRunning) {
            String[] userInput = UserInput.getUserInput();
            CommandResponse.respond(userInput);
        }
        taskList.saveTasks();
    }
}
