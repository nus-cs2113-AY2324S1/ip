import java.io.IOException;
import java.util.ArrayList;

import storage.Storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import tasks.DukeException;
import tasks.TaskList;
import ui.Ui;
import tasks.Parser;


/**
 * Represents the main logic for the Duke application.
 * Manages the initialization, execution, and storage of tasks.
 */
public class Duke {
    private static final String lineDivider = "____________________________________________________________";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /*
     * Initializes a Duke object with the given file path.
     * 
     * @param filePath The file path to the file containing the tasks.
     * @throws DukeException If an error occurs while loading the tasks from the file.
     * 
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showErrorMsg(e.getMessage());
            tasks = new TaskList();
        }
    }

    /*
     * Runs the Duke application.
     * 
     * @throws DukeException If an error occurs while running the application.
     */
    public void run() {
        Ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String action = Parser.parse(fullCommand);
                String[] parts = action.split("-", 2);
                switch(parts[0]) {
                    case "exit":
                        isExit = true;
                        ui.showLineDivider();
                        break;
                    case "list":
                        tasks.listTasks();
                        ui.showLineDivider();
                        break;
                    case "markAsDone":
                        handleMarkAsDone(parts[1]);
                        break;
                    case "markAsNotDone":
                        handleMarkAsNotDone(parts[1]);
                        break;
                    case "delete":
                        handleDelete(parts[1]);
                        break;
                    case "addTodo":
                        handleAddTodo(parts[1]);
                        break;
                    case "addDeadline":
                        handleAddDeadline(parts[1]);
                        break;
                    case "addEvent":
                        handleAddEvent(parts[1]);
                        break;
                    case "find":
                        handleFind(parts[1]);
                        break;
                    default:
                        throw new DukeException("Unknown action");
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    /*
     * Handles the markAsDone command.
     * also checks if the task number is valid.
     * 
     * @param part The part of the command after the markAsDone keyword.
     * @throws DukeException If an error occurs while marking the task as done.
     */
    private void handleMarkAsDone(String part) {
        try {
            int doneIndex = Integer.parseInt(part) - 1;
            tasks.markTaskAsDone(doneIndex);
            ui.showUnmarkedTask(tasks.getTaskIndex(doneIndex));
            storage.save(tasks.getTasks());
        } catch (NumberFormatException e) {
            ui.showErrorMsg("Error: ☹ OOPS!!! Task number must be an integer.\n");
            ui.showErrorMsg("Please enter command in the format: mark <task number>");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file.");
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMsg("Error: ☹ OOPS!!! Invalid task number.");
        }
        ui.showLineDivider();
    }

    /*
     * Handles the markAsNotDone command.
     * also checks if the task number is valid.
     * 
     * @param part The part of the command after the markAsNotDone keyword.
     * @throws DukeException If an error occurs while marking the task as not done.
     * @throws IOException If an error occurs while saving the tasks to the file.
     * @throws IndexOutOfBoundsException If the task number is invalid.
     */
    private void handleMarkAsNotDone(String part) {
        try {
            int undoneIndex = Integer.parseInt(part) - 1;
            tasks.getTaskIndex(undoneIndex).markAsDone(false);
            ui.showUnmarkedTask(tasks.getTaskIndex(undoneIndex));
            storage.save(tasks.getTasks());
        } catch (NumberFormatException e) {
            ui.showErrorMsg("Error: ☹ OOPS!!! Task number must be an integer.\n");
            ui.showErrorMsg("Please enter command in the format: unmark <task number>");
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMsg("Error: ☹ OOPS!!! Invalid task number.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file.");
        }
        ui.showLineDivider();
    }

    /*
     * Handles the delete command.
     * also checks if the task number is valid.
     * 
     * @param part The part of the command after the delete keyword.
     * @throws DukeException If an error occurs while deleting the task.
     * @throws IOException If an error occurs while saving the tasks to the file.
     * @throws IndexOutOfBoundsException If the task number is invalid.
     * @throws NumberFormatException If the task number is not an integer.q
     */
    private void handleDelete(String part) {
        try {
            int deleteIndex = Integer.parseInt(part) - 1;
            if (deleteIndex < 0 || deleteIndex >= tasks.getTaskCount()) {
                System.out.println("☹ OOPS!!! Invalid task number.");
            }
            Task taskToDelete = tasks.getTaskIndex(deleteIndex);
            tasks.deleteTask(deleteIndex);
            storage.save(tasks.getTasks());
            ui.showDeletedTask(taskToDelete, tasks.getTaskCount());
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file.");
        } catch (NumberFormatException e) {
            ui.showErrorMsg("Error: ☹ OOPS!!! Task number must be an integer.\n");
            ui.showErrorMsg("Please enter command in the format: delete <task number>");
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMsg("Error: ☹ OOPS!!! Invalid task number.");
        }
        ui.showLineDivider();
    }

    /*
     * Handles the addTodo command.
     * checks if the description is empty.
     * 
     * @param part The part of the command after the addTodo keyword.
     * @throws DukeException If an error occurs while adding the todo.
     * @throws IOException If an error occurs while saving the tasks to the file. 
     */
    private void handleAddTodo(String part) {
        if (part.trim().isEmpty()) {
            ui.showErrorMsg("☹ OOPS!!! The description of a todo cannot be empty.\n" + lineDivider);
            return;
        }
        Task newTodo = new Todo(part);
        tasks.addTask(newTodo);
        ui.showAddedTask(newTodo, tasks.getTaskCount());
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file.");
        }
        ui.showLineDivider();
    }

    /*
     * Handles the addDeadline command.
     * checks if description or deadline is empty.
     * 
     * @param part The part of the command after the addDeadline keyword.
     * @throws DukeException If an error occurs while adding the deadline.
     * @throws IOException If an error occurs while saving the tasks to the file.
     */
    private void handleAddDeadline(String part) {
        String[] deadlineParts = part.split("/by", 2);
        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty() || deadlineParts[0].trim().isEmpty()) {
            ui.showErrorMsg("☹ OOPS!!! The description of a deadline cannot be empty. \n" + lineDivider);
            return;
        }
        Task newDeadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
        tasks.addTask(newDeadline);
        ui.showAddedTask(newDeadline, tasks.getTaskCount());
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file.");
        }
        ui.showLineDivider();
    }

    /*
     * Handles the addEvent command.
     * checks if description, start time or end time is empty.
     * 
     * @param part The part of the command after the addEvent keyword.
     * @throws DukeException If an error occurs while adding the event.
     */
    private void handleAddEvent(String part) {
        String[] eventParts = part.split("/from", 2);
        if (eventParts.length < 2 || eventParts[1].trim().isEmpty() || eventParts[0].trim().isEmpty()) {
            ui.showErrorMsg("☹ OOPS!!! The description of an event cannot be empty.\n" + lineDivider);
            return;
        }
        
        String[] fromToParts = eventParts[1].split("/to", 2);
        if (fromToParts.length < 2 || fromToParts[0].trim().isEmpty() || fromToParts[1].trim().isEmpty()) {
            ui.showErrorMsg("☹ OOPS!!! The start or end time for an event cannot be empty.\n" + lineDivider);
            return;
        }
        
        Task newEvent = new Event(eventParts[0].trim(), fromToParts[0].trim(), fromToParts[1].trim());
        tasks.addTask(newEvent);
        ui.showAddedTask(newEvent, tasks.getTaskCount());
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file.");
        }
        ui.showLineDivider();
    }

    /*
     * Handles the find command.
     * checks if the keyword is empty.
     * 
     * @param part The part of the command after the find keyword.
     * @throws DukeException If an error occurs while finding the tasks.
     */
    private void handleFind(String part) {
        String keyword = part.trim();
        if (keyword.isEmpty()) {
            ui.showErrorMsg("☹ OOPS!!! The keyword for find cannot be empty.\n" + lineDivider);
            return;
        }

        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        if (foundTasks.isEmpty()) {
            ui.showErrorMsg("☹ OOPS!!! No matching tasks found with the given keyword.\n" + lineDivider);
            return;
        }
        ui.showFoundTasks(foundTasks);
        ui.showLineDivider();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
