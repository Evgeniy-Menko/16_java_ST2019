package by.menko.finalproject.entity;

public class Music extends Disk {
    private String singer;
    private String albom;

    public String getSinger() {
        return singer;
    }

    public Music setSinger(String singer) {
        this.singer = singer;
        return this;
    }

    public String getAlbom() {
        return albom;
    }

    public Music setAlbom(String albom) {
        this.albom = albom;
        return this;
    }
}
