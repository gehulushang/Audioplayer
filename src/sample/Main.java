package sample;
import Util.LogUtil;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main extends Application {
    private Sound sound;// 注意，我这里是为了防止被JVM的垃圾回收给回收掉

    //日志文件
    private File PlayLog= new File(".\\history\\PlayLog.txt");
    //播放日志工具类
    private LogUtil logUtil= new LogUtil();
    @Override
    public void start(Stage primaryStage) throws Exception {


        AnchorPane root = new AnchorPane();
        Button loopButton = new Button("循环");
        Button stopButton = new Button("暂停");

        AnchorPane.setLeftAnchor(loopButton,50.0);
        AnchorPane.setLeftAnchor(stopButton,150.0);
        root.getChildren().addAll(loopButton,stopButton);

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

                sound = new Sound(songNameOrUrl, false);
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
    }

    public static void main(String[] args) {

        launch(args);
    }
}
