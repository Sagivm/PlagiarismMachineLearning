package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.JavaFXBuilderFactory;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import Entity.Ngram;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainController implements Initializable {

	@FXML
	private Button startButton;
	@FXML
	private Button browseButton;
	@FXML
	private TextField evoTextField;
	@FXML
	private TextField fileTextField;
	@FXML
	private TextField sSizeTextField;
	@FXML
	private TextField kMaxTextField;

	private static int kmax;
	private static int segmentSize;
	private static int evolution;
	private static String doc;
	private String filePath;
	private static int[] optimalK;
	public static ArrayList pai;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void browse(ActionEvent event) throws IOException {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*", "pdf");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			filePath = chooser.getSelectedFile().getAbsolutePath();
			fileTextField.setText(filePath);
		}

	}

	@FXML
	private void start(ActionEvent event) throws IOException {
		kmax = Integer.parseInt(kMaxTextField.getText());
		segmentSize = Integer.parseInt(sSizeTextField.getText());
		evolution = Integer.parseInt(evoTextField.getText());
		// Extract pdf file text
		File file = new File(filePath);
		PDDocument document = PDDocument.load(file);
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		pdfTextStripper.setStartPage(0);
		pdfTextStripper.setStartPage(document.getNumberOfPages());
		doc = pdfTextStripper.getText(document);
		System.out.print(doc + "\n\n");
		document.close();
		// Send doc to stem and stop word analyzer and then divide it into chunks
		ArrayList<String> segments = TextEditorController.divideIntoChunks(TextEditorController.remover(doc),
				segmentSize);
		System.out.print(segments.get(0));
		// Ngram
		ArrayList<Ngram> ngrams=NgramController.GenerateNgrams(segments);
		//calculate Dzevt vector
		pai=new ArrayList<ArrayList<Double>>();
		ArrayList<Double>temp=new ArrayList();
		for(int i=1;i<ngrams.size();i++)
		{
			temp=CorrelationCoefficientController.piXCalc(ngrams, i);
			pai.add(temp);
		}
		OptimalKMeans ok=new OptimalKMeans();
		optimalK=ok.findMaxSilhouette(pai, kmax);
//		ScreenController screenController = new ScreenController();
//		try {
//			screenController.replaceSceneContent("/boundry/resultUI.fxml", "Result");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			screenController.finalize();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
		Parent resultPageP=FXMLLoader.load(getClass().getResource("/Boundry/ResultUI.fxml"));
		Scene scene=new Scene(resultPageP);
		Stage stage= new Stage();
		stage.setScene(scene);
		stage.show();
	}

	public static int getKmax() {
		return kmax;
	}

	public static int getSegmentSize() {
		return segmentSize;
	}

	public static int getEvolution() {
		return evolution;
	}

	public static String getDoc() {
		return doc;
	}

}