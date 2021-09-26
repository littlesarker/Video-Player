package com.example.mrsplayer;

import android.graphics.Bitmap;

public class VideoRModel {

    private  String VideoName;
    private  String videoPath;
    private Bitmap thumbNail;

    public VideoRModel(String videoName, String videoPath, Bitmap thumbNail) {
        this.VideoName = videoName;
        this.videoPath = videoPath;
        this.thumbNail = thumbNail;
    }

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String videoName) {
        VideoName = videoName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Bitmap getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(Bitmap thumbNail) {
        this.thumbNail = thumbNail;
    }
}
