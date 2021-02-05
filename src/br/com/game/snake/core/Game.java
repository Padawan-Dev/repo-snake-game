package br.com.game.snake.core;

import java.awt.Rectangle;

import br.com.game.snake.graphics.Rect;
import br.com.game.snake.graphics.Renderer;
import br.com.game.snake.scene.Background;
import br.com.game.snake.scene.Food;
import br.com.game.snake.scene.GameOverText;
import br.com.game.snake.scene.Snake;
import br.com.game.snake.util.Constants;
import br.com.game.snake.util.GameUtils;

public class Game implements Runnable {
	private GameWindow gameWindow;
	private Snake snake;
	private Food food;
	private Renderer renderer;
	
	public void start() {
		snake = new Snake();
		gameWindow = new GameWindow(snake);
		food = new Food(gameWindow.getDrawingArea(), snake);
		renderer = gameWindow.getRenderer();
		addElementsToScreen();
	
		new Thread(this).start();
	}

	private void addElementsToScreen() {
		renderer.add(new Background());
		renderer.add(snake);
		renderer.add(food);
	}
	
	@Override
	public void run() {
		while (!isGameOver()) {
			snake.move();
			food.checkIfEaten(snake, gameWindow.getDrawingArea());
			gameWindow.repaint();
			GameUtils.sleep(Constants.SLEEP_TIME);
		}
		
		processGameOver();
	}
	
	private void processGameOver() {
		renderer.remove(snake);
		renderer.remove(food);
		renderer.add(new GameOverText(food.getEatenTimes()));
		gameWindow.repaint();
	}
	
	private boolean isGameOver() {
		return snake.collidesWithItself() || isSnakeHitBounds();
	}
	
	private boolean isSnakeHitBounds() {
		Rect head = snake.getFirstRect();
		Rectangle drawingArea = gameWindow.getDrawingArea();
		
		int headX = (int) head.getLocation().getX();
		int headY = (int) head.getLocation().getY();
		
		int areaX1 = (int) drawingArea.getMinX();
		int areaY1 = (int) drawingArea.getMinY() - Constants.SNAKE_PIECE_SIZE * 2;
		
		int areaX2 = (int) drawingArea.getMaxX();
		int areaY2 = (int) drawingArea.getMaxY();
		
		if (headX <= areaX1 || headX + Constants.SNAKE_PIECE_SIZE >= areaX2) {
			return true;
		}
		
		if (headY <= areaY1 || headY + Constants.SNAKE_PIECE_SIZE >= areaY2) {
			return true;
		}

		return false;
	}
}