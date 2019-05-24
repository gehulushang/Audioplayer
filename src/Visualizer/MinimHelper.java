package Visualizer;

import java.io.FileInputStream;
import java.io.InputStream;

public class MinimHelper {

    public MinimHelper() {
    }

    public String sketchPath(String fileName) {
        return "" + fileName;
    }

    public InputStream createInput(String fileName) {
        FileInputStream is = null;

        try {
            is = new FileInputStream(this.sketchPath(fileName));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return is;
    }
}
