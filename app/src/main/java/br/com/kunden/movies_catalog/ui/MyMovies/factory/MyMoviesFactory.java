package br.com.kunden.movies_catalog.ui.MyMovies.factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.kunden.movies_catalog.ui.MyMovies.MyMoviesViewModel;

public class MyMoviesFactory implements ViewModelProvider.Factory {
    private final Context _context;

    public MyMoviesFactory(Context context){
        this._context = context.getApplicationContext();
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MyMoviesViewModel(_context);
    }
}
