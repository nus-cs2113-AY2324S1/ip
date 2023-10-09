import java.util.ArrayList;
import java.util.Scanner;

public class Elvin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Storage storage = new Storage();
        Ui ui = new Ui();
        Parser parser = new Parser();
        storage.readFromFile(tasks);
        ui.printGreet();
        while(true) {
            String input = scanner.nextLine();
            ArrayList<String> parsedInput;
            try {
                parsedInput = parser.parse(input);
            } catch(DukeException e) {
                continue;
            }
            if(parsedInput.get(0).equals("bye")) {
                break;
            } else if(parsedInput.get(0).equals("list")) {
                ui.printList(tasks);
            } else if(parsedInput.get(0).equals("mark")) {
                int index = Integer.parseInt(parsedInput.get(1));
                try {
                    tasks.markTask(index);
                    storage.writeToFile(tasks);
                    ui.printMark(tasks.get(index));
                } catch(IndexOutOfBoundsException e) {
                    ui.print("☹ OOPS!!! The index is out of bound.");
                }
            } else if(parsedInput.get(0).equals("unmark")) {
                int index = Integer.parseInt(parsedInput.get(1));
                try {
                    tasks.unmarkTask(index);
                    storage.writeToFile(tasks);
                    ui.printUnmark(tasks.get(index));
                } catch(IndexOutOfBoundsException e) {
                    ui.print("☹ OOPS!!! The index is out of bound.");
                }
            } else if(parsedInput.get(0).equals("todo")) {
                Todo todo;
                try {
                    todo = new Todo(parsedInput.get(1));
                } catch(DukeException e) {
                    continue;
                }
                tasks.addTask(todo);
                storage.writeToFile(tasks);
                ui.printAddition(todo, tasks.size());
            } else if(parsedInput.get(0).equals("deadline")) {
                Deadline deadline;
                try {
                    deadline = new Deadline(parsedInput.get(1), parsedInput.get(2));
                } catch (DukeException e) {
                    continue;
                }
                tasks.addTask(deadline);
                storage.writeToFile(tasks);
                ui.printAddition(deadline, tasks.size());
            } else if(parsedInput.get(0).equals("event")) {
                Event event;
                try {
                    event = new Event(parsedInput.get(1), parsedInput.get(2), parsedInput.get(3));
                } catch(DukeException e) {
                    continue;
                }
                tasks.addTask(event);
                storage.writeToFile(tasks);
                ui.printAddition(event, tasks.size());
            } else if(parsedInput.get(0).equals("delete")) {
                int index = Integer.parseInt(parsedInput.get(1));
                try {
                    ui.printDeletion(tasks.get(index), tasks.size()-1);
                    tasks.deleteTask(index);
                    storage.writeToFile(tasks);
                } catch(IndexOutOfBoundsException e) {
                    ui.print("☹ OOPS!!! The index is out of bound.");
                }
            } else if(parsedInput.get(0).equals("find")) {
                TaskList temp = tasks.find(parsedInput.get(1));
                ui.printFoundTasks(temp);
            }
        }
        scanner.close();
        ui.printExit();
    }
}
