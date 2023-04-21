package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.Gson;
import appFiles.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class GameController {
	@FXML private AnchorPane bgPane, subPane, drawing;
	@FXML private Button enterBtn;
	@FXML private TextField input;
	@FXML private TextArea hints;
	@FXML private Label guessTitle, attemptsLabel;
	@FXML private ListView<String> guessingList;
	private int attemptsLeft;
	private final int yLineCoord = 100;
	private final int yLetterCoord = 70;
	private final int halfLineLength = 10;
	private Word word;
	private Label[] allLetters;
	
	@FXML private void initialize() {
		this.enterBtn.setStyle("-fx-background-image: url('/other/logo2.png')");
	}
	
	public void generateWord(Word word) {
			this.word = word;
			this.allLetters = new Label[this.word.getName().length()];
			int r = this.word.getName().length();
			int each = 425/(r+1);
			int c = each;
			for(int i=0; i<r; i++) {
				Line line = new Line();
				Label space = new Label("" + this.word.getName().charAt(i));
				space.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: ComicSansMS");
				space.relocate(c-6.5, yLetterCoord);
				line.setStartX(c-halfLineLength);
				line.setStartY(yLineCoord);
				line.setEndX(c+halfLineLength+1);
				line.setEndY(yLineCoord);
				this.allLetters[i] = space;
				this.drawing.getChildren().add(line);
				this.drawing.getChildren().add(space);
				c += each;
			}
			loadHints();
	}
	
	private void loadHints() {
		String s[] = this.word.getDefinition().split(";");
		System.out.println(s[0]);
		for(int i=0; i<s.length; i++) {
			this.hints.setText(this.hints.getText() + s[i] + "\n");
		}
	}
}
