package Commands;

public class Delete {


    public static void deleteTasks(String command) throws DukeException {
        int taskIndex = Integer.parseInt(command.substring(7))-1;
        if (taskIndex>=list.size()) {
            throw new DukeException("Invalid task item. Check item number again~");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(taskIndex));
        list.remove(taskIndex);
        System.out.println("Now you have " + (list.size()) + " tasks in the list.");
        System.out.println(line);
    }
}
