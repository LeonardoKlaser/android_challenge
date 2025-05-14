package br.com.kunden.movies_catalog.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.kunden.movies_catalog.R;
import br.com.kunden.movies_catalog.interfaces.IRecyclerView;
import br.com.kunden.movies_catalog.models.Movie;

public class MyMovieAdapter extends RecyclerView.Adapter<MyMovieAdapter.MyViewHolder> {

    private final IRecyclerView _recyclerViewInterface;
    private List<Movie> movieList;

    public MyMovieAdapter(IRecyclerView recyclerViewInterface) {
        this._recyclerViewInterface = recyclerViewInterface;
    }

    public void setMovieList(List<Movie> movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_movielist, parent, false);

        return new MyViewHolder(movieList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.titleView.setText(movie.getTitle());
        holder.genreView.setText(movie.getGenre());
        holder.yearView.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return movieList != null ?  movieList.size() : 0;
    }

    public Movie getMovieAt(int position){
        return movieList.get(position);
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        public TextView titleView, yearView, genreView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.adp_title);
            yearView = itemView.findViewById((R.id.adp_year));
            genreView = itemView.findViewById(R.id.adp_genre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(_recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            _recyclerViewInterface.OnItemClick(pos);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(_recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            _recyclerViewInterface.OnDeleteClick(pos);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(_recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            _recyclerViewInterface.OnEditClick(pos);
                        }
                    }
                }
            });
        }
    }
}
