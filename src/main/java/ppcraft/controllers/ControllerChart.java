package ppcraft.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ControllerChart implements Initializable {
    @FXML
    private LineChart chart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series series = new XYChart.Series();
        series.setName(ControllerMain.siteChart);
        for (int i = 0; i < ControllerMain.dataForChart.size(); i++){
            if (ControllerMain.dataForChart.get(i).getAddress().equals(ControllerMain.siteChart)){
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(ControllerMain.dataForChart.get(i).getTime());
                String time = calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
                series.getData().add(new XYChart.Data(time,ControllerMain.dataForChart.get(i).getPing()));
            }
        }
        chart.getData().add(series);
    }
}
