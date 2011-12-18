package s3.client.presentation;

import static s3.client.domain.Direction.DOWN;
import static s3.client.domain.Direction.LEFT;
import static s3.client.domain.Direction.RIGHT;
import static s3.client.domain.Direction.UP;

import java.util.Collection;

import s3.client.Controller;
import s3.client.domain.CellContent;
import s3.client.domain.GameSpeed;
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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite implements View {
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
		
		for (GameSpeed speed : GameSpeed.values()) {
			speedChooser.addItem(speed.name());
		}
	}
	
	/* (non-Javadoc)
	 * @see s3.client.presentation.View#setController(s3.client.Controller)
	 */
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
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

	@UiHandler("speedChooser")
	void onSpeedChange(ChangeEvent e) {
		int selIdx = speedChooser.getSelectedIndex();
		GameSpeed speed = GameSpeed.values()[selIdx];
		controller.speedChanged(speed);		
	}

	@Override
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
	
	@Override
	public void renderSegments(Collection<Position> segments, CellContent type) {
		renderer.renderRefreshWith(segments, type);
	}
	
	public void reflectMaxScore(int score) {
		maxScoreLabel.setText(String.valueOf(score));
	}
	
	public void reflectCurrentScore(int score) {
		currScoreLabel.setText(String.valueOf(score));
	}
}
