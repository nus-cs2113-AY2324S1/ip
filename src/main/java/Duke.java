import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        //Greetings & renaming
        System.out.println("Hello! I'm JARVIS");
        System.out.println("What can I do for you?");

        Echo interact = new Echo();
        try {
            interact.echoBegin();
        }
        catch(DukeException e){
            System.out.println("OOPS! The description of a todo cannot be empty");
        }

        finally{
            interact.echoBegin();
        }

    }
}

