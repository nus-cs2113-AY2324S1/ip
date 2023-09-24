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
    private static final String FILE_PATH = "data\\";
    private static final String FILE_NAME = "data.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM uuuu");
    private File file;

    public Storage() {
        this.file = new File(FILE_PATH + FILE_NAME);    
    }

    public void writeToFile(String data) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH + FILE_NAME, true);
            fw.write(data + "\n");
            fw.close();
        } catch (IOException exception) {
            System.out.println("Cannot write to file");
        }
    }

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

    public void dataToList(Task task, String isCompleted, TaskList taskList) {
        if(isCompleted.equals("1")) {
            task.setCompleted(true);
        } else {
            task.setCompleted(false);
        }
        taskList.getTaskList().add(task);
    }

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
            System.out.println("File not found");
        } catch (ArrayIndexOutOfBoundsException exception) {
            
        }
    }

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
            System.out.println("An error occurred.");
        } catch (DateTimeParseException exception) {
            System.out.println("An error occurred.");
        }
    }
}
