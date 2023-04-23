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
	private Word word;
	
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
				generatedWord(this.diff);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/Game.fxml"));
				AnchorPane root = loader.load();
				Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				GameController gC = loader.getController();
				gC.generateGame(this.word);
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
	
	private String generatedWord(char diff) {
		URL url;
		Random rnd = new Random();
		int length = 0;
		Object obj = new Object();
		switch(diff) {
			case 'E':
				try {
					length = rnd.nextInt(2) + 3;
					url = new URL("https://random-word-api.vercel.app/api?words=1&length=" + length);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					if(con.getResponseCode() == 200) {
						Gson gson = new Gson();
						obj = gson.fromJson(new BufferedReader(new InputStreamReader(con.getInputStream())), Object.class);
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 'M':
				try {
					length = rnd.nextInt(2) + 5;
					url = new URL("https://random-word-api.vercel.app/api?words=1&length=" + length);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					if(con.getResponseCode() == 200) {
						Gson gson = new Gson();
						obj = gson.fromJson(new BufferedReader(new InputStreamReader(con.getInputStream())), Object.class);
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 'H':
				try {
					length = rnd.nextInt(3) + 7;
					url = new URL("https://random-word-api.vercel.app/api?words=1&length=" + length);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					if(con.getResponseCode() == 200) {
						Gson gson = new Gson();
						obj = gson.fromJson(new BufferedReader(new InputStreamReader(con.getInputStream())), Object.class);
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
		}
		String s = obj.toString().replace("[", "").replace("]", "");
		generateWordInfo(s);
		return s;
	}
	
	private void generateWordInfo(String s) {
		try {
			URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + s);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			if(con.getResponseCode() == 200) {
				Gson gson = new Gson();
				Object[] obj = gson.fromJson(new BufferedReader(new InputStreamReader(con.getInputStream())), Object[].class);
				String string = obj[0].toString();
				if(!string.isBlank() && obj != null) {
					int word = string.indexOf("word=");
					int comma = string.indexOf(",");
					String name = string.substring(word + 5, comma).toUpperCase();
					string = string.substring(comma + 1, string.length());
					int part = string.indexOf("partOfSpeech=");
					string = string.substring(part, string.length());
					int n = string.indexOf(",");
					String partOf = string.substring(13, n);
					int def = string.indexOf("definition=");
					string = string.substring(def, string.length());
					string.replaceFirst(", synonyms=", ".");
					int dot = string.indexOf(".");
					String meaning = string.substring(11, dot + 1);
					Word w = new Word(name, partOf, meaning);
					this.word = w;
				}
			}
		} catch (MalformedURLException e) {
			generateWordInfo(generatedWord(this.diff));
		} catch (IOException e) {
		}
	}
}
