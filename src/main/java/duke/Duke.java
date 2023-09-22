package duke;

import java.io.IOException;

public class Duke {
    private static Storage STORAGE;
    private static TaskList TASKS;
    private static Ui UI;
    public static String DATAPATH = ".\\data\\duke.txt";


    public Duke(String filePath){
        UI = new Ui();
        STORAGE = new Storage(filePath);
        try {
            TASKS = new TaskList(STORAGE.load());
        } catch (IOException ioEx) {
            UI.showLoadingError(DATAPATH);
            TASKS = new TaskList();
        } catch (DukeException dukeEx) {
            UI.showError(dukeEx);
            System.out.println("Historical data load failed.");
            TASKS = new TaskList();
        }
    }

    public void run(){
        UI.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                UI.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(TASKS);
                STORAGE.save(TASKS);
                isExit = c.isExit();
            } catch (DukeException e) {
                UI.showError(e);
            }catch (IOException IOEx) {
                System.out.println("Unable to open write handler @ " + DATAPATH);
                return;
            } finally {
                UI.showLine();
            }
        }
    }

    public static void main(String[] args){
        new Duke(DATAPATH).run();
        UI.showExitMessage();
    }
}


