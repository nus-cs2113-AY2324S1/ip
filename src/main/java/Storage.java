import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
public class Storage {
    /**
     * Write the task list to the file
     *
     * @param tasks The task list to be written to the file
     */
    public void writeToFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (int i=0;i<tasks.size();i++) {
                fw.write(tasks.get(i+1).toFile() + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    /**
     * Read the task list from the file
     *
     * @param tasks The task list to be read from the file
     */
    public void readFromFile(TaskList tasks) {
        try {
            FileReader fr = new FileReader("./data/duke.txt");
            Scanner sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] task = line.split(" \\| ");
                if (task[0].equals("T")) {
                    tasks.addTask(new Todo (task[2]));
                } else if (task[0].equals("D")) {
                    tasks.addTask(new Deadline (task[2], "by " + task[3]));
                } else if (task[0].equals("E")) {
                    tasks.addTask(new Event (task[2], "from " + task[3], "to " + task[4]));
                }
                if (task[1].equals("1")) {
                    tasks.markTask(tasks.size());
                }
            }
            sc.close();
            fr.close();
        } catch(IOException e) {
            try {
                File file = new File("./data/duke.txt");
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e2) {
                System.out.println("☹ OOPS!!! The file has existed.");
            }
        } catch(DukeException e) {
            System.out.println("☹ OOPS!!! Wrong format in file.");
        }
    }
}
