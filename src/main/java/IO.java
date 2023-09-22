import Task.Task;
import Task.Task.ToDo;
import Task.Task.Event;
import Task.Task.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class IO {
    protected static String FILE_NAME = "./data/duke.txt";
    public static void save(ArrayList<Task> tasks, int n) throws IOException {
        Files.createDirectories(Paths.get("./data"));
        Files.delete(Paths.get(FILE_NAME));
        Files.createFile(Paths.get(FILE_NAME));

        FileWriter writer = new FileWriter(FILE_NAME, false);

        for (int i = 0; i < n; i++) {
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
            System.out.println("    _____________________________________"
                    + "\n    Where's the file, yo? I couldn't find it!\n"
                    + "    _____________________________________");
        } catch(FileNotFoundException e) {
            return out;
        }

        return out;
    }
}
