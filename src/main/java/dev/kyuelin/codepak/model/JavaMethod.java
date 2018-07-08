package dev.kyuelin.codepak.model;

import com.google.gson.Gson;

public class JavaMethod {
    private String methodName;

    public void setMethodName(final String methodName) {
        this.methodName=methodName;
    }

    public JavaMethod(final String methodName) {
        this.methodName=methodName;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
