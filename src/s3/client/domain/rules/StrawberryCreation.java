package s3.client.domain.rules;

import s3.client.artifact.Artifact;
import s3.client.artifact.ArtifactTracker;
import s3.client.artifact.Strawberry;
import s3.client.domain.GameState;
import s3.client.domain.Position;
import s3.client.platform.GWTPlatform;
import s3.client.platform.Platform;

class StrawberryCreation extends ArtifactCreation {
	/*
	 * Obviously we could use factory here. 
	 * Atm that's not for portability, but for testability. 
	 * Plus we probably don't won't to compile other options
	 */
	Platform platform = new GWTPlatform();
	
	Artifact newArtifact(Position position, GameState game) {
		ArtifactTracker registry = game.getArtifacts();
		registry.removeAllOf(Strawberry.class);
		System.out.println("Creating strawberry at "+position);
		return new Strawberry(position, platform);
	}
}
