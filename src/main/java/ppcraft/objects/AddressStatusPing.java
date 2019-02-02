package ppcraft.objects;

import javafx.beans.property.SimpleStringProperty;

public class AddressStatusPing {
    private SimpleStringProperty address = new SimpleStringProperty("");
    private SimpleStringProperty status = new SimpleStringProperty("");
    private SimpleStringProperty ping = new SimpleStringProperty("");

    public AddressStatusPing(){

    }

    public AddressStatusPing(String address, String status, String ping){
        this.address = new SimpleStringProperty(address);
        this.status = new SimpleStringProperty(status);
        this.ping = new SimpleStringProperty(ping);
    }

    public String getAddress(){
        return address.get();
    }

    public void setAddress(String address){
        this.address.set(address);
    }

    public String getStatus(){
        return status.get();
    }

    public void setStatus(String login){
        this.status.set(login);
    }

    public String getPing(){
        return ping.get();
    }

    public void setPing(String password){
        this.ping.set(password);
    }

    public SimpleStringProperty addressProperty(){
        return address;
    }

    public SimpleStringProperty statusProperty(){
        return status;
    }

    public SimpleStringProperty pingProperty(){
        return ping;
    }
}
