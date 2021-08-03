package com.example.myapplication.media;

public class ResDTO {
    private AnimeInfoDTO anime_info;
    private int atime;
    private String author;
    private String type;
    private boolean recommend;
    private String id;
    private String title;

    public AnimeInfoDTO getAnime_info() {
        return anime_info;
    }

    public void setAnime_info(AnimeInfoDTO anime_info) {
        this.anime_info = anime_info;
    }

    public int getAtime() {
        return atime;
    }

    public void setAtime(int atime) {
        this.atime = atime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
