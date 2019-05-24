package views.list;

import sample.Main;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.File;
public class SongListView extends BorderPane{

    private VBox songList = new VBox();

    public SongListView(File directory)
    {
        // add mp3 files from music directory to list
        for(File file : directory.listFiles())
        {
            if (file.getName().contains(".mp3"))
            {
                // add click event to each element which changes the view
                SongElement element = new SongElement(file);
                element.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> Main.songClicked(element.file));

                songList.getChildren().add(element);
            }
        }

        songList.setSpacing(5);
        this.setCenter(songList);
    }
}
