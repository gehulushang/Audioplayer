package Util;

import java.io.File;

public class FileUtil {

    public String traverseFolder2(String path){
        String songList = "";

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if(files==null)
                return null;
            if (files.length == 0) {
             //   System.out.println("文件夹是空的!");
                return songList;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                     //   System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    } else {
                        String absolutePath = file2.getAbsolutePath();
                        absolutePath = absolutePath.substring(absolutePath.length()-3);
                        if(absolutePath.equals("mp3")){
                       //     System.out.println("文件:" +file2.getAbsolutePath() );
                            songList += file2.getAbsolutePath() +"\n";
                         //   System.out.println(songList);
                        }

                    }

                }


            }
        } else {
        //    System.out.println("文件不存在!");
            return songList;
        //
        }
        return songList;
    }
}
