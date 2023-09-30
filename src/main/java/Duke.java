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

public class Duke {
    private static final String lineDivder = "____________________________________________________________";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public void run(){
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String action = Parser.parse(fullCommand);
                String [] parts = action.split("-", 2);
                switch(parts[0]) {
                    case "exit" :
                        isExit = true;
                        ui.showLineDivider();
                        break;
                    
                    case "list" :
                        tasks.listTasks();
                        ui.showLineDivider();
                        break;
                    
                    case "markAsDone" :
                        try{
                            int doneIndex = Integer.parseInt(parts[1]) - 1;
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
                        break;
                    
                    case "unmark" :
                        try{
                            int undoneIndex = Integer.parseInt(parts[1]) -1;
                            tasks.markTaskAsUndone(undoneIndex);
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
                        break;

                    case "delete":
                        int deleteIndex = Integer.parseInt(parts[1]) - 1;
                        if (deleteIndex < 0 || deleteIndex >= tasks.getTaskCount()) {
                            throw new DukeException("☹ OOPS!!! Invalid task number.");
                        }
                        Task taskToDelete = tasks.getTaskIndex(deleteIndex);
                        tasks.deleteTask(deleteIndex);
                        try{
                            storage.save(tasks.getTasks());
                        } catch (IOException e) {
                            System.out.println("An error occurred while saving tasks to the file.");
                        }
                        ui.showDeletedTask(taskToDelete, tasks.getTaskCount());
                        ui.showLineDivider();
                        break;

                    case "addTodo":
                        if (parts[1].trim().isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n" + lineDivder);
                        }
                        Task newTodo = new Todo(parts[1]);
                        tasks.addTask(newTodo);
                        ui.showAddedTask(newTodo, tasks.getTaskCount());
                        try{
                            storage.save(tasks.getTasks());
                        } catch (IOException e) {
                            System.out.println("An error occurred while saving tasks to the file.");
                        }
                        ui.showLineDivider();
                        break;

                    case "addDeadline":
                        String[] deadlineParts = parts[1].split("/by", 2);
                        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty() || deadlineParts[0].trim().isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty. \n" + lineDivder);
                        }
                        Task newDeadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                        tasks.addTask(newDeadline);
                        ui.showAddedTask(newDeadline, tasks.getTaskCount());
                        try{
                            storage.save(tasks.getTasks());
                        } catch (IOException e) {
                            System.out.println("An error occurred while saving tasks to the file.");
                        }
                        ui.showLineDivider();
                        break;

                        case "addEvent":
                        String[] eventParts = parts[1].split("/from", 2);
                        if (eventParts.length < 2 || eventParts[1].trim().isEmpty() || eventParts[0].trim().isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        
                        String[] fromToParts = eventParts[1].split("/to", 2);
                        if (fromToParts.length < 2 || fromToParts[0].trim().isEmpty() || fromToParts[1].trim().isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The start or end time for an event cannot be empty.\n" + lineDivder);
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
                        break;

                    case "find":
                        String keyword = parts[1].trim();
                        if (keyword.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The keyword for find cannot be empty.\n" + lineDivder);
                        }

                        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
                        if (foundTasks.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! No matching tasks found with the given keyword.\n" + lineDivder);
                        }
                        ui.showFoundTasks(tasks.findTasks(keyword));
                        ui.showLineDivider();
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
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}