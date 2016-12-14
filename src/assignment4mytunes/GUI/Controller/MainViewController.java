/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4mytunes.GUI.Controller;

import assignment4mytunes.BE.Music;
import assignment4mytunes.BE.Playlist;
import assignment4mytunes.GUI.Model.MusicModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

    @FXML
    private Button btnPrevSong;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Button btnNewPlaylist;
    @FXML
    private Button btnEditPlaylist;
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
    @FXML
    private ToggleButton btnPlayPause;

    public static final int SONGS_ON_PLAYLIST = 1;
    public static final int ALL_SONGS = 2;
    public static final int PLAYLIST = 3;

    private int songPlaying;
    private int playingFrom;

    private int lastClicked;
    private MediaPlayer mp = null;
    private MediaPlayer nextMp = null;

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

        try {
            if (!musicModel.loadAllSongs().isEmpty()) {

                ObservableList<Music> sngList
                        = musicModel.getAllSongs();
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

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Are you sure you want to delete the following?");
        alert.setContentText(contentDeleteText());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            MusicModel musicModel = MusicModel.getMusicModel();
            if (lastClicked == ALL_SONGS) {
                Music music = tblAllSongs.getSelectionModel().getSelectedItem();
                musicModel.removeSong(music);

            } else if (lastClicked == SONGS_ON_PLAYLIST) {
                int index = tblSongsOnPlaylist.getSelectionModel().getSelectedIndex();
                Playlist playlist = tblPlaylist.getSelectionModel().getSelectedItem();
                playlist.removeSong(index);
                musicModel.savePlaylist(playlist);
                updateSongsPlaylist();

            } else if (lastClicked == PLAYLIST) {
                Playlist playlist = tblPlaylist.getSelectionModel().getSelectedItem();
                musicModel.removePlaylist(playlist);
                updater();
            }
        }

    }

    private String contentDeleteText() {
        if (lastClicked == ALL_SONGS) {
            Music music = tblAllSongs.getSelectionModel().getSelectedItem();
            System.out.println("ALLSONGS");
            return music.getTitle();

        } else if (lastClicked == SONGS_ON_PLAYLIST) {
            Music music = tblSongsOnPlaylist.getSelectionModel().getSelectedItem();
            System.out.println("SONGS ON PLAYLIST");
            return music.getTitle();

        } else if (lastClicked == PLAYLIST) {
            Playlist playlist = tblPlaylist.getSelectionModel().getSelectedItem();
            System.out.println("PLAYLIST");
            return playlist.getName();
        } else {
            return "Sorry, nothing was selected";

        }

    }

    @FXML
    private void play(ActionEvent event) {
        playSong();

    }

    private void playSong() {
        if (mp == null) {
            SongSelected();
        }
        Status status = mp.getStatus();
        if (status == Status.PAUSED
                || status == Status.READY
                || status == Status.STOPPED
                || status == Status.UNKNOWN) {
            btnPlayPause.setText("Pause");
            mp.play();
            mp.setOnEndOfMedia(new endOfSongEvent());
            volumeSlider.setValue(mp.getVolume() * 100);
            volumeSlider.valueProperty().addListener(new InvalidationListener() {

                @Override
                public void invalidated(javafx.beans.Observable observable) {
                    mp.setVolume(volumeSlider.getValue() / 100);
                }
            });

            System.out.println("should be playing");

        } else {
            btnPlayPause.setText("Play");
            mp.pause();
        }
    }

    @FXML
    private void AddSongToPlaylist(ActionEvent event
    ) {
        Playlist p;

        p = tblPlaylist.getSelectionModel().getSelectedItem();
        Music m;
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
    private void songsInPlaylistUpdate(MouseEvent event) {
        lastClicked = 1;
        System.out.println(lastClicked + "");
    }

    @FXML
    private void songsUpdate(MouseEvent event) {
        lastClicked = 2;
        System.out.println(lastClicked + "");
    }

    private void SongSelected() {
        if (lastClicked == 0 || lastClicked == PLAYLIST) {

        }
        if (lastClicked == SONGS_ON_PLAYLIST) {
            songPlaying = tblSongsOnPlaylist.getSelectionModel().getSelectedIndex();
            Music music = tblSongsOnPlaylist.getSelectionModel().getSelectedItem();
            String path = music.getPath();
            playingFrom = SONGS_ON_PLAYLIST;
            lblCurrentSong.setText("Song: "
                    + music.getTitle()
                    + " Artist: "
                    + music.getArtist());
            Media media = new Media(new File(path).toURI().toString());
            mp = new MediaPlayer(media);

        }
        if (lastClicked == ALL_SONGS) {
            songPlaying = tblAllSongs.getSelectionModel().getSelectedIndex();
            Music music = tblAllSongs.getSelectionModel().getSelectedItem();
            String path = music.getPath();
            playingFrom = ALL_SONGS;
            lblCurrentSong.setText("Song: "
                    + music.getTitle()
                    + " Artist: "
                    + music.getArtist());
            Media media = new Media(new File(path).toURI().toString());
            mp = new MediaPlayer(media);

        }
    }

    @FXML
    private void editPlaylist(ActionEvent event) {
        if (lastClicked == PLAYLIST) {
            Playlist playlist = tblPlaylist.getSelectionModel().getSelectedItem();
            System.out.println("EDITPLAYLIST");

            Stage primaryStage = (Stage) btnNewSong.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assignment4mytunes/GUI/View/EditPlaylist.fxml"));
            Parent root = null;
            try {
                root = loader.load();

            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            Stage subStage = new Stage();
            subStage.setScene(new Scene(root));
            MusicModel musicModel = MusicModel.getMusicModel();
            musicModel.setMainView(this);
            subStage.initModality(Modality.WINDOW_MODAL);
            subStage.initOwner(primaryStage);
            subStage.show();

            EditPlaylistController editPlaylistViewController
                    = loader.getController();
            editPlaylistViewController.setTxtName(playlist);

        }

    }

    @FXML
    private void next(ActionEvent event) {
        if (mp != null) {
            mp.stop();
            System.out.println("does nothing");
            Music music = null;
            if (playingFrom == SONGS_ON_PLAYLIST) {
                if (songPlaying < tblSongsOnPlaylist.getItems().size() - 1) {
                    songPlaying++;
                    tblSongsOnPlaylist.getSelectionModel().clearAndSelect(songPlaying);
                    System.out.println("reaches here?");
                    music = tblSongsOnPlaylist.getSelectionModel().getSelectedItem();

                } else {
                    songPlaying = 0;
                    System.out.println("Does this when reaching the end");
                    tblSongsOnPlaylist.getSelectionModel().clearAndSelect(songPlaying);
                    music = tblSongsOnPlaylist.getSelectionModel().getSelectedItem();
                }
            }
            if (playingFrom == ALL_SONGS) {
                if (songPlaying < tblAllSongs.getItems().size() - 1) {
                    songPlaying++;
                    tblAllSongs.getSelectionModel().clearAndSelect(songPlaying);
                    System.out.println("reaches here?");
                    music = tblAllSongs.getSelectionModel().getSelectedItem();

                } else {
                    songPlaying = 0;
                    System.out.println("Does this when reaching the end");
                    tblAllSongs.getSelectionModel().clearAndSelect(songPlaying);
                    music = tblAllSongs.getSelectionModel().getSelectedItem();
                }
            }
            if (music != null) {
                String path = music.getPath();
                lblCurrentSong.setText("Song: "
                        + music.getTitle()
                        + " Artist: "
                        + music.getArtist());
                Media media = new Media(new File(path).toURI().toString());
                mp = new MediaPlayer(media);

                playSong();
            }

        }
    }

    @FXML
    private void prev(ActionEvent event) {
        if (mp != null) {
            mp.stop();
            System.out.println("does nothing");
            Music music = null;
            if (playingFrom == SONGS_ON_PLAYLIST) {
                if (songPlaying > 0) {
                    songPlaying--;
                    tblSongsOnPlaylist.getSelectionModel().clearAndSelect(songPlaying);
                    System.out.println("reaches here?");
                    music = tblSongsOnPlaylist.getSelectionModel().getSelectedItem();

                } else {
                    songPlaying = tblSongsOnPlaylist.getItems().size() - 1;
                    System.out.println("Does this when reaching the end");
                    tblSongsOnPlaylist.getSelectionModel().clearAndSelect(songPlaying);
                    music = tblSongsOnPlaylist.getSelectionModel().getSelectedItem();
                }
            }
            if (playingFrom == ALL_SONGS) {
                if (songPlaying > 0) {
                    songPlaying--;
                    tblAllSongs.getSelectionModel().clearAndSelect(songPlaying);
                    System.out.println("reaches here?");
                    music = tblAllSongs.getSelectionModel().getSelectedItem();

                } else {
                    songPlaying = tblAllSongs.getItems().size() - 1;
                    System.out.println("Does this when reaching the end");
                    tblAllSongs.getSelectionModel().clearAndSelect(songPlaying);
                    music = tblAllSongs.getSelectionModel().getSelectedItem();
                }
            }
            if (music != null) {
                String path = music.getPath();
                lblCurrentSong.setText("Song: "
                        + music.getTitle()
                        + " Artist: "
                        + music.getArtist());
                Media media = new Media(new File(path).toURI().toString());
                mp = new MediaPlayer(media);

                playSong();
            }

        }
    }

    @FXML
    private void editSong(ActionEvent event
    ) {
        if (lastClicked == ALL_SONGS)//checks which table is selected
        {
            Music music = tblAllSongs.getSelectionModel().getSelectedItem();
            System.out.println("THIS SONG IS SELECTED");

            //makes the AddMusic window open
            Stage primaryStage = (Stage) btnNewSong.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assignment4mytunes/GUI/View/AddMusic.fxml"));
            Parent root = null;
            try {
                root = loader.load();

            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            Stage subStage = new Stage();
            subStage.setScene(new Scene(root));
            MusicModel musicModel = MusicModel.getMusicModel();
            musicModel.setMainView(this);
            subStage.initModality(Modality.WINDOW_MODAL);
            subStage.initOwner(primaryStage);
            subStage.show();

            //gets the controller from the AddMusicController
            AddMusicController addMusicController
                    = loader.getController();

            addMusicController.setTitle(music);
            addMusicController.setArtist(music);
            addMusicController.setTime(music);
            addMusicController.setFile(music);
        }

    }

    @FXML
    private void search(KeyEvent event) {
        MusicModel musicModel = MusicModel.getMusicModel();
        if (textFieldFilter.textProperty().get().isEmpty()) {
            tblAllSongs.setItems(musicModel.getAllSongs());
            updateSongsPlaylist();

        }
        String query = textFieldFilter.getText().trim();
        try {
            tblAllSongs.setItems(getSongList(query, musicModel.getAllSongs()));
            if (!tblPlaylist.getSelectionModel().isEmpty()) {
                Playlist pl = tblPlaylist.getSelectionModel().getSelectedItem();
                ObservableList<Music> SearchArray
                        = FXCollections.observableArrayList(pl.getPlaylist());
                tblSongsOnPlaylist.setItems(getSongList(query, SearchArray));
            }
            tblAllSongs.setItems(getSongList(query, musicModel.getAllSongs()));

        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Music> getSongList(String query, ObservableList<Music> SearchArray) throws IOException {

        MusicModel musicModel = MusicModel.getMusicModel();
        ObservableList<Music> allSongs = SearchArray;
        if (query.isEmpty()) {
            return FXCollections.observableArrayList(allSongs);
        }
        ObservableList<Music> searchList = FXCollections.observableArrayList();

        for (Music music : allSongs) {
            if (music.getAllMusicStringInfo().toLowerCase().contains(query.toLowerCase())) {
                searchList.add(music);
            }
        }
        return searchList;

    }

    private class endOfSongEvent implements Runnable {

        public void run() {
            System.out.println("End of song");
            next(null);
        }
    }

}
