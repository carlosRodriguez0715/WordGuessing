package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class DifficultyController {

	@FXML private AnchorPane bgPane;
	@FXML private Button startBtn;
	@FXML private RadioButton easy, medium, hard;
	@FXML private Label title;
	@FXML private TextArea txtArea;
	private char difficulty;
	private Alert alert;
	
	@FXML private void initialize() {
		this.difficulty = 'E';
		alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid action:");
		alert.setHeaderText("Warning: action performed is invalid!");
		alert.setContentText("Please select a difficulty before\nrequesting to start the game!");
	}
	
	@FXML private void choice(ActionEvent evt) {
		if(evt.getSource() == this.easy) {
			this.txtArea.setText("EASY MODE: Length of the word to\nguess is somehow... short.");
			this.difficulty = 'E';
			this.medium.setSelected(false);
			this.hard.setSelected(false);
		}
		else if(evt.getSource() == this.medium) {
			this.txtArea.setText("MEDIUM MODE: Length of the word to\nguess ranges from okay to long enough\nto become a challenge.");
			this.difficulty = 'M';
			this.easy.setSelected(false);
			this.hard.setSelected(false);
		}
		if(evt.getSource() == this.hard) {
			this.txtArea.setText("HARD MODE: Length of the word to guess \nis long enough to become a real challenge.\nAh, and there is time limit of 3 minutes too.");
			this.difficulty = 'H';
			this.easy.setSelected(false);
			this.medium.setSelected(false);
		}
	}
	
	private char getDifficulty() {
		return this.difficulty;
	}
	
	@FXML private void startGame() {
		if(this.easy.isSelected() || this.medium.isSelected() || this.hard.isSelected()) {
			System.out.println("Difficulty: " + this.difficulty);
		}
		else {
			this.alert.show();
		}
	}
	
}
