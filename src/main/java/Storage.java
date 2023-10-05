import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* Storage Class that read & save txt file*/
/* If no file exist, create new one*/
public class Storage {

    protected String path;

    public Storage(String path){
        this.path = path;
    }

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
                // delete "(by:" and ")"
                Deadline deadline = new Deadline(schedules[0].trim(),
                        schedules[1].substring(0, schedules[1].length()-1).trim());
                if(taskTokens[1].trim().equals("1")){
                    deadline.doMark();
                }
                tasks.add(deadline);
            } else{
                // task (from: t to: t)
                String[] schedules = taskTokens[2].split(" \\(from: ");
                String[] times = schedules[1].split(" to: ");
                Event event = new Event(schedules[0].trim(), times[0].trim(),
                        times[1].substring(0, times[1].length()-1).trim());
                if(taskTokens[1].trim().equals("1")){
                    event.doMark();
                }
                tasks.add(event);
            }
        }

        return tasks;

    }
}
