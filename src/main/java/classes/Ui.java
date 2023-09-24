package classes;
import java.util.Scanner;
import java.util.ArrayList;

import task.Task;

public class Ui {
	Scanner input = new Scanner(System.in);
	
	public void greetUser() {
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
	
	public void sayGoodbye() {
    	printLines();
        System.out.println("    Bye. Hope to see you again soon!");
        printLines();
        endRemy();
    }
	
	/**
	 * Notifies the user that a task has been added to their list.
	 * 
	 * @param task the task that was added to the list
	 * @param toDoList the list containing all the tasks
	 */
	public void printTaskAdded(Task task, TaskList toDoList) {
    	printLines();
    	System.out.println("     Got it. I've added this task:");
    	System.out.println("       " + task);
    	System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
    	printLines();
    	System.out.println();
	}
	
	/**
	 * Notifies the user that a task has been removed from their list.
	 * 
	 * @param task the task that was removed from the list
	 * @param toDoList the list containing all the tasks
	 */
	public void printTaskRemoved(Task task, TaskList toDoList) {
		printLines();
    	System.out.println("     Noted. I've removed this task:");
    	System.out.println("       " + task);
    	System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
    	printLines();
    	System.out.println();
	}
	
	/**
	 * Prints out the entire user's list.
	 * 
	 * @param toDoList the list containing all the tasks
	 */
    public void printToDoList(TaskList toDoList) {
    	printLines();
    	if (!toDoList.isEmpty()) {
    		System.out.println("     " + "Here's your tasks:");
        	for (int i = 0; i < toDoList.size(); i++) {
        		System.out.println("     " + Integer.toString(i + 1) + "." + toDoList.getTask(i));

        	}
    	} else {
    		System.out.println("     You do not currently have any tasks!");
    	}
    	printLines();
    }
    
    public void invalidCommandResponse() {
    	printLines();
    	System.out.println("     Sorry I don't understand that command (◡︵◡)");
    	printLines();
    }
    
    public String getUserCommand() {
    	return input.nextLine();
    }
    
    /**
     * Searches for all tasks whose descriptions match a specific keyword and prints them out
     * 
     * @param tasks the list containing all the user's tasks
     * @param keyword the keyword to be matched
     */
    public void searchForTasks(TaskList tasks, String keyword) {
    	printLines();
    	ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
    	if (matchingTasks.isEmpty()) {
    		System.out.println("     No tasks found!!!");
    	} else {
    		System.out.println("     Here's what I found:");
    		for (int i = 0; i < matchingTasks.size(); i++) {
    	    	System.out.println("     " + Integer.toString(i + 1) + "." + matchingTasks.get(i));
    		}
    	}
    	printLines();
    }
    
    public void endRemy() {
    	input.close();
    }
}
