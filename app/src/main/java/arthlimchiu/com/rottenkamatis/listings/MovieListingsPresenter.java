package arthlimchiu.com.rottenkamatis.listings;

import java.util.List;

import arthlimchiu.com.rottenkamatis.Movie;

/**
 * Created by arthlimchiu on 23/02/2017.
 */

public class MovieListingsPresenter {

    private MovieListingsView view;

    public MovieListingsPresenter(MovieListingsView view) {
        this.view = view;
    }

    public void onMoviesFetchSucces(List<Movie> movies) {
        view.showMovies(movies);
    }
}
