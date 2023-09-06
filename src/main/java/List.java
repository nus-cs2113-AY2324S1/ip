public class List{
    private String[] tasks;
    private boolean[] isDone;
    private int numItems;

    public List(){
        tasks = new String[100];
        isDone = new boolean[100];
        numItems = 0;

        for(int i = 0; i < 100; i++){
            isDone[i] = false;
        }
    }

    public void add(String arg){
        System.out.println("added: " + arg);
        tasks[numItems] = arg;
        numItems++;
    }

    public void mark(int i){
        //Check if integer value is in range
        if(i < 1 || i > numItems){
            System.out.println("Please enter an integer index within range");
            return;
        }

        isDone[i - 1] = true;
        System.out.println("Marked as done:");
        System.out.println(tasks[i - 1]);
        show();
    }

    public void unmark(int i){
        //Check if integer value is in range
        if(i < 1 || i > numItems){
            System.out.println("Please enter an integer index within range");
            return;
        }

        isDone[i - 1] = false;
        System.out.println("Marked as not done yet:");
        System.out.println(tasks[i - 1]);
        show();
    }

    public void show(){
        for(int i = 0; i < numItems; i++){
            System.out.print(i + 1);
            System.out.print(".[");

            if(isDone[i]){
                System.out.print("X");
            }
            else{
                System.out.print(" ");
            }

            System.out.print("] ");
            System.out.println(tasks[i]);
        }
    }
}
