import lemon.commands.Command;
import lemon.exception.LemonException;
import lemon.file.Storage;
import lemon.task.TaskList;
import lemon.ui.UI;
import lemon.validation.Parser;

public class Lemon {
    private final Storage storage;
    private TaskList tasks;
    private final UI ui;
    private final Parser parser = new Parser();

    public Lemon(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LemonException e) {
            ui.displayError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getInput();
                ui.displayDivider();
                Command c = parser.parseInput(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LemonException e) {
                ui.displayError(e.getMessage());
            } finally {
                if (!isExit) {
                    ui.displayDivider();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Lemon("./data/lemon.txt").run();
    }
}
