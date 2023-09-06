public class List{
    private String[] tasks;
    private int numItems;

    public List(){
        tasks = new String[100];
        numItems = 0;
    }

    public void add(String arg){
        System.out.println("added: " + arg);
        tasks[numItems] = arg;
        numItems++;
    }

    public void show(){
        for(int i = 0; i < numItems; i++){
            System.out.print(i + 1);
            System.out.println(". " + tasks[i]);
        }
    }
}
