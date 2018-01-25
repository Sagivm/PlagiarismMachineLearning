package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class ResultController implements Initializable {

	private static final String SilhouetteMap = null;
	@FXML
	private LineChart<Number, Number> sChart;
	@FXML
	private NumberAxis nCluster;
	@FXML
	private NumberAxis nSilhouette;
	@FXML
	private LineChart cChart;
	@FXML
	private NumberAxis iSegment;
	@FXML
	private NumberAxis iCluster;
	@FXML
	private Button backButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		SilhouetteMap();
		ClusterMap();

	}

	private void ClusterMap() {
		// TODO Auto-generated method stub
		iCluster=new NumberAxis();
		iSegment=new NumberAxis();
		sChart=new LineChart<Number,Number>(iSegment,iCluster);
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		for(int i=1;i<MainController.DZVET.size();i++)
			//get ranking based on KMEANS fix
			 series.getData().add(new XYChart.Data<Number, Number>(MainController.DZVET.get(i),null));
	    sChart.getData().add(series);
		
		
	}

	private void SilhouetteMap() {
		// TODO Auto-generated method stub
		nCluster=new NumberAxis();
		nSilhouette=new NumberAxis();
		sChart=new LineChart<Number,Number>(nCluster,nSilhouette);
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		for(int i=1;i<OptimalKMeans.Silhouette.size();i++)
			 series.getData().add(new XYChart.Data<Number, Number>(i,OptimalKMeans.Silhouette.get(i)));
	    sChart.getData().add(series);
	}

	@FXML
	public void backButtonPressed(ActionEvent event) {
		ScreenController screenController = new ScreenController();
		try {
			screenController.replaceSceneContent("/boundry/MainUI.fxml", "Main");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			screenController.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
