package ppcraft.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ppcraft.interfaces.AddressDirectory;
import ppcraft.objects.AddressStatusPing;

public class CollectionAddressDirectory implements AddressDirectory {
    private ObservableList<AddressStatusPing> addressStatusPingList = FXCollections.observableArrayList();

    @Override
    public void add(AddressStatusPing addressStatusPing) {
        getAddressStatusPingList().add(addressStatusPing);
    }

    @Override
    public void update(AddressStatusPing addressStatusPing) {

    }

    @Override
    public void delete(AddressStatusPing addressStatusPing) {
        getAddressStatusPingList().remove(addressStatusPing);
    }

    public ObservableList<AddressStatusPing> getAddressStatusPingList(){
        return addressStatusPingList;
    }
}
