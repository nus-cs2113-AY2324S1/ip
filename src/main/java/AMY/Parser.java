package AMY;

import AMY.Exceptions.EmptyDeadlineException;
import AMY.Exceptions.EmptyDeleteException;
import AMY.Exceptions.EmptyEventException;
import AMY.Exceptions.EmptyInput;
import AMY.Exceptions.EmptyMarkException;
import AMY.Exceptions.EmptyToDoException;
import AMY.Exceptions.EmptyUnmarkException;
import AMY.Exceptions.EmptyFindException;

import AMY.command.Deadline;
import AMY.command.Event;
import AMY.command.Todo;

import java.util.Scanner;

public class Parser {

    public static final String LINE = "____________________________________________________________";

    public static void drawLine() {
        System.out.println(LINE);
    }

    /**
     * Throw exceptions when exceptions are encountered to avoid an error
     * @param userInput the user input
     * @throws EmptyInput when the input is empty, the exception is thrown
     * @throws EmptyToDoException when the todo command is empty, the exception is thrown
     * @throws EmptyMarkException when the mark is command empty, the exception is thrown
     * @throws EmptyUnmarkException when the unmark command is empty, the exception is thrown
     * @throws EmptyDeadlineException when the deadline command is empty, the exception is thrown
     * @throws EmptyEventException when the event command is empty, the exception is thrown
     * @throws EmptyDeleteException when the delete command is empty, the exception is thrown
     */
    public static void manageException(String userInput) throws EmptyInput, EmptyToDoException,
            EmptyMarkException, EmptyUnmarkException, EmptyDeadlineException, EmptyEventException,
            EmptyDeleteException, EmptyFindException {

        Scanner input = new Scanner(userInput);
        String command;
        if (!input.hasNext()) {
            throw new EmptyInput();
        } else {
            command = input.next();
        }
        if (command.equals("todo") && !input.hasNext()) {
            throw new EmptyToDoException();
        }
        if (command.equals("mark") && !input.hasNext()) {
            throw new EmptyMarkException();
        }
        if (command.equals("unmark") && !input.hasNext()) {
            throw new EmptyUnmarkException();
        }
        if (command.equals("deadline") && !input.hasNext()) {
            throw new EmptyDeadlineException();
        }
        if (command.equals("event") && !input.hasNext()) {
            throw new EmptyEventException();
        }
        if (command.equals("delete") && !input.hasNext()) {
            throw new EmptyDeleteException();
        }
        if (command.equals("find") && !input.hasNext()) {
            throw new EmptyFindException();
        }
    }

    /**
     * Manage input when input is given to follow order
     */
    public static void manageInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine(); // Read user input
            System.out.println(LINE);
            try {
                manageException(userInput);
                if (userInput.equals("bye")) {
                    break; // Exit the loop if the user enters "bye"
                } else if (userInput.equals("list")) {
                    TaskList.listTasks(); // list tasks
                } else if (userInput.startsWith("mark")) {
                    // Extract the task index from the user input
                    int taskIndex = Integer.parseInt(userInput.substring(5).trim());
                    TaskList.markTaskAsDone(taskIndex);
                } else if (userInput.startsWith("unmark")) {
                    // Extract the task index from the user input
                    int taskIndex = Integer.parseInt(userInput.substring(7).trim());
                    TaskList.unmarkTask(taskIndex);
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(5).trim();
                    Todo todo = new Todo(description);
                    TaskList.addToList(todo);
                } else if (userInput.startsWith("deadline")) {
                    int inputIndex = userInput.indexOf(" /by ");
                    String description = userInput.substring("deadline ".length(), inputIndex);
                    String by = userInput.substring(inputIndex + " /by ".length());
                    TaskList.addToList(new Deadline(description, by));
                } else if (userInput.startsWith("event")) {
                    String[] parts = userInput.substring(5).split("/from");
                    String description = parts[0].trim();
                    String[] dateTimeParts = parts[1].split("/to");
                    String from = dateTimeParts[0].trim();
                    String to = dateTimeParts[1].trim();
                    Event event = new Event(description, from, to);
                    TaskList.addToList(event);
                } else if (userInput.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(userInput.substring(7).trim());
                    TaskList.deleteTask(taskIndex);
                } else if (userInput.startsWith("find")) {
                    String keyword = userInput.substring(5).trim();
                    TaskList.findTasks(keyword);
                }else {
                    System.out.println("Invalid command. Please try again.");
                }
            } catch (EmptyInput exception) {
                System.out.println("☹ OOPS!!! The description cannot be empty.");
            } catch (EmptyToDoException exception) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            } catch (EmptyMarkException exception) {
                System.out.println("☹ OOPS!!! The description of a mark cannot be empty.");
            } catch (EmptyUnmarkException exception) {
                System.out.println("☹ OOPS!!! The description of an unmark cannot be empty.");
            } catch (EmptyDeadlineException exception) {
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            } catch (EmptyEventException exception) {
                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            } catch (EmptyDeleteException exception) {
                System.out.println("☹ OOPS!!! The description of an delete cannot be empty.");
            } catch (EmptyFindException exception){
                System.out.println("☹ OOPS!!! The description of an find cannot be empty.");
            }
            drawLine();
        }
    }
}
