public class Ui {
	
	public void greetUser() {
    	 printLines();
    	 System.out.println("\n"
    	 		+ "    __________                      \n"
    	 		+ "    \\______   \\ ____   _____ ___.__.\n"
    	 		+ "     |       _// __ \\ /     <   |  |\n"
    	 		+ "     |    |   \\  ___/|  Y Y  \\___  |\n"
    	 		+ "     |____|_  /\\___  >__|_|  / ____|\n"
    	 		+ "            \\/     \\/      \\/\\/     \n"
    	 		+ "");
         System.out.println("    Hello! I'm Remy");
         System.out.println("    What can I do for you?");
         printLines();
	}
    
	public static void printLines() {
    	System.out.println("    ____________________________________________________________");
    }
	
	public void sayGoodbye() {
    	printLines();
        System.out.println("    Bye. Hope to see you again soon!");
        printLines();
    }
}
