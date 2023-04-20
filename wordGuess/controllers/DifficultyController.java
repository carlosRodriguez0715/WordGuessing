package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import com.google.gson.Gson;
import appFiles.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DifficultyController {

	@FXML private AnchorPane bgPane;
	@FXML private Button startBtn;
	@FXML private RadioButton easy, medium, hard;
	@FXML private Label title;
	@FXML private TextArea txtArea;
	private Alert alert;
	private char diff;
	
	@FXML private void initialize() {
		this.diff = 'E';
		alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid action:");
		alert.setHeaderText("Warning: action performed is invalid!");
		alert.setContentText("Please select a difficulty before\nrequesting to start the game!");
	}
	
	@FXML private void choice(ActionEvent evt) {
		if(evt.getSource() == this.easy) {
			this.txtArea.setText("EASY MODE: Length of the word to\nguess is somehow... short.");
			this.medium.setSelected(false);
			this.hard.setSelected(false);
			this.diff = 'E';
		}
		else if(evt.getSource() == this.medium) {
			this.txtArea.setText("MEDIUM MODE: Length of the word to\nguess ranges from okay to long enough\nto become a challenge.");
			this.easy.setSelected(false);
			this.hard.setSelected(false);
			this.diff = 'M';
		}
		if(evt.getSource() == this.hard) {
			this.txtArea.setText("HARD MODE: Length of the word to guess \nis long enough to become a real challenge.\nAh, and there is time limit of 3 minutes too.");
			this.easy.setSelected(false);
			this.medium.setSelected(false);
			this.diff = 'H';
		}
	}
	
	@FXML private void startGame(ActionEvent evt) {
		if(this.easy.isSelected() || this.medium.isSelected() || this.hard.isSelected()) {
			try {
				generateWord(this.diff);
				AnchorPane root = FXMLLoader.load(getClass().getResource("/fxmlFiles/Game.fxml"));
				Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.centerOnScreen();
				stage.show();
			} catch (IOException e) {
			}
		}
		else {
			this.alert.show();
		}
	}
	
	private void generateWord(char diff) {
		URL url;
		Random rnd = new Random();
		int length = 0;
		Word generatedWord;
		switch(diff) {
			case 'E':
				try {
					length = rnd.nextInt(5) + 1;
					url = new URL("https://pokeapi.co/api/v2/pokemon/ditto");
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					if(con.getResponseCode() == 200) {
						Gson gson = new Gson();
						String s= gson.fromJson(con.getContentType(), String.class);
						System.out.println(s);
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 'M':
				try {
					length = rnd.nextInt(9) + 1;
					url = new URL("https://pokeapi.co/api/v2/pokemon/ditto");
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					if(con.getResponseCode() == 200) {
						Gson gson = new Gson();
						String s = gson.fromJson(new BufferedReader(new InputStreamReader(con.getInputStream())), String.class);
						System.out.println(s);
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 'H':
				try {
					length = rnd.nextInt(12) + 1;
					url = new URL("https://pokeapi.co/api/v2/pokemon/ditto");
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					if(con.getResponseCode() == 200) {
						Gson gson = new Gson();
						String s = gson.fromJson(new BufferedReader(new InputStreamReader(con.getInputStream())), String.class);
						System.out.println(s);
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
		}
	}
	
}
