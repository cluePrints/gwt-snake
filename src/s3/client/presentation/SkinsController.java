package s3.client.presentation;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

public class SkinsController {	
	private List<String> ids = Arrays.asList("theme1", "theme2");
	private SpriteRenderer renderer;
	private Widget[] statics;
	private String lastStyle;
	
	public SkinsController(SpriteRenderer sRenderer, Widget... statics) {
		super();
		this.renderer = sRenderer;
		this.statics = statics;
		switchTo(0);
	}
	
	public void switchTo(int skinIndex) {
		String skinStyleName = ids.get(skinIndex);
		updateFactoryTheme(skinStyleName);
		updateExistingObjects(skinStyleName);
		updateLastStyle(skinStyleName);
	}

	public List<String> getThemeIds() {
		return ids;
	}
	
	private void updateExistingObjects(String newStyle) {
		Collection<UIObject> objectsToStyle = getStyleableObjects();
		for (UIObject o : objectsToStyle) {
			o.removeStyleDependentName(lastStyle);
			o.addStyleDependentName(newStyle);
		}
	}

	private void updateFactoryTheme(String currentId) {
		renderer.setThemeName(currentId);
	}

	private Collection<UIObject> getStyleableObjects() {
		List<UIObject> objectsToStyle = new LinkedList<UIObject>();
		objectsToStyle.addAll(Arrays.asList(statics));
		objectsToStyle.addAll(renderer.getStyledChildren());
		return objectsToStyle;
	}
	
	private void updateLastStyle(String themeId) {
		lastStyle = themeId;
	}

}
