package arthlimchiu.com.rottenkamatis.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import arthlimchiu.com.rottenkamatis.data.Movie;
import arthlimchiu.com.rottenkamatis.data.MoviesDataSource;

/**
 * Created by arthlimchiu on 21/03/2017.
 */

public class MoviesLocalDataSource implements MoviesDataSource {

    private static MoviesLocalDataSource INSTANCE = null;

    private MoviesDBHelper moviesDBHelper;

    private MoviesLocalDataSource(Context context) {
        moviesDBHelper = new MoviesDBHelper(context);
    }

    public static MoviesLocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new MoviesLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void saveMovie(Movie movie) {
        SQLiteDatabase db = moviesDBHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MoviesTable.COLUMN_TITLE, movie.getTitle());
        cv.put(MoviesTable.COLUMN_POSTER_URL, movie.getPosterPath());

        db.insert(MoviesTable.TABLE_NAME, null, cv);

        db.close();
    }

    @Override
    public void deleteAllMovies() {
        SQLiteDatabase db = moviesDBHelper.getWritableDatabase();

        db.delete(MoviesTable.TABLE_NAME, null, null);

        db.close();
    }

    @Override
    public void getMovies(GetMoviesCallback mGetMoviesCallback) {
        List<Movie> movies = new ArrayList<>();
        SQLiteDatabase db = moviesDBHelper.getReadableDatabase();

        String[] projection = {
                MoviesTable.COLUMN_ID,
                MoviesTable.COLUMN_TITLE,
                MoviesTable.COLUMN_POSTER_URL
        };

        Cursor c = db.query(MoviesTable.TABLE_NAME, projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                long id = c.getLong(c.getColumnIndex(MoviesTable.COLUMN_ID));
                String title = c.getString(c.getColumnIndex(MoviesTable.COLUMN_TITLE));
                String posterUrl = c.getString(c.getColumnIndex(MoviesTable.COLUMN_POSTER_URL));

                Movie movie = new Movie(id, title, posterUrl);
                movies.add(movie);
            }
        }

        if (c != null) {
            c.close();
        }

        db.close();

        if (movies.isEmpty()) {
            mGetMoviesCallback.onMoviesNotAvailable();
        } else {
            mGetMoviesCallback.onMoviesFetched(movies);
        }
    }
}
