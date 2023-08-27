import java.util.Scanner;

    public class Echo {
        private String bye;
        private String text;

    public Echo() {
        bye ="bye";

    }

    toDoList newList  = new toDoList();

    public void echoBack(){

        Scanner in = new Scanner(System.in);
        text = in.nextLine();
        if(text.equals(bye)){
            System.out.println("Bye. Hope to see you again soom!");
        }
        else if(text.equals(newList.checker)){
            newList.listTask();
            echoBack();
        }
        else if(text.startsWith(newList.marker)){
            newList.mark(text);
            echoBack();
        }
        else if(text.startsWith(newList.unmarked)){
            newList.unmark(text);
            echoBack();
        }
        else {
           newList. addTask(text);
           echoBack();

        }
    }
}
