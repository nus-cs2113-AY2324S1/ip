import java.util.List;

// 1. Create a Command Interface
interface Command {
	void execute(String input, List<Task> tasks);
}

class ListCommand implements Command {
	public void execute(String input, List<Task> tasks) {
		int numOfTasks = 0;
		for (Task task : tasks) {
			numOfTasks++;
			System.out.println(numOfTasks + ": " + task.getDescription());
		}
	}
}

class HelpCommand implements Command {
	public void execute(String input, List<Task> tasks) {
		showCommands();
	}

	private void showCommands() {
		// Implement the details of showCommands here.
	}
}

class MarkCommand implements Command {
	public void execute(String input, List<Task> tasks) {
		String[] parts = input.split(" ");
		int markedObjectInt = Integer.parseInt(parts[1]);
		try {
			Task markedTask = tasks.get(markedObjectInt - 1);
			markedTask.isDone = true;
			System.out.println("Congrats! I marked this task as done: " + markedTask.getDescription());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("That task doesn't exist.");
		}
	}
}

class UnmarkCommand implements Command {
	public void execute(String input, List<Task> tasks) {
		String[] parts = input.split(" ");
		int markedObjectInt = Integer.parseInt(parts[1]);
		try {
			Task unmarkedTask = tasks.get(markedObjectInt - 1);
			unmarkedTask.isDone = false;
			System.out.println("I unmarked this task as done: " + unmarkedTask.getDescription());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("That task doesn't exist.");
		}
	}
}



class DeleteCommand implements Command {
	public void execute(String input, List<Task> tasks) {
		String[] parts = input.split(" ");
		int taskToDelete = Integer.parseInt(parts[1]);

		// Remove task from file (assuming a method exists for this)
		//removeTaskFromFile(taskToDelete - 1);

		try {
			Task deleteTask = tasks.get(taskToDelete - 1);
			tasks.remove(taskToDelete - 1);
			System.out.println("I deleted this task: " + deleteTask.getDescription());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("That task doesn't exist");
		}
	}
}

class DeadlineCommand implements Command {
	public void execute(String input, List<Task> tasks) {
		try {
			String[] toDoSplit = input.split("/");
			String desc = toDoSplit[0].substring(9).trim();
			Deadline deadline = new Deadline(desc, toDoSplit[1].trim());
			tasks.add(deadline);
			System.out.println(deadline.getDescription());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Put a / after your task if you want to add a todo");
		}
	}
}

class EventCommand implements Command {
	public void execute(String input, List<Task> tasks) {
		try {
			String[] toDoSplit = input.split("/");
			String desc = toDoSplit[0].substring(6).trim();
			Event event = new Event(desc, toDoSplit[1].trim(), toDoSplit[2].trim());
			tasks.add(event);
			System.out.println(event.getDescription());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Put a /time after description for start and a /time for end");
		} catch (NullPointerException e) {
			System.out.println("Please enter an input");
		}
	}
}

class TodoCommand implements Command {
	public void execute(String input, List<Task> tasks) {
		try {
			String desc = input.substring(4).trim();
			Todo todo = new Todo(desc);
			tasks.add(todo);
		} catch (Exception e) {
			System.out.println("An error occurred while adding the todo.");
		}
	}
}

class CommandFactory {
	public static Command getCommand(String keyword) {
		switch(keyword) {
		case "list": return new ListCommand();
		case "help": return new HelpCommand();
		case "mark": return new MarkCommand();
		case "unmark": return new UnmarkCommand();
		case "delete": return new DeleteCommand();
		case "deadline": return new DeadlineCommand();
		case "event": return new EventCommand();
		case "todo": return new TodoCommand();
		//... other cases ...
		default: return null;
		}
	}
}
