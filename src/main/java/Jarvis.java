import jarvis.exception.JarvisException;
import jarvis.tasklist.UserInputHandler;
import jarvis.ui.MessageDisplayer;

public class Jarvis {
    public static void main(String[] args) throws JarvisException {
        MessageDisplayer.displayWelcomeMessage();
        UserInputHandler.loadTasksFromFile(); // Load jarvis.tasks from file at startup
        UserInputHandler.processUserCommands();
        MessageDisplayer.displayGoodbyeMessage();
    }
}
