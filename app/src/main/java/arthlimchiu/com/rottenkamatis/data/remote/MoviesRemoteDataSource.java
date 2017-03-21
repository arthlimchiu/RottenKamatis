package arthlimchiu.com.rottenkamatis.data.remote;

import java.util.List;

import arthlimchiu.com.rottenkamatis.data.RootMovie;
import arthlimchiu.com.rottenkamatis.data.Movie;
import arthlimchiu.com.rottenkamatis.data.MoviesDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arthlimchiu on 21/03/2017.
 */

public class MoviesRemoteDataSource implements MoviesDataSource {

    private static MoviesRemoteDataSource INSTANCE = null;

    private MoviesRemoteDataSource() {
    }

    public static MoviesRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MoviesRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void saveMovie(Movie movie) {

    }

    @Override
    public void deleteAllMovies() {

    }

    @Override
    public void getMovies(final GetMoviesCallback mGetMoviesCallback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api;
        api = retrofit.create(Api.class);

        Call<RootMovie> call = api.getMovies("en", "popularity.desc", "3cf8d3fe303c4d3c4859fa7f85d090eb");
        call.enqueue(new Callback<RootMovie>() {
            @Override
            public void onResponse(Call<RootMovie> call, Response<RootMovie> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getMovies();
                    mGetMoviesCallback.onMoviesFetched(movies);
                } else {
                    mGetMoviesCallback.onMoviesNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<RootMovie> call, Throwable t) {
                mGetMoviesCallback.onMoviesNotAvailable();
            }
        });
    }
}
