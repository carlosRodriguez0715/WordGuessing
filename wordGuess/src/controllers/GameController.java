package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class GameController {
	@FXML private AnchorPane bgPane, subPane, drawing;
	@FXML private Button enterBtn;
	@FXML private TextField input;
	@FXML private TextArea hints;
	@FXML private Label guessTitle;
	@FXML private ListView<String> guessingList;
	
	@FXML private void initialize() {
		this.enterBtn.setStyle("-fx-background-image: url('/other/logo2.png')");
	}
	
	//button.setStyle("  -fx-border-style: none; -fx-border-width: 2px; -fx-border-insets: 0; -fx-font-size:4px; -fx-background-image: url('image.png')");

}
