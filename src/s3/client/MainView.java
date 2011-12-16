package s3.client;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite implements HasText {
	int squareSize = 20;	
	SnakeRenderer renderer;
	SkinsController skins;
	
	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
		renderer = new SnakeRenderer(playground);
		skins = new SkinsController(renderer, playground);
		
		for (String id : skins.getIds()) {
			skinsChooser.addItem(id);
		}
	}

	@UiField
	Button button;
	
	@UiField
	ListBox skinsChooser;
	
	@UiField
	AbsolutePanel playground;

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}
	
	@UiHandler("skinsChooser")
	void onClick(ChangeEvent e) {
		int selIdx = skinsChooser.getSelectedIndex();
		skins.switchTo(selIdx);
	}


	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}
	
	public void setPlaygroundSize(int horizontalCells, int verticalCells) {
		int width = horizontalCells * squareSize;
		int height = verticalCells * squareSize;
		playground.setPixelSize(width, height);
	}
	
	public AbsolutePanel getPlayground() {
		return playground;
	}
	
	public FocusWidget getFocusWidget() {
		return button;
	}
	
	public void renderSnakeSegments(Collection<Position> segments) {
		renderer.renderRefreshWith(segments);
	}
}
