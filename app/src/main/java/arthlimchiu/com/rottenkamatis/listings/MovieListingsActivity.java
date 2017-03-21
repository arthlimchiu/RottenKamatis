package arthlimchiu.com.rottenkamatis.listings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import arthlimchiu.com.rottenkamatis.R;
import arthlimchiu.com.rottenkamatis.data.Movie;
import arthlimchiu.com.rottenkamatis.data.MoviesRepository;
import arthlimchiu.com.rottenkamatis.data.local.MoviesLocalDataSource;
import arthlimchiu.com.rottenkamatis.data.remote.MoviesRemoteDataSource;

public class MovieListingsActivity extends AppCompatActivity implements MovieListingsView {

    private MovieListingsPresenter mMovieListingsPresenter;

    private RecyclerView movieList;
    private MovieListingsAdapter adapter;
//    private List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_listings);
        movieList = (RecyclerView) findViewById(R.id.movies);

        mMovieListingsPresenter = new MovieListingsPresenter(this, MoviesRepository.getInstance(MoviesLocalDataSource.getInstance(this), MoviesRemoteDataSource.getInstance()));
        mMovieListingsPresenter.getMovies();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showMovies(List<Movie> movies) {
        movieList.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new MovieListingsAdapter(movies);
        movieList.setAdapter(adapter);
    }

    @Override
    public void showEmptyError() {
        Toast.makeText(this, "No movies found", Toast.LENGTH_LONG).show();
    }
}
