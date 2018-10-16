package com.evados.geelyapps.model;

import android.graphics.drawable.Drawable;

public class AppModel {
    private String name;
    private String packageName;
    private Drawable icon;

    public AppModel(String name, String packageName, Drawable icon) {
        this.name = name;
        this.packageName = packageName;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getIcon() {
        return icon;
    }
}
