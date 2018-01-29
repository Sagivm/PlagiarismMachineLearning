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
	private LineChart<String, Number> sChart;
	@FXML
	private CategoryAxis nCluster;
	@FXML
	private NumberAxis nSilhouette;
	@FXML
	private LineChart cChart;
	@FXML
	private CategoryAxis iSegment;
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
		iSegment=new CategoryAxis();
		sChart=new LineChart<String,Number>(iSegment,iCluster);
		XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
		for(int i=1;i<OptimalKMeans.Silhouette.size();i++)
			//get ranking based on KMEANS fix
			 series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i),OptimalKMeans.Silhouette.get(i)));
	    sChart.getData().add(series);
		
		
	}

	private void SilhouetteMap() {
		// TODO Auto-generated method stub
		nCluster=new CategoryAxis();
		nSilhouette=new NumberAxis();
		sChart=new LineChart<String,Number>(nCluster,nSilhouette);
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		for(int i=0;i<OptimalKMeans.Silhouette.size();i++)
			 series.getData().add(new XYChart.Data(String.valueOf(i),OptimalKMeans.Silhouette.get(i)));
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
