package arthlimchiu.com.rottenkamatis.data;

import java.util.List;

/**
 * Created by arthlimchiu on 21/03/2017.
 */

public class MoviesRepository implements MoviesDataSource {

    private static MoviesRepository INSTANCE = null;

    private final MoviesDataSource moviesLocalDataSource;

    private final MoviesDataSource moviesRemoteDataSource;

    private MoviesRepository(MoviesDataSource moviesLocalDataSource, MoviesDataSource moviesRemoteDataSource) {
        this.moviesLocalDataSource = moviesLocalDataSource;
        this.moviesRemoteDataSource = moviesRemoteDataSource;
    }

    public static MoviesRepository getInstance(MoviesDataSource moviesLocalDataSource, MoviesDataSource moviesRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MoviesRepository(moviesLocalDataSource, moviesRemoteDataSource);
        }
        return INSTANCE;
    }

    public void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getMovies(final GetMoviesCallback mGetMoviesCallback) {
        moviesRemoteDataSource.getMovies(new GetMoviesCallback() {
            @Override
            public void onMoviesFetched(List<Movie> movies) {
                refreshLocalDataSource(movies);
                mGetMoviesCallback.onMoviesFetched(movies);
            }

            @Override
            public void onMoviesNotAvailable() {
                getMoviesFromLocalDataSource(mGetMoviesCallback);
            }
        });
    }

    @Override
    public void saveMovie(Movie movie) {

    }

    @Override
    public void deleteAllMovies() {

    }

    private void refreshLocalDataSource(List<Movie> movies) {
        moviesLocalDataSource.deleteAllMovies();
        for (Movie movie : movies) {
            moviesLocalDataSource.saveMovie(movie);
        }
    }

    private void getMoviesFromLocalDataSource(final GetMoviesCallback mGetMoviesCallback) {
        moviesLocalDataSource.getMovies(new GetMoviesCallback() {
            @Override
            public void onMoviesFetched(List<Movie> movies) {
                mGetMoviesCallback.onMoviesFetched(movies);
            }

            @Override
            public void onMoviesNotAvailable() {
                mGetMoviesCallback.onMoviesNotAvailable();
            }
        });
    }
}
