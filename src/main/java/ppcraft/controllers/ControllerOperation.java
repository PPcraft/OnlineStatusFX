package ppcraft.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ppcraft.objects.AddressStatusPing;
import ppcraft.operations.Check;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static ppcraft.main.Main.*;

public class ControllerOperation implements Initializable {
    @FXML
    private TextField addressTextField;
    @FXML
    private Button okBtn;
    @FXML
    private Button cancelBtn;

    private AddressStatusPing addressStatusPing;
    private List<AddressStatusPing> addressStatusPingList;
    private ResourceBundle resourceBundle;
    private FXMLLoader fxmlLoader = new FXMLLoader();

    public void ok(ActionEvent actionEvent) {
        if (addressTextField.getText().equals("")){
            errorMessage(fxmlLoader.getResources().getString("error_not_address"));
        }else if (Check.checkAddress(addressStatusPingList,addressTextField.getText().trim()) < addressStatusPingList.size()){
            if (!idButton.equals(String.valueOf(Check.checkAddress(addressStatusPingList,addressTextField.getText())))){
                errorMessage(addressTextField.getText() + fxmlLoader.getResources().getString("error_exist"));
            }else {
                writeInAddress(actionEvent);
            }
        }else{
            writeInAddress(actionEvent);
        }
    }

    public void cancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    private void writeInAddress(ActionEvent actionEvent){
        addressStatusPing.setAddress(addressTextField.getText().toLowerCase().trim());
        cancel(actionEvent);
    }

    public void setAddressStatusPingList(List<AddressStatusPing> addressStatusPingList) {
        this.addressStatusPingList = addressStatusPingList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        fxmlLoader.setResources(ResourceBundle.getBundle(LOCALEPATH, LOCALLANG));
    }

    private void errorMessage(String textError){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(fxmlLoader.getResources().getString("error_operation"));
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(textError);
        alert.showAndWait();
    }

    public AddressStatusPing getAddressStatusPing() {
        return addressStatusPing;
    }

    public void setAddressStatusPing(AddressStatusPing addressStatusPing) {
        if (addressStatusPing == null){
            return;
        }
        this.addressStatusPing = addressStatusPing;
        addressTextField.setText(addressStatusPing.getAddress());
    }
}
