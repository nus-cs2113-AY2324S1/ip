package doli.files;

import doli.exceptions.DoliExceptions;
import doli.tasks.Task;
import doli.tasks.ToDo;
import doli.tasks.Deadline;
import doli.tasks.Event;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    private static final String FILE_PATH = "Agenda.txt";
    private static final String INFO_SEPARATOR = "|";
    public static void initializeFile(ArrayList<Task> agenda) throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                System.out.println(e);
            }
        } else {
            try {
                loadExistingFile(file, agenda);
            } catch (DoliExceptions e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void loadExistingFile(File file, ArrayList<Task> agenda) throws IOException, DoliExceptions {
        Scanner fs = new Scanner(file);
        while (fs.hasNext()) {
            String line = fs.nextLine();
            String[] task = line.split("\\|");
            String type = task[0];
            boolean isDone = task[1] == "X";
            String description = task[2];

            switch (type) {
            case "T":
                addTodo(description, isDone, agenda);
                break;
            case "D":
                String deadline = task[3];
                addDeadline(description, isDone, deadline, agenda);
                break;
            case "E":
                String startDate = task[3];
                String endDate = task[4];
                addEvent(description, isDone, startDate, endDate, agenda);
                break;
            default:
                throw new DoliExceptions("Task not recognized.");
            }
        }
    }
    public static void modifyFile(ArrayList<Task> agenda) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : agenda) {
                if (task instanceof ToDo) {
                    writeTodo(fw, (ToDo) task);
                } else if (task instanceof Deadline) {
                    writeDeadline(fw, (Deadline) task);
                } else if (task instanceof Event) {
                    writeEvent(fw, (Event) task);
                } else {
                    throw new DoliExceptions("Task type unrecognized.");
                }
            }
            fw.close();
        } catch (DoliExceptions | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void addTodo(String description, boolean isDone, ArrayList<Task> agenda) {
        ToDo todo = new ToDo(description);
        todo.setDone(isDone);
        agenda.add(todo);
    }
    private static void addDeadline(String description, boolean isDone, String date, ArrayList<Task> agenda) {
        Deadline deadline = new Deadline(description, date);
        deadline.setDone(isDone);
        agenda.add(deadline);
    }
    private static void addEvent(String description, boolean isDone,
                                 String startDate, String endDate, ArrayList<Task> agenda) {
        Event event = new Event(description, startDate, endDate);
        event.setDone(isDone);
        agenda.add(event);
    }
    public static void writeTodo(FileWriter fw, ToDo todo) throws IOException {
        fw.write("T" + INFO_SEPARATOR + todo.getStatusIcon()
                + INFO_SEPARATOR + todo.getDescription() + System.lineSeparator());
    }
    public static void writeDeadline(FileWriter fw, Deadline deadline) throws IOException {
        fw.write("D" + INFO_SEPARATOR + deadline.getStatusIcon()
                + INFO_SEPARATOR + deadline.getDescription() +
                INFO_SEPARATOR + deadline.getDeadline() + System.lineSeparator());
    }
    public static void writeEvent(FileWriter fw, Event event) throws IOException {
        fw.write("E" + INFO_SEPARATOR + event.getStatusIcon()
                + INFO_SEPARATOR + event.getDescription() +
                INFO_SEPARATOR + event.getStartTime() +
                INFO_SEPARATOR + event.getEndTime() + System.lineSeparator());
    }
}
