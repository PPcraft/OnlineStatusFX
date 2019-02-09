package ppcraft.operations;

import javafx.fxml.FXMLLoader;
import ppcraft.controllers.ControllerMain;
import ppcraft.objects.ChartData;

import java.util.ResourceBundle;

import static ppcraft.main.Main.LOCALEPATH;
import static ppcraft.main.Main.LOCALLANG;

public class PrepareListThread extends Thread {
    @Override
    public void run(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle(LOCALEPATH, LOCALLANG));
        int j = Integer.parseInt(Thread.currentThread().getName());
        int count = PrepareList.threadSize.get(j);
        int totalizer = 0;
        for(int k = 0; k < j; k++){
            totalizer += PrepareList.threadSize.get(k);
        }
        while (count != 0){
            int current = PrepareList.threadSize.get(j) - count +totalizer;
            String siteAddress = PrepareList.addressStatusPingList.get(current).getAddress();
            PrepareList.addressStatusPingList.get(current).setStatus(Ping.aliveStatus(siteAddress));
            long pingTime;
            int ping;
            if (PrepareList.addressStatusPingList.get(current).getStatus().equals(fxmlLoader.getResources().getString("offline"))){
                PrepareList.addressStatusPingList.get(current).setPing("---");
                ping = 0;
                pingTime = System.currentTimeMillis();
            }else {
                PrepareList.addressStatusPingList.get(current).setPing(Ping.ping(siteAddress));
                if (PrepareList.addressStatusPingList.get(current).getPing().split(" ")[0].equals("<")){
                    ping = 1;
                }else {
                    ping = Integer.parseInt(PrepareList.addressStatusPingList.get(current).getPing().split(" ")[0]);
                }
                pingTime = System.currentTimeMillis();
            }
            ChartData chartData = new ChartData(siteAddress, pingTime, ping);
            ControllerMain.dataForChart.add(chartData);
            count--;
        }
    }
}
