import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.List;

public class Storage {
	private static File dir;
	private static File textFile;
	private static FileWriter writeFile;
	
	public Storage(String dirName, String textFileName) {
		dir = new File(dirName);
		textFile = new File(textFileName);
	}

	public void checkForTextFile(List<Task> taskList) throws IOException {
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
    		createTaskListFile();
    	}
		writeFile = new FileWriter(textFile, true);
	}

    public static void textToTask(String[] existingTask, List<Task> taskList) {
    	if (existingTask[0].equals("todo")) {
    		Task toDoTask = new ToDo(existingTask[1]);
    		if (existingTask[2].equals("x")) {
    			toDoTask.setDone(true);
    		}
    		taskList.add(toDoTask);
    	} else if (existingTask[0].equals("deadline")) {
    		Task deadlineTask = new Deadline(existingTask[1], existingTask[2]);
    		if (existingTask[3].equals("x")) {
    			deadlineTask.setDone(true);
    		}
    		taskList.add(deadlineTask);
    	} else if (existingTask[0].equals("event")) {
    		Task eventTask = new Event(existingTask[1], existingTask[2], existingTask[3]);
    		if (existingTask[4].equals("x")) {
    			eventTask.setDone(true);
    		}
    		taskList.add(eventTask);
    	}
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
    
    public void markTaskFromFile(int taskIndex, boolean done) {
    	try {
        	Scanner readFile = new Scanner(textFile);
        	File tempFile = new File("./data/temp.txt");
        	FileWriter tempWriter = new FileWriter(tempFile);
        	
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
        	writeFile = new FileWriter(textFile, true);
    	}
    	catch (FileNotFoundException e) {
    		System.out.println(e);
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    public void writeToDoTaskToFile(ToDo task) {
    	try {
    		writeFile.write("todo," + task.getDescription() + ",o" + "\n");
    		writeFile.flush();
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    public void writeDeadlineTaskToFile(Deadline task) {
    	try {
    		writeFile.write("deadline," + task.getDescription() + "," + task.getDue() + ",o" + "\n");
    		writeFile.flush();
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    
    public void writeEventTaskToFile(Event task) {
    	try {
    		writeFile.write("event," + task.getDescription() + "," + task.getFrom() + "," + task.getTo() + ",o" + "\n");
    		writeFile.flush();
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    public void deleteTaskFromFile(int taskIndex) {
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
        	textFile = new File("./data/toDoList.txt");
        	writeFile = new FileWriter(textFile, true);
    	}
    	catch (FileNotFoundException e) {
    		System.out.println(e);
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}
    }
}
