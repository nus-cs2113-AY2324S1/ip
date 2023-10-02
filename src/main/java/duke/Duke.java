package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tasks.Tasklist;
import duke.storage.FileRW;
import duke.ui.Ui;

public class Duke {

    public static void main(String[] args) {
        try {
            Tasklist tasks = new Tasklist();
            FileRW.readFromFile(tasks);

            Ui.showWelcome();

            Command nextCommand = new Command();

            while (!nextCommand.isExit()) {
                try {
                    String input = Ui.readCommand();
                    nextCommand = Parser.parse(input);
                    nextCommand.execute(tasks);
                } catch (DukeException e) {
                    Ui.showMessage(e.getMessage());
                }
            }
            
            Ui.showGoodbye();

            FileRW.writeToFile(tasks);
        } catch (DukeException e) {
            Ui.showMessage(e.getMessage());
        }
    }
}