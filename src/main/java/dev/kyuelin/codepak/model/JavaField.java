package dev.kyuelin.codepak.model;

import com.google.gson.Gson;

public class JavaField {
    private String fieldName;

    public void setFieldName(final String fieldName) {
        this.fieldName=fieldName;
    }

    public JavaField(final String fieldName) {
        this.fieldName=fieldName;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);

    }
}
