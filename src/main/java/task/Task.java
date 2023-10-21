package task;

import Oriento.Oriento;


public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     *
     * @param taskNo the index of the target task
     * @param taskCount total number of existing task in the list
     * @param list the target taskList (there will only be one list in this application).
     */
    public void setDone(int taskNo, int taskCount, Task[] list) {
        if ( (taskNo > taskCount ) || (taskNo <1) ){
            System.out.println("Oops! You don't have any task in this positions.");
        } else if(this.isDone){
            System.out.println("You have already completed the task.");
        } else{
            this.isDone = true;
            System.out.println("  Nice! I've marked this task as done:\n"
                    + "  [X] " + list[taskNo - 1].description);
        }
    }

    public void restoreIsDone(){
        this.isDone = true;
    }

    /**
     * similar to setDone method
     */
    public void setNotDone(int taskNo, int taskCount, Task[] list) {
        if ( (taskNo > taskCount ) || (taskNo <1) ){
            System.out.println("Oops! You don't have any task in this position.");
        } else if(!this.isDone){
            System.out.println("Oh, you haven't finished this yet.");
        } else{
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + "  [ ] " + list[taskNo - 1].description);
        }
    }

    /**
     * only use to update list array once an objected is deleted and removed
     * @param indexOfDelete target index
     * @return a new list object array
     */
    public static Task[] updatedTaskList(int indexOfDelete){
        Task[] newList = new  Task[100];
        int numOfCopy = Oriento.list.length - indexOfDelete - 1;
        System.arraycopy(Oriento.list, 0, newList, 0, indexOfDelete);
        System.arraycopy(Oriento.list, indexOfDelete + 1, newList, indexOfDelete, numOfCopy);
        return newList;
    }

    /**
     * This method will only read the global taskList
     * @return string contains all task data inside the global taskList
     */
    public static String getConcatenateTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Oriento.taskCount; i++) {
            String taskAppend = (i +1) + ". " +  Oriento.list[i].toString();
            stringBuilder.append(taskAppend).append("\n");
        }
        return stringBuilder.toString();
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
