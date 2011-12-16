package s3.client;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.UIObject;

public class SkinsController {
	private List<String> ids = Arrays.asList("theme1", "theme2");
	private String currentId;
	private SnakeRenderer sRenderer;
	private AbsolutePanel playground;
	
	public SkinsController(SnakeRenderer sRenderer, AbsolutePanel playground) {
		super();
		this.sRenderer = sRenderer;
		this.playground = playground;
		switchTo(0);
	}

	public List<String> getIds() {
		return ids;
	}
	
	public void switchTo(int skinIndex) {
		currentId = ids.get(skinIndex);
		sRenderer.setThemeName(currentId);
		Set<UIObject> objectsToStyle = new HashSet<UIObject>();
		objectsToStyle.add(playground);
		objectsToStyle.addAll(sRenderer.getStyledChildren());
		for (UIObject o : objectsToStyle) {
			updateWithCurrentStyle(o);
		}
	}
	
	public void updateWithCurrentStyle(UIObject o) {
		o.setStylePrimaryName(currentId);
	}
}
