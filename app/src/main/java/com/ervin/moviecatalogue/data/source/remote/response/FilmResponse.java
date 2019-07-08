package com.ervin.moviecatalogue.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FilmResponse implements Parcelable {
    private String filmID;
    private String filmName;
    private List<String> fimGenre;
    private String filmImage;
    private String filmBackdrop;
    private String filmRating;
    private Boolean bookmarked = false;
    private String filmDirector;
    private String filmSinopsis;
    private String filmDuration;

    public FilmResponse(String filmID, String filmName, List<String> fimGenre, String filmImage, String filmBackdrop, String filmRating, String filmSinopsis) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.fimGenre = fimGenre;
        this.filmImage = filmImage;
        this.filmBackdrop = filmBackdrop;
        this.filmRating = filmRating;
        this.filmSinopsis = filmSinopsis;
    }



    public FilmResponse(String filmID, String filmName, List<String> fimGenre, String filmImage, String filmRating) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.fimGenre = fimGenre;
        this.filmImage = filmImage;
        this.filmRating = filmRating;
    }

    protected FilmResponse(Parcel in) {
        filmID = in.readString();
        filmName = in.readString();
        fimGenre = in.createStringArrayList();
        filmImage = in.readString();
        filmBackdrop = in.readString();
        filmRating = in.readString();
        byte tmpBookmarked = in.readByte();
        bookmarked = tmpBookmarked == 0 ? null : tmpBookmarked == 1;
        filmDirector = in.readString();
        filmSinopsis = in.readString();
        filmDuration = in.readString();
    }

    public static final Creator<FilmResponse> CREATOR = new Creator<FilmResponse>() {
        @Override
        public FilmResponse createFromParcel(Parcel in) {
            return new FilmResponse(in);
        }

        @Override
        public FilmResponse[] newArray(int size) {
            return new FilmResponse[size];
        }
    };

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

    public String getFilmImage() {
        return filmImage;
    }

    public String getFilmBackdrop() {
        return filmBackdrop;
    }

    public String getFilmRating() {
        return filmRating;
    }


    public String getFilmSinopsis() {
        return filmSinopsis;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(filmID);
        parcel.writeString(filmName);
        parcel.writeStringList(fimGenre);
        parcel.writeString(filmImage);
        parcel.writeString(filmBackdrop);
        parcel.writeString(filmRating);
        parcel.writeByte((byte) (bookmarked == null ? 0 : bookmarked ? 1 : 2));
        parcel.writeString(filmDirector);
        parcel.writeString(filmSinopsis);
        parcel.writeString(filmDuration);
    }
}
