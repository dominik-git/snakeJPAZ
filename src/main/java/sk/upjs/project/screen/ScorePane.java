package sk.upjs.project.screen;

import java.awt.Color;
import java.awt.Font;

import sk.upjs.jpaz2.*;

/**
 * A class representing a scorePane that displays the number of eaten snacks.
 */
public class ScorePane extends Pane {
	private int score = 0;
	private Turtle painter;
	private static ScorePane instance;

	public ScorePane() {
		super(400,25);
		setBorderWidth(0);
		setTransparentBackground(true);
		painter = new Turtle();
		painter.setVisible(false);
		painter.setDirection(90);
		painter.setPenColor(Color.white);
		painter.setFont(new Font("Lucida Sans", Font.BOLD, 20));
		add(painter);
		painter.center();
		resetScore();
	}
	
	public static ScorePane getInstance() {
		if(instance == null) {
			instance = new ScorePane();
		}
		return instance;
	}	

	/**
	 * Sets the score to 0.
	 */
	public void resetScore() {
		score = 0;
		repaintScore();
	}

	public void updateScore() {
		this.score += 10;
		repaintScore();
	}

	/**
	 * Returns the current score.
	 */
	public int getScore() {
		return score;
	}
	
	public void printScore() {
		clear();
		painter.printCenter("YOUR SCORE: "+Integer.toString(score));
	}

	/**
	 * Repaints the displayed score.
	 */
	private void repaintScore() {
		clear();
		painter.printCenter(Integer.toString(score));
	}
	
}
