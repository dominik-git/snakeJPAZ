package sk.upjs.project.screenPart;

import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class Background extends Turtle {
	private Pane pane;
	private String imageUrl;

	public Background(Pane pane, String imagesrc) {
		this.pane = pane;	
		this.imageUrl = imagesrc;
		
	}
	
	public void drawBackround() {
		pane.add(this);
		pane.setTransparentBackground(false);
		this.setShape(new ImageTurtleShape(this.imageUrl));
		this.center();
		this.stamp();
		pane.remove(this);
	}
	

}
