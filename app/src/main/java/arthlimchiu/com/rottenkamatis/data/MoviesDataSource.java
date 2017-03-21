package arthlimchiu.com.rottenkamatis.data;

import java.util.List;

/**
 * Created by arthlimchiu on 21/03/2017.
 */

public interface MoviesDataSource {

    void saveMovie(Movie movie);

    void deleteAllMovies();

    interface GetMoviesCallback {
        void onMoviesFetched(List<Movie> movies);

        void onMoviesNotAvailable();
    }

    void getMovies(GetMoviesCallback mGetMoviesCallback);
}
