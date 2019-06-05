package sample;


import Util.FileUtil;
import Util.LogUtil;
import com.sun.security.auth.SolarisNumericGroupPrincipal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import views.list.SongListView;
import views.song.SongView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    private Sound sound;// 注意，我这里是为了防止被JVM的垃圾回收给回收掉
    private static SongListView songList = new SongListView(new File("C:\\Users\\Administrator\\Desktop"));
    private static Stage stage;
    //日志文件
    private File PlayLog= new File(".\\history\\PlayLog.txt");
    //播放日志工具类
    private LogUtil logUtil= new LogUtil();
    private FileUtil fileUtil = new FileUtil();
    private List<String> songs;
    private int songIndex = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {


       AnchorPane root = new AnchorPane();
        Button loopButton = new Button("循环");
        Button stopButton = new Button("暂停");
        Button importButton = new Button("引入");
        Button nextButton = new Button("下一首");
        Label songList = new Label();

        AnchorPane.setLeftAnchor(loopButton,50.0);
        AnchorPane.setLeftAnchor(stopButton,100.0);
        AnchorPane.setLeftAnchor(importButton,150.0);
        AnchorPane.setTopAnchor(songList,100.0);
        AnchorPane.setLeftAnchor(nextButton,200.0);
        root.getChildren().addAll(loopButton,stopButton,songList,importButton,nextButton);

        //SongView songView = new SongView(new File("C:\\Users\\Administrator\\Desktop\\cet601.mp3"));

     //   Scene scene = new Scene(songView);
       // songView.startMusic();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setHeight(600.0);
        primaryStage.setWidth(450.0);
        primaryStage.getIcons().add(new Image("file:icon/music.png"));

        primaryStage.setTitle("Audio");
        primaryStage.show();



        //播放
       loopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                if(songs.size()>=songIndex+1){

                        File songFile = new File(songs.get(songIndex));
                        sound = new Sound(songFile, false);
                        PlayLog = logUtil.loopWrite(PlayLog,songs.get(songIndex));
                }
                sound.play();
            }
        });
       //简单实现下一首功能
       nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sound.close();
                songIndex++;
                if(songs.size()>=songIndex+1){
                }else {
                    songIndex = 0;
                }
                File songFile = new File(songs.get(songIndex));
                sound = new Sound(songFile, false);
                PlayLog = logUtil.loopWrite(PlayLog,songs.get(songIndex));
                sound.play();
            }
        });

        //停止
        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PlayLog = logUtil.stopWrite(PlayLog);
                sound.close();
            }
        });
        importButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                DirectoryChooser directoryChooser = new DirectoryChooser();

                File file = directoryChooser.showDialog(primaryStage);
                String tempPath = file.getAbsolutePath();

                System.out.println(tempPath);
                songs  = fileUtil.traverseFolder2(tempPath);
                String tempSP = "";
                if(songs!=null|| songs.size()>=1){
                    for(int i = 0;i<songs.size();i++){
                        tempSP = tempSP+ songs.get(i)+"\n";

                        songList.setText(tempSP);
                     //   songList.setText("\n");
                    }
                }
                System.out.println(songs);
            }
        });
    }

    public static void songClicked(File file)
    {
        stage.setScene(new Scene(new SongView(file), 800, 600));
    }

    public static void main(String[] args) {

        launch(args);
    }
}
