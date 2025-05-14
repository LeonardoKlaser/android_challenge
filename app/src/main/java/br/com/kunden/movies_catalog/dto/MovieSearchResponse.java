package br.com.kunden.movies_catalog.dto;

import java.util.List;

public class MovieSearchResponse {
    public int page;
    public List<MovieResult> results;
    public int total_results;
    public int total_pages;

    public List<MovieResult> getResults() {
        return results;
    }

    public void setResults(List<MovieResult> results) {
        this.results = results;
    }
}
