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
 * Class represents Intro screen.
 * @author dominik.kolesar
 *
 */
public class IntroScreenPane extends Pane {

	private Button startButton;
	private Button exitButton;
	private MusicButton player;

	public IntroScreenPane() {
		super(Sizes.gameScreenSize, Sizes.gameScreenSize);
		setBorderWidth(0);
		Background bg = new Background(this, "/images/startBg.png");
		bg.drawBackround();
		this.initScreenParts();
	}

	private void initScreenParts() {
		startButton = new Button(this, "/images/start.png", 200, 400, 1);
		exitButton = new Button(this, "/images/exit.png", 200, 500, 1);
		player = MusicButton.getInstance();
	}

	@Override
	protected void onMousePressed(int x, int y, MouseEvent detail) {
		if (detail.getButton() == MouseEvent.BUTTON1) {
			if (startButton.isMouseOver(x, y)) {
				player.playMusicFootage(new AudioClip("audio", "click.wav", true));
				Launcher.getInstance().changeScreen(GameScreen.getInstance());
				ScorePane.getInstance().resetScore();
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
