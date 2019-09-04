package sk.upjs.project.SnakeJPAZ;

import sk.upjs.jpaz2.*;
import sk.upjs.project.screen.IntroScreenPane;
import sk.upjs.project.screenPart.MusicButton;

public class Launcher {

	private JPAZWindow mainWindow;

	/**
	 * The instance of the game.
	 */
	private static Launcher instance;

	private Pane currectScreen;

	public JPAZWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(JPAZWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public void startGame() {
		currectScreen = new IntroScreenPane();
		mainWindow = new JPAZWindow(currectScreen);
	}

	/**
	 * Gets the instance of the class.
	 */
	public static Launcher getInstance() {
		if (instance == null)
			instance = new Launcher();
		return instance;
	}

	public void changeScreen(Pane screen) {
		currectScreen = screen;
		mainWindow.rebindWithEffect(screen, TransitionEffect.FADE_OUT_BLACK_FADE_IN, 500);
		MusicButton.getInstance().getMusicBg().stop();
	}

	public static void main(String[] args) {
		Launcher.getInstance().startGame();
		MusicButton.getInstance().playInLoop(new AudioClip("audio", "bgMusic.wav", true));
	}
}

