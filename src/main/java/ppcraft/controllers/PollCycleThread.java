package ppcraft.controllers;

import ppcraft.operations.PrepareList;

import static ppcraft.main.Main.timer;

public class PollCycleThread extends Thread{
    @Override
    public void run(){
        while (ControllerMain.counter == true){
            try {
                PrepareList.addressStatusPingList = ControllerMain.addressDirectoryImpl.getAddressStatusPingList();
                PrepareList.mainPrepareList();
                sleep(timer*1000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException_PollCycleThread: " + e);
            }
        }
    }
}
