package s3.client.controller;

 import static java.util.EnumSet.of;
import static s3.client.domain.CellContent.APPLE;
import static s3.client.domain.CellContent.STRAWBERRY;
import static s3.client.domain.GameStatus.IN_PROGRESS;
import static s3.client.domain.GameStatus.OVER;
import static s3.client.domain.GameStatus.PAUSE;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import s3.client.domain.CellContent;
import s3.client.domain.Direction;
import s3.client.domain.GameSpeed;
import s3.client.domain.GameState;
import s3.client.domain.Position;
import s3.client.domain.rules.Rule;
import s3.client.domain.rules.Rules;
import s3.client.platform.Platform;
import s3.client.presentation.View;
import s3.client.scoring.Scoring;

public class Controller {
	Rule rules = Rules.standard();
	GameState game;
	View view;
	Direction newDirection;
	Clock clock;
	Platform platform;	
	
	public Controller(GameState state, View view) {
		super();
		this.game = state;
		this.view = view;
		newDirection = game.getSnakeDirection();
		injectViewWithThis(view);		
	}

	public void onGameClockTick() {
		updateDirectionToLastRequested();
		
		evaluateRules();
		
		handleGameOver();		
		
		render();		
	}

	public void onDirectionChange(Direction desired) {
		Direction currentDirection = game.getSnakeDirection();
		newDirection = DirectionTransitions.from(currentDirection).to(desired);
	}
	
	public void onPauseToggle() {
		togglePause();
	}

	public void init() {
		syncSizeWithView();
	}
	
	public void setClock(Clock clock) {
		this.clock = clock;
	}
	
	public void onSpeedChange(GameSpeed speed) {
		game.setSpeed(speed);
	}
	
	public void onGameFieldBoundaryChange(Direction direction) {
		resizeIfPossible(direction);		
		syncSizeWithView();
	}

	private void resizeIfPossible(Direction direction) {
		int width = game.getHorizontalCellsCount();
		int height = game.getVerticalCellsCount();
		int desiredWidth = width + direction.getDeltaX();
		int desiredHeight = height + direction.getDeltaY();
		
		game.tryResize(desiredWidth, desiredHeight);
	}
	
	void renderArtifacts() {
		Map<CellContent, Collection<Position>> byType = game.getArtifacts().byType();
		for (CellContent type : of(APPLE, STRAWBERRY)) {
			Collection<Position> elements = byType.get(type);
			if (elements == null) {
				elements = Collections.emptySet();
			}
			view.renderSegments(elements, type);
		}
	}


	private void evaluateRules() {
		rules.evaluate(game);
	}

	private void handleGameOver() {
		if (game.getStatus() == OVER) {
			game.reset();
			view.clearPlayground();
		}
	}
	
	/**
	 * This is to prevent problem where user could press different keys 
	 * during one tick and thus change direction to opposite of what was on tick start 
	 */
	private void updateDirectionToLastRequested() {
		game.setSnakeDirection(newDirection);
	}	
	
	private void render() {
		renderArtifacts();		
		renderSnake();		
		renderScores();
	}
	
	private void renderScores() {
		Scoring scoring = game.getScoring();
		view.renderCurrentScore(scoring.getCurrentScore());
		view.renderMaxScore(scoring.getBestScore());
	}

	private void renderSnake() {
		view.renderSegments(game.getSnakeSegments(), CellContent.SNAKE);
	}
	
	private void syncSizeWithView() {
		view.updatePlaygroundSize(game.getHorizontalCellsCount(), game.getVerticalCellsCount());
	}
	
	private void togglePause() {
		if (game.getStatus() == IN_PROGRESS) {
			clock.pause();
			game.setStatus(PAUSE);
		} else if (game.getStatus() == PAUSE) {
			game.setStatus(IN_PROGRESS);
			clock.resume();
		}
	}
	
	private void injectViewWithThis(View view) {
		if (view instanceof ControllerAware) {
			((ControllerAware) view).setController(this);
		}
	}
}
