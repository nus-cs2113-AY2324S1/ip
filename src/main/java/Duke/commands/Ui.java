package duke.commands;

import duke.exceptions.*;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents how the programme interacts with the user
 * and how programme reacts to user inputs.
 */
public class Ui  {
    public Ui() {

    }
     public static TaskList newList;

    static {
        try {
            newList = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * User interface that takes in commands to enable programme to run specific methods.
     *
     *
     * @throws DukeException If todo description is empty.
     * @throws IOException If input is unaccepted.
     */
    public void UiBegin() throws DukeException, IOException {
        Scanner in = new Scanner(System.in);
        String text = in.next();
        if (text.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        while (text.equals("bye") == false) {

            if (text.equals(newList.checker)) {
                newList.listTask();
                text = in.next();

            } else if (text.startsWith(newList.marker)) {
                try {
                    String part = in.nextLine();
                    newList.mark(text + part);
                } catch(NumberFormatException e){
                    System.out.println("Which task? Retype command as mark <task number>");
                } text = in.next();

            } else if (text.startsWith(newList.unmarked)) {
                try {
                    String part = in.nextLine();
                    newList.unmark(text + part);
                }  catch(NumberFormatException e){
                    System.out.println("Which task? Retype command as unmark <task number>");

                } text = in.next();

            } else if (text.equals("todo")) {
                String task = in.nextLine();
                if (task.length() == 0) {
                    throw new DukeException();
                }
                newList.addToDo(task);

                text = in.next();

            } else if (text.equals("deadline")) {
                String[] task = in.nextLine().split(" /by");
                newList.addDeadline(task[0], task[1]);

                text = in.next();

            } else if (text.equals("event")) {
                String[] task = in.nextLine().split(" /from | /to");
                newList.addEvent(task[0], task[1], task[2]);

                text = in.next();

            } else if(text.startsWith(newList.delete)){
                String part = in.nextLine();
                newList.removeTask(text + part);
                text = in.next();

            }else{
                text= in.nextLine();
                System.out.println("OOPS! I dont know what that means");
                text = in.next();
            }

            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            }
        }
    }
}
