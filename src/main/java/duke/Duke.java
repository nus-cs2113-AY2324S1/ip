package duke;

import java.io.IOException;

public class Duke {
    private static Storage STORAGE;
    private static TaskList TASKS;
    private static Ui UI;
    private static Parser PARSER;
    public static int FIRST_INDEX=0;
    public static int SECOND_INDEX=1;
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
            System.out.println(dukeEx);
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


