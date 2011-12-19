package s3.client.presentation;

import static s3.client.domain.Direction.DOWN;
import static s3.client.domain.Direction.LEFT;
import static s3.client.domain.Direction.RIGHT;
import static s3.client.domain.Direction.UP;

import java.util.Collection;

import s3.client.controller.Controller;
import s3.client.controller.ControllerAware;
import s3.client.domain.CellContent;
import s3.client.domain.GameSpeed;
import s3.client.domain.Position;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite implements View, ControllerAware {
	private static final int CELL_SIZE_PX = 20;	
	SnakeRenderer renderer;
	SkinsController skins;
	Controller controller;
	
	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	interface MainViewUiBinder extends UiBinder<Widget, MainView> {}
	
	@UiField
	ListBox speedChooser;
	
	@UiField
	Button btnIncreaseWidth;
	
	@UiField
	ListBox skinsChooser;
	
	@UiField
	AbsolutePanel playground;
	
	@UiField
	Label currScoreLabel;
	
	@UiField
	Label maxScoreLabel;
	

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
		renderer = new SnakeRenderer(playground);
		skins = new SkinsController(renderer, playground);
		
		playground.addStyleDependentName("playground");
		
		for (String id : skins.getIds()) {
			skinsChooser.addItem(id);
		}
		
		for (GameSpeed speed : GameSpeed.values()) {
			speedChooser.addItem(speed.name());
		}
	}
	
	@UiHandler("btnIncreaseWidth")
	public void onKeyDown(KeyDownEvent event) {
		controller.onKeyDown(event.getNativeKeyCode());
	}

	@UiHandler("btnIncreaseWidth")
	void onIncreaseWidth(ClickEvent e) {
		controller.onGameFieldBoundaryChange(RIGHT);
	}
	
	@UiHandler({"btnDecreaseWidth"})
	void onDecreaseWidth(ClickEvent e) {
		controller.onGameFieldBoundaryChange(LEFT);
	}
	
	@UiHandler("btnIncreaseHeight")
	void onIncreaseHeight(ClickEvent e) {
		controller.onGameFieldBoundaryChange(DOWN);
	}
	
	@UiHandler({"btnDecreaseHeight"})
	void onDecreaseHeight(ClickEvent e) {
		controller.onGameFieldBoundaryChange(UP);
	}
	
	@UiHandler("skinsChooser")
	void onSkinsChange(ChangeEvent e) {
		int selIdx = skinsChooser.getSelectedIndex();
		skins.switchTo(selIdx);
	}

	@UiHandler("speedChooser")
	void onSpeedChange(ChangeEvent e) {
		int selIdx = speedChooser.getSelectedIndex();
		GameSpeed speed = GameSpeed.values()[selIdx];
		controller.onSpeedChange(speed);		
	}

	@Override
	public void updatePlaygroundSize(int horizontalCells, int verticalCells) {
		int width = horizontalCells * CELL_SIZE_PX;
		int height = verticalCells * CELL_SIZE_PX;
		playground.setPixelSize(width, height);
	}
	
	public AbsolutePanel getPlayground() {
		return playground;
	}
	
	public FocusWidget getFocusWidget() {
		return btnIncreaseWidth;
	}
	
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void renderSegments(Collection<Position> segments, CellContent type) {
		renderer.renderRefreshWith(segments, type);
	}
	
	@Override
	public void renderMaxScore(int score) {
		maxScoreLabel.setText(String.valueOf(score));
	}
	
	@Override
	public void renderCurrentScore(int score) {
		currScoreLabel.setText(String.valueOf(score));
	}
	
	@Override
	public void clearPlayground() {
		playground.clear();
	}
}
