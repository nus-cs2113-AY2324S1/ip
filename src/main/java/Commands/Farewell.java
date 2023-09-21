package Commands;

import Data.TaskList;

import Ui.TextUi;
public class Farewell extends Command {
    private static final String[] farewellLines = {
            "Farewell, dear pawns. Until our paths cross again in the tapestry of time.",
            "The curtain falls, but remember, the show never truly ends.",
            "As shadows lengthen, it's time to bid adieu. Until fate conspires to reunite us.",
            "Ah, the thrill of departure, a prelude to our next devilish escapade.",
            "Take your leave, but tread carefully – the world outside is a canvas of treachery.",
            "May the echoes of our encounter resonate in your thoughts until destiny beckons anew.",
            "The tendrils of night stretch out, embracing you as you venture beyond my domain.",
            "Cherish the fleeting moments we've shared, for they are the embers of a fire we've stoked.",
            "Like whispers on the wind, I vanish into the shadows. Until next we cross paths...",
            "And so, the threads of our interaction fray, yet the tapestry remains forever changed."
    };
    public void execute(TaskList list, TextUi ui) {
        String randomFarewellLine = farewellLines[(int) (Math.random() * farewellLines.length)];
        ui.printText(randomFarewellLine);
    }
}
