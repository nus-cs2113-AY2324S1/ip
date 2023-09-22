import listWhisper.exceptions.DescriptionFormatException;
import listWhisper.exceptions.InvalidCommandException;
import listWhisper.task.List;
import listWhisper.task.Saver;

import java.io.IOException;
import java.util.Scanner;

class Manager {
    List list;

    public Manager() {
        this.list = new List();
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
                Saver.saveList(this.list);
                exit();
            } else if (input.startsWith("list")) {
                Messages.printListMessage(this.list);
            } else if (input.startsWith("mark")) {
                Messages.printMarkMessage(this.list.mark(input));
            } else if (input.startsWith("unmark")) {
                this.list.unmark(input);
                Messages.printUnmarkMessage(this.list.unmark(input));
            } else if (input.startsWith("todo")) {
                this.list.addTodo(input.substring("todo".length()));
                Messages.printAddMessage(this.list);
            } else if (input.startsWith("deadline")) {
                this.list.addDeadline(input.substring("deadline".length()));
                Messages.printAddMessage(this.list);
            } else if (input.startsWith("event")) {
                this.list.addEvent(input.substring("event".length()));
                Messages.printAddMessage(this.list);
            } else if (input.startsWith("delete")) {
                Messages.printDeleteMessage(this.list, this.list.delete(input));
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
}
