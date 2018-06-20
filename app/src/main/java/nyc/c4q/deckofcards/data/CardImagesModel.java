package nyc.c4q.deckofcards.data;

public class CardImagesModel {
    private String png;
    private String svg;

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public CardImagesModel(String png, String svg) {
        this.png = png;
        this.svg = svg;

    }
}
