package ppcraft.operations;

import ppcraft.objects.AddressStatusPing;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static ppcraft.main.Main.CONFIG;

public class Check {
    public static void checkFile(){
        File config = new File(CONFIG);
        if (config.isFile()){
            ReadFile.read();
        }else {
            File createConfig = new File(CONFIG);
            try {
                createConfig.createNewFile();
            } catch (IOException e) {
                System.out.println("IOException_checkFile: " + e);
            }
            ReadFile.read();
        }
    }

    public static int checkAddress(List<AddressStatusPing> siteList, String searchAddress) {
        int index = siteList.size() + 1;
        for (int i = 0; i < siteList.size(); i++) {
            AddressStatusPing selectAddressStatusPing = siteList.get(i);
            if (selectAddressStatusPing.getAddress().equals(searchAddress)) {
                index = i;
            }
        }
        return index;
    }
}
