package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SampleController implements Initializable {
    @FXML
    public LineChart<Number, Number> grafikL;
    @FXML
    public Button cizB;
    @FXML
    public Button temizleB;
    @FXML
    public TextField frekansI;
    @FXML
    public TextField harmonikI;
    @FXML
    public TextField genlikI;
    @FXML
    public CheckBox sinusBilesenGoster;
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    double t = 10.0, toplam = 0.0;

    XYChart.Series<Number, Number> series = new XYChart.Series<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xAxis.setLabel("Zaman");
        grafikL.setAnimated(false);
        grafikL.setTitle("Fuorier Serisi");
        grafikL.setCreateSymbols(false);
        genlikI.setText("10");
        frekansI.setText("0.1");
        harmonikI.setText("15");
        temizleB.setDisable(true);
    }

    @FXML
    protected void cizAction() {
        try {
            if (sinusBilesenGoster.isSelected()) {
                for (int j = 1; j <= Double.parseDouble(harmonikI.getText()); j += 2.0) {
                    XYChart.Series<Number, Number> serie = new XYChart.Series<>();
                    for (double i = 0; i < t; i += 0.1) {
                        toplam = ((4.0 * Double.parseDouble(genlikI.getText())) / (Math.PI * j)) * Math.sin(j * 2.0 * Math.PI * Double.parseDouble(frekansI.getText()) * i);
                        serie.getData().add(new XYChart.Data<Number, Number>(i, toplam));
                    }
                    serie.setName(j + ". Harmonik");
                    grafikL.getData().add(serie);
                }
                for (double i = 0; i < t; i += 0.05) {
                    toplam = 0;
                    for (double j = 1.0; j <= Double.parseDouble(harmonikI.getText()); j += 2.0) {
                        toplam += ((4.0 * Double.parseDouble(genlikI.getText())) / (Math.PI * j)) * Math.sin(j * 2.0 * Math.PI * Double.parseDouble(frekansI.getText()) * i);
                    }
                    series.getData().add(new XYChart.Data<Number, Number>(i, toplam));
                }
                grafikL.getData().add(series);
            } else {
                for (double i = 0; i < t; i += 0.05) {
                    toplam = 0;
                    for (double j = 1.0; j <= Double.parseDouble(harmonikI.getText()); j += 2.0) {
                        toplam += ((4.0 * Double.parseDouble(genlikI.getText())) / (Math.PI * j)) * Math.sin(j * 2.0 * Math.PI * Double.parseDouble(frekansI.getText()) * i);
                    }
                    series.getData().add(new XYChart.Data<Number, Number>(i, toplam));
                }
                grafikL.getData().add(series);
            }
        } catch (Exception e) {

        }

        cizB.setDisable(true);
        temizleB.setDisable(false);
    }

    @FXML
    protected void temizleAction() {
        series = new XYChart.Series<>();
        grafikL.setData(FXCollections.<XYChart.Series<Number, Number>>observableArrayList());
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        cizB.setDisable(false);
        temizleB.setDisable(true);
    }


}
