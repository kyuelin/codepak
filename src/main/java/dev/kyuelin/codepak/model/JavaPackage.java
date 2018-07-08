package dev.kyuelin.codepak.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JavaPackage {
    public String getPackageName() {
        return packageName;
    }

    private String packageName;

    public List<JavaClass> getJavaClasses() {
        return javaClasses;
    }

    private List<JavaClass> javaClasses = new ArrayList<>();

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setJavaClasses(List<JavaClass> javaClasses) {
        this.javaClasses = javaClasses;
    }

    public JavaPackage (final String packageName) {
        this.packageName=packageName;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
