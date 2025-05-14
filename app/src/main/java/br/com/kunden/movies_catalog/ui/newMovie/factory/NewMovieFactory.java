package br.com.kunden.movies_catalog.ui.newMovie.factory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.kunden.movies_catalog.ui.newMovie.NewMovieViewModel;

public class NewMovieFactory implements ViewModelProvider.Factory {
    private final Context context;

    public NewMovieFactory(Context context){
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new NewMovieViewModel(context);
    }
}
