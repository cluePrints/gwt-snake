package s3.client.presentation;

import s3.client.domain.CellContent;

public class StyleNames {
	public static final String PLAYGROUND = "playground";
	public static final String APPLE = "apple";
	public static final String SNAKE_SEGMENT = "snakeSegment";
	public static final String STRAWBERRY = "strawberry";
	
	public static String forCellContent(CellContent c) {
		switch (c) {
			case APPLE 		: return APPLE;
			case SNAKE 		: return SNAKE_SEGMENT;
			case STRAWBERRY : return STRAWBERRY;
		}
		return null;
	}
}
