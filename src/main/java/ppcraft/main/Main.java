package ppcraft.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ppcraft.controllers.ControllerMain;
import ppcraft.operations.Check;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Main extends Application {
    public static final String ICO = "images/ico.png";
    public static final String FXMLMAIN = "/fxml/main.fxml";
    public static final String FXMLCLEAR = "/fxml/clearDialog.fxml";
    public static final String FXMLOPERATION = "/fxml/operationDialog.fxml";
    public static final String FXMLCHART = "/fxml/chart.fxml";
    public static final String LOCALEPATH = "bundles.Locale";
    public static final int NUMOFTHREADS = Runtime.getRuntime().availableProcessors();

    public static String CONFIG;
    public static String idButton = "";
    public static String[] resurs;
    public static Locale LOCALLANG;
    public static int timer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        try {
            CONFIG = String.valueOf(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent()) + File.separator + "config.txt";
        } catch (URISyntaxException e) {
            System.out.println("URISyntaxException_Main: " + e);
        }
        Check.checkFile();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setAlertType(Alert.AlertType.NONE);
        alert.setHeaderText(null);
        alert.setContentText("Выберете язык/Choose language!");
        ButtonType buttonTypeRU = new ButtonType("Русский");
        ButtonType buttonTypeEN = new ButtonType("English");
        alert.getButtonTypes().setAll(buttonTypeRU, buttonTypeEN);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeRU){
            LOCALLANG = new Locale("ru");
        } else{
            LOCALLANG = new Locale("en");
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(FXMLMAIN));
        fxmlLoader.setResources(ResourceBundle.getBundle(LOCALEPATH, LOCALLANG));
        Parent fxmlMain = null;
        try {
            fxmlMain = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("IOException_Main: " + e);
        }
        ControllerMain controllerMain = fxmlLoader.getController();
        controllerMain.setMainStage(primaryStage);
        primaryStage.getIcons().add(new Image(ICO));
        primaryStage.setTitle(fxmlLoader.getResources().getString("online_status"));
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(700);
        primaryStage.setScene(new Scene(fxmlMain, 750, 400));
        primaryStage.show();
    }
}
