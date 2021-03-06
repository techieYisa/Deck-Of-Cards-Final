package nyc.c4q.deckofcards.data;

import java.util.List;

public class CardsApiResponse {
    private boolean success, shuffled;
    private String deck_id;
    private int remaining;
    private List<Cards> cards;


    public CardsApiResponse(boolean success, boolean shuffled, String deck_id, int remaining, List<Cards> cards) {
        this.success = success;
        this.shuffled = shuffled;
        this.deck_id = deck_id;
        this.remaining = remaining;
        this.cards = cards;

    }

    public boolean isShuffled() {
        return shuffled;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public int getRemaining() {
        return remaining;
    }

    public List<Cards> getCards() {
        return cards;
    }
}

