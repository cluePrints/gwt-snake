package s3.client.presentation;

import static s3.client.domain.Direction.DOWN;
import static s3.client.domain.Direction.LEFT;
import static s3.client.domain.Direction.RIGHT;
import static s3.client.domain.Direction.UP;

import java.util.Collection;

import s3.client.Controller;
import s3.client.domain.Position;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite {
	int squareSize = 20;	
	SnakeRenderer renderer;
	SkinsController skins;
	Controller controller;
	
	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	interface MainViewUiBinder extends UiBinder<Widget, MainView> {}

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
		renderer = new SnakeRenderer(playground);
		skins = new SkinsController(renderer, playground);
		
		for (String id : skins.getIds()) {
			skinsChooser.addItem(id);
		}
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	@UiField
	Button btnIncreaseWidth;
	
	@UiField
	ListBox skinsChooser;
	
	@UiField
	AbsolutePanel playground;

	@UiHandler("btnIncreaseWidth")
	void onIncreaseWidth(ClickEvent e) {
		controller.pushFieldBoundary(RIGHT);
	}
	
	@UiHandler({"btnDecreaseWidth"})
	void onDecreaseWidth(ClickEvent e) {
		controller.pushFieldBoundary(LEFT);
	}
	
	@UiHandler("btnIncreaseHeight")
	void onIncreaseHeight(ClickEvent e) {
		controller.pushFieldBoundary(DOWN);
	}
	
	@UiHandler({"btnDecreaseHeight"})
	void onDecreaseHeight(ClickEvent e) {
		controller.pushFieldBoundary(UP);
	}
	
	@UiHandler("skinsChooser")
	void onSkinsChange(ChangeEvent e) {
		int selIdx = skinsChooser.getSelectedIndex();
		skins.switchTo(selIdx);
	}

	
	public void updatePlaygroundSize(int horizontalCells, int verticalCells) {
		int width = horizontalCells * squareSize;
		int height = verticalCells * squareSize;
		playground.setPixelSize(width, height);
	}
	
	public AbsolutePanel getPlayground() {
		return playground;
	}
	
	public FocusWidget getFocusWidget() {
		return btnIncreaseWidth;
	}
	
	public void renderSnakeSegments(Collection<Position> segments) {
		renderer.renderRefreshWith(segments, SnakeRenderer.SNAKE);
}
}
