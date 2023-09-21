package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static int FIRST_INDEX=0;
    public static int SECOND_INDEX=1;
    public static int THIRD_INDEX=2;
    public static int FOURTH_INDEX=3;
    public static int FIFTH_INDEX=4;
    protected File dataFile;
    protected String filePath;
    protected FileWriter fw;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.dataFile = new File(filePath);
    }

    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> TASKS = new ArrayList<Task>();
        if (this.dataFile.createNewFile()){
            System.out.println("Data file not found @ " + this.filePath +
                    "\nCreating new data file @ " + this.filePath);
        }else{
            Scanner dataScanner = new Scanner(dataFile);
            while(dataScanner.hasNext()){
                String dataLine =  dataScanner.nextLine();
                String[] splitLineArguments = dataLine.split(" \\| ");
                switch (splitLineArguments[FIRST_INDEX]) {
                    case "T":
                        ToDo newTodo = new ToDo(splitLineArguments[THIRD_INDEX]);
                        TASKS.add(newTodo);
                        if (splitLineArguments[SECOND_INDEX].equals("1")){
                            newTodo.markAsDone();
                        }
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(splitLineArguments[THIRD_INDEX],
                                splitLineArguments[FOURTH_INDEX]);
                        TASKS.add(newDeadline);
                        if (splitLineArguments[SECOND_INDEX].equals("1")){
                            newDeadline.markAsDone();
                        }
                        break;
                    case "E":
                        Event newEvent = new Event(splitLineArguments[THIRD_INDEX], splitLineArguments[FOURTH_INDEX],
                                splitLineArguments[FIFTH_INDEX]);
                        TASKS.add(newEvent);
                        if (splitLineArguments[SECOND_INDEX].equals("1")){
                            newEvent.markAsDone();
                        }
                        break;
                    default:
                        throw new DukeException("Corrupt data bro..");
                }
            }
            System.out.println("Loaded historical data successfully.");
        }
        return TASKS;
    }

    public void save(TaskList TASKS) throws IOException, DukeException{
        FileWriter FW = new FileWriter(this.filePath);
        ArrayList<Task> AL = TASKS.getArrayList();
        for (Task taskToSave : AL){
            String taskSaveFormat = String.format("%s | %d | " , taskToSave.getTaskType(),
                    taskToSave.getCompletionStatus() ? 1:0);
            if (taskToSave.getTaskType().equals("T")){
                ToDo todoToSave = (ToDo) taskToSave;
                taskSaveFormat =  String.format("%s%s\n",taskSaveFormat,todoToSave.description);
            } else if (taskToSave.getTaskType().equals("D")) {
                Deadline deadlineToSave = (Deadline) taskToSave;
                taskSaveFormat = String.format("%s%s | %s\n",taskSaveFormat,deadlineToSave.description,
                        deadlineToSave.getBy());
            } else {
                Event eventToSave = (Event) taskToSave;
                taskSaveFormat = String.format("%s%s | %s | %s\n", taskSaveFormat, eventToSave.description,
                        eventToSave.getStart(), eventToSave.getEnd());
            }
            FW.write(taskSaveFormat);
        }
        FW.close();
    }
}
