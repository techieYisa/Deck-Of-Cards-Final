package nyc.c4q.deckofcards.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CardsApiService {

    String BASE_URL = "http://deckofcardsapi.com/api/deck/";

    @GET("new/shuffle/")
    Call<CardsApiResponse> getShuffledCards();

    @GET("{deck_id}/draw/?count")
    Call<CardsApiResponse> getCards(@Path("deck_id") String cards, @Query("count") int numOfCards);
}
