package sk.upjs.project.gameController;

import java.util.ArrayList;
import java.util.List;
import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Turtle;
import sk.upjs.project.constant.Sizes;

public class Snake {

	private List<Turtle> snakeParts = new ArrayList<>();
	private int angle;
	private boolean isOnSnack;
	private boolean canChangeDirection = true;

	public Snake() {
		this.initSnake();
	}

	/**
	 * initialize snake's body
	 */
	private void initSnake() {
		Turtle headOfSnake = new Turtle();
		Turtle snakePart = new Turtle();
		headOfSnake.setShape(new ImageTurtleShape("/images/head2.png"));
		snakeParts.add(headOfSnake);
		snakePart.setShape(new ImageTurtleShape("/images/snakeBody.png"));
		snakeParts.add(snakePart);
	}

	/**
	 * move with whole parts of snake
	 */
	public void updatePositionOfSnake() {
		List<Integer> oldTurtles = new ArrayList<>();
		for (Turtle turtle : this.snakeParts) {
			oldTurtles.add((int) turtle.getDirection());
		}
		this.snakeParts.get(0).setDirection(this.angle);
		this.snakeParts.get(0).step(Sizes.snakeStep);
		if (this.isOnSnack) {
			for (int i = 1; i < this.snakeParts.size() - 1; i++) {
				this.snakeParts.get(i).setDirection(oldTurtles.get(i - 1));
				this.snakeParts.get(i).step(Sizes.snakeStep);
			}
		} else {
			for (int i = 1; i < this.snakeParts.size(); i++) {
				this.snakeParts.get(i).setDirection(oldTurtles.get(i - 1));
				this.snakeParts.get(i).step(Sizes.snakeStep);
			}
		}
		this.isOnSnack = false;
	}

	public void addNewSnakePart(Turtle turtle) {
		this.snakeParts.add(turtle);
	}

	public List<Turtle> getSnakeParts() {
		return snakeParts;
	}

	public void setSnakeParts(List<Turtle> snake) {
		this.snakeParts = snake;
	}

	public boolean isOnSnack() {
		return isOnSnack;
	}

	public void setOnSnack(boolean isOnSnack) {
		this.isOnSnack = isOnSnack;
	}

	public boolean isCanChangeDirection() {
		return canChangeDirection;
	}

	public void setCanChangeDirection(boolean canChangeDirection) {
		this.canChangeDirection = canChangeDirection;
	}

	public void moveLeft() {

		if (angle != 90 && canChangeDirection == true) {
			angle = 270;
			canChangeDirection = false;
		}
	}

	public void moveRight() {
		if (angle != 270 && canChangeDirection == true) {
			angle = 90;
			canChangeDirection = false;
		}
	}

	public void moveUp() {
		if (angle != 180 && canChangeDirection == true) {
			angle = 0;
			canChangeDirection = false;
		}
	}

	public void moveDown() {
		if (angle != 0 && canChangeDirection == true) {
			angle = 180;
			canChangeDirection = false;
		}
	}

}
