/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.BE;

import java.util.ArrayList;

/**
 *
 * @author gudla
 */
public class Playlist
{
    private String name;
    private ArrayList<Music> playlist;

    public Playlist(String name)
      {
        this.name = name;
      }

    public String getName()
      {
        return name;
      }

    public void setName(String name)
      {
        this.name = name;
      }
    
    public void addSong(Music song)
      {
        playlist.add(song);
      }

    public ArrayList<Music> getPlaylist()
      {
        return playlist;
      }
    
}
