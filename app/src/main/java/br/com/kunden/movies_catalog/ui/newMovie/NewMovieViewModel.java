package br.com.kunden.movies_catalog.ui.newMovie;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import br.com.kunden.movies_catalog.models.Movie;
import br.com.kunden.movies_catalog.presenters.MoviePresenter;

public class NewMovieViewModel extends ViewModel {

    private final MoviePresenter moviePresenter;

    public NewMovieViewModel(Context context) {
        moviePresenter = new MoviePresenter(context);
    }

    public long insertMovie(Movie movie){
        return moviePresenter.insertMovie(movie);
    }
//    public LiveData<String> getText() {
//        return mText;
//    }
}