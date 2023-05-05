package controllers;

import appFiles.Word;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class GameController {
	@FXML private AnchorPane bgPane, subPane, drawing;
	@FXML private Button enterBtn;
	@FXML private TextField input;
	@FXML private TextArea hints, guessingList;
	@FXML private Label guessTitle, attemptsLabel, timerLabel;
	private int attemptsLeft;
	private final int yLineCoord = 100;
	private final int yLetterCoord = 70;
	private final int halfLineLength = 10;
	private int time = 70;
	private Word word;
	private Label[] allLetters;
	private Alert alert;
	private boolean hasWon, hasTime;
	
	@FXML private void initialize() {
		this.enterBtn.setStyle("-fx-background-image: url('/other/logo2.png')");
		this.alert = new Alert(AlertType.WARNING);
	}
	
	public void generateGame(Word word, boolean aTimer) {
			this.word = word;
			this.hasWon = false;
			this.hasTime = aTimer;
			this.allLetters = new Label[this.word.getName().length()];
			int r = this.word.getName().length();
			int each = 425/(r+1);
			int c = each;
			for(int i=0; i<r; i++) {
				Line line = new Line();
				Label space = new Label("");
				space.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: Comic Sans MS");
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
			loadAttempts();
			if(this.hasTime == true) {
				this.timerLabel.setText("Time Left: " + this.time);
				this.timerLabel.setVisible(true);
				this.showTimerLabel();
			}
			else {
				this.timerLabel.setVisible(false);
			}
	}
	
	@FXML private void enterGuess(ActionEvent evt) {
		if(!this.input.getText().isBlank()) {
			try {
				long test = Long.parseLong(this.input.getText());
				this.alert.setAlertType(AlertType.WARNING);
				this.alert.setTitle("HMMMM... No.");
				this.alert.setHeaderText("Invalid Input:");
				this.alert.setContentText("You must enter letters/words only as guesses!");
				this.alert.show();
				}
			catch(NumberFormatException e) {
				String in = this.input.getText().toUpperCase().trim();
				if((in.length() == 1) || (in.length() == this.allLetters.length)){
					if(this.attemptsLeft > 0 && this.hasWon != true) {
						if(this.word.getName().contains(in)) {
							if(in.length() == 1) {
								for(int i=0; i<this.word.getName().length(); i++) {
									if(this.word.getName().charAt(i) == in.charAt(0)) {
										this.allLetters[i].setText("" + in);
									}
								}
							}
							else if(in.length() > 1) {
								if(in.equals(this.word.getName())) {
									for(int i=0; i<in.length(); i++) {
										this.allLetters[i].setText("" + in.charAt(i));
									}
									this.hasWon = true;
								}	
							}			
						}
						if(this.guessingList.getText().isBlank()) {
							this.guessingList.setText(in + "\n");
						}
						else {
							this.guessingList.setText(this.guessingList.getText() + in + "\n");
						}
							this.input.setText("");
							checkCompletion();
							updateAttemptsLabel();	
						}
				}
				else {
					this.alert.setAlertType(AlertType.WARNING);
					this.alert.setHeaderText("Warning!");
					this.alert.setContentText("Please enter a single letter or the whole word as input,\nnot just part of the text in between!");
					this.alert.setTitle("");
					this.alert.showAndWait();
				}
			}	
		}
	}
	
	private void loadHints() {
		this.hints.setText("HINTS: \n\n");
		String s[] = this.word.getDefinition().split(", synonyms");
		if(s[0].length() > 46) {
			int cutNum = s[0].length() / 46;
			String[] chain = new String[cutNum + 1];
			int counter = 0;
			int currC = 0;
			int currE = 47;
			while(counter < cutNum) {
				String st = s[0].substring(currC, currE);
				chain[counter] = st;
				counter++;
				currC += 47;
				currE += 47;
			}
			chain[counter] = s[0].substring(currC, s[0].length());
			for(int i=0; i<chain.length-1; i++) {
				if(chain[i].charAt(0) == ' ') {
					this.hints.setText(this.hints.getText() + "   " + chain[i] + "\n");
				}
				else {
					this.hints.setText(this.hints.getText() + "   " + chain[i] + "-\n");
				}
			}
			this.hints.setText(this.hints.getText() + "   " + chain[chain.length-1]);
		}
		else {
			this.hints.setText(this.hints.getText() + "   " + s[0]);
		}
	}
	
	private void loadAttempts() {
		this.attemptsLeft = this.word.getName().length() + 5;
		this.attemptsLabel.setText("Attempts Left: " + this.attemptsLeft);
	}
	
	private void updateAttemptsLabel() {
		if(this.attemptsLeft > 1 && !this.hasWon) {
			this.attemptsLeft--;
			this.attemptsLabel.setText("Attempts Left: " + this.attemptsLeft);
		}
		else {
			this.attemptsLeft--;
			this.attemptsLabel.setText("Attempts Left: " + this.attemptsLeft);
			this.alert.setAlertType(AlertType.INFORMATION);
			if(this.hasWon) {
				this.alert.setHeaderText("Congratulations, you won!");
				if(this.hasTime) {
					this.alert.setContentText("Amazing job, you beat the game with " + this.attemptsLeft + " attempts remaining and with " + 
							this.time + " seconds left!" + "!\nClosing game now.");
				}
				else {
					this.alert.setContentText("Amazing job, you beat the game with " + this.attemptsLeft + " attempts remaining. Closing game now!");
				}
				
			}
			else {
				this.alert.setHeaderText("Ran out of attempts, game over!\nClosing game now.");
				this.alert.setContentText("Word was: " + this.word.getName());
			}
			this.alert.showAndWait();
			System.exit(0);
		}
	}	
	private void checkCompletion() {	
		String s = "";
		for(int i=0; i<this.allLetters.length; i++) {
			s = s + this.allLetters[i].getText();
		}
		if(s.equals(this.word.getName())) {
			this.hasWon = true;
		}
	}
	
	public void showTimerLabel() {
		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		if(t != null) {
			t.stop();
		}
		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				time--;
				timerLabel.setText("Time Left: " + time);
				if(time <= 0 || hasWon || attemptsLeft == 0) {
					t.stop();
				}
			}
		});
		t.getKeyFrames().add(frame);
		t.playFromStart();
		if(this.time <= 0) {
			alert.setHeaderText("BAD LUCK!!!!");
			alert.setContentText("Ran out of time with " + attemptsLeft + " attempts remaining " + ".\n Word was: " + this.word.getName() + "! Closing game now.");
			alert.show();
			System.exit(0);
		}
	}
	
}
