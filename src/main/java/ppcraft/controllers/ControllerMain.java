package ppcraft.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ppcraft.impls.CollectionAddressDirectory;
import ppcraft.objects.AddressStatusPing;
import ppcraft.operations.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ppcraft.main.Main.*;

public class ControllerMain implements Initializable {
    protected static CollectionAddressDirectory addressDirectoryImpl = new CollectionAddressDirectory();

    private PrepareData prepareData = new PrepareData();

    @FXML
    private Button delete;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private TableView tableAddressDirectory;
    @FXML
    private TableColumn addressStatusPing;
    @FXML
    private TableColumn status;
    @FXML
    private TableColumn ping;
    @FXML
    private Button runStop;
    @FXML
    private ChoiceBox sleepThread;

    private  Stage mainStage;
    private ResourceBundle resourceBundle;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private ControllerOperation controllerOperation;
    private Stage operationStage;
    private Parent fxmlEdit;
    protected static boolean counter;

    public void setMainStage(Stage mainStage){
        this.mainStage = mainStage;
    }

    public void delete(ActionEvent actionEvent) {
        try {
            AddressStatusPing selectedAddressStatusPing = (AddressStatusPing) tableAddressDirectory.getSelectionModel().getSelectedItem();
            if (selectedAddressStatusPing != null){
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource(FXMLCLEAR), resourceBundle);
                stage.setTitle(fxmlLoader.getResources().getString("dialog_clear"));
                stage.getIcons().add(new Image(ICO));
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                stage.showAndWait();
                if (idButton.equals("yes")){
                    addressDirectoryImpl.delete(selectedAddressStatusPing);
                    prepareData.writeAddress(addressDirectoryImpl.getAddressStatusPingList());
                }
            }
        } catch (IOException e) {
            System.out.println("IOException_delete: " + e);
        }
    }

    public void add(ActionEvent actionEvent) {
        controllerOperation.setAddressStatusPing(new AddressStatusPing());
        controllerOperation.setAddressStatusPingList(addressDirectoryImpl.getAddressStatusPingList());
        showEditDialog();
        if (controllerOperation.getAddressStatusPing().getAddress().equals("")){
            addressDirectoryImpl.delete(controllerOperation.getAddressStatusPing());
        }else {
            addressDirectoryImpl.add(controllerOperation.getAddressStatusPing());
            prepareData.writeOneAddress(controllerOperation.getAddressStatusPing());
        }
    }

    public void update(ActionEvent actionEvent) {
        updateRun();
    }

    public void runStop(ActionEvent actionEvent) {
        if (counter == false){
            counter = true;
            runStop.setText(fxmlLoader.getResources().getString("stopPing"));
            PollCycleThread pollCycleThread = new PollCycleThread();
            pollCycleThread.setDaemon(true);
            pollCycleThread.start();
        }else {
            counter = false;
            runStop.setText(fxmlLoader.getResources().getString("runPing"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        fxmlLoader.setResources(ResourceBundle.getBundle(LOCALEPATH,LOCALLANG));
        addressStatusPing.setCellValueFactory(new PropertyValueFactory<AddressStatusPing, String>("address"));
        status.setCellValueFactory(new PropertyValueFactory<AddressStatusPing, String>("status"));
        ping.setCellValueFactory(new PropertyValueFactory<AddressStatusPing, String>("ping"));
        unitListeners();
        tableAddressDirectory.setItems(addressDirectoryImpl.getAddressStatusPingList());
        unitLoader();
        sleepThread.setItems(FXCollections.observableArrayList(1,2,5,10,15,30));
        sleepThread.setValue(5);
        status.setStyle("-fx-alignment: CENTER;");
        ping.setStyle("-fx-alignment: CENTER;");
    }

    private void unitListeners(){
        tableAddressDirectory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() ==2){
                    updateRun();
                }
            }
        });

        sleepThread.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer = Integer.parseInt(sleepThread.getValue().toString());
            }
        });
    }

    private void unitLoader(){
        try {
            fxmlLoader.setLocation(getClass().getResource(FXMLOPERATION));
            fxmlEdit = fxmlLoader.load();
            controllerOperation = fxmlLoader.getController();
        }catch (IOException e){
            System.out.println("IOException_unitLoader: " + e);
        }
        if (!resurs[0].equals("")){
            for (int i = 0; i < resurs.length; i++) {
                addressDirectoryImpl.add(prepareData.readSite(i));
            }
        }
    }

    private void updateRun(){
        AddressStatusPing selectedAddress = (AddressStatusPing) tableAddressDirectory.getSelectionModel().getSelectedItem();
        if (selectedAddress != null) {
            idButton = String.valueOf(Check.checkAddress(addressDirectoryImpl.getAddressStatusPingList(), selectedAddress.getAddress()));
            controllerOperation.setAddressStatusPing((AddressStatusPing) tableAddressDirectory.getSelectionModel().getSelectedItem());
            controllerOperation.setAddressStatusPingList(addressDirectoryImpl.getAddressStatusPingList());
            showEditDialog();
            prepareData.writeAddress(addressDirectoryImpl.getAddressStatusPingList());
        }
    }

    private void showEditDialog(){
        if (operationStage==null){
            operationStage = new Stage();
            operationStage.setTitle(fxmlLoader.getResources().getString("dialog_operation"));
            operationStage.getIcons().add(new Image(ICO));
            operationStage.setResizable(false);
            operationStage.setScene(new Scene(fxmlEdit));
            operationStage.initModality(Modality.WINDOW_MODAL);
            operationStage.initOwner(mainStage);
        }
        operationStage.showAndWait();
    }
}