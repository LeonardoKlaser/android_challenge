package br.com.kunden.movies_catalog.dto;

import java.util.List;

public class MovieResult {
    public int id;
    public String title;
    public String overview;
    public String release_date;
    public String poster_path;
    public List<Integer> genre_ids;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setRelease_date(String date){
        this.release_date = date;
    }

    public void setPoster_path(String path){
        this.poster_path = path;
    }
}
