package duke;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to manage the read and writing of data between the tasklist and a text file.
 */
public class FileManager {

    public FileWriter writer;
    public File file;
    private static final ArrayList<duke.Task> taskList = new ArrayList<>();

    public FileManager() throws IOException {
        this.writer = new FileWriter("./test.txt", true);
        this.file = new File("./test.txt");
    }

    /**
     * @return taskList that is populated with data from the task objects as written in the text file
     */
    public ArrayList<Task> read(){
        try {
            FileReader fileReader = new FileReader("./test.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String keyword = parts[0].trim();
                switch (keyword){
                case "T":
                    Task todo = new ToDo(parts, true);
                    todo.isDone = Boolean.parseBoolean(parts[1].trim());
                    taskList.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(parts, true);
                    deadline.isDone = Boolean.parseBoolean(parts[1].trim());
                    taskList.add(deadline);
                    break;
                case "E":
                    Task event = new Event(parts, true);
                    event.isDone = Boolean.parseBoolean(parts[1].trim());
                    taskList.add(event);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * @param text text to write to the text file
     */
    public void write(String text){
        try {
            this.writer.write(text);
            this.writer.flush();
        }
        catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * @param taskList the populated task list to be written to the file.
     */
    public void save(ArrayList<duke.Task> taskList){
        try {
            if (this.writer != null){
                closeFile();
            }
            this.writer = new FileWriter("./test.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Task task:taskList){
            write(task.taskFormatted());
        }
    }

    /**
     * @throws IOException if there is an IO error when closing the FileWriter object.
     */
    public void closeFile() throws IOException {
        writer.close();
    }
}