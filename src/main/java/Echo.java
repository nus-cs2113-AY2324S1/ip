import java.util.Scanner;

public class Echo  {
    public String text;
    public Echo() {

    }
    ToDoList newList = new ToDoList();

    public void echoBegin() throws DukeException{
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
                String[] task = in.nextLine().split("/by");
                newList.addDeadline(task[0], task[1]);
                text = in.next();

            } else if (text.equals("event")) {
                String[] task = in.nextLine().split("/from | /to");
                newList.addEvent(task[0], task[1], task[2]);
                text = in.next();
            } else {
               System.out.println("OOPS! I'm sorry i don't know what that means.");
               text = in.next();

            } if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            }
        }
    }
}
