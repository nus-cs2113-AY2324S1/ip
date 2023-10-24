package chatbot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }
    /**
     * Parse the storage file and load the data
     *
     * @param   tasks       The current task list
     * @author  Jeremy
     * @since   2023-10-06
     */
    public ArrayList<String> parseFile(ArrayList<Task> tasks) throws IOException {
        Path path = Paths.get(this.filepath);
        ArrayList<String> lines = new ArrayList<>();
        if(!Files.exists(path)) {
            return lines;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path.toAbsolutePath().toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                //System.out.println(line);
                if(line.trim().isEmpty()) {
                    continue;
                }
                lines.add(line);
                //parseCommand(tasks, line, false);
            }
        }
        return lines;
    }
    /**
     * Save input to storage file
     *
     * @param   input       The input to be saved
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void saveToFile(String input) {
        String str = input + System.lineSeparator();
        try {
            Path path = Paths.get(this.filepath);
            //System.out.println("fullpath: " + path.toAbsolutePath().toString());
            if(Files.exists(path)) {
                Files.write(path, str.getBytes(), StandardOpenOption.APPEND);
            } else {
                Files.write(path, str.getBytes(), StandardOpenOption.CREATE);
            }

        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
