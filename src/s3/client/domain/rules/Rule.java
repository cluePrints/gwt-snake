package s3.client.domain.rules;

import s3.client.domain.GameState;

public interface Rule {
	public void evaluate(GameState state);
}
