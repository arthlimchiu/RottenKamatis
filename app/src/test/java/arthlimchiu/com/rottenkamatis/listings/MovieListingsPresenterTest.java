package arthlimchiu.com.rottenkamatis.listings;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import arthlimchiu.com.rottenkamatis.Movie;

import static org.mockito.Mockito.verify;

/**
 * Created by arthlimchiu on 23/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieListingsPresenterTest {

    private MovieListingsPresenter presenter;

    @Mock
    private MovieListingsView view;

    @Mock
    List<Movie> movies;

    @Before
    public void setUp() throws Exception {
        presenter = new MovieListingsPresenter(view);
    }

    @Test
    public void onMoviesFetchSuccessShouldShowMovies() throws Exception {
        presenter.onMoviesFetchSucces(movies);

        verify(view).showMovies(movies);
    }
}