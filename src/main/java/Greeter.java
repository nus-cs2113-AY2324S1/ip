
public class Greeter {
    public static void main(String[] args) {
        String name = args[0];
        boolean family = false;
        if (args.length>2){
            family = true;
        }
        if (family){
            System.out.println("Dear "+name+" family");
        } else {
            if (args[1].equals("M")){
                System.out.println("Dear Mr. "+name);
            } else if (args[1].equals("F")){
                System.out.println("Dear Ms. "+name);
            }
        }
        
    }
}