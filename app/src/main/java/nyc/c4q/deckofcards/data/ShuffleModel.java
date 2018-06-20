package nyc.c4q.deckofcards.data;

public class ShuffleModel {
    private boolean success;
    private String deck_id;
    private boolean shuffled;
    private int remaining;

    public boolean isSuccess() {
        return success;
    }
    public String getDeck_id() {
        return deck_id;
    }
    public boolean isShuffled() {
        return shuffled;
    }
    public int getRemaining() {
        return remaining;
    }
}

