package Objects;

public class DukeException {

    public static String outOfBoundsError(){
        return "index out of bounds, try again later.";
    }

    public static String invalidInputForType(String function){
        return String.format("Invalid input for %s only accepts integers \nFor example: '%s 1'",function,function);
    }

}
