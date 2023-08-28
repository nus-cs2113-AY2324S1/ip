public class TaskList {
    private String[] tasks = new String[100];
    private int taskCount = 0;

    public void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    public String[] getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void deleteTask(int taskNumber) {
        tasks[taskNumber - 1] = null;
        taskCount--;
    }

    
}
