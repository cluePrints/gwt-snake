package s3.client.domain.rules;

import s3.client.artifact.Apple;
import s3.client.artifact.Artifact;
import s3.client.domain.GameState;
import s3.client.domain.Position;

class AppleCreation extends ArtifactCreation {	
	Artifact newArtifact(Position position, GameState game) {
		return new Apple(position, game.getScoring());
	}
}
