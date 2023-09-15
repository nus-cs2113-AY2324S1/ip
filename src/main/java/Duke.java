import java.util.Arrays;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Duke {
	static Task[] toDoList = new Task[100];
	static int size = 0;
	
    public static void main(String[] args) {
		greetUser();
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        
        while (!command.equals("bye")) {
        	String strippedCommand = command.strip();
    		String[] userCmd = strippedCommand.split(" ");
        	if (command.equals("list")) {
        		printToDoList();
        	} else if (userCmd[0].contains("mark")) {
        		try {
        			int index = Integer.parseInt(userCmd[1]) - 1;
        			if (userCmd[0].equals("mark")) {
            			toDoList[index].setDone(true);
            		} else {
            			toDoList[index].setDone(false);
            		}
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
        			addTask(toDoTask);
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
        			addTask(deadlineTask);
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
        			addTask(eventTask);
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
    		} else {
    			invalidCommandResponse();
    		}
        	
        	command = input.nextLine();
            }
            
        	sayGoodbye();
            input.close();
	}
    
    public static void greetUser() {
    	 printLines();
    	 System.out.println("\n"
    	 		+ "    __________                      \n"
    	 		+ "    \\______   \\ ____   _____ ___.__.\n"
    	 		+ "     |       _// __ \\ /     <   |  |\n"
    	 		+ "     |    |   \\  ___/|  Y Y  \\___  |\n"
    	 		+ "     |____|_  /\\___  >__|_|  / ____|\n"
    	 		+ "            \\/     \\/      \\/\\/     \n"
    	 		+ "");
         System.out.println("    Hello! I'm Remy");
         System.out.println("    What can I do for you?");
         printLines();
    }
    
    public static void printLines() {
    	System.out.println("    ____________________________________________________________");
    }
    
    public static void addTask(Task task) {
    	toDoList[size++] = task;
    	printLines();
    	System.out.println("     Got it. I've added this task:");
    	System.out.println("       " + task);
    	System.out.println("     Now you have " + size + " tasks in the list.");
    	printLines();
    	System.out.println();
    }
    
    public static void sayGoodbye() {
    	printLines();
        System.out.println("    Bye. Hope to see you again soon!");
        printLines();
    }
    
    public static void printToDoList() {
    	printLines();
    	if (size != 0) {
    		System.out.println("     " + "Here's your tasks:");
        	for (int i = 0; i < size; i++) {
        		System.out.println("     " + Integer.toString(i + 1) + "." + toDoList[i]);
        	}
    	} else {
    		System.out.println("     You do not currently have any tasks!");
    	}
    	printLines();
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
    	String[] descAndDue = {desc, due};
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
    	String[] info = {desc, from, to};
    	return info;
    }
    
    public static void invalidCommandResponse() {
    	printLines();
    	System.out.println("     Sorry I don't understand that command (◡︵◡)");
    	printLines();
    }
}
