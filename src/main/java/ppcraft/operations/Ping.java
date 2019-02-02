package ppcraft.operations;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import static ppcraft.main.Main.LOCALEPATH;
import static ppcraft.main.Main.LOCALLANG;

public class Ping {
    public static String aliveStatus(String netAddress){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle(LOCALEPATH, LOCALLANG));
        String statusOnline;
        boolean online = false;
        try {
            online = InetAddress.getByName(netAddress).isReachable(1000);
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException_aliveStatus: " + e);
        } catch (IOException e) {
            System.out.println("IOException_aliveStatus: " + e);
        }
        if (online == true){
            statusOnline = fxmlLoader.getResources().getString("online");
        }else {
            statusOnline = fxmlLoader.getResources().getString("offline");
        }
        return statusOnline;
    }

    public static String ping(String netAddress){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle(LOCALEPATH, LOCALLANG));
        String result;
        long ping = 0;
        try {
            long timebefore = System.currentTimeMillis();
            boolean status = InetAddress.getByName(netAddress).isReachable(1000);
            long timeafter = System.currentTimeMillis();
            ping =(int) (timeafter - timebefore);
        } catch (IOException e) {
            System.out.println("IOException_ping: " + e);
        }
        if (ping < 1){
                result = "< 1" + fxmlLoader.getResources().getString("ms");
        }else {
                result = String.valueOf(ping) + fxmlLoader.getResources().getString("ms");
        }
        return result;
    }
}
