package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to store the tasks that our user has added before
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method is designed to scan the text saved by our program
     * and add them back to the list when our user reopens it,
     * so that the tasks the user saved before will not be lost
     * @return the list of Tasks that the user saved before
     */
    public List<Task> load() {
        List<Task> lines = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (getTheTask(line) != null) {
                        lines.add(getTheTask(line));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Now 'lines' contains all the lines from the file
            // You can iterate through 'lines' or perform any other operations as needed

            // Example: Printing the lines
            if (lines.size() != 0) {
                System.out.println("Remember that you have already added the following tasks before!");
            }
            for (Task fileLine : lines) {
                System.out.println(fileLine);
            }
        }

        return lines;
    }

    /**
     * This method is used to analyze every line in our file and create the corresponding task based on it
     * @param lineInTheFile the line in our File
     * @return the task we create based on the line in the file
     */
    public Task getTheTask(String lineInTheFile) {
        if (lineInTheFile.contains("[T]")) {
            String[] words = lineInTheFile.split(" ");
            String task = "";
            Todo todo;
            if (lineInTheFile.contains("[X]")) {
                for (int i = 2; i < words.length; i++) {
                    task += words[i] + " ";
                }
                todo = new Todo(task.trim());
                todo.setDone(true);
            } else {
                for (int i = 3; i < words.length; i++) {
                    task += words[i] + " ";
                }
                todo = new Todo(task.trim());
                todo.setDone(false);
            }
            return todo;
        } else if (lineInTheFile.contains("[D]")) {
            Deadline deadline = transformInputToDeadline(lineInTheFile);
            return deadline;
        } else if (lineInTheFile.contains("[E]")){
            Event event = getEvent(lineInTheFile);
            return event;
        }
        return null;
    }

    private static Deadline transformInputToDeadline(String lineInTheFile) {
        String by = lineInTheFile.split("by: ")[1].replace(")", "");
        String wordsBeforeBy = lineInTheFile.split("by: ")[0].replace("(", "");
        Deadline deadline;
        String[] allWords = wordsBeforeBy.split(" ");
        String description = "";
        if (lineInTheFile.contains("[X]")) {
            for (int i = 2; i < allWords.length; i++) {
                description += allWords[i] + " ";
            }
            deadline = new Deadline(description, by.replace("by", "").trim());
            deadline.setDone(true);
        } else {
            for (int i = 3; i < allWords.length; i++) {
                description += allWords[i] + " ";
            }
            deadline = new Deadline(description, by.replace("by", "").trim());
            deadline.setDone(false);
        }
        return deadline;
    }

    private static Event getEvent(String lineInTheFile) {
        String[] words;
        if (lineInTheFile.contains("from: ")) {
            words = lineInTheFile.split("from: ");
        } else {
            words = lineInTheFile.split("from ");
        }
        String timeperiod = "from " + words[1].replace(")", "");
        String description = "";
        String[] potentailDescription = words[0].replace("(", "").split(" ");
        Event event;
        if (lineInTheFile.contains("[X]")) {
            for (int i = 2; i < potentailDescription.length; i++) {
                description += potentailDescription[i] + " ";
            }
            event = new Event(description, timeperiod);
            event.setDone(true);
            return event;
        } else {
            for (int i = 3; i < potentailDescription.length; i++) {
                description += potentailDescription[i] + " ";
            }
            event = new Event(description, timeperiod);
            event.setDone(false);
            return event;
        }
    }
}
