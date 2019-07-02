package com.ervin.moviecatalogue.model;

import java.util.List;

public class FilmModel {
    private String filmID;
    private String filmName;
    private List<String> fimGenre;
    private List<CastingModel> filmCasters;
    private int filmImage;
    private String filmRating;
    private Boolean bookmarked = false;
    private String filmDirector;
    private String filmSinopsis;
    private String filmDuration;

    public FilmModel(String filmID, String filmName, List<String> fimGenre, int filmImage, String filmRating, String filmDuration, String filmSinopsis){
        this.filmID = filmID;
        this.filmName = filmName;
        this.fimGenre = fimGenre;
        this.filmImage = filmImage;
        this.filmRating = filmRating;
        this.filmDuration = filmDuration;
        this.filmSinopsis = filmSinopsis;
    }

    public FilmModel(String filmID, String filmName, List<String> fimGenre, List<CastingModel> filmCasters, int filmImage, String filmRating, Boolean bookmarked, String filmDirector, String filmSinopsis, String filmDuration) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.fimGenre = fimGenre;
        this.filmCasters = filmCasters;
        this.filmImage = filmImage;
        this.filmRating = filmRating;
        if (bookmarked != null) {
            this.bookmarked = bookmarked;
        }
        this.filmDirector = filmDirector;
        this.filmSinopsis = filmSinopsis;
        this.filmDuration = filmDuration;
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

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public List<String> getFimGenre() {
        return fimGenre;
    }

    public void setFimGenre(List<String> fimGenre) {
        this.fimGenre = fimGenre;
    }

    public List<CastingModel> getFilmCasters() {
        return filmCasters;
    }

    public void setFilmCasters(List<CastingModel> filmCasters) {
        this.filmCasters = filmCasters;
    }

    public int getFilmImage() {
        return filmImage;
    }

    public void setFilmImage(int filmImage) {
        this.filmImage = filmImage;
    }

    public String getFilmRating() {
        return filmRating;
    }

    public void setFilmRating(String filmRating) {
        this.filmRating = filmRating;
    }

    public Boolean getBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(Boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public String getFilmDirector() {
        return filmDirector;
    }

    public void setFilmDirector(String filmDirector) {
        this.filmDirector = filmDirector;
    }

    public String getFilmSinopsis() {
        return filmSinopsis;
    }

    public void setFilmSinopsis(String filmSinopsis) {
        this.filmSinopsis = filmSinopsis;
    }

    public String getFilmDuration() {
        return filmDuration;
    }

    public void setFilmDuration(String filmDuration) {
        this.filmDuration = filmDuration;
    }
}
