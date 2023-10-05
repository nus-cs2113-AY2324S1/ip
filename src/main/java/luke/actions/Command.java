package luke.actions;

import luke.files.Storage;
import luke.tasks.*;
import luke.user.LukeTimeError;
import luke.user.Ui;

public abstract class Command {
    protected ActionType theAction;
    protected String parameters; //no. or description
    public Command(ActionType theAction, String parameters) {
        this.theAction = theAction;
        this.parameters = parameters;
    }
    private boolean isExit;
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        //ui has String echo, storage has ArrayList<Task> tasks, tasks has ArrayList<Task> mainTaskList;

        String[] words = echo.split(" "); //to identify usage of features "mark" & "unmark"

        try {
            ActionType action = ActionType.valueOf(words[0].toUpperCase());

            try {
                switch (action) {



                    default:
                        assert false: "This line should never be reached";
                        break;


                }
            } catch (IndexOutOfBoundsException e) { //empty for MARK, UNMARK, TO DO description, DEADLINE description, EVENT description
                System.out.println("\tOOPS!!! You have missing arguments for " + words[0] + ".");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        echo = userInput.nextLine();

    }

    public boolean isExit() {
        return isExit;
    }
}
