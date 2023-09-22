import java.util.Scanner;
public class Frank {
    public static void main(String[] args) {

        final String SOLIDLINE = "\n------------------------------------------------------------------------------------------------------------------------------\n";
        final String LOGO =
                "FFFFFFFFFFFFFFFFFFFFFFRRRRRRRRRRRRRRRRR                  AAA               NNNNNNNN        NNNNNNNNKKKKKKKKK    KKKKKKK" + System.lineSeparator() +
                "F::::::::::::::::::::FR::::::::::::::::R                A:::A              N:::::::N       N::::::NK:::::::K    K:::::K" + System.lineSeparator() +
                "F::::::::::::::::::::FR::::::RRRRRR:::::R              A:::::A             N::::::::N      N::::::NK:::::::K    K:::::K" + System.lineSeparator() +
                "FF::::::FFFFFFFFF::::FRR:::::R     R:::::R            A:::::::A            N:::::::::N     N::::::NK:::::::K   K::::::K" + System.lineSeparator() +
                "F:::::F       FFFFFF  R::::R     R:::::R           A:::::::::A           N::::::::::N    N::::::NKK::::::K  K:::::KKK" + System.lineSeparator() +
                "F:::::F               R::::R     R:::::R          A:::::A:::::A          N:::::::::::N   N::::::N  K:::::K K:::::K" + System.lineSeparator() +
                "F::::::FFFFFFFFFF     R::::RRRRRR:::::R          A:::::A A:::::A         N:::::::N::::N  N::::::N  K::::::K:::::K" + System.lineSeparator() +
                "F:::::::::::::::F     R:::::::::::::RR          A:::::A   A:::::A        N::::::N N::::N N::::::N  K:::::::::::K" + System.lineSeparator() +
                "F:::::::::::::::F     R::::RRRRRR:::::R        A:::::A     A:::::A       N::::::N  N::::N:::::::N  K:::::::::::K" + System.lineSeparator() +
                "F::::::FFFFFFFFFF     R::::R     R:::::R      A:::::AAAAAAAAA:::::A      N::::::N   N:::::::::::N  K::::::K:::::K" + System.lineSeparator() +
                "F:::::F               R::::R     R:::::R     A:::::::::::::::::::::A     N::::::N    N::::::::::N  K:::::K K:::::K" + System.lineSeparator() +
                "F:::::F               R::::R     R:::::R    A:::::AAAAAAAAAAAAA:::::A    N::::::N     N:::::::::NKK::::::K  K:::::KKK" + System.lineSeparator() +
                "FF:::::::FF           RR:::::R     R:::::R   A:::::A             A:::::A   N::::::N      N::::::::NK:::::::K   K::::::K" + System.lineSeparator() +
                "F::::::::FF           R::::::R     R:::::R  A:::::A               A:::::A  N::::::N       N:::::::NK:::::::K    K:::::K" + System.lineSeparator() +
                "F::::::::FF           R::::::R     R:::::R A:::::A                 A:::::A N::::::N        N::::::NK:::::::K    K:::::K" + System.lineSeparator() +
                "FFFFFFFFFFF           RRRRRRRR     RRRRRRRAAAAAAA                   AAAAAAANNNNNNNN         NNNNNNNKKKKKKKKK    KKKKKKK ";

        System.out.println(SOLIDLINE + LOGO + SOLIDLINE);
        System.out.println("Hello user, I'm FRANK! Nice to meet you!\n" + SOLIDLINE);

        Command command;
        TaskList tasks = new TaskList();
        while(true){
            try {
                command = CommandParser.getCommand();
                assert command != null;
                command.execute(tasks);
            } catch(FrankException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
