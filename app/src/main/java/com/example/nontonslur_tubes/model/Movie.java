package com.example.nontonslur_tubes.model;

public class Movie {
    private String title;
    private String description;
    private int thumbnail;
    private String streamLink;
    private int coverPhoto;

    private String thumb;
    private String cover;

    public Movie(String title, String description, String thumbnail, String streamLink, String coverPhoto) {
        this.title = title;
        this.description = description;
        this.thumb = thumbnail;
        this.streamLink = streamLink;
        this.cover = coverPhoto;
    }

    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getThumbnail() {
        return thumbnail;
    }


    public String getStreamLink() {
        return streamLink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setStreamLink(String streamLink) {
        this.streamLink = streamLink;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getThumb() {
        return thumb;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }
}
