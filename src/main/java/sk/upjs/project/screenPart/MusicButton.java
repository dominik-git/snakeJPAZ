package sk.upjs.project.screenPart;

import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.AudioClip;
import sk.upjs.jpaz2.Pane;

/**
 * Class represents music player.
 * 
 * @author dominik.kolesar
 *
 */
public class MusicButton extends Pane {

	private static MusicButton instance;
	private boolean isMusicOn = true;
	private Button musicOnOffButton;
	private AudioClip musicBg;

	public MusicButton() {
		super(100, 100);
		setBorderWidth(0);
		this.setTransparentBackground(true);
		this.init();
	}

	private void init() {
		musicOnOffButton = new Button(this, "/images/musicOn.png", 45, 45, 0.15);
	}
	
	

	public AudioClip getMusicBg() {
		return musicBg;
	}

	public void setMusicBg(AudioClip musicBg) {
		this.musicBg = musicBg;
	}

	public static MusicButton getInstance() {
		if (instance == null) {
			instance = new MusicButton();
		}
		return instance;
	}

	private void musicOnOff() {
		if (isMusicOn) {
			musicOnOffButton.setImgShape("/images/musicOff.png");
			isMusicOn = false;
		} else if (isMusicOn == false) {
			musicOnOffButton.setImgShape("/images/musicOn.png");
			isMusicOn = true;
		}
	}

	public void playMusicFootage(AudioClip clip) {
		if (isMusicOn) {
			clip.setVolume(0.5);
			clip.playAsActionSound();
		}

	}

	public void playInLoop(AudioClip clip) {
		musicBg = clip;
		if (isMusicOn) {
			musicBg.setVolume(0.1);
			musicBg.playInLoop();
		}
	}

	@Override
	protected void onMousePressed(int x, int y, MouseEvent detail) {
		if (detail.getButton() == MouseEvent.BUTTON1) {
			if (musicOnOffButton.isMouseOver(x, y)) {
				this.musicOnOff();
			}
		}
	}

	@Override
	protected boolean onCanClick(int x, int y) {
		return super.onCanClick(x, y) || musicOnOffButton.isMouseOver(x, y);
	}

}
