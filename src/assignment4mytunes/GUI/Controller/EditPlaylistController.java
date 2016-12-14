/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Controller;

import assignment4mytunes.BE.Playlist;
import assignment4mytunes.GUI.Model.MusicModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gudla
 */
public class EditPlaylistController implements Initializable
{

    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtName;
    private Playlist namePlaylist;
    private MusicModel musicModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        // TODO
      }    
    
    public void setTxtName(Playlist playlist)
      {
        namePlaylist = playlist;
        txtName.setText(namePlaylist.getName());
      }

    

    @FXML
    private void clickSave(ActionEvent event)
      {
        musicModel = MusicModel.getMusicModel();
        musicModel.newPlaylist(txtName.getText());
        musicModel.renamePlaylist(namePlaylist);
        MainViewController mainView = musicModel.getMainView();
        mainView.doDelete();
        mainView.updater();
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
        
      }

    @FXML
    private void clickCancel(ActionEvent event)
      {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
      }
    
    
    
    
}
