package s3.client.presentation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.UIObject;

public class SkinsController {	
	private List<String> ids = Arrays.asList("theme1", "theme2");
	private SpriteRenderer renderer;
	private AbsolutePanel playground;
	
	public SkinsController(SpriteRenderer sRenderer, AbsolutePanel playground) {
		super();
		this.renderer = sRenderer;
		this.playground = playground;
		switchTo(0);
	}
	
	public void switchTo(int skinIndex) {
		String themeId = ids.get(skinIndex);
		updateFactoryTheme(themeId);
		updateExistingObjects(themeId);
	}

	public List<String> getThemeIds() {
		return ids;
	}
	
	private void updateExistingObjects(String currentId) {
		Set<UIObject> objectsToStyle = getStyleableObjects();
		for (UIObject o : objectsToStyle) {
			o.setStylePrimaryName(currentId);
		}
	}

	private void updateFactoryTheme(String currentId) {
		renderer.setThemeName(currentId);
	}

	private Set<UIObject> getStyleableObjects() {
		Set<UIObject> objectsToStyle = new HashSet<UIObject>();
		objectsToStyle.add(playground);
		objectsToStyle.addAll(renderer.getStyledChildren());
		return objectsToStyle;
	}
}
