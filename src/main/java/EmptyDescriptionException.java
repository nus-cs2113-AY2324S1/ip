public class EmptyDescriptionException extends Exception{
    public void printErrorMessage(){
        System.out.println("    _____________________________________"
                + "\n    Hey, you forgot your task's name or time!!\n"
                + "    _____________________________________");
    }
    public EmptyDescriptionException() {
    }
}
