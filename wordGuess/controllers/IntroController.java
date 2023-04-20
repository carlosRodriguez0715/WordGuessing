package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class IntroController {

	@FXML private AnchorPane bgPane, subPane;
	@FXML private Button playBtn, exitBtn;
	@FXML private Label title;
	private Stage stage;
	
	@FXML private void initialize() {
		//playBtn drag initialization
		this.playBtn.setOnMouseEntered(e -> this.playBtn.setStyle("-fx-background-color: #00ff04; -fx-border-color: #006b02; -fx-border-width: 10"));
		this.playBtn.setOnMouseExited(e -> this.playBtn.setStyle("-fx-background-color: #02b805; -fx-border-color: #006b02; -fx-border-width: 10"));
		
		//exitBtn drag initialization
		this.exitBtn.setOnMouseEntered(e -> this.exitBtn.setStyle("-fx-background-color: #fc0303; -fx-border-color: #630000; -fx-border-width: 10"));
		this.exitBtn.setOnMouseExited(e -> this.exitBtn.setStyle("-fx-background-color: #ab0202; -fx-border-color: #630000; -fx-border-width: 10"));
	}
	
	@FXML private void play(ActionEvent evt) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/fxmlFiles/Difficulty.fxml"));
			Scene scene = new Scene(root);
			this.stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
			this.stage.setScene(scene);
			this.stage.centerOnScreen();
			this.stage.show();
		} catch (IOException e) {
		}
	}
	
	@FXML private void exit(ActionEvent evt) {
		System.exit(0);
	}
}
