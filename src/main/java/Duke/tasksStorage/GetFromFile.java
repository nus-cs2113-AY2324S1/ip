package duke.tasksStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import duke.inputProcess.TaskList;
import java.util.Scanner;

/**
 * GetFromFile is a class responsible for reading from text files
 * and fill the task list with the retrieved tasks. it supports reading
 * different types of tasks (Todo, deadlines, events) and their respective statuses.
 * <p>
 * GetFromFile is used to load tasks from a file when the Hilary robot starts.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-09-30
 */
public class GetFromFile {
    private final File file;
    private static final String FILE_CREATION_ERROR = "\tSomething went wrong";
    private static final String FILE_FORMAT_ERROR = "\tThe file is not in the correct format";

    /**
     * Constructs a `GetFromFile` object with the given file path.
     *
     * @param path The file path where to read task data.
     */
    public GetFromFile(String path) {
        file = new File(path);
    }

    /**
     * Reads task data from the text file and add to the provided task list.
     *
     * @param list The task list to be added with tasks read from the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public void getFromTextFile(TaskList list) throws FileNotFoundException {
        try {
            if (file.createNewFile()) {
                return;
            }
        } catch (IOException e) {
            System.out.println(FILE_CREATION_ERROR);
        }
        Scanner s = new Scanner(file); // Create a Scanner using the File as the source
        int lineCount = 0;
        while (s.hasNext()) {
            String textLine = s.nextLine();
            try {
                String commandFromFile = textLine.split(" \\| ")[0];
                String isDoneFromFile = textLine.split(" \\| ")[1];
                String taskFromFile = textLine.split(" \\| ")[2];
                String timeFromFile = "";
                if (!commandFromFile.equals("T")) {
                    timeFromFile = textLine.split(" \\| ")[3];
                }
                switch (commandFromFile) {
                case "T":
                    list.addTodo(taskFromFile);
                    break;
                case "D":
                    list.addDeadline(taskFromFile, LocalDateTime.parse(timeFromFile));
                    break;
                case "E":
                    LocalDateTime start = LocalDateTime.parse(timeFromFile.split(" to ", 2)[0]);
                    LocalDateTime end = LocalDateTime.parse(timeFromFile.split(" to ", 2)[1]);
                    list.addEvent(taskFromFile, start, end);
                    break;
                default:
                    System.out.println("The format in the file is incorrect from this line " + textLine);
                }
                if (isDoneFromFile.equals("1")) {
                    list.getByIndex(lineCount).markAsDone();
                }
                lineCount++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(FILE_FORMAT_ERROR);
            }
        }
    }
}
