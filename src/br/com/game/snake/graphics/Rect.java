package br.com.game.snake.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Rect extends Drawable {
	private Rectangle rectangle;
	
	public Rect() {
		rectangle = new Rectangle(0, 0, 0, 0);
	}
	
	public Rect(int x, int y, int width, int height) {
		rectangle = new Rectangle(x, y, width, height);
	}
	
	public Rect(Point location, Dimension dimension) {
		rectangle = new Rectangle(location, dimension);
	}

	@Override
	public void draw(Graphics g) {
		g.fillRect(
				(int) rectangle.getLocation().getX(), 
				(int) rectangle.getLocation().getY(), 
				(int) rectangle.getSize().getWidth(), 
				(int) rectangle.getSize().getHeight()
		);
	}
	
	public void setLocation(Point location) {
		rectangle.setLocation(location);
	}
	
	public void setDimension(Dimension dimension) {
		rectangle.setSize(dimension);
	}
	
	public boolean intersects(Rect other) {
		return rectangle.intersects(other.rectangle);
	}
	
	public Point getLocation() {
		return rectangle.getLocation();
	}

	public Dimension getDimension() {
		return rectangle.getSize();
	}
}