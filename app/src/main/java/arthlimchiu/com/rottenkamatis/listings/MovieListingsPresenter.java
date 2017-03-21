package arthlimchiu.com.rottenkamatis.listings;

import java.util.List;

import arthlimchiu.com.rottenkamatis.data.Movie;
import arthlimchiu.com.rottenkamatis.data.MoviesDataSource;
import arthlimchiu.com.rottenkamatis.data.MoviesRepository;

/**
 * Created by arthlimchiu on 23/02/2017.
 */

public class MovieListingsPresenter {

    private MovieListingsView view;

    private MoviesRepository moviesRepository;

    public MovieListingsPresenter(MovieListingsView view, MoviesRepository moviesRepository) {
        this.view = view;
        this.moviesRepository = moviesRepository;
    }

    public void getMovies() {
        moviesRepository.getMovies(new MoviesDataSource.GetMoviesCallback() {
            @Override
            public void onMoviesFetched(List<Movie> movies) {
                view.showMovies(movies);
            }

            @Override
            public void onMoviesNotAvailable() {
                view.showEmptyError();
            }
        });
    }
}
