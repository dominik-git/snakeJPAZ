package sk.upjs.project.screen;

import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.AudioClip;
import sk.upjs.jpaz2.Pane;
import sk.upjs.project.SnakeJPAZ.Launcher;
import sk.upjs.project.constant.Sizes;
import sk.upjs.project.screenPart.Background;
import sk.upjs.project.screenPart.Button;
import sk.upjs.project.screenPart.MusicButton;

/**
 * Class represents game over screen.
 * 
 * @author dominik.kolesar
 *
 */
public class GameOverPane extends Pane {

	private Button startButton;
	private Button exitButton;
	private static GameOverPane instance;
	private ScorePane scorePane;

	public GameOverPane() {
		super(Sizes.gameScreenSize, Sizes.gameScreenSize);
		Background bg = new Background(this, "/images/gameOver.png");
		bg.drawBackround();
		initScreenParts();
	}

	private void initScreenParts() {
		startButton = new Button(this, "/images/playAgain.png", 200, 600, 1);
		exitButton = new Button(this, "/images/exit.png", 200, 700, 1);	
		scorePane = ScorePane.getInstance();
		this.add(scorePane);
		scorePane.setPosition(200, 200);
	}
	
	/**
	 * Gets the instance of the class.
	 */
	public static GameOverPane getInstance() {
		if (instance == null)
			instance = new GameOverPane();
		return instance;
	}


	@Override
	protected void onMousePressed(int x, int y, MouseEvent detail) {
		if (detail.getButton() == MouseEvent.BUTTON1) {
			if (startButton.isMouseOver(x, y)) {
				MusicButton.getInstance().playMusicFootage(new AudioClip("audio", "click.wav", true));
				GameScreen gameScreen = new GameScreen();
				Launcher.getInstance().changeScreen(gameScreen);
				
			}

			else if (exitButton.isMouseOver(x, y)) {
				System.exit(0);
			}
		}
	}
	@Override
	protected boolean onCanClick(int x, int y) {
		return super.onCanClick(x, y) || startButton.isMouseOver(x, y) || exitButton.isMouseOver(x, y);
	}
}
