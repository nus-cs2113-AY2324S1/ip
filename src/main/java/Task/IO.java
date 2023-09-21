package Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class IO {
    public static void save(Task[] tasks) throws IOException {
        Files.delete(Paths.get("./data/duke.txt"));
        try {
            FileWriter writer = new FileWriter("./data/duke.txt");
            for (Task task: tasks) {
                writer.write(task.toFileLine()
                        + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("    _____________________________________"
                    + "\n    Error saving file!\n"
                    + "    _____________________________________");
        }
    }

    public static Task[] load() {
        Task[] out = new Task[100];
        int i = 0;
        try {
            File in = new File("./data/duke.txt");
            Scanner scanner = new Scanner(in);
            while(scanner.hasNextLine()){
                String[] params = scanner
                        .nextLine()
                        .split(" /");

                switch(params[2]){
                    case "TODO":
                        Task.Task.ToDo todo = new Task.Task.ToDo(params[0],
                                (params[1] == "true"));
                        out[i] = todo;
                        break;

                    case "EVENT":
                        Task.Task.Event event = new Task.Task.Event(params[0],
                                (params[1] == "true"),
                                params[3],
                                params[4]);
                        out[i] = event;
                        break;

                    case "DEADLINE":
                        Task.Task.Deadline deadline = new Task.Task.Deadline(params[0],
                                (params[1] == "true"),
                                params[3]);
                        out[i] = deadline;
                        break;
                    default:
                }

                i += 1;
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
