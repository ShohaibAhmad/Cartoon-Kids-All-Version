package com.promoteprovider.cartoonkids.Models;

public class CartoonModel {
    String duration,imageUrl,playUrl,title,videoUrl;

    public CartoonModel() {
    }

    public CartoonModel(String duration, String imageUrl, String playUrl, String title, String videoUrl) {
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.playUrl = playUrl;
        this.title = title;
        this.videoUrl = videoUrl;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
