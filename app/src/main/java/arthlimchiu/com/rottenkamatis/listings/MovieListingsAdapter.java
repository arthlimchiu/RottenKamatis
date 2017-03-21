package arthlimchiu.com.rottenkamatis.listings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import arthlimchiu.com.rottenkamatis.R;
import arthlimchiu.com.rottenkamatis.data.Movie;

/**
 * Created by arthlimchiu on 21/03/2017.
 */

public class MovieListingsAdapter extends RecyclerView.Adapter<MovieListingsAdapter.ViewHolder> {

    List<Movie> movies;
    Context context;

    public MovieListingsAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(movies.get(position).getTitle());
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500" + movies.get(position).getPosterPath())
                .placeholder(android.R.drawable.btn_star)
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView title;

        public ViewHolder(View root) {
            super(root);
            poster = (ImageView) root.findViewById(R.id.poster);
            title = (TextView) root.findViewById(R.id.title);
        }
    }
}
