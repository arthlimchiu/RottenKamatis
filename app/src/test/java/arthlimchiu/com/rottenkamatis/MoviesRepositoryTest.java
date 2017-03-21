package arthlimchiu.com.rottenkamatis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import arthlimchiu.com.rottenkamatis.data.MoviesDataSource;
import arthlimchiu.com.rottenkamatis.data.MoviesRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by arthlimchiu on 21/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviesRepositoryTest {

    private MoviesRepository mMoviesRepository;

    @Mock
    private MoviesDataSource mMoviesLocalDataSource;

    @Mock
    private MoviesDataSource mMoviesRemoteDataSource;

    @Mock
    private MoviesDataSource.GetMoviesCallback mGetMoviesCallback;

    @Captor
    private ArgumentCaptor<MoviesDataSource.GetMoviesCallback> mGetMoviesCallbackCaptor;

    @Before
    public void setUp() throws Exception {
        mMoviesRepository = MoviesRepository.getInstance(mMoviesLocalDataSource, mMoviesRemoteDataSource);
    }

    @Test
    public void getMoviesFromRemoteDataSource() throws Exception {
        mMoviesRepository.getMovies(mGetMoviesCallback);

        verify(mMoviesRemoteDataSource).getMovies(any(MoviesDataSource.GetMoviesCallback.class));
    }

    @Test
    public void getMoviesFromLocalDataSourceWhenNetworkIsNotAvailable() throws Exception {
        mMoviesRepository.getMovies(mGetMoviesCallback);

        verify(mMoviesRemoteDataSource).getMovies(mGetMoviesCallbackCaptor.capture());
        mGetMoviesCallbackCaptor.getValue().onMoviesNotAvailable();

        verify(mMoviesLocalDataSource).getMovies(any(MoviesDataSource.GetMoviesCallback.class));
    }

    @After
    public void tearDown() throws Exception {
        mMoviesRepository.destroyInstance();

    }
}