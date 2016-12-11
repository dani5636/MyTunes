/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Controller;

import assignment4mytunes.BE.Music;
import assignment4mytunes.BE.Playlist;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author gudla
 */
public class DeleteViewController implements Initializable
{

    
    private Playlist thisPlaylist;
    private Music thisMusic;
    @FXML
    private Label lblPlaylist;
    @FXML
    private Label lblSongs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        
        // TODO
      }   

    
    
    
      

    public void setMusicLable(Music music)
      {
        thisMusic = music;
        lblSongs.setText(thisMusic.getTitle());
        
      }

    public void setPlaylistLable(Playlist playlist)
      {
        thisPlaylist = playlist;
        lblPlaylist.setText(thisPlaylist.getName());
        
  
      }
    

   
    
    
    
    
}
