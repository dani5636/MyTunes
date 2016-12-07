/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Model;

import assignment4mytunes.BE.Music;
import assignment4mytunes.BE.Playlist;
import assignment4mytunes.BLL.GenreManager;
import assignment4mytunes.BLL.PlaylistManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mecaa
 */
public final class MusicModel {

    private static final MusicModel musicModel = new MusicModel();
    private GenreManager genreManager = new GenreManager();
    private PlaylistManager playlistManager = new PlaylistManager();
    private PlaylistManager pManager = new PlaylistManager();

    private MusicModel() {

    }

    public static MusicModel getMusicModel() {
        return musicModel;
    }

    public ArrayList<String> loadGenre() throws IOException {
        return (ArrayList<String>) genreManager.loadGenres();
    }

    public void saveGenre(List<String> genres) throws IOException {
        genreManager.saveGenres(genres);
    }

    public void saveSongs() {
        System.out.println("new songs not implemented!");
    }

    public ArrayList<Music> loadSongs() {
        System.out.println("loading songs not implemented!");
        return null;
    }

    public void newPlaylist(String name) {
        pManager.newPlaylist(new Playlist(name));
    }
}
