package Visualizer;


import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

import java.io.File;
import java.util.ArrayList;

public class Visual extends HBox {

    private static final int RECTANGLE_WIDTH = 40;
    private ArrayList<Rectangle> visualizerBars = new ArrayList<>();

    // minim/fft
    private Minim minim;
    private AudioPlayer audioPlayer;
    private FFT fft;

    // fft settings
    private int minBandwidthOctave = 100;
    private int bandsPerOctave = 3;
    private int barAmount;

    public Visual(File musicFile, int width, int height)
    {
        this.setMinHeight(500);
        this.setStyle("-fx-background-color: red");
        configureMinim(musicFile);

        // normally displays upside down, so we flip it
        flipView();
    }

    private void configureMinim(File musicFile)
    {
        minim = new Minim(new MinimHelper());
        audioPlayer = minim.loadFile(musicFile.getAbsolutePath(), 2048);
        fft = new FFT(audioPlayer.bufferSize(), audioPlayer.sampleRate());

        fft.logAverages(minBandwidthOctave, bandsPerOctave);
        barAmount = fft.avgSize();
    }

    private void flipView()
    {
        Scale scale = new Scale();
        scale.setX(1);
        scale.setY(-1);

        scale.pivotYProperty().bind(Bindings.createDoubleBinding(() ->
                        this.getBoundsInLocal().getMinY() + this.getBoundsInLocal().getHeight() /2,
                this.boundsInLocalProperty()));
        this.getTransforms().add(scale);
    }


    /**
     * Retrieves the current FFT data and displays it.
     */
    public void drawCurrentFrame()
    {
        // create an array to store bar values in, and go to the next fft frame
        float[] displayValues = new float[barAmount];
        fft.forward(audioPlayer.mix);

        for (int i = 0; i < displayValues.length; i++)
        {
            displayValues[i] = fft.getAvg(i);
        }

        // if the displayArray & visualizerBars lists have the same length,
        // we will simply change the values of the visualizerBars without re-adding them.
        if(visualizerBars.size() == displayValues.length)
        {
            for (int index = 0; index < displayValues.length; index++)
                visualizerBars.get(index).setHeight(displayValues[index]);
        }

        else
        {
            visualizerBars.clear();
            for(float f : displayValues)
            {
                Rectangle rectangle = new Rectangle(RECTANGLE_WIDTH, f);
                visualizerBars.add(rectangle);
                this.getChildren().add(rectangle);
            }
        }
    }

    public void start()
    {
        audioPlayer.play();
    }

    public void stop()
    {
        audioPlayer.pause();
    }
}
