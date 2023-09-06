package Jarvis;

public class Jarvis {
    public static void main(String[] args) {
        Command.MessageDisplayer.displayWelcomeMessage();
        Command.UserInputHandler.processUserCommands();
        Command.MessageDisplayer.displayGoodbyeMessage();
    }
}
