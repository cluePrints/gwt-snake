package s3.client.artifact;

import s3.client.domain.CellContent;
import s3.client.domain.GameState;

public interface Artifact {
	void reflectConsumption(GameState state);
	boolean causesGrowth();
	CellContent type();
}
