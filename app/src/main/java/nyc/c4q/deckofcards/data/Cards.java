package nyc.c4q.deckofcards.data;

public class Cards {
    private String suit;
    private CardImagesModel images;
    private String image;
    private String code;
    private String value;

    public Cards(String suit, CardImagesModel images, String image, String code, String value) {
        this.suit = suit;
        this.images = images;
        this.image = image;
        this.code = code;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public CardImagesModel getImages() {
        return images;
    }

    public void setImages(CardImagesModel images) {
        this.images = images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}