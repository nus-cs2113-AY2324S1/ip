
import luke.actions.*;
import luke.user.LukeTimeError;
import luke.user.Ui;
import luke.tasks.*;
import luke.files.*;

public class Luke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Luke(String filePath) {//i dont get how this filePath works
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LukeTimeError e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                //c has theActionWord and parameters
                c.execute(tasks, ui, storage);
                //tasks has ArrayList<Task> mainTaskList, ui has String echo, storage has ArrayList<Task> tasks
                isExit = c.isExit(); //for bye command
            } catch (LukeTimeError e) { //from Parser.parse i think
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Luke("./out/artifacts/ip_jar/memory.txt").run();
        //new Luke("data/tasks.txt").run();
    }
}

