package GUI;

import Music.CurrentMusic;
import Music.MP3Music;
import Music.MusicListManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MusicList {
    public static int listNum = 0;

    private ListView<MP3Music> musicList;
    private BorderPane musicListPane = new BorderPane();
    private VBox vBox;

    public MusicList(PlayerTab playerTab) {
    	musicList = new ListView<MP3Music>();
        musicList.setOrientation(Orientation.VERTICAL);

        musicList.setPrefHeight(300);
        musicList.setOnMouseClicked(new EventHandler<MouseEvent>() {

             @Override
             public void handle(MouseEvent click) {

                 if (click.getClickCount() == 2) {
                     MP3Music currentMP3Music = musicList.getSelectionModel().getSelectedItem();

                     playerTab.doStop();
                     CurrentMusic.getInstance().setMedia(currentMP3Music.getFilename());
                     MusicListManager.getInstance().addToRecentPlayList(CurrentMusic.getInstance().toMusic());
                     playerTab.doPlay();
                     listNum = Tab.listNum;
                 }
             }
         });

        vBox = new VBox();

        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        updateVBox();

        musicListPane.setCenter(vBox);
        musicList.setVisible(true);
        musicListPane.setVisible(true);

    }

    public BorderPane getPane() {
        return musicListPane;
    }

    public void setMusicList(ArrayList<MP3Music> arrMP3Music) {
        ObservableList<MP3Music> items = FXCollections.observableArrayList(arrMP3Music);
        musicList.setItems(items);
        updateVBox();
    }

    public void updateVBox() {
        vBox.getChildren().clear();
        vBox.getChildren().add(musicList);
    }

}
