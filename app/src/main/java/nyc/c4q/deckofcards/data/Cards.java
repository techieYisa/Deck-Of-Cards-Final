package nyc.c4q.deckofcards.data;

public class Cards {
    private String image, value, suit, code;

    public Cards(String image, String value, String suit, String code) {
        this.image = image;
        this.value = value;
        this.suit = suit;
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
