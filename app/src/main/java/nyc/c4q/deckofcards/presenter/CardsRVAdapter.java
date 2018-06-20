package nyc.c4q.deckofcards.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nyc.c4q.deckofcards.R;
import nyc.c4q.deckofcards.data.Cards;

public class CardsRVAdapter extends RecyclerView.Adapter<CardsRVAdapter.CardsViewHolder>{
    List<Cards> cardList;

    public CardsRVAdapter(List<Cards> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_item_view, parent, false);
        return new CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int position) {
        holder.bindImage(cardList.get(position));

    }

    @Override
    public int getItemCount() { return cardList.size(); }

    public void refreshCards(List<Cards> cardMod){
            cardList.addAll(cardMod);
    }

    public class CardsViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardImageView;


        public CardsViewHolder(View itemView) {
            super(itemView);
            cardImageView = itemView.findViewById(R.id.card_image);
        }
        void bindImage(Cards cards) {
            Picasso.get().load(String.valueOf(cards)).into(cardImageView);
            cards.getValue();
            cards.getSuit();
        }
    }
}
