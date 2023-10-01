import jarvis.command.JarvisException;

public class Jarvis {
    public static void main(String[] args) throws JarvisException {
        jarvis.command.MessageDisplayer.displayWelcomeMessage();
        jarvis.command.UserInputHandler.loadTasksFromFile(); // Load jarvis.tasks from file at startup
        jarvis.command.UserInputHandler.processUserCommands();
        jarvis.command.MessageDisplayer.displayGoodbyeMessage();
    }
}
