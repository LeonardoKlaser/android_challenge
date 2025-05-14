package br.com.kunden.movies_catalog.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.kunden.movies_catalog.models.Movie;


public interface IMovieDAO {
    public long insert (Movie movie);
    public List<Movie> list() throws SQLException;
    public boolean delete(int ID);

    public boolean edit (Movie movie);

    public List<Movie> filteredMoviesList(String searchText) throws SQLException;
}
