package com.ervin.moviecatalogue.model;

public class CastingModel {
    private String CastingName;
    private String CastingAs;

    public CastingModel(String castingName, String castingAs) {
        CastingName = castingName;
        CastingAs = castingAs;
    }

    public String getCastingName() {
        return CastingName;
    }

    public void setCastingName(String castingName) {
        CastingName = castingName;
    }

    public String getCastingAs() {
        return CastingAs;
    }

    public void setCastingAs(String castingAs) {
        CastingAs = castingAs;
    }
}
