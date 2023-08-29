import java.util.Scanner;
public class Frank {
    public static void main(String[] args) {

        String solidLine = "\n------------------------------------------------------------------------------------------------------------------------------\n";
        String logo = """
                FFFFFFFFFFFFFFFFFFFFFFRRRRRRRRRRRRRRRRR                  AAA               NNNNNNNN        NNNNNNNNKKKKKKKKK    KKKKKKK
                F::::::::::::::::::::FR::::::::::::::::R                A:::A              N:::::::N       N::::::NK:::::::K    K:::::K
                F::::::::::::::::::::FR::::::RRRRRR:::::R              A:::::A             N::::::::N      N::::::NK:::::::K    K:::::K
                FF::::::FFFFFFFFF::::FRR:::::R     R:::::R            A:::::::A            N:::::::::N     N::::::NK:::::::K   K::::::K
                F:::::F       FFFFFF  R::::R     R:::::R           A:::::::::A           N::::::::::N    N::::::NKK::::::K  K:::::KKK
                F:::::F               R::::R     R:::::R          A:::::A:::::A          N:::::::::::N   N::::::N  K:::::K K:::::K
                F::::::FFFFFFFFFF     R::::RRRRRR:::::R          A:::::A A:::::A         N:::::::N::::N  N::::::N  K::::::K:::::K
                F:::::::::::::::F     R:::::::::::::RR          A:::::A   A:::::A        N::::::N N::::N N::::::N  K:::::::::::K
                F:::::::::::::::F     R::::RRRRRR:::::R        A:::::A     A:::::A       N::::::N  N::::N:::::::N  K:::::::::::K
                F::::::FFFFFFFFFF     R::::R     R:::::R      A:::::AAAAAAAAA:::::A      N::::::N   N:::::::::::N  K::::::K:::::K
                F:::::F               R::::R     R:::::R     A:::::::::::::::::::::A     N::::::N    N::::::::::N  K:::::K K:::::K
                F:::::F               R::::R     R:::::R    A:::::AAAAAAAAAAAAA:::::A    N::::::N     N:::::::::NKK::::::K  K:::::KKK
                FF:::::::FF           RR:::::R     R:::::R   A:::::A             A:::::A   N::::::N      N::::::::NK:::::::K   K::::::K
                F::::::::FF           R::::::R     R:::::R  A:::::A               A:::::A  N::::::N       N:::::::NK:::::::K    K:::::K
                F::::::::FF           R::::::R     R:::::R A:::::A                 A:::::A N::::::N        N::::::NK:::::::K    K:::::K
                FFFFFFFFFFF           RRRRRRRR     RRRRRRRAAAAAAA                   AAAAAAANNNNNNNN         NNNNNNNKKKKKKKKK    KKKKKKK""";

        System.out.println(solidLine + logo + solidLine);
        System.out.println("Hello user, I'm FRANK! Nice to meet you!\n" + solidLine);

        // Add, List
        TodoList user = new TodoList();
        Scanner input = new Scanner(System.in);
        String command;
        do{
            command = input.nextLine();
            switch (command) {
            case "bye":
                System.out.println(solidLine + "\nGoodbye User, zai jian yong ze, sampai jumpa lagi user \n" + solidLine);
                break;
            case "list":
                user.printTodos();
                break;
            default:
                user.addTodo(command);
                break;
            }
        } while(!command.equals("bye"));
    }
}
