package linguobot;

import linguobot.command.Command;
import linguobot.command.Exit;
import linguobot.exceptions.LinguoBotException;
import linguobot.parser.Parser;
import linguobot.task.TaskList;

/**
 * The <code>LinguoBot</code> class represents a simple command-line task management application.
 * It allows users to interact with tasks, such as adding, listing, marking, unmarking,
 * and deleting tasks. Tasks are stored in a task list and can be loaded from and saved
 * to a file.
 */
public class LinguoBot {
    private final Input input = new Input();
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser(taskList);

    /**
     * Main entry-point for the application.
     */
    public void run() {
        String logo =
                " __    _                 _____     _   \n" +
                "|  |  |_|___ ___ _ _ ___| __  |___| |_ \n" +
                "|  |__| |   | . | | | . | __ -| . |  _|\n" +
                "|_____|_|_|_|_  |___|___|_____|___|_|  \n" +
                "            |___|                      ";

        String[] welcomeMessage = {"Hello I'm", logo, "What can I do for you?"};
        Ui.printMultipleText(welcomeMessage);

        Command command = null;
        while (!(command instanceof Exit)) {
            String inputString = input.getInputString();
            try {
                command = parser.parse(inputString);
                command.execute();
            } catch (LinguoBotException e) {
                Ui.printMultipleText(new String[]{
                        e.getMessage()
                });
            }
        }
    }
    public static void main(String[] args) {
        new LinguoBot().run();
    }
}