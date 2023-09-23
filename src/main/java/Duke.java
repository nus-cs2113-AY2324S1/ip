import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Duke {
	static TaskList toDoList;
	static Ui ui;
	static Storage storage;
	
    public static void main(String[] args) {
    	try {
    		ui = new Ui();
    		toDoList = new TaskList();
    		storage = new Storage("data", "./data/toDoList.txt");
        	storage.checkForTextFile(toDoList);
    		ui.greetUser();
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            while (!command.equals("bye")) {
            	String strippedCommand = command.strip();
        		String[] userCmd = strippedCommand.split(" ");
            	if (command.equals("list")) {
            		ui.printToDoList(toDoList);
            	} else if (userCmd[0].contains("mark")) {
            		try {
            			System.out.println("    ____________________________________________________________");
            			int index = Integer.parseInt(userCmd[1]) - 1;
            			if (userCmd[0].equals("mark")) {
                			toDoList.setTaskStatus(index, true);
                			System.out.println("     Nice! I've marked this task as done:");
                			storage.markTaskFromFile(index, true);
                		} else {
                			toDoList.setTaskStatus(index, false);
                			System.out.println("     OK, I've marked this task as not done yet:");
                			storage.markTaskFromFile(index, false);
                		}
            			System.out.println("       " + toDoList.getTask(index));
            			System.out.println("    ____________________________________________________________");
            		}
            		catch (NumberFormatException e) {
            			printLines();
                		System.out.println("     Please specify the number of the task that you want to mark/unmark");
                		printLines();
                	}
                	catch (NullPointerException e) {
                		printLines();
                		System.out.println("     Cannot mark/unmark a non-existent task");
                		printLines();
                	}
            		catch (ArrayIndexOutOfBoundsException e) {
            			printLines();
                		System.out.println("     Please specify a number of the task that you want to mark/unmark");
                		printLines();
            		}
        		} else if (userCmd[0].equals("todo")) {
        			try {
        				if (userCmd.length == 1) {
        					throw new RemyException("Error: Your todo task description cannot be empty!!!");
        				}
            			String description = String.join(" ", Arrays.copyOfRange(userCmd, 1, userCmd.length));
            			Task toDoTask = new ToDo(description);
            			toDoList.addTask(toDoTask);
            			ui.printTaskAdded(toDoTask, toDoList);
            			ToDo toDoTemp = (ToDo) toDoTask;
            			storage.writeToDoTaskToFile(toDoTemp);
        			}
        			catch (RemyException e) {
        				printLines();
        				System.out.println("     " + e.getMessage());
        				printLines();
        			}
        		} else if (userCmd[0].equals("deadline")) {
        			try {
        				String[] descAndDue = getDeadlineDescription(userCmd);
        				if (descAndDue[0] == "") {
        					throw new RemyException("Error: Please specify a description for this deadline");
        				}
            			if (descAndDue[1] == "") {
            				throw new RemyException("Error: Please specify a due date for this task with '/by'");
            			}
            			Task deadlineTask = new Deadline(descAndDue[0], descAndDue[1]);
            			toDoList.addTask(deadlineTask);
            			ui.printTaskAdded(deadlineTask, toDoList);
            			Deadline deadlineTemp = (Deadline) deadlineTask;
            			storage.writeDeadlineTaskToFile(deadlineTemp);
        			}
        			catch (RemyException e) {
        				printLines();
        				System.out.println("     " + e.getMessage());
        				printLines();
        			}
        		} else if (userCmd[0].equals("event")) {
        			try {
        				String[] info = getEventDescription(userCmd);
        				if (info[0] == "" | info[1] == "" | info[2] == "") {
        					throw new RemyException("Error: Please input your event in the right format");
        				}
            			Task eventTask = new Event(info[0], info[1], info[2]);
            			toDoList.addTask(eventTask);
            			ui.printTaskAdded(eventTask, toDoList);
            			Event eventTemp = (Event) eventTask;
            			storage.writeEventTaskToFile(eventTemp);
        			}
        			catch (RemyException e) {
        				printLines();
        				System.out.println("     " + e.getMessage());
        				printLines();
        			}
        			catch (IllegalArgumentException e) {
        				printLines();
        				System.out.println("     Error: Please input your event in the right format");
        				printLines();
        			}
        		} else if (userCmd[0].equals("delete")) {
        			try {
        				int index = Integer.valueOf(userCmd[1]) - 1;
            			Task removed = toDoList.deleteTask(index);
            			ui.printTaskRemoved(removed, toDoList);
            			storage.deleteTaskFromFile(index);
        			}
        			catch (IndexOutOfBoundsException e) {
        				printLines();
        				System.out.println("     Error: Cannot delete a non-existent task");
        				printLines();
        			}
        			catch (NumberFormatException e) {
        				printLines();
        				System.out.println("     Error: Cannot index a non-integer value");
        				printLines();
        			}
        		} else {
        			ui.invalidCommandResponse();
        		}
            	
            	command = input.nextLine();
                }
                
            	ui.sayGoodbye();
                input.close();
    	}
    	catch (IOException e) {
    		System.out.println("     File not found and could not be initialized to write to");
    	}
    }
    
    public static void printLines() {
    	System.out.println("    ____________________________________________________________");
    }
    
    public static String[] getDeadlineDescription(String[] description) {
    	String due = "";
    	String desc = "";
    	for (int i = 1; i < description.length; i++) {
    		if (!description[i].equals("/by")) {
    			desc += description[i] + " ";
    		} else {
    			due += String.join(" ", Arrays.copyOfRange(description, i + 1, description.length));
    			break;
    		}
    	}
    	String[] descAndDue = {desc.strip(), due.strip()};
    	return descAndDue;
    }
    
    public static String[] getEventDescription(String[] description) {
    	int fromIndex = 0;
    	int toIndex = 0;
    	String desc = "";
    	String from = "";
    	String to = "";
    	
    	for (int i = 0; i < description.length; i++) {
    		if (description[i].equals("/from")) {
    			fromIndex = i;
    		} else if (description[i].equals("/to")) {
    			toIndex = i;
    			break;
    		}
    	}
    	desc = String.join(" ", Arrays.copyOfRange(description, 1, fromIndex));
		from = String.join(" ", Arrays.copyOfRange(description, fromIndex + 1, toIndex));
		if (toIndex + 1 != description.length) {
	    	to = String.join(" ", Arrays.copyOfRange(description, toIndex + 1, description.length));
		}
    	String[] info = {desc.strip(), from.strip(), to.strip()};
    	return info;
    }
}
