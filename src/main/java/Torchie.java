import java.util.Arrays;
import java.util.Scanner;
public class Torchie {

    private static String[] textStore = new String[100];
    private static int textStoreSpace = 0; // keep track of next null space in array
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Torchie!");
        System.out.println("What can I do for you?");
//        System.out.println("Let's play copytorchie today! You say something and I ll copy!");
        System.out.println("Let's play storetorchie today! You say something and I ll store it!");

        String input;

        do {
            input = scanner.nextLine();
            if (input.equals("list")){
                String[] list = Arrays.copyOf(getTextStore(),textStoreSpace);
                showList(list);
            }else if (!input.equals("bye")){
                setTextStore(input);
                System.out.println("added: " + input);
            }
        } while (!input.equals("bye"));

        System.out.println("Awww bye :( Let's play again soon!");
    }

    public static String[] getTextStore() {
        return textStore;
    }

    public static void setTextStore(String text) {
        textStore[textStoreSpace] = text;
        textStoreSpace += 1;
    }

    public static void showList(String[] list){
        // list is an array containing no null items
        for (int i=0; i<list.length; i++){
            System.out.println(( (i+1) + ". " + list[i]));
        }
    }
}
