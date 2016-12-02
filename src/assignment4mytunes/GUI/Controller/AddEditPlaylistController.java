/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Controller;

import assignment4mytunes.BE.Playlist;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author gudla
 */
public class AddEditPlaylistController implements Initializable
{

    @FXML
    private TextField txtName;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    private Playlist playlist;
    

    
    public AddEditPlaylistController(Playlist playlist)
      {
        this.playlist = playlist;
      }

    public Playlist getPlaylist()
      {
        return playlist;
      }

    public AddEditPlaylistController()
      {
        
      }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        // TODO
      }    
    
}
