package org.kakrot.tiktok.model;

import android.os.Bundle;

public class TabItemModel {

    private CharSequence title;
    private String clazz;
    private Bundle args;

    public TabItemModel(CharSequence title, String clazz, Bundle args) {
        this.title = title;
        this.clazz = clazz;
        this.args = args;
    }

    public CharSequence getTitle() {
        return title;
    }

    public String getClazz() {
        return clazz;
    }

    public Bundle getArgs() {
        return args;
    }
}
