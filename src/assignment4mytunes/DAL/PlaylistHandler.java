/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.DAL;

import assignment4mytunes.BE.Music;
import assignment4mytunes.BE.Playlist;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 *
 * @author gudla
 */
public class PlaylistHandler
{

    private String fileName;

    public PlaylistHandler()
      {

      }

    public void savePlaylist(Playlist p)
      {
        p.getName();
        fileName = p.getName() + ".pll";    
        File file = new File("DATA/Playlist/"+fileName);
          System.out.println(file.getAbsolutePath());
        if(!file.exists())
          {
            try
              {
                file.createNewFile();
              } catch (IOException ex)
              {
                Logger.getLogger(PlaylistHandler.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        savePlaylistMethod(p, file);

      }

    public ArrayList<Playlist> loadPlaylist()
      {
        throw new UnsupportedOperationException("Gudlaug should drown herself in a puddle of water");
      }

    public void savePlaylistMethod(Playlist p, File file)
      {
        ArrayList<Music> songs = p.getPlaylist();

       
        String plstString = "";
        if(songs!=null){
        for (Music song : songs)
          {
            plstString += song.getTitle()
                    + ","
                    + song.getArtist()
                    + ","
                    + song.getGenre()
                    + ","
                    + song.getPath()
                    + String.format("%n");
          }
        }

        try (BufferedWriter bw
                = new BufferedWriter(
                        new FileWriter(file.getAbsoluteFile())))
          {
            bw.write(plstString);
          } 
        catch (IOException ex)
          {
            java.util.logging.Logger.getLogger(PlaylistHandler.class.getName()).log(Level.SEVERE, null, ex);
          }

      }
}
