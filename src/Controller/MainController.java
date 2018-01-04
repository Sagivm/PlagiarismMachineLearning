package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
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
		System.out.print(doc);
		document.close();
		// Send settings to stem and stop word analyzer
		
		//Test ngramGenerator
//		String s1="aabaaba";
//		String s2="baaaaba";
//		ArrayList<String> segments=new ArrayList<String>();
//		segments.add(s1);
//		segments.add(s2);
//		NgramGeneratorController.GenerateNgrams(segments);
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
	public static String getDoc()
	{
		return doc;
	}

}