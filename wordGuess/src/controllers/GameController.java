package controllers;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class GameController {
	@FXML private AnchorPane bgPane, subPane, drawing;
	@FXML private Button enterBtn;
	@FXML private TextField input;
	@FXML private TextArea hints;
	@FXML private Label guessTitle, attemptsLabel;
	@FXML private ListView<String> guessingList;
	
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
			space.relocate(c-6.5, 70);
			line.setStartX(c-10);
			line.setStartY(100);
			line.setEndX(c+11);
			line.setEndY(100);
			this.drawing.getChildren().add(line);
			this.drawing.getChildren().add(space);
			c += each;
		}
	}
}
