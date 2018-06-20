package nyc.c4q.deckofcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import nyc.c4q.deckofcards.data.CardsApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;

import nyc.c4q.deckofcards.data.Cards;
import nyc.c4q.deckofcards.data.CardsApiService;
import nyc.c4q.deckofcards.presenter.CardsRVAdapter;
import retrofit2.converter.gson.GsonConverterFactory;

import static nyc.c4q.deckofcards.data.CardsApiService.BASE_URL;

public class CardActivity extends AppCompatActivity {
    private Button shuffle, draw;
    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private CardsRVAdapter cardsRvAdapter;
    private CardsApiService cardsApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        shuffle = (Button) findViewById(R.id.shuffle_button);
        draw = (Button) findViewById(R.id.draw_cards_button);
        recyclerView =  (RecyclerView) findViewById(R.id.recycler_view);

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            cardsApiService = retrofit.create(CardsApiService.class);
                Call<CardsApiResponse> call = cardsApiService.getShuffledCards();
                call.enqueue(new Callback<CardsApiResponse>() {
                    @Override
                    public void onResponse(Call<CardsApiResponse> call, Response<CardsApiResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<CardsApiResponse> call, Throwable t) {

                    }
                });
            }
        });
        setRecyclerView();
        setRetrofit();
    }

    public void setRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        cardsRvAdapter = new CardsRVAdapter(new ArrayList<Cards>());
        recyclerView.setAdapter(cardsRvAdapter);

    }
    public void setRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
