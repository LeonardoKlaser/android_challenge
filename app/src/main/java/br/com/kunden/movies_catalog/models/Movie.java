package br.com.kunden.movies_catalog.models;

import java.io.Serializable;
import java.util.Locale;

public class Movie implements Serializable {
    private String title;
    private String year;
    private String genre;
    private String descricao;
    private String image;
    private Integer ID;

    //region getSetters
    public Integer getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImage(String image) {
        this.image = image;
    }
    //endregion

    public Movie(String title, String year, String genre, String descricao){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.descricao = descricao;
    }

    public Movie(int ID, String title, String year, String genre, String descricao){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.descricao = descricao;
        this.ID = ID;
    }

    public Movie(String title, String year, String genre, String descricao, String Image){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.descricao = descricao;
        this.image = Image;
    }

    public Movie(int ID, String title, String year, String genre, String descricao, String Image){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.descricao = descricao;
        this.image = Image;
        this.ID = ID;
    }

    public boolean anyMatch(String searchText){
        String key = searchText.toLowerCase(Locale.getDefault());
        if (title.toLowerCase().contains(key) ||
            year.toLowerCase().contains(key) ||
            genre.toLowerCase().contains(key) ||
            descricao.toLowerCase().contains(key)){
            return true;
        }
        return false;
    }

}
