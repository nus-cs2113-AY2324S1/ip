package taskmanagement;


import java.util.ArrayList;


public class TaskList {
    public ArrayList<Task> listItems;
    public TaskList(){
        this.listItems = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> items){
        this.listItems=items;
    }

}
