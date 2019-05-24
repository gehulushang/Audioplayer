package views.list;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.File;

public class SongElement extends HBox {

    private Label songName;
    public final File file;

    public SongElement(File file)
    {
        this.file = file;
        this.songName = new Label(file.getName());
        configureChildren();
    }

    private void configureChildren()
    {
        this.getChildren().add(songName);
    }
}
