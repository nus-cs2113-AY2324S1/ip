package classes;
import java.util.ArrayList;

import task.Task;

public class TaskList {
	private static ArrayList<Task> tasks;
	
	public TaskList() {
		tasks = new ArrayList<Task>();
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public Task deleteTask(int index) {
		return tasks.remove(index);
	}
	
	public void setTaskStatus(Integer index, boolean status) {
		tasks.get(index).setDone(status);
	}
	
	public Task getTask(int index) {
		return tasks.get(index);
	}
	
	public boolean isEmpty() {
		return tasks.isEmpty();
	}
	
	public Integer size() {
		return tasks.size();
	}
	
	public ArrayList<Task> findTasks(String keyword) {
		ArrayList<Task> matchingTasks = new ArrayList<>();
		for (Task t: tasks) {
			if (t.getDescription().contains(keyword)) {
				matchingTasks.add(t);
			}
		}
		return matchingTasks;
	}
}
