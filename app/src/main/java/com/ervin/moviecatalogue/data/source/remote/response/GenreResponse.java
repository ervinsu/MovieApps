package com.ervin.moviecatalogue.data.source.remote.response;

public class GenreResponse {
    private String genreID;
    private String genreName;

    public GenreResponse(String genreID, String genreName) {
        this.genreID = genreID;
        this.genreName = genreName;
    }

    public String getGenreID() {
        return genreID;
    }

    public String getGenreName() {
        return genreName;
    }
}
