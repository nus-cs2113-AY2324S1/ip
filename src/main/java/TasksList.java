import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

	public static void setCommandNames(ArrayList<String> commandNames) {
		TasksList.commandNames = commandNames;
	}

	public static void setTasksToBeSaved(ArrayList<Task> tasksToBeSaved) {
		TasksList.tasksToBeSaved = tasksToBeSaved;
	}

	public static void setExitProgram(boolean exitProgram) {
		TasksList.exitProgram = exitProgram;
	}

	public static ArrayList<Task> getTasks() {
		return tasks;
	}

	public static ArrayList<String> getCommandNames() {
		return commandNames;
	}

	public static ArrayList<Task> getTasksToBeSaved() {
		return tasksToBeSaved;
	}

	public static boolean isExitProgram() {
		return exitProgram;
	}

	public static void showCommands(){
		System.out.println("Here is a list of commands you can use: ");
		for (String command: commandNames){
			System.out.println(command);
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
			try {
				String desc = input.substring(4).trim();
				Todo todo = new Todo(desc);


				tasks.add(todo);
				tasksToBeSaved.add(todo);

			} catch (Exception e) {
				System.out.println("An error occurred while adding the todo.");
			}
			break;
		case("find"):
			searchAndDisplayTasks(parts[1]);
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

	public static void searchAndDisplayTasks(String keyword) {
		System.out.println("____________________________________________________________");
		System.out.println("Here are the matching tasks in your list:");

		int count = 1;
		for (Task task : tasks) {
			if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
				System.out.println(count + "." + task.getDescription());
				count++;
			}
		}
	}
}
