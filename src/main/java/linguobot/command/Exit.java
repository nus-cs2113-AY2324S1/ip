package linguobot.command;

import linguobot.Ui;

/**
 * The Exit class represents a command to exit the LinguoBot application.
 */
public class Exit extends Command{

    /**
     * Executes the Exit command, printing a farewell message to the user.
     */
    @Override
    public void execute() {
        Ui.printText("Bye. Hope to see you again soon!");
    }

}
