package s3.client.domain;

public enum CellContent {
	APPLE("apple"),
	STRAWBERRY("strawberry"),
	SNAKE("snakeSegment");
	private String styleName;

	private CellContent(String style) {
		this.styleName = style;
	}
	
	public String getStyleName() {
		return styleName;
	}
}
