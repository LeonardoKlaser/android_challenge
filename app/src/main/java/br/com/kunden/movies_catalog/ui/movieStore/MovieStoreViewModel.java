package br.com.kunden.movies_catalog.ui.movieStore;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.kunden.movies_catalog.api.controllers.TdmbController;
import br.com.kunden.movies_catalog.api.services.TdmbService;
import br.com.kunden.movies_catalog.dto.MovieDetailResponse;
import br.com.kunden.movies_catalog.dto.MovieResult;
import br.com.kunden.movies_catalog.dto.MovieSearchResponse;
import br.com.kunden.movies_catalog.models.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieStoreViewModel extends ViewModel {

    TdmbService service;
    TdmbController controller;

    private final MutableLiveData<MovieSearchResponse> moviesList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MovieSearchResponse allMoviesCache;

    public MovieStoreViewModel() {
        service = new TdmbService();
        controller = new TdmbController(service);
        loadMovies();
    }

    public void loadMovies(){
        new Thread(() ->{

        }).start();
    }

    public void searchedMovies(String search){
        controller.searchMovieByTitle(search, new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    for (MovieResult movie : response.body().getResults()){
                       String year =  movie.getRelease_date().split("-")[0];
                       movie.setRelease_date(year);
                    }
                    moviesList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                errorMessage.postValue("Erro ao buscar filmes: " + t.getMessage());
            }
        });
    }

    public LiveData<List<MovieDetailResponse.Genre>> getGenreNames(int movieId){
       MutableLiveData<List<MovieDetailResponse.Genre>> movieDetailResponse = new MutableLiveData<>();
        controller.getMovieGenres(movieId, new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movieDetailResponse.postValue(response.body().getGenres());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                errorMessage.postValue("Erro ao buscar filmes: " + t.getMessage());
            }
        });

        return movieDetailResponse;
    }

    public LiveData<MovieSearchResponse> getMovies() {
        return moviesList;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

}