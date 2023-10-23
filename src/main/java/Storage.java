import java.util.Objects;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;

/**
 * Storage saves and loads previous trials using a text file.
 * @author Isaiah Cerven
 * @version 1.0
 */
public class Storage {

	//fileTasksArray stores all tasks that are to be loaded
	static ArrayList<Task> fileTasksArray = new ArrayList<>(100);
	static Path dirPath = Paths.get("./storage");
	static Path filePath = dirPath.resolve("tasks.txt");

	/**
	 * A get function to allow Duke to view the stored Task objects
	 *
	 * @return ArrayList<Task>
	 * @throws IOException
	 */
	public static ArrayList<Task> getFileTasksArray() throws IOException {
		if (fileTasksArray.isEmpty()) {
			loadFileObjects();
		}
		return fileTasksArray;
	}

	/**
	 * Prints all text file contents
	 * @throws IOException
	 */
	public static void printFileContents() throws IOException {
		// Create directory and file if they don't exist
		if (!Files.exists(dirPath)) {
			Files.createDirectories(dirPath);
		}
		if (!Files.exists(filePath)) {
			Files.createFile(filePath);
		}
		try (Scanner s = new Scanner(filePath)) {
			while (s.hasNext()) {
				System.out.println(s.nextLine());
			}
		}
	}

	/**
	 * Checks text file for different Tasks objects and loads them.
	 * Each Task Starts with the Task letter, with their parameters separated by a '/'
	 * Non denoted Task is the exception and isnt marked.
	 * @throws IOException
	 */


	public static void loadFileObjects() throws IOException {
		// Check for file's existence, and create one if it doesn't exist
		checkFileExists();

		try (Scanner s = new Scanner(filePath)) {
			while (s.hasNext()) {
				//entry is the task on each line.
				String entry = s.nextLine();

				//Separate the Tasks into components.
				String[] parts = entry.split("/");

				//Get the first word as keyword.
				String keyword = parts[0];
				keyword = keyword.toUpperCase();


				switch (keyword) {
				case ("D"):
					//Is marked, is if a task is finished and is shown in the txt file as 'X'
					String isMarked = parts[1];
					if (!Objects.equals(isMarked, "X")) {
						//The X should be a space

						String desc = parts[2];
						String time = parts[3];
						Deadline deadline = new Deadline(desc, time);
						fileTasksArray.add(deadline);
					} else {

						String desc = parts[2];
						String time = parts[3];
						Deadline deadline = new Deadline(desc, time);
						deadline.isDone = true;
						fileTasksArray.add(deadline);
					}

					break;
				case ("T"):
					isMarked = parts[1];
					if (!Objects.equals(isMarked, "X")) {
						//The X should be a space
						String desc = parts[2];
						Todo todo = new Todo(desc);
						fileTasksArray.add(todo);
					} else {
						String desc = parts[2];

						Todo todo = new Todo(desc);
						todo.isDone = true;
						fileTasksArray.add(todo);
					}
					break;
				case ("E"):
					isMarked = parts[1];
					if (!Objects.equals(isMarked, "X")) {
						//The X should be a space

						String desc = parts[2];
						String start = parts[3];
						String end = parts[4];
						Event event = new Event(desc, start, end);
						fileTasksArray.add(event);
					} else {

						String desc = parts[2];
						String start = parts[3];
						String end = parts[4];
						Event event = new Event(desc, start, end);
						event.isDone = true;
						fileTasksArray.add(event);
					}
				default:
					//Default case is the case where its not a specific kind of task added
					isMarked = parts[0];
					//System.out.println(isMarked);
					if (!Objects.equals(isMarked, "X")) {
						//System.out.println(parts[2]);

						Task newTask = new Task(parts[2]);
						//System.out.println(newTask.getFileReadableString() + "TASK NAME");
						fileTasksArray.add(newTask);
					} else {
						Task newTask = new Task(parts[2]);
						newTask.isDone = true;
						fileTasksArray.add(newTask);
					}

				}

			}


		} catch (IOException e) {
			System.out.println("Error reading file");
		}
	}

	/**
	 * Default check to see if text file data/tasks.txt exists. If not create it.
	 */
	private static void checkFileExists(){
		if (!Files.exists(dirPath)) {
			System.out.println("Dir does not exist. Creating a new one...");
			try {
				Files.createDirectories(dirPath);
			} catch (IOException e) {
				System.out.println("Error creating directory: " + e.getMessage());
				return;
			}
		}

		// Ensure the file exists before reading. If not, create it.
		if (!Files.exists(filePath)) {
			System.out.println("File does not exist. Creating a new one...");
			try {
				Files.createFile(filePath);
			} catch (IOException e) {
				System.out.println("Error creating file: " + e.getMessage());
				return;
			}
		}
	}



	//Called with Save file. Appends latest task to text file.
	public static void writeToFile(String textToAdd) throws IOException {
		// Ensure the directory exists
		if (!Files.exists(dirPath)) {
			Files.createDirectories(dirPath);
		}
		try (FileWriter fw = new FileWriter(filePath.toFile(), true)) {
			fw.write(textToAdd);
		}
	}

	//Clears Text file
	public static void clearFile() throws IOException {
		if (!Files.exists(dirPath)) {
			Files.createDirectories(dirPath);
		}
		try (FileWriter fw = new FileWriter(filePath.toFile())) {
			// Intentionally left empty to clear the file
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	//Saves tasks from this run to text file.
	public static void saveFile() {
		try {
			Storage.printFileContents();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		int numOfTasks = 0;
		for (Task task : TasksList.tasksToBeSaved) {
			try {
				Storage.writeToFile(task.getFileReadableString() + System.lineSeparator());
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	//Use for removing different tasks from text file.
	public static void removeEntryAtIndex(int index) throws IOException {
		List<String> lines = new ArrayList<>();

		// Ensure the file exists before reading
		if (!Files.exists(filePath)) {
			return;
		}

		try (BufferedReader br = Files.newBufferedReader(filePath)) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		}

		// Check if the index is valid and remove the entry
		if (index >= 0 && index < lines.size()) {
			lines.remove(index);
		}

		// Write modified list back to the file
		try (FileWriter fw = new FileWriter(filePath.toFile())) {
			for (String l : lines) {
				fw.write(l + System.lineSeparator());
			}
		}
	}
}
