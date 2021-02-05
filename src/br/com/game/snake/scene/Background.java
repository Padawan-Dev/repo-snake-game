package br.com.game.snake.scene;
import br.com.game.snake.graphics.Rect;
import br.com.game.snake.util.Constants;

public class Background extends Rect {

	public Background() {
		super(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setColor(Constants.BACKGROUND_COLOR);
	}
}