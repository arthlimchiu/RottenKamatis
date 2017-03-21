package arthlimchiu.com.rottenkamatis.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arthlimchiu on 21/03/2017.
 */

public class RootMovie {

    @SerializedName("results")
    @Expose
    public List<Movie> movies;

    public RootMovie() {
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
