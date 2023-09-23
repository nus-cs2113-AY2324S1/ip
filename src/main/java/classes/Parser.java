package classes;
import java.util.Arrays;

import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import command.Command;
import command.ExitCommand;

public class Parser {
	
	public Command parse(String userCommand) {
		String[] command = userCommand.split(" ");
		switch (command[0]) {
		case "bye":
			return new ExitCommand();
		default:
			return new UnknownCommand();
		}
		
	}
}
