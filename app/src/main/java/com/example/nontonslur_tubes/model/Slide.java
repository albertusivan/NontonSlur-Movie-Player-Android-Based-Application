package com.example.nontonslur_tubes.model;

public class Slide {

    private int Image;
    private String Title;
    private String Desc;

    public Slide(int image, String title, String desc) {
        Image = image;
        Title = title;
        Desc = desc;
    }

    public int getImage() {
        return Image;
    }

    public String getTitle() {
        return Title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
