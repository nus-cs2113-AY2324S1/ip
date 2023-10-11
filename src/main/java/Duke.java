import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
//Refactoring methods is helpful for transforming
public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>(100);
    static ArrayList<String> commandNames = new ArrayList<>(Arrays.asList("list", "mark", "unmark","delete","deadline","event","todo"));


    public static void removeTaskFromFile(int index) {
        try {
            FileSave.removeEntryAtIndex(index);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void showCommands(){
        System.out.println("Here is a list of commands you can use: ");
        for (String command: commandNames){
            System.out.println(command);
        }
    }

    public static void checkInput(String input) {
        //Function to check if the input is a string
    }

    private static void showGoodbye() {
        System.out.println("Fine. If you have no ideas imma head out");
    }

    private static void showWelcome() {
        System.out.println("Hello my name is DUKE");
        System.out.println("What can I do for you today? Enter 'help' for a list of commands");
    }


    private static void saveFile(){
        try {
            FileSave.printFileContents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int numOfTasks = 0;
        for (Task task : tasks) {
            try {
                FileSave.writeToFile(task.getFileReadableString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void chooseCommand(String input) {

        //checkInput(input);
        String[] getKeyword = input.split(" ");

        String keyword = getKeyword[0];
        keyword = keyword.toLowerCase();

        String[] parts = input.split(" ");



        switch (keyword) {
        case ("list"):
            int numOfTasks = 0;

            for (Task task : tasks) {
                numOfTasks++;
                System.out.println(numOfTasks + ": " + task.getDescription());
            }
            break;
        case("help"):
            showCommands();
            break;
        case ("mark"):
            int markedObjectInt = Integer.parseInt(parts[1]);
            try {

                Task markedTask = tasks.get(markedObjectInt - 1);

                markedTask.isDone = true;
                System.out.println("Congrats I marked this class as done : " + markedTask.getDescription());
            } catch (Exception ArrayIndexOutOfBoundsException) {
                System.out.println("That task doesnt exist");
            }
            break;
        case ("unmark"):
			markedObjectInt = Integer.parseInt(parts[1]);
            try {
                Task unmarkedTask = tasks.get(markedObjectInt - 1);
                unmarkedTask.isDone = false;
                System.out.println("I unmarked this class as done: " + unmarkedTask.getDescription());
            } catch (Exception ArrayIndexOutOfBoundsException) {
                System.out.println("That task doesnt exist");
            }
            break;
        case("delete"):
            int taskToDelete = Integer.parseInt(parts[1]);
            tasks.get(taskToDelete - 1).isDone = false;

            removeTaskFromFile(taskToDelete - 1);
            try {
                Task deleteTask = tasks.get(taskToDelete - 1);
                tasks.remove(taskToDelete - 1);

                System.out.println("I deleted this task this class as done: " + deleteTask.getDescription());
            } catch (Exception ArrayIndexOutOfBoundsException) {
                System.out.println("That task doesnt exist");
            }
            break;
        case ("deadline"):
            try {
                String[] toDoSplit = input.split("/");
                //First part is task, and last is when by
                String desc = toDoSplit[0].substring(9).trim();  // removes "deadline
                Deadline deadline = new Deadline(desc, toDoSplit[1].trim());
                System.out.println(deadline.getDescription());

                tasks.add(deadline);

            } catch (Exception ArrayIndexOutOfBoundsException){
                System.out.println("Put a / after your task if you want to add a todo");
            }
            break;
        case ("event"):
            try {
                String[] toDoSplit = input.split("/");
                //First part is task, and last is when by
                String desc = toDoSplit[0].substring(6).trim();
                Event event = new Event(desc, toDoSplit[1].trim(), toDoSplit[2].trim());
                System.out.println(event.getDescription());
                tasks.add(event);

            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Put a /time after description for start and a /time for end");
            } catch (NullPointerException e){
                System.out.println("Please enter a input");
            }
            break;
        case("todo"):
            try {
                String desc = input.substring(4).trim();
                Todo todo = new Todo(desc);


                tasks.add(todo);

            } catch (Exception e) {
                System.out.println("An error occurred while adding the todo.");
            }
            break;
        default:
            // Add the task to the list
            Task newTask = new Task(input);
            tasks.add(newTask);
            System.out.println("Added: " + input);
            break;
        }


    }

    public static void main(String[] args) throws IOException {
        showWelcome();

        FileSave.loadFileObjects();
        ArrayList<Task> fileTasks = FileSave.getFileTasksArray();
        tasks.addAll(fileTasks);

        Scanner scan = new Scanner(System.in);
        String input;
        input = scan.nextLine();
        while (!input.equalsIgnoreCase("bye")){
            chooseCommand(input);
            input = scan.nextLine();
        }
        saveFile();
        showGoodbye();
    }
}

