package duke.task;

import duke.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void mark(int taskIndex) { //marking task as done
        Utils.printDivider();
        System.out.println("Nice! I've marked this task as done:\n");
        Task task = list.get(taskIndex - 1);
        task.markAsDone();
        printTask(task);
        Utils.printDivider();
        saveToFile();
    }

    int Number_Of_Task = 0;
    public void addToList(String taskType, Task task) { //adding a new task into todolist
        list.add(task);
        Number_Of_Task += 1;
        Utils.printDivider();
        if (taskType.equals("T") || taskType.equals("D") || taskType.equals("E") ) {
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
        } else {
            Utils.echo("added: " + task);
        }
        System.out.println("Now you have " + Number_Of_Task + " tasks in the list.");
        saveToFile();
        Utils.printDivider();
    }

    public void deleteTask(int taskNumber) {
        Task taskToDelete = list.get(taskNumber - 1);
        Utils.printDivider();
        list.remove(taskNumber - 1);
        Number_Of_Task -= 1;
        Utils.echo("Noted. I've removed this task:");
        printTask(taskToDelete);
        Utils.printDivider();
        saveToFile();
    }

    public void unmark(int taskIndex) { //unmarking a task
        Utils.printDivider();
        System.out.println("OK, I've marked this task as not done yet:\n");
        Task task = list.get(taskIndex - 1);
        task.markAsUndone();
        printTask(task);
        Utils.printDivider();
        saveToFile();
    }

    private void printTask(Task task) { //printing the task
        System.out.println(task.toString() + "\n");
    }

    public void printList() { //printing the current to do list
        Utils.printDivider();
        System.out.println("Here are the tasks in your list:\n");
        int counter = 1;
        for (Task task : list) {
            System.out.print(counter + ".");
            printTask(task);
            counter += 1;
        }
        Utils.printDivider();
    }

    private void saveToFile() {
        //saving the current todolist into the json file if it exists, else create new json file
        // the object to write to file
        JSONArray jsonArray = new JSONArray();

        // populate json array
        for (Task task : list) {
            jsonArray.put(task.toJSONObject());
        }

        // try to write to .json file
        try {
            FileWriter file = new FileWriter("./list.json");
            JSONObject jo = new JSONObject();
            jo.put("savedToDoList", jsonArray);
            file.write(jo.toString());
            file.close();

        } catch (IOException e) {
            System.out.println("Something is wrong when writing to file.");
        }
    }

    public void initialiseToDoList() { //Will read existing to do list from json if it exists, else a new list is created
        try {
            FileReader fileReader = new FileReader("./list.json");
            JSONTokener tokener = new JSONTokener(fileReader);
            JSONObject jo = new JSONObject(tokener);

            JSONArray savedData = jo.getJSONArray("savedToDoList");
            for (int i = 0; i < savedData.length(); i++) { //running throught the jsonarray "savedToDoList" for previously stored data of to do list
                JSONObject jsonObject = savedData.getJSONObject(i);

                String taskType = jsonObject.getString("taskType");
                String taskName = jsonObject.getString("taskName");
                boolean isDone = jsonObject.getBoolean("isDone");

                switch (taskType) { //adding to the list based on the task type
                    case "T":
                        list.add(new ToDos(taskName, isDone));

                        break;
                    case "D":
                        String dueDate = jsonObject.getString("dueDate");
                        list.add(new Deadlines(taskName, dueDate, isDone));

                        break;
                    case "E":
                        String from = jsonObject.getString("from");
                        String end = jsonObject.getString("end");
                        list.add(new Events(taskName, from, end, isDone));

                        break;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("No existing file, creating new");
        } catch (JSONException e) {
            System.out.println("New User, creating new to do list");
        }
    }
}
