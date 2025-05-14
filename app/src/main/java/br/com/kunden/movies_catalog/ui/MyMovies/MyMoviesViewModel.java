package br.com.kunden.movies_catalog.ui.MyMovies;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.kunden.movies_catalog.models.Movie;
import br.com.kunden.movies_catalog.presenters.MoviePresenter;

public class MyMoviesViewModel extends ViewModel {


    private final MutableLiveData<List<Movie>> movieList = new MutableLiveData<>();

    private final MutableLiveData<List<Movie>> movieListFiltered = new MutableLiveData<>();
    private List<Movie> allMovies = new ArrayList<>();
    private final MoviePresenter moviePresenter;

    public MyMoviesViewModel(Context context) {
        moviePresenter = new MoviePresenter(context);
        loadMovies();
    }

    public boolean deleteMovie(int ID){
        return moviePresenter.delete(ID);
    }
    public void loadMovies() {
        new Thread(() -> {
            try {
                List<Movie> movies = moviePresenter.getAll();
                movieList.postValue(movies);
                movieListFiltered.postValue(movies);
            } catch (SQLException e) {
                throw new Error(e.getMessage());
            }
        }).start();
    }

    public void filterMovies(String searchText) {
        List<Movie> source = movieList.getValue();
        if (source == null) return;

        List<Movie> filtered = new ArrayList<>();
        for (Movie movie : source) {
            if (movie.anyMatch(searchText)) {
                filtered.add(movie);
            }
        }
        movieListFiltered.postValue(filtered);
    }

    public LiveData<List<Movie>> getMovies() {
        return movieList;
    }
    public LiveData<List<Movie>> getFilteredMovies() {
        return movieListFiltered;
    }
}