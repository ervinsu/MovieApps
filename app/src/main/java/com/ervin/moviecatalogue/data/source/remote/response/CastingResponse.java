package com.ervin.moviecatalogue.data.source.remote.response;

public class CastingResponse {
    private String CastingID;
    private String CastingName;
    private String CastingPhoto;
    private String CastingAs;

    public CastingResponse(String castingID, String castingName, String castingPhoto, String castingAs) {
        CastingID = castingID;
        CastingName = castingName;
        CastingPhoto = castingPhoto;
        CastingAs = castingAs;
    }

    public String getCastingID() {
        return CastingID;
    }

    public String getCastingName() {
        return CastingName;
    }

    public String getCastingPhoto() {
        return CastingPhoto;
    }

    public String getCastingAs() {
        return CastingAs;
    }
}
