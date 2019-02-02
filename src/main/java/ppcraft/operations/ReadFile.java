package ppcraft.operations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ppcraft.main.Main.CONFIG;
import static ppcraft.main.Main.resurs;

public class ReadFile {
    public static void read(){
        try(FileInputStream fis = new FileInputStream(CONFIG)) {
            byte[] content = new byte[fis.available()];
            fis.read(content);
            resurs = new String(content).split("\n");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException_read: " + e);
        } catch (IOException e) {
            System.out.println("IOException_read: " + e);
        }
    }
}
