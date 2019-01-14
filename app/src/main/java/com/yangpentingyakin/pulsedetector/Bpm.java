package com.yangpentingyakin.pulsedetector;

public class Bpm {
    private int id;
    private String Bpm;
    private String Time;
    public Bpm()
    {
    }
    public Bpm(int id,String Bpm,String Time)
    {
        this.id=id;
        this.Bpm=Bpm;
        this.Time=Time;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setBpm(String Bpm) {
        this.Bpm = Bpm;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
    public int getId() {
        return id;
    }
    public String getTime() {
        return Time;
    }
    public String getBpm() {
        return Bpm;
    }
}

