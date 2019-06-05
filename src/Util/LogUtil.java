package Util;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.io.*;
import java.util.Date;

public class LogUtil {
    private Date now = new Date();


    public File loopWrite(File PlayLog,String songNameOrUrl)  {

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(PlayLog, true);
            osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(now.toString()+"   "+ songNameOrUrl+"-------播放\r\n");
            osw.flush();
        //    writer.close(); // 最后记得关闭文件
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                osw.close();
            }catch (IOException e3){
                e3.printStackTrace();
            }

        }
        return PlayLog;
    }
    public File stopWrite(File PlayLog){
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(PlayLog, true);
            osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(now.toString()+"-------暂停播放\r\n");
            osw.flush();
            //    writer.close(); // 最后记得关闭文件
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                osw.close();
            }catch (IOException e3){
                e3.printStackTrace();
            }

        }
        return PlayLog;
    }


}
