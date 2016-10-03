package springtrenings.springinaction.knight;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Vitek000 on 23.08.2016.
 */
public class BraveKnightTest {
    @Test
    public void knightShouldEmbarkOnQuest() throws Exception {
        Quest mockQuest = mock(Quest.class);
        BraveKnight braveKnight = new BraveKnight(mockQuest);
        braveKnight.embarkOnQuest();
        verify(mockQuest, times(1)).embark();
    }

}