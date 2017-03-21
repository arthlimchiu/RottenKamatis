package arthlimchiu.com.rottenkamatis.listings;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import arthlimchiu.com.rottenkamatis.data.Movie;
import arthlimchiu.com.rottenkamatis.data.MoviesDataSource;
import arthlimchiu.com.rottenkamatis.data.MoviesRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by arthlimchiu on 23/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieListingsPresenterTest {

    private MovieListingsPresenter presenter;

    @Mock
    private MovieListingsView view;

    @Mock
    private MoviesRepository moviesRepository;

    @Mock
    List<Movie> movies;

    @Captor
    private ArgumentCaptor<MoviesDataSource.GetMoviesCallback> mGetMoviesCallbackCaptor;

    @Before
    public void setUp() throws Exception {
        presenter = new MovieListingsPresenter(view, moviesRepository);
    }

    @Test
    public void onMoviesFetchSuccessShouldShowMovies() throws Exception {
        presenter.getMovies();

        verify(moviesRepository).getMovies(mGetMoviesCallbackCaptor.capture());
        mGetMoviesCallbackCaptor.getValue().onMoviesFetched(movies);

        verify(view).showMovies(movies);
    }
}