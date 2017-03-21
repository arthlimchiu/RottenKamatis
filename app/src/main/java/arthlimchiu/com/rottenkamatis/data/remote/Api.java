package arthlimchiu.com.rottenkamatis.data.remote;

import arthlimchiu.com.rottenkamatis.data.RootMovie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by arthlimchiu on 21/03/2017.
 */

public interface Api {

//    ?language=en&sort_by=popularity.desc&api_key=3cf8d3fe303c4d3c4859fa7f85d090eb")
    @GET("3/discover/movie")
    Call<RootMovie> getMovies(
            @Query("language") String language,
            @Query("sort_by") String sortBy,
            @Query("api_key") String apiKey
    );
}
