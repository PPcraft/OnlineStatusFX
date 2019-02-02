package ppcraft.operations;

import ppcraft.objects.AddressStatusPing;


import java.util.List;

import static ppcraft.main.Main.resurs;

public class PrepareData {
    private String address;
    private String status;
    private String ping;

    public AddressStatusPing readSite(int j){
        AddressStatusPing result = new AddressStatusPing();
        if (resurs[j] != ""){
            this.address = resurs[j].trim();
            this.status = "";
            this.ping = "";
            result.setAddress(this.address);
            result.setStatus(this.status);
            result.setPing(this.ping);
        }
        return result;
    }

    public void writeAddress(List<AddressStatusPing> addressStatusPingList){
        WriteFile.write("");
        for (int i = 0; i < addressStatusPingList.size(); i++){
            AddressStatusPing addressStatusPingThis = addressStatusPingList.get(i);
            writeOneAddress(addressStatusPingThis);
        }
    }

    public void writeOneAddress(AddressStatusPing addressStatusPing){
        this.address = addressStatusPing.getAddress();
        String data = this.address + "\n";
        WriteFile.writeAdd(data);
    }
}
