/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Controller;

import assignment4mytunes.BE.Music;
import assignment4mytunes.BE.Playlist;
import assignment4mytunes.GUI.Model.MusicModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

    @FXML
    private Button btnPrevSong;
    @FXML
    private Button btnNextSong;
    @FXML
    private Slider sliderVolume;
    @FXML
    private Button btnNewPlaylist;
    @FXML
    private Button btnEditPlaylist;
    @FXML
    private Button btnDeletePlaylist;
    @FXML
    private Label lblCurrentSong;
    @FXML
    private Button btnMoveUpSong;
    @FXML
    private Button btnMoveDownSong;
    @FXML
    private Button btnDeleteSong;
    @FXML
    private TextField textFieldFilter;
    @FXML
    private Button btnFilterSearch;
    @FXML
    private Button btnNewSong;
    @FXML
    private Button btnEditSong;
    @FXML
    private Button btnCloseProgram;
    @FXML
    private Button btnAddSongsToPlaylist;
    @FXML
    private TableView<Playlist> tblSongsOnPlaylist;
    @FXML
    private TableColumn<?, ?> tblSongsOnPlaylistArtist;
    @FXML
    private TableColumn<?, ?> tblSongsOnPlaylistSongName;
    @FXML
    private TableView<Music> tblAllSongs;
    @FXML
    private TableColumn<Music, String> tblAllSongsArtist;
    @FXML
    private TableColumn<Music, String> tblAllSongsName;
    @FXML
    private TableView<Playlist> tblPlaylist;
    @FXML
    private TableColumn<Playlist, String> clmPlaylist;
    @FXML
    private TableColumn<Playlist, ?> clmTime;

    MediaPlayer mp = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updater();

        // bindPlayerToGUI();
        // TOD
    }

    @FXML
    private void newMusic(ActionEvent event) {
        try {
            windowloader("/assignment4mytunes/GUI/View/AddMusic.fxml");

        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void newPlaylist(ActionEvent event) {
        try {
            windowloader("/assignment4mytunes/GUI/View/AddPlaylist.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void windowloader(String p) throws IOException {
        Stage primaryStage = (Stage) btnNewSong.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(p));
        Parent root = loader.load();
        Stage subStage = new Stage();
        subStage.setScene(new Scene(root));

        subStage.initModality(Modality.WINDOW_MODAL);
        subStage.initOwner(primaryStage);

        subStage.show();
    }

    private void updater() {
        MusicModel musicModel = MusicModel.getMusicModel();

        try {
            if (!musicModel.loadAllSongs().isEmpty()) {
                ObservableList<Music> sngList
                        = FXCollections.observableArrayList(musicModel.loadAllSongs());
                tblAllSongs.setItems(sngList);
                tblAllSongsName.setCellValueFactory(
                        new PropertyValueFactory("title"));
                tblAllSongsArtist.setCellValueFactory(
                        new PropertyValueFactory("artist"));
            }
            if (!musicModel.loadAllPlaylists().isEmpty()) {
                ObservableList<Playlist> pList
                        = FXCollections.observableArrayList(musicModel.loadAllPlaylists());
                tblPlaylist.setItems(pList);
                clmPlaylist.setCellValueFactory(
                        new PropertyValueFactory("name"));

            }
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void prev(ActionEvent event) {
    }

    @FXML
    private void play(ActionEvent event) {

    }

    @FXML
    private void next(ActionEvent event) {
    }

    private Music selectedSong() {
        return tblAllSongs.getSelectionModel().getSelectedItem();
    }
}
