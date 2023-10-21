package command;

import message.Text;

import java.io.IOException;

public class ListCommand extends Command {

    public ListCommand(){
        super(false);
    }

    /**
     * list out all the tasks by print out all content in the saved file
     * raises exception if failed to access or locate the output file
     */
    @Override
    public void executeCommand() {
        try {
            Text.printList();
        } catch (IOException io) {
            System.out.println("OMG! Something went wrong! Please check if the source files are available.");
        }
    }
}
