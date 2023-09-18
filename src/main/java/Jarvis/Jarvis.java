package Jarvis;

import Command.JarvisException;

import java.io.IOException;

public class Jarvis {
    public static void main(String[] args) throws JarvisException {
        Command.MessageDisplayer.displayWelcomeMessage();
        Command.UserInputHandler.loadTasksFromFile(); // Load tasks from file at startup
        Command.UserInputHandler.processUserCommands();
        Command.MessageDisplayer.displayGoodbyeMessage();
    }
}
