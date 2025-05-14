package br.com.kunden.movies_catalog.api.services;

import br.com.kunden.movies_catalog.api.interfaces.ITmdbService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TdmbService {
    private final ITmdbService api;

    public TdmbService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.api = retrofit.create(ITmdbService.class);
    }

    public ITmdbService getApi() {
        return api;
    }
}
