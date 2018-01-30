package com.example.nhvn.guessthecelebrity;

/**
 * Created by nguye on 1/30/2018.
 */

public class Celebrity {
    String name;
    String url;

    public Celebrity() {
    }

    public Celebrity(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
