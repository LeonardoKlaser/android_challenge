package br.com.kunden.movies_catalog.api.controllers;

import br.com.kunden.movies_catalog.api.services.TdmbService;
import br.com.kunden.movies_catalog.dto.MovieDetailResponse;
import br.com.kunden.movies_catalog.dto.MovieSearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TdmbController {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "5dee46b53591fa6c01636c09e1df6d51";

    private final TdmbService _service;

    public TdmbController(TdmbService _service){
        this._service = _service;
    }

    public void searchMovieByTitle(String title, Callback<MovieSearchResponse> callback){
        _service.getApi().searchMovies(API_KEY, title).enqueue(callback);
    }

    public void getMovieGenres(int movieId, Callback<MovieDetailResponse> callback) {
        _service.getApi().getMovieDetails(movieId, API_KEY, "en-US").enqueue(callback);
    }
}
