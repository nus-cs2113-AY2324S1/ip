import java.util.ArrayList;

public class ToDoList {
    private ArrayList<String> list;
    public ToDoList() {
        list = new ArrayList<>();
    }

    public void addToList(String task){
        list.add(task);
        Utils.printDivider();
        Utils.echo("added: " + task);
    }

    public void printList() {
        Utils.printDivider();
        int counter = 1;
        for (String item : list) {
            System.out.println(counter + "." + item + "\n");
            counter += 1;
        }
        Utils.printDivider();
    }
}
