package duke;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.TaskList;
import duke.task.ToDos;

public class Ui extends Utils{
    public static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String prompt = "___________________________________\n"
                +  "Hello! I'm NOXIN \n"
                + "What can I do for you?\n"
                + "___________________________________\n";


        System.out.println(prompt);

    }

    public static void outro() {
        System.out.println("Bye. Hope to see you again soon!\n");
        printDivider();
    }

    static void mainLogic(TaskList taskList, String[] command, String input) throws StringIndexOutOfBoundsException, NoTaskNameException, ArrayIndexOutOfBoundsException {
        if (command[0].equals("list")) {
            taskList.printList();
        } else if (command[0].equals("mark")) {
            taskList.mark(Integer.parseInt(command[1]));
        } else if (command[0].equals("unmark")) {
            taskList.unmark(Integer.parseInt(command[1]));
        } else if (command[0].equals("todo")) {
            try {
                ToDos toDo = new ToDos(input.substring(5), false); //read whatever is behind "todo "
                taskList.addToList("T", toDo);
            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("todo");
            }

        } else if (command[0].equals("deadline")) {
            try {
                String[] parts = input.split("/by"); // store the input into different array elements split by "/by"
                String deadlineName = parts[0].substring(9).trim(); //store name by reading what is behind "deadline "
                String due = parts[1].trim();
                Deadlines deadline = new Deadlines(deadlineName, due, false);
                taskList.addToList("D", deadline);
            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("deadline");
            }


        } else if (command[0].equals("event")) {
            try {
                String[] parts = input.split(("/from")); //store input into different elements by split "/from"
                String eventName = parts[0].substring(6).trim(); //store event name by reading what is behind "event "
                String[] timeFromTimeTo = parts[1].split("/to"); // split the time into time from and time to
                String timeFrom = timeFromTimeTo[0].trim();
                String timeTo = timeFromTimeTo[1].trim();
                Events event = new Events(eventName, timeFrom, timeTo, false);
                taskList.addToList("E", event);
            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("event");
            }

        } else if (command[0].equals("delete")) {
            try {
                taskList.deleteTask(Integer.parseInt(command[1]));
            } catch (IndexOutOfBoundsException e) {
                Utils.printDivider();
                System.out.println("There is no such task number!");
                Utils.printDivider();
            }

        } else {
            throw new NoTaskNameException();
        }
    }
}
