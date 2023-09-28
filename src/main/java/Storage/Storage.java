package Storage;

import Task.Task;
import Task.ToDo;
import Task.Event;
import Task.Deadline;
import Task.TaskList;
import Ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected static String FILE_NAME = "./src/main/java/data/duke.txt";
    public static void save(TaskList tasks) throws IOException {
        Files.createDirectories(Paths.get("./src/main/java/data"));
        if(Files.exists(Paths.get(FILE_NAME))) {
            Files.delete(Paths.get(FILE_NAME));
        }
        Files.createFile(Paths.get(FILE_NAME));

        FileWriter writer = new FileWriter(FILE_NAME, false);

        for (int i = 0; i < tasks.size(); i++) {
            writer.write(tasks.get(i).toFileLine()
                    + System.lineSeparator());
        }
        writer.close();
    }

    public static ArrayList<Task> load() {
        ArrayList<Task> out = new ArrayList<Task>();

        try {
            File in = new File(FILE_NAME);
            Scanner scanner = new Scanner(in);
            while(scanner.hasNext()){
                String[] params = scanner
                        .nextLine()
                        .split(" /");

                switch(params[2]){
                    case "TODO":
                        ToDo todo = new ToDo(params[0],
                                (params[1].equalsIgnoreCase("true")));
                        out.add(todo);
                        break;

                    case "EVENT":
                        Event event = new Event(params[0],
                                (params[1].equalsIgnoreCase("true")),
                                params[3],
                                params[4]);
                        out.add(event);
                        break;

                    case "DEADLINE":
                        Deadline deadline = new Deadline(params[0],
                                (params[1].equalsIgnoreCase("true")),
                                params[3]);
                        out.add(deadline);
                        break;
                    default:
                        break;
                }

            }
        } catch(NullPointerException e) {
            Ui.reportFileMissingError();
        } catch(FileNotFoundException e) {
            return out;
        }

        return out;
    }
}
