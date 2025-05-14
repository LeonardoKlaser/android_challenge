package br.com.kunden.movies_catalog.presenters;


import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import br.com.kunden.movies_catalog.models.Movie;
import br.com.kunden.movies_catalog.repositories.MovieRepository;

public class MoviePresenter {
    private MovieRepository repository;

    public MoviePresenter(Context context){
        repository = new MovieRepository(context);
    }

    public long insertMovie(Movie movie){
        return repository.insert(movie);
    }

    public List<Movie> getAll() throws SQLException {
        return repository.list();
    }

    public boolean delete(int ID){
        return repository.delete(ID);
    }
    public boolean edit(Movie movie){
        return repository.edit(movie);
    }
}