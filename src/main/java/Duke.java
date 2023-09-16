import duke.commands.*;
import duke.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static duke.commands.FileEditor.printFileContents;


public class Duke {
    public static void main(String[] args) throws DukeException, IOException {

        File taskListFile = new File("./duke.txt");

        //Greetings & renaming
        System.out.println("Hello! I'm JARVIS");
        System.out.println("What can I do for you?");

        Echo interact = new Echo();
        try {
            printFileContents("./duke.txt");
            interact.echoBegin();
        }
        catch(FileNotFoundException e){
            taskListFile.createNewFile();


        }
        catch(DukeException | IOException e){
            System.out.println("OOPS! The description of a todo cannot be empty");
            interact.echoBegin();
        }

    }
}

