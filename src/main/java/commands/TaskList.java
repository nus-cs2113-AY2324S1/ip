package commands;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasksList){
        this.tasks = tasksList;
    }

    public Task get(int index){
        return tasks.get(index);
    }

    public void delete(int index){
        tasks.remove(index);
    }

    public void add(Task task){
        tasks.add(task);
    }

    public int size(){
        return tasks.size();
    }



    /**
     * Prints a list of tasks with their status icons and descriptions.
     *
     */

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for(Task task: tasks){
            System.out.println(
                    count + "." + "[" + task.getType() + "]" + "[" + task.getStatusIcon() + "]" + task);
            count += 1;
        }
    }

}
