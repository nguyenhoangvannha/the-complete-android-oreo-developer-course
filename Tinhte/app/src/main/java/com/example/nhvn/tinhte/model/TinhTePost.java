package com.example.nhvn.tinhte.model;

import android.graphics.Bitmap;

/**
 * Created by nguye on 1/30/2018.
 */

public class TinhTePost {
    private String url;
    private Bitmap postImage;
    private Bitmap avatarImage;
    private String owner;
    private String time;
    private String collect;
    private String title;

    public TinhTePost() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TinhTePost(String url, Bitmap postImage, Bitmap avatarImage, String owner, String time, String collect, String title) {
        this.url = url;

        this.postImage = postImage;
        this.avatarImage = avatarImage;
        this.owner = owner;
        this.time = time;
        this.collect = collect;
        this.title = title;
    }

    public Bitmap getPostImage() {
        return postImage;
    }

    public void setPostImage(Bitmap postImage) {
        this.postImage = postImage;
    }

    public Bitmap getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(Bitmap avatarImage) {
        this.avatarImage = avatarImage;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
