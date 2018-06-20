package nyc.c4q.deckofcards;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import nyc.c4q.deckofcards.data.CardsApiResponse;
import nyc.c4q.deckofcards.data.ShuffleModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.deckofcards.data.Cards;
import nyc.c4q.deckofcards.data.CardsApiService;
import nyc.c4q.deckofcards.presenter.CardsRVAdapter;
import retrofit2.converter.gson.GsonConverterFactory;

import static nyc.c4q.deckofcards.data.CardsApiService.BASE_URL;

public class CardActivity extends AppCompatActivity {
    Button shuffle, draw;
    static String shuffleId;
    int cardsRemaining;
    EditText numInput;
    TextView remainderTV;
    private RecyclerView recyclerView;
    private Retrofit retrofit;
    CardsRVAdapter cardsRvAdapter;
    CardsApiService cardsApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        shuffle = (Button) findViewById(R.id.shuffle_button);
        draw = (Button) findViewById(R.id.draw_cards_button);
        remainderTV = (TextView) findViewById(R.id.cards_remaining_tv);
        numInput = (EditText) findViewById(R.id.cards_et);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardsApiService = retrofit.create(CardsApiService.class);
                Call<ShuffleModel> call = cardsApiService.getShuffledCards();
                call.enqueue(new Callback<ShuffleModel>() {

                    @Override
                    public void onResponse(Call<ShuffleModel> call, Response<ShuffleModel> response) {
                        shuffleId = response.body().getDeck_id();
                        cardsRemaining = response.body().getRemaining();
                        remainderTV.setText("Cards Remaining " + cardsRemaining);

                        Log.d("DECK ID", shuffleId);
                        hideKeypad();

                    }

                    @Override
                    public void onFailure(Call<ShuffleModel> call, Throwable t) {
                        t.printStackTrace();

                    }
                });
            }
        });

        draw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (numInput.getText().toString().isEmpty()) {
                    numInput.setError("You must draw atleast 1 card");
                }


                int i = Integer.parseInt(numInput.getText().toString());


                if (i < 1) {
                    numInput.setError("You must draw atleast 1 card");
                } else if (numInput.getText().toString().isEmpty()) {
                    numInput.setError("You must draw atleast 1 card");
                } else {
                    cardsApiService = retrofit.create(CardsApiService.class);
                    Call<CardsApiResponse> call = cardsApiService.getCards(shuffleId, i);

                    call.enqueue(new Callback<CardsApiResponse>() {
                        @Override
                        public void onResponse(Call<CardsApiResponse> call, Response<CardsApiResponse> response) {
                            List<Cards> refreshedCards = response.body().getCards();
                            cardsRvAdapter.refreshCards(refreshedCards);
                            cardsRvAdapter.notifyDataSetChanged();
                            numInput.getText().clear();
                            hideKeypad();
                        }

                        @Override
                        public void onFailure(Call<CardsApiResponse> call, Throwable t) {
                            t.printStackTrace();

                        }
                    });
                }
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
    public void hideKeypad() {
        InputMethodManager inputManager =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }
}
