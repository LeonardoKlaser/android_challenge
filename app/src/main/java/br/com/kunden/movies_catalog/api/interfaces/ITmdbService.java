package br.com.kunden.movies_catalog.api.interfaces;

import br.com.kunden.movies_catalog.dto.MovieDetailResponse;
import br.com.kunden.movies_catalog.dto.MovieSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ITmdbService {
    @GET("search/movie")
    Call<MovieSearchResponse> searchMovies(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );

    @GET("movie/{movie_id}")
    Call<MovieDetailResponse> getMovieDetails(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
