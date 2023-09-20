import duke.commands.*;
import duke.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static duke.commands.FileEditor.loadFileContents;


public class Duke {
    public static void main(String[] args) throws DukeException, IOException ,FileNotFoundException{


        File taskListFile = new File("./duke.txt");
        if(taskListFile.exists() == false){
            taskListFile.createNewFile();
        }
        //Greetings & renaming
        System.out.println("Hello! I'm JARVIS");
        System.out.println("What can I do for you?");

        Echo interact = new Echo();
        try {
                loadFileContents("./duke.txt");
                interact.echoBegin();
        }
        catch(FileNotFoundException e){

            taskListFile.createNewFile();
            interact.echoBegin();


        }
        catch(DukeException | IOException e){
            System.out.println("OOPS! The description of a todo cannot be empty");
            interact.echoBegin();
        }

    }
}

