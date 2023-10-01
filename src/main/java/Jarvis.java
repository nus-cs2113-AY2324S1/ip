import jarvis.exception.JarvisException;
import jarvis.ui.MessageDisplayer;

public class Jarvis {
    public static void main(String[] args) throws JarvisException {
        MessageDisplayer.displayWelcomeMessage();
        jarvis.command.UserInputHandler.loadTasksFromFile(); // Load jarvis.tasks from file at startup
        jarvis.command.UserInputHandler.processUserCommands();
        MessageDisplayer.displayGoodbyeMessage();
    }
}
