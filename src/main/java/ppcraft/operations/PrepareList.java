package ppcraft.operations;

import ppcraft.objects.AddressStatusPing;

import java.util.ArrayList;
import java.util.List;

import static ppcraft.main.Main.NUMOFTHREADS;

public class PrepareList {
    protected static ArrayList<Integer> threadSize = new ArrayList();
    public static List<AddressStatusPing> addressStatusPingList;

    private static int runProcess( int size, int processThread){
        while (processThread != 1){
            processThread = processThread/2;
            size = size/2;
            runProcess(size,processThread);
        }
        return size;
    }

    public static ArrayList<Integer> division (int size){
        ArrayList<Integer> threadCounter = new ArrayList();
        int sizeOneThread = runProcess(size,NUMOFTHREADS);
        for (int i = 0; i<NUMOFTHREADS; i++){
            threadCounter.add(i, sizeOneThread);
        }
        int summ = 0;
        for(int j:threadCounter){
            summ += j;
        }
        int index = 0;
        while (summ != size){
            threadCounter.set(index,(threadCounter.get(index)+1));
            index++;
            if (index == NUMOFTHREADS){
                index = 0;
            }
            summ++;
        }
        return threadCounter;
    }

    public static void mainPrepareList() {
        int sizeList = addressStatusPingList.size();
        threadSize = division(sizeList);
        for (int j = 0; j < threadSize.size(); j++){
            int count = threadSize.get(j);
            PrepareListThread prepareListThread = new PrepareListThread();
            prepareListThread.setName(String.valueOf(j));
            prepareListThread.start();
        }
    }
}
