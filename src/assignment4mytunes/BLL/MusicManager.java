/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.BLL;

import assignment4mytunes.BE.Music;
import assignment4mytunes.DAL.MusicHandler;
import java.util.ArrayList;

/**
 *
 * @author Mecaa
 */
public class MusicManager {

    private MusicHandler musicHandler = new MusicHandler();

    public void saveMusic(Music song) {
        musicHandler.saveMusic(song);
        System.out.println("Not yet implemented");
    }

    public ArrayList<Music> loadAllMusic() {
        return musicHandler.loadAllMusic();
    }
}
