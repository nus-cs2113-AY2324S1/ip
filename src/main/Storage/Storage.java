package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.ToDo;
import TaskList.TaskList;

public class Storage {
    
    private static final String FILE_IO_ERROR = "error with file";
    private static final String FILE_PATH = "data\\";
    private static final String FILE_NAME = "data.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private File file;

    public Storage() {
        this.file = new File(FILE_PATH + FILE_NAME);    
    }

    /**
     * Writes data to file.
     * 
     * @param data Data to be written into the file.
     */
    public void writeToFile(String data) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH + FILE_NAME, true);
            fw.write(data + "\n");
            fw.close();
        } catch (IOException exception) {
            System.out.println(FILE_IO_ERROR);
        }
    }

    /**
     * Refreshes the data taken from taskList and updates and write to file.
     * 
     * @param taskList TaskList object that contains the updated task list.
     */
    public void refreshData(TaskList taskList) {
        try {
            PrintWriter writer = new PrintWriter(FILE_PATH +FILE_NAME);
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }
        Iterator<Task> taskListIter = taskList.getTaskList().iterator();
        while(taskListIter.hasNext()) {
            Task task = taskListIter.next();
            if(task instanceof ToDo) {
                ToDo todo = (ToDo)task;
                writeToFile(todo.toFile());
            } else if(task instanceof Deadline) {
                Deadline deadline = (Deadline)task;
                writeToFile(deadline.toFile());
            } else {
                Event event = (Event)task;
                writeToFile(event.toFile());
            }
        }
    }

    /**
     * Converts data from file and stores into the TaskList object.
     * 
     * @param task Task to be stored into the task list.
     * @param isCompleted Used to check whether task is completed or not completed.
     * @param taskList TaskList object that the task is going to be stored in.
     */
    public void dataToList(Task task, String isCompleted, TaskList taskList) {
        if(isCompleted.equals("1")) {
            task.setCompleted(true);
        } else {
            task.setCompleted(false);
        }
        taskList.getTaskList().add(task);
    }

    /**
     * Gets the file data and parses it into dataToList function.
     * 
     * @param taskList TaskList object that the task is going to be stored in.
     * @throws IOException If the file cannot be found.
     * @throws DateTimeParseException If data in file is not in the correct format.
     */
    public void getFileData(TaskList taskList) throws IOException, DateTimeParseException {
        try{
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()) {
                String[] dataSplit = (reader.nextLine()).split(",");
                String isCompleted = dataSplit[1];
                if(dataSplit[0].equals("T")) {
                    ToDo todoTask = new ToDo(dataSplit[2]);
                    dataToList(todoTask, isCompleted, taskList);
                } else if(dataSplit[0].equals("D")) {
                    Deadline deadlineTask = new Deadline(dataSplit[2], LocalDate.parse(dataSplit[3], FORMATTER));
                    dataToList(deadlineTask, isCompleted, taskList);
                } else {
                    LocalDate from = LocalDate.parse(dataSplit[3], FORMATTER);
                    LocalDate to = LocalDate.parse(dataSplit[4], FORMATTER);
                    if(from.isAfter(to)) {
                        throw new IOException("Error");
                    }
                    Event eventTask = new Event(dataSplit[2], from, to);
                    dataToList(eventTask, isCompleted, taskList);
                }
            }
            reader.close();
        } catch (FileNotFoundException exception) {
            System.out.println(FILE_IO_ERROR);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println(FILE_IO_ERROR);
        }
    }

    /**
     * Get the file and store into TaskList object.
     * 
     * @param taskList TaskList object that the task is going to be stored in.
     */
    public void importToArrayList(TaskList taskList) {
        try {
            File directory = new File(FILE_PATH);
            directory.mkdir();
            if(file.exists()) {
                getFileData(taskList);
            } else {
                file.createNewFile();
            }
        } catch (IOException exception) {
            System.out.println(FILE_IO_ERROR);
        } catch (DateTimeParseException exception) {
            System.out.println(FILE_IO_ERROR);
        }
    }
}
