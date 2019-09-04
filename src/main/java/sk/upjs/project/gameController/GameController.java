package sk.upjs.project.gameController;

import sk.upjs.jpaz2.AudioClip;
import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;
import sk.upjs.project.constant.Sizes;
import sk.upjs.project.screen.ScorePane;
import sk.upjs.project.screenPart.MusicButton;
import sk.upjs.project.utils.Utils;

public class GameController {

	private Turtle snack = new Turtle();
	private Snake snake;
	private Pane pane;
	private ScorePane scorePane;
	private Turtle headOfSnake;
	private AudioClip audioClip;

	public GameController(Pane pane) {
		snake = new Snake();
		this.headOfSnake = this.snake.getSnakeParts().get(0);
		this.pane = pane;
		this.initGame();

	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public Turtle getPotrava() {
		return snack;
	}

	public void setPotrava(Turtle potrava) {
		this.snack = potrava;
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}

	public void addTurtleOnScreen(Turtle turtle) {
		this.pane.add(turtle);
	}

	public void removeTurtleFromScreen(Turtle turtle) {
		this.pane.remove(turtle);
	}

	public void initGame() {
		for (Turtle turtle : this.snake.getSnakeParts()) {
			this.pane.add(turtle);
			turtle.setPenDown(false);
		}
		this.snake.getSnakeParts().get(0).setPosition(340, 816);
		this.snake.getSnakeParts().get(1).setPosition(340, 850);

		this.pane.add(snack);
		snack.setPenDown(false);
		snack.setShape(new ImageTurtleShape("/images/snack.gif"));
		snack.setDirection(0);
		snack.setPosition(306, 340);
		scorePane = ScorePane.getInstance();
		this.pane.add(scorePane);
		scorePane.resetScore();
		scorePane.setPosition(10, 10);

		audioClip = new AudioClip("audio", "click.wav", true);
	}

	/**
	 * generate snack for snake
	 */
	private void addNewSnackOnScreen() {
		int randomX = Utils.generateRandomNumber();
		int randomY = Utils.generateRandomNumber();
		this.pane.setTickPeriod(0);
		for (int i = 0; i < this.snake.getSnakeParts().size(); i++) {
			// need to ensure that snack will no be generate over snack body
			if (this.snake.getSnakeParts().get(i).distanceTo(randomX, randomY) >= 0
					&& this.snake.getSnakeParts().get(i).distanceTo(randomX, randomY) < 18) {
				randomX = Utils.generateRandomNumber();
				randomY = Utils.generateRandomNumber();
				i = 0;
			}
		}
		this.pane.setTickPeriod(125);
		this.snack = new Turtle();
		this.pane.add(snack);
		snack.setPenDown(false);
		snack.setShape(new ImageTurtleShape("/images/snack.gif"));
		this.snack.setPosition(randomX, randomX);
	}

	public boolean canContinue() {
		this.snake.updatePositionOfSnake();
		this.snake.setCanChangeDirection(true);
		if (headOfSnake.distanceTo(this.snack.getX(), this.snack.getY()) == 0) {
			MusicButton.getInstance().playMusicFootage(audioClip);
			this.addSnakePart();
			this.addNewSnackOnScreen();
			scorePane.updateScore();

		} else if (this.isCollision() || this.isOnTheEdge()) {
			this.pane.setTickPeriod(0);
			return false;
		}
		return true;

	}

	private void addSnakePart() {
		Turtle lastElementOfSnake = this.snake.getSnakeParts().get(this.snake.getSnakeParts().size() - 1);
		lastElementOfSnake.setShape(new ImageTurtleShape("/images/snakeBody.png"));
		this.snack.setShape(new ImageTurtleShape("/images/tail2.gif"));
		this.snack.setPosition(lastElementOfSnake.getX(), lastElementOfSnake.getY());
		this.snack.setDirection(lastElementOfSnake.getDirection());
		this.snake.addNewSnakePart(this.snack);
		this.snake.setOnSnack(true);
	}

	private boolean isCollision() {
		for (int i = 1; i < this.snake.getSnakeParts().size(); i++) {
			double x = this.snake.getSnakeParts().get(i).getX();
			double y = this.snake.getSnakeParts().get(i).getY();
			if (headOfSnake.distanceTo(x, y) == 0) {
				return true;
			}
		}
		return false;
	}

	private boolean isOnTheEdge() {
		if (headOfSnake.getX() == Sizes.gameScreenSize) {
			return true;
		} else if (headOfSnake.getX() == 0) {
			return true;
		} else if (headOfSnake.getY() == Sizes.gameScreenSize) {
			return true;
		} else if (headOfSnake.getY() == 0) {
			return true;
		}
		return false;
	}

}
