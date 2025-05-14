package br.com.kunden.movies_catalog.dto;

import java.util.List;

public class MovieDetailResponse {
    private int id;
    private String title;
    private String overview;
    private String release_date;
    private List<Genre> genres;

    // Getters e setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getOverview() { return overview; }
    public void setOverview(String overview) { this.overview = overview; }

    public String getRelease_date() { return release_date; }
    public void setRelease_date(String release_date) { this.release_date = release_date; }

    public List<Genre> getGenres() { return genres; }
    public void setGenres(List<Genre> genres) { this.genres = genres; }

    public static class Genre {
        private int id;
        private String name;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
