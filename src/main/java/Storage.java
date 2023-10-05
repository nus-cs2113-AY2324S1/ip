import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/* Storage Class that read & save txt file*/
/* If no file exist, create new one*/
public class Storage {

    protected String path;

    public Storage(String path){
        this.path = path;
    }

    /* method for save tasks to disk */
    public void saveTask(TaskList tasks, int tasks_size) throws IOException {
        FileWriter fw = new FileWriter("./nupjuk.txt");
        for(int i=0;i<tasks.getSize();i++){
            Task task = tasks.getTask(i);
            fw.write(String.format("%s | %d | %s\r\n",
                    task.getTypeIcon(), task.isDone?1:0 , task.getDescription()));
        }
        fw.close();
    }

    /* Automatically load tasks from disk */
    public ArrayList<Task> loadTask() throws FileNotFoundException {
        ArrayList <Task> tasks = new ArrayList<>();

        File f = new File (this.path);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] taskTokens = s.nextLine().split("\\|");
            if(taskTokens[0].trim().equals("T")){
                Todo todo = new Todo(taskTokens[2].trim());
                if(taskTokens[1].trim().equals("1")){
                    todo.doMark();
                }
                tasks.add(todo);
            } else if(taskTokens[0].trim().equals("D")){
                // task (by: deadline)
                String[] schedules = taskTokens[2].split(" \\(by: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH);
                // delete "(by:" and ")"
                Deadline deadline = new Deadline(schedules[0].trim(),
                        LocalDateTime.parse(schedules[1].substring(0, schedules[1].length()-1).trim(), formatter));
                if(taskTokens[1].trim().equals("1")){
                    deadline.doMark();
                }
                tasks.add(deadline);
            } else{
                // task (from: t to: t)
                String[] schedules = taskTokens[2].split(" \\(from: ");
                String[] times = schedules[1].split(" to: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH);
                Event event = new Event(schedules[0].trim(),
                        LocalDateTime.parse(times[0].trim(), formatter),
                        LocalDateTime.parse(times[1].substring(0, times[1].length()-1).trim(),formatter));
                if(taskTokens[1].trim().equals("1")){
                    event.doMark();
                }
                tasks.add(event);
            }
        }

        return tasks;

    }
}
