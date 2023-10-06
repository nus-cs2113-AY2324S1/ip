package main.java.duke.util;

import main.java.duke.util.task.Deadline;
import main.java.duke.util.task.Event;
import main.java.duke.util.task.Task;
import main.java.duke.util.task.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
    public static void parse(TaskList taskList, UIHandler uiHandler) {
        // Scanner object for getting user input from the terminal
        Scanner scanner = new Scanner(System.in);

        // string to monitor current user input
        String userInput = scanner.nextLine();
        String[] userWords = userInput.split(" ");
        String userCommand = userWords[0];

        // if 'bye' command is given exit program, else keep prompting for user input
        while (!userCommand.equals("bye")) {
            // if 'list' command is given, list out all tasks
            if (userCommand.equals("list")) {
                // if list is empty, print 'no item' message instead of tasks
                if (taskList.isEmpty()) {
                    try {
                        throw new DukeException("Empty List");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    uiHandler.printTasks(taskList);
                }
            }
            // if "mark" command is given, mark the corresponding task in tasks
            else if (userCommand.equals("mark")) {
                try {
                    // split userInput into command and integer
                    int selectedItem = Integer.parseInt(userWords[1]);
                    taskList.markTask(selectedItem);
                    uiHandler.printMarkedMessage(selectedItem, taskList);
                } catch (Exception e) {
                    uiHandler.integerErrorMessage();
                }
            }
            // if "unmark" command is given, unmark the corresponding task in tasks
            else if (userCommand.equals("unmark")) {
                try {
                    // split userInput into command and integer
                    int selectedItem = Integer.parseInt(userWords[1]);
                    taskList.unmarkTask(selectedItem);
                    uiHandler.printUnmarkedMessage(selectedItem, taskList);
                } catch (Exception e) {
                    uiHandler.integerErrorMessage();
                }
            }
            // if "todo" command is given
            else if (userCommand.equals("todo")) {
                try {
                    // store information of todo: Name
                    if (userWords.length == 1) {
                        throw new DukeException("Todo Name Missing");
                    }
                    // store information of todo: Name
                    String[] todoNameWords = Arrays.copyOfRange(userWords, 1, userWords.length);
                    String todoName = String.join(" ", todoNameWords);
                    Todo todo = taskList.addTodo(todoName);
                    uiHandler.printTaskAddedMessage(todo, taskList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            // if "event" command is given
            else if (userCommand.equals("event")) {
                // find the index of "/from" and "/to" to separate the information
                int fromSplitIndex = 0;
                int toSplitIndex = 0;
                for (int i = 1; i < userWords.length; i++) {
                    if (userWords[i].equals("/from")) {
                        fromSplitIndex = i;
                    } else if (userWords[i].equals("/to")) {
                        toSplitIndex = i;
                        break;
                    }
                }
                try {
                    if (fromSplitIndex == 1) {
                        throw new DukeException("Event Name Missing");
                    }
                    if (fromSplitIndex == toSplitIndex - 1) {
                        throw new DukeException("Event From Missing");
                    }
                    if (toSplitIndex == userWords.length - 1) {
                        throw new DukeException("Event To Missing");
                    }                // store information of event: Name, From and To
                    String[] eventNameWords = Arrays.copyOfRange(userWords, 1, fromSplitIndex);
                    String[] eventFromWords = Arrays.copyOfRange(userWords, fromSplitIndex + 1, toSplitIndex);
                    String[] eventToWords = Arrays.copyOfRange(userWords, toSplitIndex + 1, userWords.length);
                    String eventName = String.join(" ", eventNameWords);
                    String eventFrom = String.join(" ", eventFromWords);
                    String eventTo = String.join(" ", eventToWords);
                    Event event = taskList.addEvent(eventName, eventFrom, eventTo);
                    uiHandler.printTaskAddedMessage(event, taskList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            // if "deadline" command is given
            else if (userCommand.equals("deadline")) {
                // find the index of "/by" to separate the information
                int splitIndex = 0;
                for (int i = 1; i < userWords.length; i++) {
                    if (userWords[i].equals("/by")) {
                        splitIndex = i;
                        break;
                    }
                }
                try {
                    if (splitIndex == 1) {
                        throw new DukeException("Deadline Name Missing");
                    }
                    if (splitIndex == userWords.length - 1) {
                        throw new DukeException("Deadline By Missing");
                    }
                    // store information of deadline: Name and By
                    String[] deadlineNameWords = Arrays.copyOfRange(userWords, 1, splitIndex);
                    String[] deadlineByWords = Arrays.copyOfRange(userWords, splitIndex + 1, userWords.length);
                    String deadlineName = String.join(" ", deadlineNameWords);
                    String deadlineBy = String.join(" ", deadlineByWords);
                    Deadline deadline = taskList.addDeadline(deadlineName, deadlineBy);
                    uiHandler.printTaskAddedMessage(deadline, taskList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            // if "delete" command is given, delete the corresponding task in the list
            else if (userCommand.equals("delete")) {
                try {
                    // split userInput into command and integer
                    int selectedItem = Integer.parseInt(userWords[1]);
                    Task removedTask = taskList.removeTask(selectedItem);
                    uiHandler.printTaskDeleted(selectedItem, removedTask);
                } catch (Exception e) {
                    uiHandler.integerErrorMessage();
                }
            }
            // if "find" command is given, find the corresponding task(s) in the list with the same name
            else if (userCommand.equals("find")) {
                try {
                    String[] taskNameWords = Arrays.copyOfRange(userWords, 1, userWords.length);
                    String taskName = String.join(" ", taskNameWords);
                    ArrayList<Integer> foundTasksIndex = taskList.find(taskName);
                    uiHandler.printFoundTasks(foundTasksIndex, taskList);
                } catch (Exception e) {
                    uiHandler.integerErrorMessage();
                }
            }
            // if not unique command, prompt user again
            else {
                uiHandler.printInvalidCommandMessage();
            }

            uiHandler.continueMessage();
            userInput = scanner.nextLine();
            userWords = userInput.split(" ");
            userCommand = userWords[0];
        }
    }
}
