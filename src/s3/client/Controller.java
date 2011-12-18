package s3.client;

import static s3.client.domain.CellContent.APPLE;
import static s3.client.domain.CellContent.STRAWBERRY;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import s3.client.domain.CellContent;
import s3.client.domain.Direction;
import s3.client.domain.GameSpeed;
import s3.client.domain.GameState;
import s3.client.domain.Position;
import s3.client.presentation.View;

public class Controller {
	private Set<CellContent> handledTypes = EnumSet.of(APPLE, STRAWBERRY);
	GameState game;
	View view;
	
	public Controller(GameState state, View view) {
		super();
		this.game = state;
		this.view = view;
		view.setController(this);
		syncSizeWithView();
	}
	
	public void speedChanged(GameSpeed speed) {
		game.setSpeed(speed);
	}
	
	public void pushFieldBoundary(Direction direction) {
		int width = game.getHorizontalCellsCount();
		int height = game.getVerticalCellsCount();
		int desiredWidth = width + direction.getDeltaX();
		int desiredHeight = height + direction.getDeltaY();
		
		game.tryResize(desiredWidth, desiredHeight);
		
		syncSizeWithView();
	}
	
	void renderArtifacts() {
		Map<CellContent, Collection<Position>> byType = game.getArtifacts().byType();
		for (CellContent type : handledTypes) {
			Collection<Position> elements = byType.get(type);
			if (elements == null) {
				elements = Collections.emptySet();
			}
			view.renderSegments(elements, type);
		}
	}

	
	private void syncSizeWithView() {
		view.updatePlaygroundSize(game.getHorizontalCellsCount(), game.getVerticalCellsCount());
	}
}
