package classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;


public class Storage {
	private static File dir;
	private static File textFile;
	private static FileWriter writeFile;
	
	public Storage(String dirName, String textFileName) {
		dir = new File(dirName);
		textFile = new File(textFileName);
	}
	
	/**
	 * Checks to see if the data directory and toDoList.txt file exist. If so,
	 * reads through toDoList.txt to get all the tasks. If not, creates the directory and/or
	 * text file as needed.
	 * 
	 * @param taskList the list that stores all the tasks
	 * @throws IOException if writing to a file that does not exist, an IOException is thrown
	 */
	public void checkForTextFile(TaskList taskList) throws IOException {
		if (dir.exists() && textFile.exists()) {
    		try {
    			Scanner s = new Scanner(textFile);
        		while (s.hasNextLine()) {
        			textToTask(s.nextLine().split(","), taskList);
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
    		textFile.createNewFile();
    	}
		writeFile = new FileWriter(textFile.toPath().toString(), true);
	}
	
	/**
	 * Parses the user's input and creates the corresponding task depending on what
	 * the read input is.
	 * 
	 * @param existingTask the array containing the details of the task to be created
	 * @param taskList the list containing all tasks
	 */
    public static void textToTask(String[] existingTask, TaskList taskList) {
    	if (existingTask[0].equals("todo")) {
    		Task toDoTask = new ToDo(existingTask[1]);
    		if (existingTask[2].equals("x")) {
    			toDoTask.setDone(true);
    		}
    		taskList.addTask(toDoTask);
    	} else if (existingTask[0].equals("deadline")) {
    		Task deadlineTask = new Deadline(existingTask[1], existingTask[2]);
    		if (existingTask[3].equals("x")) {
    			deadlineTask.setDone(true);
    		}
    		taskList.addTask(deadlineTask);
    	} else if (existingTask[0].equals("event")) {
    		Task eventTask = new Event(existingTask[1], existingTask[2], existingTask[3]);
    		if (existingTask[4].equals("x")) {
    			eventTask.setDone(true);
    		}
    		taskList.addTask(eventTask);
    	}
    }
    
    /**
     * Marks the task as done on the hard drive when the user wants to mark it in their list.
     * 
     * @param taskIndex the index of the task to be marked
     * @param done whether the task is to be marked as done or not done
     */
    public void markTaskFromFile(int taskIndex, boolean done) {
    	try {
        	Scanner readFile = new Scanner(textFile);
        	File tempFile = new File("./data/temp.txt");
        	FileWriter tempWriter = new FileWriter(tempFile.toPath().toString());
        	
        	int currIndex = 0;
        	while (readFile.hasNextLine()) {
    			String line = readFile.nextLine();
        		if (currIndex != taskIndex) {
            		tempWriter.write(line + "\n");
        		} else {
        			if (done) {
        				tempWriter.write(line.substring(0, line.length() - 1) + "x" + "\n");
        			} else {
        				tempWriter.write(line.substring(0, line.length() - 1) + "o" + "\n");
        			}
        			
        		}
        		currIndex++;
        	}
        	readFile.close();
        	tempWriter.close();
        	textFile.delete();
        	tempFile.renameTo(textFile);
        	textFile = new File("./data/toDoList.txt");
        	writeFile = new FileWriter(textFile.toPath().toString(), true);
    	}
    	catch (FileNotFoundException e) {
    		System.out.println(e);
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    /**
     * When a new Todo task is created, it is written to the hard drive.
     * 
     * @param task the todo task that is to be saved on the hard drive
     */
    public void writeToDoTaskToFile(ToDo task) {
    	try {
    		writeFile.write("todo," + task.getDescription() + ",o" + "\n");
    		writeFile.flush();
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    /**
     * When a new Deadline task is created, it is written to the hard drive.
     * 
     * @param task the deadline task that is to be saved on the hard drive
     */
    public void writeDeadlineTaskToFile(Deadline task) {
    	try {
    		writeFile.write("deadline," + task.getDescription() + "," + task.getDue() + ",o" + "\n");
    		writeFile.flush();
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    /**
     * When a new Event task is created, it is written to the hard drive.
     * 
     * @param task the event task that is to be saved on the hard drive
     */
    public void writeEventTaskToFile(Event task) {
    	try {
    		writeFile.write("event," + task.getDescription() + "," + task.getFrom() + "," + task.getTo() + ",o" + "\n");
    		writeFile.flush();
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    /**
     * When the user inputs a delete command, deletes the task at the specified index from the
     * hard drive.
     * 
     * @param taskIndex the index of the task you want to delete from the hard drive
     */
    public void deleteTaskFromFile(int taskIndex) {
    	try {
        	Scanner readFile = new Scanner(textFile);
        	File tempFile = new File("./data/temp.txt");
        	FileWriter tempWriter = new FileWriter(tempFile.toPath().toString());
        	
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
        	textFile = new File("./data/toDoList.txt");
        	writeFile = new FileWriter(textFile.toPath().toString(), true);
    	}
    	catch (FileNotFoundException e) {
    		System.out.println(e);
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
}
