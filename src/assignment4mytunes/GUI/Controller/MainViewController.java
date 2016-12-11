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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

    @FXML
    private Button btnPrevSong;
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
    private TableView<Music> tblSongsOnPlaylist;
    @FXML
    private TableColumn<Music, String> tblSongsOnPlaylistArtist;
    @FXML
    private TableColumn<Music, String> tblSongsOnPlaylistSongName;
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

    private int lastClicked;
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
        MusicModel musicModel = MusicModel.getMusicModel();
        musicModel.setMainView(this);
        subStage.initModality(Modality.WINDOW_MODAL);
        subStage.initOwner(primaryStage);

        subStage.show();
    }

    public void updater() {
        MusicModel musicModel = MusicModel.getMusicModel();
        selectedItemFromList();

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
                updateSongsPlaylist();
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
//        selectedItemFromList();
//        try
//          {
//            windowloader("/assignment4mytunes/GUI/View/DeleteView.fxml");
//          } catch (IOException ex)
//          {
//
//            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
//          }
    }

    @FXML
    private void prev(ActionEvent event) {
    }

    @FXML
    private void play(ActionEvent event) {
        if (mp != null && mp.getStatus() == MediaPlayer.Status.PLAYING) {
            mp.stop();
        }

        if (lastClicked == 0 || lastClicked == 3) {
            System.out.println("Do nothing");
        }
        if (lastClicked == 1) {
            /*  try {
                mp = new MediaPlayer(new Media(tblSongsOnPlaylist.getSelectionModel().getSelectedItem().getPath()));

                mp.play();
            } catch (UnsupportedOperationException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            System.out.println("Hopefully");
        }
        if (lastClicked == 2) {
            /* try {
                mp = new MediaPlayer(new Media(tblAllSongs.getSelectionModel().getSelectedItem().getPath()));

                mp.play();
            } catch (UnsupportedOperationException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
             */ System.out.println("Works like a charm");
        }
    }

    @FXML
    private void next(ActionEvent event) {
    }

    private Music selectedSong() {
        return tblAllSongs.getSelectionModel().getSelectedItem();
    }

    private void selectedItemFromList() {
//        if (tblAllSongs.isFocused())
//              System.out.println("TableAllSongs");
//        else if (tblPlaylist.isFocused())
//              System.out.println("TablePlaylist");
//        else if (tblSongsOnPlaylist.isFocused())
//              System.out.println("Songs on Playlist");

        tblAllSongs.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> {
            if (newSelection != null) {
                tblPlaylist.getSelectionModel().getSelectedItem();
                System.out.println("Table All Songs");

            }
        });

    }

    @FXML
    private void AddSongToPlaylist(ActionEvent event) {
        Playlist p = null;
        p = tblPlaylist.getSelectionModel().getSelectedItem();
        Music m = null;
        m = tblAllSongs.getSelectionModel().getSelectedItem();
        if (m != null && p != null) {
            p.addSong(m);
            updateSongsPlaylist();
            MusicModel musicModel = MusicModel.getMusicModel();
            musicModel.savePlaylist(p);
        }
    }

    private void updateSongsPlaylist() {
        Playlist p = null;
        p = tblPlaylist.getSelectionModel().getSelectedItem();
        if (p != null) {
            ObservableList<Music> plSongList
                    = FXCollections.observableArrayList(p.getPlaylist());
            tblSongsOnPlaylist.setItems(plSongList);
            tblSongsOnPlaylistSongName.setCellValueFactory(
                    new PropertyValueFactory("title"));
            tblSongsOnPlaylistArtist.setCellValueFactory(
                    new PropertyValueFactory("artist"));
        }

    }

    @FXML
    private void PlaylistUpdate(MouseEvent event) {
        updateSongsPlaylist();
        lastClicked = 3;
        System.out.println(lastClicked + "");
    }

    @FXML
    private void songsinPlaylistUpdate(MouseEvent event) {
        lastClicked = 1;
        System.out.println(lastClicked + "");
    }

    @FXML
    private void songsUpdate(MouseEvent event) {
        lastClicked = 2;
        System.out.println(lastClicked + "");
    }
}
