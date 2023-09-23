import duke.commands.*;
import duke.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static duke.commands.Storage.loadFileContents;


/**
 * Represents entry point to the duke programme.
 */
public class Duke {
    public static void main(String[] args) throws DukeException, IOException ,FileNotFoundException{

        File taskListFile = new File("./duke.txt");

        if(taskListFile.exists() == false){
            taskListFile.createNewFile();
        }

        Ui interact = new Ui();

        System.out.println("Hello! I'm JARVIS");
        System.out.println("What can I do for you?");

        try {
            loadFileContents("./duke.txt");
            interact.UiBegin();
        }
        catch(FileNotFoundException e){

            taskListFile.createNewFile();
            interact.UiBegin();
        }

        catch(DukeException | IOException e){
            System.out.println("OOPS! The description of a todo cannot be empty");
            interact.UiBegin();
        }

    }
}

