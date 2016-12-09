/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Controller;

import assignment4mytunes.BE.Music;
import assignment4mytunes.BE.Playlist;
import assignment4mytunes.BLL.PlaylistManager;
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
public class AddPlaylistController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    private Playlist playlist;
    private MusicModel musicModel;

    public AddPlaylistController(Playlist playlist) {
        this.playlist = playlist;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public AddPlaylistController() {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickSave(ActionEvent event) {
        musicModel = MusicModel.getMusicModel();
        musicModel.newPlaylist(txtName.getText());
        MainViewController mainView = musicModel.getMainView();
        mainView.updater();
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void CancelPlaylist(ActionEvent event) {

        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }

}
