import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Duke {
	static ArrayList<Task> toDoList = new ArrayList<>();
	
    public static void main(String[] args) {
    	File dir = new File("data");
		File textFile = new File("./data/toDoList.txt");
    	checkForTextFile(dir, textFile);
    	
    	try {
    		FileWriter writeFile = new FileWriter("./data/toDoList.txt", true);
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
            			System.out.println("    ____________________________________________________________");
            			int index = Integer.parseInt(userCmd[1]) - 1;
            			if (userCmd[0].equals("mark")) {
                			toDoList.get(index).setDone(true);
                			System.out.println("     Nice! I've marked this task as done:");
                		} else {
                			toDoList.get(index).setDone(false);
                			System.out.println("     OK, I've marked this task as not done yet:");
                		}
            			System.out.println("       " + toDoList.get(index));
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
            			addTask(toDoTask);
            			ToDo toDoTemp = (ToDo) toDoTask;
            			writeToDoTaskToFile(writeFile, toDoTemp);
                		writeFile.flush();
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
            			Deadline deadlineTemp = (Deadline) deadlineTask;
            			writeDeadlineTaskToFile(writeFile, deadlineTemp);
                		writeFile.flush();
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
            			Event eventTemp = (Event) eventTask;
            			writeEventTaskToFile(writeFile, eventTemp);
                		writeFile.flush();
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
            			deleteTask(index);
            			textFile = deleteTaskFromFile(textFile, index);
            			writeFile = new FileWriter(textFile, true);
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
        			invalidCommandResponse();
        		}
            	
            	command = input.nextLine();
                }
                
            	sayGoodbye();
                input.close();
    	}
		catch (IOException e) {
			System.out.println(e);
		}
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
    	toDoList.add(task);
    	printLines();
    	System.out.println("     Got it. I've added this task:");
    	System.out.println("       " + task);
    	System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
    	printLines();
    	System.out.println();
    }
    
    public static void deleteTask(int index) {
    	Task removed = toDoList.remove(index);
    	printLines();
    	System.out.println("     Noted. I've removed this task:");
    	System.out.println("       " + removed);
    	System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
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
    	if (!toDoList.isEmpty()) {
    		System.out.println("     " + "Here's your tasks:");
        	for (int i = 0; i < toDoList.size(); i++) {
        		System.out.println("     " + Integer.toString(i + 1) + "." + toDoList.get(i));

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
    
    public static void invalidCommandResponse() {
    	printLines();
    	System.out.println("     Sorry I don't understand that command (◡︵◡)");
    	printLines();
    }
    
    public static void createTaskListFile() {
    	try {
    		FileWriter outputFile = new FileWriter("./data/toDoList.txt");
			outputFile.close();
    	}
    	catch (java.io.IOException e) {
    		System.out.println(e);
    	}
    }
    
    public static void checkForTextFile(File dir, File textFile) {
    	if (dir.exists() && textFile.exists()) {
    		try {
    			Scanner s = new Scanner(textFile);
        		while (s.hasNextLine()) {
        			textToTask(s.nextLine().split(","));
        		}
        		s.close();
    		}
    		catch (FileNotFoundException e) {
    			System.out.println(e);
    		}
    	}
    	if (!dir.exists()) {
    		dir.mkdir();
    	}
    	if (!textFile.exists()) {
    		createTaskListFile();
    	}
	}
    
    public static void writeToDoTaskToFile(FileWriter writeFile, ToDo task) {
    	try {
    		writeFile.write("todo," + task.getDescription() + ",o" + "\n");
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    public static void writeDeadlineTaskToFile(FileWriter writeFile, Deadline task) {
    	try {
    		writeFile.write("deadline," + task.getDescription() + "," + task.getDue() + ",o" + "\n");
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    public static void writeEventTaskToFile(FileWriter writeFile, Event task) {
    	try {
    		writeFile.write("event," + task.getDescription() + "," + task.getFrom() + "," + task.getTo() + ",o" + "\n");
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    public static void textToTask(String[] existingTask) {
    	if (existingTask[0].equals("todo")) {
    		Task toDoTask = new ToDo(existingTask[1]);
    		if (existingTask[2].equals("x")) {
    			toDoTask.setDone(true);
    		}
    		toDoList.add(toDoTask);
    	} else if (existingTask[0].equals("deadline")) {
    		Task deadlineTask = new Deadline(existingTask[1], existingTask[2]);
    		if (existingTask[3].equals("x")) {
    			deadlineTask.setDone(true);
    		}
    		toDoList.add(deadlineTask);
    	} else if (existingTask[0].equals("event")) {
    		Task eventTask = new Event(existingTask[1], existingTask[2], existingTask[3]);
    		if (existingTask[4].equals("x")) {
    			eventTask.setDone(true);
    		}
    		toDoList.add(eventTask);
    	}
    }
    
    public static File deleteTaskFromFile(File textFile, int taskIndex) {
    	try {
        	Scanner readFile = new Scanner(textFile);
        	File tempFile = new File("./data/temp.txt");
        	FileWriter tempWriter = new FileWriter(tempFile);
        	
        	int currIndex = 0;
        	while (readFile.hasNextLine()) {
    			String line = readFile.nextLine();
        		if (currIndex != taskIndex) {
            		tempWriter.write(line + "\n");
        		}
        		currIndex++;
        	}
        	readFile.close();
        	tempWriter.close();
        	textFile.delete();
        	tempFile.renameTo(textFile);
        	tempFile = new File("./data/toDoList.txt");
        	return tempFile;
    	}
    	catch (FileNotFoundException e) {
    		System.out.println(e);
        	return textFile;
    	}
    	catch (IOException e) {
    		System.out.println(e);
        	return textFile;
    	}
    }
}
