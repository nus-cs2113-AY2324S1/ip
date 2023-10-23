import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains a list of all commands to instantiate different tasks.
 * UI directly uses this to input tasks.
 * @author Isaiah Cerven
 * @version 1.0
 */
public class TasksList {

	static ArrayList<Task> tasks = new ArrayList<>(100);


	static ArrayList<String> commandNames = new ArrayList<>(Arrays.asList("list", "mark", "unmark","delete","deadline","event","todo", "find"));

	static ArrayList<Task> tasksToBeSaved = new ArrayList<>(100);

	static boolean exitProgram = false;


	public static void removeTaskFromFile(int index) {
		try {
			Storage.removeEntryAtIndex(index);
		} catch (IOException e) {
			System.out.println(e);
		}
	}


	public static ArrayList<Task> getTasks() {
		return tasks;
	}

	//Sets a variable to false if program can be exited
	public static boolean isExitProgram() {
		return exitProgram;
	}

	//Prints all commands
	public static void showCommands(){
		System.out.print("Here is a list of commands you can use: ");
		for (String command: commandNames){
			System.out.println(command);
		}
	}

	/**
	 * Handles parsing UI commands and creating tasks from them
	 * @param input
	 */
	public static void chooseCommand(String input) {
		//checkInput(input);
		String[] getKeyword = input.split(" ");

		String keyword = getKeyword[0];
		keyword = keyword.toLowerCase();

		String[] parts = input.split(" ");



		switch (keyword) {
		case ("list"):
			int numOfTasks = 0;


			if (!tasks.isEmpty()){
				for (Task task : tasks) {
					numOfTasks++;
					System.out.println(numOfTasks + ": " + task.getDescription());
				}
			} else if (parts.length > 1) {
				System.out.println("No other command is needed besides 'list'");
			} else {
				System.out.println("You currently have no tasks.");
			}

			break;
		case("help"):
			System.out.println("Disclaimer: No command entered will result in the input to be ");
			System.out.println("entered as a task without a deadline. (Clone Assignment) ");
			showCommands();
			break;
		case ("mark"):

			try {
				int markedObjectInt = Integer.parseInt(parts[1]);
				Task markedTask = tasks.get(markedObjectInt - 1);

				markedTask.isDone = true;
				System.out.println("Congrats I marked this class as done : " + markedTask.getDescription());
			} catch (Exception ArrayIndexOutOfBoundsException) {
				System.out.println("That task doesnt exist");
			}
			break;
		case ("unmark"):
			try {
				int markedObjectInt = Integer.parseInt(parts[1]);
				Task unmarkedTask = tasks.get(markedObjectInt - 1);
				unmarkedTask.isDone = false;
				System.out.println("I unmarked this class as done: " + unmarkedTask.getDescription());
			} catch (Exception ArrayIndexOutOfBoundsException) {
				System.out.println("That task doesnt exist");
			}
			break;
		case("delete"):
			if (ensureHasParameters(parts)) {
				try {
					int taskToDelete = Integer.parseInt(parts[1]);
					tasks.get(taskToDelete - 1).isDone = false;

					removeTaskFromFile(taskToDelete - 1);

					Task deleteTask = tasks.get(taskToDelete - 1);
					tasks.remove(taskToDelete - 1);

					System.out.println("I deleted this task this class as done: " + deleteTask.getDescription());
				} catch (Exception ArrayIndexOutOfBoundsException) {
					System.out.println("That task doesnt exist");
				}
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
				tasksToBeSaved.add(deadline);
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
				tasksToBeSaved.add(event);

			} catch (ArrayIndexOutOfBoundsException e){
				System.out.println("Put a /time after description for start and a /time for end");
			} catch (NullPointerException e){
				System.out.println("Please enter a input");
			}
			break;
		case("todo"):
			if (ensureHasParameters(parts)){
				try {
					String desc = input.substring(4).trim();
					Todo todo = new Todo(desc);

					System.out.println(todo.getDescription());
					tasks.add(todo);
					tasksToBeSaved.add(todo);

				} catch (Exception e) {
					System.out.println("An error occurred while adding the todo.");
				}
			}

			break;
		case("find"):
			if (ensureHasParameters(parts)){
				searchAndDisplayTasks(parts[1]);
			}
			break;

		case("bye"):
			exitProgram = true;
			break;
		default:
			// Add the task to the list
			Task newTask = new Task(input);
			tasks.add(newTask);
			tasksToBeSaved.add(newTask);
			System.out.println("Added: " + input);
			break;
		}

	}

	/**
	 * Use for find case. Finds specific tasks with keyword
	 * @param keyword
	 */
	public static void searchAndDisplayTasks(String keyword) {
		System.out.println("Here are the matching tasks in your list:");

		int count = 1;
		for (Task task : tasks) {
			if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
				System.out.println(count + "." + task.getDescription());
				count++;
			}
		}
	}

	/**
	 * Make sure commands that have more than 1 parameter is handled
	 * @param parts
	 * @return
	 */
	static private boolean ensureHasParameters(String[] parts){
		if (!(parts.length >= 2)){
			System.out.println("Please entered the required parameters for the command");
			return false;
		}
		return true;
	}

}
