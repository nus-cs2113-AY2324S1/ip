package skippy;

import java.io.IOException;

import tasklist.TaskList;
import parser.SkippyParser;


/**
 *
 * The main program that controls all classes, inputs and outputs.
 */
public class Skippy {

    public boolean enteredBye;
    public SkippyUi ui;
    private final boolean WITH_SCANNER = true;
    public TaskList taskList;
    public FileManager fileManager;
    public SkippyParser parser;

    public Skippy() {
        enteredBye = false;
        ui = new SkippyUi(WITH_SCANNER);
        fileManager = new FileManager();
        parser = new SkippyParser(this);
    }

    public void exit() {
        try {
            fileManager.writeToFile(taskList);
        } catch (IOException e) {
            System.out.println("Saving file failed :(");
            e.printStackTrace();
        }
        ui.closeScanner();
        ui.printExitMessage();
    }

    public static void main (String[] args) {
        Skippy skippy = new Skippy();

        try {
            skippy.fileManager.createNewFileDirectory();
            skippy.fileManager.createNewSaveFile();
            skippy.taskList = skippy.fileManager.parseSaveFile();
            skippy.fileManager.printFileContents();
        } catch (IOException e) {
            System.out.println("File cannot be found.");
            return;
        } catch (NullPointerException e) {
            System.out.println("Nothing in list");
        }

        skippy.ui.printWelcomeMessage();

        while (!skippy.enteredBye) {
            String line = skippy.ui.getNextLine();
            skippy.parser.parseUserInput(line);
        }
        skippy.ui.closeScanner();
    }
}

