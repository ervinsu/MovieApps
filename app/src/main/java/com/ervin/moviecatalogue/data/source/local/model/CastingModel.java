package com.ervin.moviecatalogue.data.source.local.model;

public class CastingModel {
    private String CastingID;
    private String CastingName;
    private String CastingPhoto;
    private String CastingAs;

    public CastingModel(String castingID, String castingName, String castingAs, String CastingPhoto) {
        this.CastingName = castingName;
        this.CastingID = castingID;
        this.CastingAs = castingAs;
        this.CastingPhoto  = CastingPhoto;
    }

    public String getCastingPhoto() {
        return CastingPhoto;
    }

    public String getCastingName() {
        return CastingName;
    }

    public String getCastingAs() {
        return CastingAs;
    }

}
