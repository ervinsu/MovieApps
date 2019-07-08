package com.ervin.moviecatalogue.data.source.local.model;

import java.util.List;

public class FilmModel {
    private String filmID;
    private String filmName;
    private List<String> fimGenre;
    private String filmImage;
    private String filmBackdrop;
    private String filmRating;
    private String filmSinopsis;
    private String filmDuration;

    public FilmModel(String filmID, String filmName, List<String> fimGenre, String filmImage, String filmRating) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.fimGenre = fimGenre;
        this.filmImage = filmImage;
        this.filmRating = filmRating;
    }

    public FilmModel(String filmID, String filmName, List<String> fimGenre, String filmImage, String filmRating, String filmDuration, String filmSinopsis){
        this.filmID = filmID;
        this.filmName = filmName;
        this.fimGenre = fimGenre;
        this.filmImage = filmImage;
        this.filmRating = filmRating;
        this.filmDuration = filmDuration;
        this.filmSinopsis = filmSinopsis;
    }

    public FilmModel(String filmID, List<String> fimGenre, String filmName, String filmImage, String filmBackdrop, String filmRating, String filmSinopsis) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.fimGenre = fimGenre;
        this.filmImage = filmImage;
        this.filmBackdrop = filmBackdrop;
        this.filmRating = filmRating;
        this.filmSinopsis = filmSinopsis;
    }

    public String getFilmBackdrop() {
        return filmBackdrop;
    }

   public String getFilmID() {
        return filmID;
    }

    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }

    public String getFilmName() {
        return filmName;
    }

   public List<String> getFimGenre() {
        return fimGenre;
    }

    public void setFimGenre(List<String> fimGenre) {
        this.fimGenre = fimGenre;
    }

    public String getFilmImage() {
        return filmImage;
    }

    public String getFilmRating() {
        return filmRating;
    }

   public String getFilmSinopsis() {
        return filmSinopsis;
    }

   public String getFilmDuration() {
        return filmDuration;
    }

}
