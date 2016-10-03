package springtrenings.springinaction.knight;

/**
 * Created by Vitek000 on 23.08.2016.
 */

import java.io.PrintStream;

public class SlayDragonQuest implements Quest {
    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    public void embark() {
        stream.println("Embarking on quest to slay the dragon!");
    }
}