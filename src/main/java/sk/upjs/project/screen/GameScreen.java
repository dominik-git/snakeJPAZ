package sk.upjs.project.screen;

import java.awt.event.KeyEvent;

import sk.upjs.jpaz2.Pane;
import sk.upjs.project.SnakeJPAZ.Launcher;
import sk.upjs.project.constant.Sizes;
import sk.upjs.project.gameController.GameController;
import sk.upjs.project.screenPart.Background;
import sk.upjs.project.screenPart.MusicButton;

/**
 * Class represents main game screen.
 * 
 * @author dominik.kolesar
 *
 */
public class GameScreen extends Pane {

	private GameController controller;
	private static GameScreen instance;
	private MusicButton player;

	public GameScreen() {
		super(Sizes.gameScreenSize, Sizes.gameScreenSize);
		Background bg = new Background(this, "/images/gameBg.png");
		bg.drawBackround();
		controller = new GameController(this);
		player = MusicButton.getInstance();
		this.add(player);
		player.setPosition(Sizes.musicIconX, Sizes.musicIconY);
		setTickPeriod(125);
	}

	public static GameScreen getInstance() {
		if (instance == null) {
			instance = new GameScreen();
		}
		return instance;
	}

	@Override
	protected void onTick() {
		if (!this.controller.canContinue()) {
			Launcher.getInstance().changeScreen(GameOverPane.getInstance());
			ScorePane.getInstance().printScore();
		}
	}

	@Override
	protected void onKeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			controller.getSnake().moveLeft();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			controller.getSnake().moveRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			controller.getSnake().moveUp();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			controller.getSnake().moveDown();
		}

	}

}
