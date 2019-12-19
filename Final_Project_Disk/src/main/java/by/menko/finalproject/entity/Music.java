package by.menko.finalproject.entity;

public class Music extends Disk {
    /**
     * singer.
     */
    private String singer;
    /**
     * albom.
     */
    private String albom;

    public String getSinger() {
        return singer;
    }

    public void setSinger(final String singer) {
        this.singer = singer;
    }

    public String getAlbom() {
        return albom;
    }

    public void setAlbom(final String albom) {
        this.albom = albom;

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Music music = (Music) o;

        if (!singer.equals(music.singer)) return false;
        return albom.equals(music.albom);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (singer != null ? singer.hashCode() : 0);
        result = 31 * result + (albom != null ? albom.hashCode() : 0);
        return result;
    }
}
