import java.util.Scanner;

public class Echo {
    private String bye;
    private String text;


public Echo() {
    bye ="bye";
}

public void echoBack(){
    Scanner in = new Scanner(System.in);
    text = in.nextLine();
    if(text.equals(bye)){
        System.out.println("Bye. Hope to see you again soom!");
    }
    else {
        System.out.println(text);
        echoBack();

    }
}


}
