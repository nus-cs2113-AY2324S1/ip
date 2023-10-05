package luke.tasks;

import java.util.ArrayList;
import luke.user.LukeTimeError;

public class TaskList{
    //contains the task list e.g., it has operations to add/delete tasks in the list

    private ArrayList<Task> mainTaskList;
    public int numberOfTasks;
    public TaskList(ArrayList<Task> thetasks) throws LukeTimeError {
        mainTaskList = new ArrayList<Task>();
        numberOfTasks = 0;
        for (Task item: thetasks) {
            addTask(item);
        }
    }

    public TaskList() {

    }

    public void addTask(Task taskName) {
        mainTaskList.add(taskName);
        //System.out.println(numbers);
        numberOfTasks += 1;
    }

    public void removeTask(int taskNumber) {
        mainTaskList.remove(taskNumber);
        //System.out.println(numbers);
        numberOfTasks -= 1;
    }

    public int size() {
        return numberOfTasks;
    }

    public Task get(int TaskNumber) throws IndexOutOfBoundsException {
        return mainTaskList.get(TaskNumber);

    }

    public ArrayList<Task> getMainTaskList() {
        return mainTaskList;
    }
}
