/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Model;

import assignment4mytunes.BE.Music;
import assignment4mytunes.BE.Playlist;
import assignment4mytunes.BLL.GenreManager;
import assignment4mytunes.BLL.MusicManager;
import assignment4mytunes.BLL.PlaylistManager;
import assignment4mytunes.GUI.Controller.MainViewController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mecaa
 */
public final class MusicModel {

    private static final MusicModel musicModel = new MusicModel();
    private GenreManager genreManager = new GenreManager();
    private PlaylistManager pManager = new PlaylistManager();
    private MusicManager musicManager = new MusicManager();
    private MainViewController mainView = null;

    private final ObservableList<Music> thisMusic;
    private final ObservableList<Playlist> thisPlaylist;

    private MusicModel() {
        thisMusic = FXCollections.observableArrayList();
        try {

            thisMusic.addAll(musicManager.loadAllMusic());
        } catch (IOException ex) {
            Logger.getLogger(MusicModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        thisPlaylist = FXCollections.observableArrayList();
        try {
            thisPlaylist.addAll(pManager.loadAllPlaylists());

        } catch (IOException ex) {
            Logger.getLogger(MusicModel.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public void saveSongs(Music song) {
        musicManager.saveMusic(song);
        thisMusic.add(song);
    }

    public ArrayList<Music> loadAllSongs() throws IOException {
        return musicManager.loadAllMusic();

    }

    public ArrayList<Playlist> loadAllPlaylists() throws IOException {
        return pManager.loadAllPlaylists();
    }

    public void savePlaylist(Playlist p) {
        pManager.newPlaylist(p);
        System.out.println("Trying to save");
    }

    public MainViewController getMainView() {
        return mainView;
    }

    public void setMainView(MainViewController mainView) {
        this.mainView = mainView;
    }

    public void removeSong(Music music) {

        musicManager.removeMusic(music);

        thisMusic.remove(music);

    }

    public void removePlaylist(Playlist playlist) {
        pManager.removePlaylist(playlist);

    }

    public void renamePlaylist(Playlist playlist) {
        pManager.renamePlaylist(playlist);
    }

    public ObservableList<Music> getAllSongs() {
        return thisMusic;
    }

    public ObservableList<Playlist> getAllPlaylist() {
        return thisPlaylist;
    }

}
