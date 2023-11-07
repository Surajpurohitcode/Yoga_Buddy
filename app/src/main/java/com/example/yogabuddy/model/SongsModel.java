package com.example.yogabuddy.model;

public class SongsModel {
    String songTitle,songDescription,songTime;
    int songImg;

    public SongsModel(String songTitle, String songDescription, String songTime, int songImg) {
        this.songTitle = songTitle;
        this.songDescription = songDescription;
        this.songTime = songTime;
        this.songImg = songImg;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongDescription() {
        return songDescription;
    }

    public void setSongDescription(String songDescription) {
        this.songDescription = songDescription;
    }

    public String getSongTime() {
        return songTime;
    }

    public void setSongTime(String songTime) {
        this.songTime = songTime;
    }

    public int getSongImg() {
        return songImg;
    }

    public void setSongImg(int songImg) {
        this.songImg = songImg;
    }
}
