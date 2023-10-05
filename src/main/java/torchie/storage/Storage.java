package torchie.storage;

import torchie.exception.CorruptedFileException;
import torchie.exception.TorchieException;
import torchie.parser.TaskDetailsParser;
import torchie.task.TaskList;
import torchie.task.Task;
import torchie.task.ToDo;
import torchie.task.Deadline;
import torchie.task.Event;

import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private File file;
    private File directory;
    private final String PATH = "./data/";
    private final String FILENAME = "torchie.txt";

    public Storage() {
        directory = new File(PATH);
        file = new File(PATH + FILENAME);

        try {
            if (!directory.exists()) {
                // create directory
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(PATH + FILENAME);
            for (Task task : taskList.getTaskList()) {
                fw.write(task.toFileFormat() + "\n");

            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public TaskList retrieveData() {
        TaskList taskList = new TaskList();
        Task task;
        Scanner scanner=null;

        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                try {
                    task = decode(scanner.nextLine());
                    taskList.addTask(task);
                } catch (CorruptedFileException e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
        return taskList;
    }

    public Task decode(String s) throws CorruptedFileException {
        String[] taskDetails = s.split(" \\| ");

        String taskType = null;
        String taskStatus = null;
        String taskDescription = null;

        try {
            taskType = taskDetails[0];
            taskStatus = taskDetails[1];
            taskDescription = taskDetails[2];
        } catch (Exception e) {
            throw new CorruptedFileException();
        }

        Task task = null;
        TaskDetailsParser taskDetailsParser = new TaskDetailsParser();

        switch(taskType) {
        case "T":
            task = new ToDo(taskDescription);
            break;
        case "D":
            try {
                String taskDuration =  taskDetails[3];
                LocalDateTime deadlineDate = taskDetailsParser.getDeadlineDate(taskDuration);
                task = new Deadline(taskDescription, deadlineDate);
            } catch (TorchieException e) {
                throw new CorruptedFileException();
            }
            break;
        case "E":
            try {
                String taskDuration =  taskDetails[3];
                LocalDateTime eventStart = taskDetailsParser.getEventStart(taskDuration);
                LocalDateTime eventEnd = taskDetailsParser.getEventEnd(taskDuration);
                task = new Event(taskDescription, eventStart, eventEnd);
            } catch (TorchieException e) {
                throw new CorruptedFileException();
            }
            break;
        default:
            throw new CorruptedFileException();
        }
        if (taskStatus.equals("X")) {
            task.setIsDone(true);
        }
        return task;

    }
}