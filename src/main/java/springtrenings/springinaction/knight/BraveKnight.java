package springtrenings.springinaction.knight;

/**
 * Created by Vitek000 on 23.08.2016.
 */
public class BraveKnight implements Knight{

    private Quest quest;

    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }
}
