package controllers;

import java.util.Random;
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
	
	@FXML private void initialize() {
		this.enterBtn.setStyle("-fx-background-image: url('/other/logo2.png')");
		generateWord();
	}
	
	private void generateWord() {
		
		Random rnd = new Random();
		int r = rnd.nextInt(12) + 1;
		int each = 425/(r+1);
		int c = each;
		for(int i=0; i<r; i++) {
			Line line = new Line();
			Label space = new Label("A");
			space.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: ComicSansMS");
			space.relocate(c-6.5, yLetterCoord);
			line.setStartX(c-halfLineLength);
			line.setStartY(yLineCoord);
			line.setEndX(c+halfLineLength+1);
			line.setEndY(yLineCoord);
			this.drawing.getChildren().add(line);
			this.drawing.getChildren().add(space);
			c += each;
		}
	}
}
