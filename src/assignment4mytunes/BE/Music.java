/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.BE;

/**
 *
 * @author Mecaa
 */
public class Music {

    private String path, genre, artist, title;
    private String time;

    public Music(String title, String artist, String genre, String path, String time) {
        this.path = path;
        this.genre = genre;
        this.artist = artist;
        this.time = time;
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAllMusicStringInfo() {
        return title + " " + artist + " " + genre;
    }
}
