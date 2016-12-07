/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.BLL;

import assignment4mytunes.BE.Playlist;
import assignment4mytunes.DAL.PlaylistHandler;
import java.util.ArrayList;

/**
 *
 * @author gudla
 */
public class PlaylistManager {

    private PlaylistHandler pHandler = new PlaylistHandler();

    public void newPlaylist(Playlist playlist) {
        pHandler.savePlaylist(playlist);
    }

    public ArrayList<Playlist> loadAllPlaylists() {
        return pHandler.loadPlaylist();
    }

}
