package dev.kyuelin.codepak.model;

import com.google.gson.Gson;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JavaClass {


    private String className;

    public String getPackageName() {
        return packageName;
    }

    private String packageName;
    private List<JavaMethod> methods = new ArrayList<>();

    public List<JavaField> getFields() {
        return fields;
    }

    private List<JavaField> fields = new ArrayList<>();

    public void setClassName(final String className) {
        this.className = className;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public void setMethods(final List<JavaMethod> methods) {
        this.methods = methods;
    }

    public void setFields(final List<JavaField> fields) {
        this.fields = fields;
    }

    public JavaClass(final String className) {
        this.className=className;

    }

    @Override
    public String toString() {
        return new Gson().toJson(this);

    }

    public List<JavaMethod> getMethods() {
        return methods;
    }
}