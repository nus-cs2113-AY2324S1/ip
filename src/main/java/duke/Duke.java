package duke;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDoList;
import duke.task.ToDos;

import java.util.Scanner;

public class Duke {
    private static ToDoList toDoList = new ToDoList();

    public static void main(String[] args) {
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

        Scanner scanner = new Scanner(System.in);
        toDoList.initialiseToDoList();

        while (true) { //continuous loop unless "bye" is input
            String input = scanner.nextLine();
            String[] command = input.split(" ");

            if (command[0].equals("bye")) { //exit when "bye"
                System.out.println("Bye. Hope to see you again soon!\n");
                Utils.printDivider();
                break;
            }

            try{ //run main logic unless there is exceptions and print error message based on exceptions
                mainLogic(command, input);

            } catch (NoTaskNameException e) {
                Utils.handleNoTaskNameException();
            } catch (StringIndexOutOfBoundsException e) {
                Utils.handleStringIndexOutOfBoundsException(e);
            } catch (ArrayIndexOutOfBoundsException e) {
                Utils.handleArrayIndexOutOfBoundsException();
            }
        }

    }

    private static void mainLogic(String[] command, String input) throws StringIndexOutOfBoundsException, NoTaskNameException, ArrayIndexOutOfBoundsException {
        if (command[0].equals("list")) {
            toDoList.printList();
        } else if (command[0].equals("mark")) {
            toDoList.mark(Integer.parseInt(command[1]));
        } else if (command[0].equals("unmark")) {
            toDoList.unmark(Integer.parseInt(command[1]));
        } else if (command[0].equals("todo")) {
            try {
                ToDos toDo = new ToDos(input.substring(5), false); //read whatever is behind "todo "
                toDoList.addToList("T", toDo);
            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("todo");
            }

        } else if (command[0].equals("deadline")) {
            try {
                String[] parts = input.split("/by"); // store the input into different array elements split by "/by"
                String deadlineName = parts[0].substring(9).trim(); //store name by reading what is behind "deadline "
                String due = parts[1].trim();
                Deadlines deadline = new Deadlines(deadlineName, due, false);
                toDoList.addToList("D", deadline);
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
                toDoList.addToList("E", event);
            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("event");
            }

        } else if (command[0].equals("delete")) {
            try {
                toDoList.deleteTask(Integer.parseInt(command[1]));
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


