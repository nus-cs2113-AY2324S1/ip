package Jarvis;

import Command.JarvisException;

public class Jarvis {
    public static void main(String[] args) throws JarvisException {
        Command.MessageDisplayer.displayWelcomeMessage();
        Command.UserInputHandler.processUserCommands();
        Command.MessageDisplayer.displayGoodbyeMessage();
    }
}
