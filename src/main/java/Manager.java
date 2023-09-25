import common.Messages;
import listWhisper.exceptions.DescriptionFormatException;
import listWhisper.exceptions.InvalidCommandException;
import listWhisper.task.DataManager;
import listWhisper.task.ListOfTasks;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Manager {
    ListOfTasks listOfTasks;

    public Manager() throws IOException, DescriptionFormatException {
        this.listOfTasks = new ListOfTasks();
        Manager.loadTasksToList(this.listOfTasks);
    }

    public void readInput() {
            Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String input = s.nextLine();
            this.execute(input);
        }
    }
    void execute(String input) {

        try {
            if (input.startsWith("bye")) {
                Messages.printByeMessage();
                DataManager.saveList(this.listOfTasks);
                exit();
            } else if (input.startsWith("list")) {
                Messages.printListMessage(this.listOfTasks);
            } else if (input.startsWith("mark")) {
                Messages.printMarkMessage(this.listOfTasks.mark(input));
            } else if (input.startsWith("unmark")) {
                this.listOfTasks.unmark(input);
                Messages.printUnmarkMessage(this.listOfTasks.unmark(input));
            } else if (input.startsWith("todo")) {
                this.listOfTasks.addTodo(input.substring("todo".length()));
                Messages.printAddMessage(this.listOfTasks);
            } else if (input.startsWith("deadline")) {
                this.listOfTasks.addDeadline(input.substring("deadline".length()));
                Messages.printAddMessage(this.listOfTasks);
            } else if (input.startsWith("event")) {
                this.listOfTasks.addEvent(input.substring("event".length()));
                Messages.printAddMessage(this.listOfTasks);
            } else if (input.startsWith("delete")) {
                Messages.printDeleteMessage(this.listOfTasks, this.listOfTasks.delete(input));
            } else {
                throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DescriptionFormatException | InvalidCommandException | IOException e) {
            System.out.println(e);
        }
    }

    private static void exit() {
        System.exit(0);
    }
    private static void loadTasksToList(ListOfTasks listOfTasks) throws IOException, DescriptionFormatException {
        ArrayList<String> data = DataManager.readFile();
        listOfTasks.load(data);
    }
}
