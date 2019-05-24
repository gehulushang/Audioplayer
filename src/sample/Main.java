package sample;


import Util.FileUtil;
import Util.LogUtil;
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


public class Main extends Application {
    private Sound sound;// 注意，我这里是为了防止被JVM的垃圾回收给回收掉
    private static SongListView songList = new SongListView(new File("C:\\Users\\Administrator\\Desktop"));
    private static Stage stage;
    //日志文件
    private File PlayLog= new File(".\\history\\PlayLog.txt");
    //播放日志工具类
    private LogUtil logUtil= new LogUtil();
    private FileUtil fileUtil = new FileUtil();
    private String songs;
    @Override
    public void start(Stage primaryStage) throws Exception {


       AnchorPane root = new AnchorPane();
        Button loopButton = new Button("循环");
        Button stopButton = new Button("暂停");
        Button importButton = new Button("引入");

        Label songList = new Label();

        AnchorPane.setLeftAnchor(loopButton,50.0);
        AnchorPane.setLeftAnchor(stopButton,100.0);
        AnchorPane.setLeftAnchor(importButton,150.0);
        AnchorPane.setTopAnchor(songList,100.0);
        root.getChildren().addAll(loopButton,stopButton,songList,importButton);

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




                String songNameOrUrl = "http://sc1.111ttt.cn/2017/1/04/26/297262113196.mp3";
                File songFile = new File("C:\\Users\\Administrator\\Desktop\\cet601.mp3");
               // String s1 = "C:\\Users\\Administrator\\Desktop\\www.mp3";

                sound = new Sound(songFile, false);
                //  sound = new Sound("https://music.163.com/#/song?id=1313354324", false);
                PlayLog = logUtil.loopWrite(PlayLog,songNameOrUrl);

                sound.loop();// 循环播放
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
                songList.setText(songs);
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
