package br.com.game.snake.scene;
import br.com.game.snake.core.Direction;
import br.com.game.snake.graphics.Rect;
import br.com.game.snake.graphics.Shape;
import br.com.game.snake.util.Constants;
import br.com.game.snake.util.GameUtils;

public class Snake extends Shape {
	private Direction direction;
	private int piecesToElongate;

	public Snake() {
		super(Constants.SNAKE_COLOR);

		direction = Direction.NONE;
		Rect rect = new Rect(Constants.SNAKE_START_X, Constants.SNAKE_START_Y, Constants.SNAKE_PIECE_SIZE,
				Constants.SNAKE_PIECE_SIZE);
		addRect(rect);

		for (int i = 1; i < Constants.SNAKE_SIZE; i++) {
			rect = duplicate(rect, Direction.LEFT);
			addRect(rect);
		}
	}
	
	public synchronized void move() {
		if (direction != Direction.NONE) {
			Rect head = getFirstRect();
			Rect tail = getLastRect();
			GameUtils.moveRect(getRects());
			Rect newHead = duplicate(head, direction);
			getRects().set(0, newHead);
			
			if (piecesToElongate > 0) {
				getRects().add(tail);
				piecesToElongate--;
			}
		}
	}

	public synchronized void up() {
		if (direction.canChangeTo(Direction.UP)) {
			direction = Direction.UP;
		}
	}

	public synchronized void down() {
		if (direction.canChangeTo(Direction.DOWN)) {
			direction = Direction.DOWN;
		}
	}

	public synchronized void left() {
		if (direction.canChangeTo(Direction.LEFT)) {
			direction = Direction.LEFT;
		}
	}

	public synchronized void right() {
		if (direction.canChangeTo(Direction.RIGHT)) {
			direction = Direction.RIGHT;
		}
	}
	
	public boolean collidesWithItself() {
		Rect head = getFirstRect();
		
		for (int i = 1; i < getRects().size(); i++) {
			Rect rect = getRects().get(i);
			if (head.intersects(rect)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void elongate() {
		piecesToElongate = Constants.SNAKE_ELONGATE_PIECES;
	}
}