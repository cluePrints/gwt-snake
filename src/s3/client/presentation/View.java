package s3.client.presentation;

import java.util.Collection;

import s3.client.Controller;
import s3.client.domain.CellContent;
import s3.client.domain.Position;

public interface View {

	void setController(Controller controller);

	void updatePlaygroundSize(int horizontalCells, int verticalCells);

	void renderSegments(Collection<Position> segments, CellContent type);

}