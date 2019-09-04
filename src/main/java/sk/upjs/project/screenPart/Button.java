package sk.upjs.project.screenPart;

import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class Button extends Turtle {

	public Button(Pane pane, String picture, double x, double y, double scale) {
		this.makeButton(pane, picture, x, y, scale);
	}

	public void setImgShape(String imgSrc) {
		this.setShape(new ImageTurtleShape(imgSrc));
	}

	public void makeButton(Pane pane, String picture, double x, double y, double scale) {
		this.setShape(new ImageTurtleShape(picture));
		pane.add(this);
		this.setPosition(x, y);
		this.setScale(scale);
	}

	public boolean isMouseOver(int x, int y) {
		return containsInShape(x, y);
	}

}
