package nuke.storage;

import nuke.storage.exception.TaskFileCopyException;
import nuke.storage.exception.TaskLoadException;
import nuke.storage.exception.TaskParseException;
import nuke.storage.exception.TaskSaveException;
import nuke.task.Task;
import nuke.task.TaskParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents storage of Nuke.
 */
public class Storage {
    private static final String DIR_DATA = "data";
    private static final String FILENAME_TASKS = "nuke.txt";
    private static final String FILENAME_TASKS_BACKUP = "nuke_old.txt";

    /**
     * Loads saved tasks from the designated file.
     *
     * @return list of saved tasks
     * @throws TaskLoadException if loading the tasks fails
     * @throws TaskFileCopyException if both loading the tasks and backing up the file fails
     */
    public ArrayList<Task> loadTasks()
            throws TaskLoadException, TaskFileCopyException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        Path currentRelativePath = Paths.get("");
        Path currentDir = currentRelativePath.toAbsolutePath();
        Path filePath = currentDir.resolve(DIR_DATA).resolve(FILENAME_TASKS);

        File file = new File(filePath.toString());
        if (!file.exists()) {
            return loadedTasks;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = TaskParser.parseTask(line);
                loadedTasks.add(task);
            }
        } catch (IOException | TaskParseException e) {
            Path oldFilePath = currentDir.resolve(DIR_DATA).resolve(FILENAME_TASKS_BACKUP);
            try {
                Files.copy(filePath, oldFilePath, StandardCopyOption.REPLACE_EXISTING);
                throw new TaskLoadException(FILENAME_TASKS_BACKUP);
            } catch (IOException ex) {
                throw new TaskFileCopyException(FILENAME_TASKS);
            }
        }
        return loadedTasks;
    }

    /**
     * Saves the tasks to the designated file.
     *
     * @param formattedTasks tasks formatted to save.
     * @throws TaskSaveException if saving the tasks fails
     */
    public void saveTasks(String[] formattedTasks) throws TaskSaveException {
        Path currentRelativePath = Paths.get("");
        Path currentDir = currentRelativePath.toAbsolutePath();
        Path dirPath = currentDir.resolve(DIR_DATA);
        Path filePath = currentDir.resolve(DIR_DATA).resolve(FILENAME_TASKS);

        File dir = new File(dirPath.toString());
        File file = new File(filePath.toString());
        try {
            dir.mkdir();
            file.createNewFile();
            try (FileWriter writer = new FileWriter(file)) {
                for (String formattedTask: formattedTasks) {
                    writer.write(formattedTask);
                    writer.write('\n');
                }
            }
        } catch (IOException e) {
            throw new TaskSaveException();
        }
    }
}
