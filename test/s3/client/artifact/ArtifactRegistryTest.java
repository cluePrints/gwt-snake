package s3.client.artifact;

import static s3.client.domain.Position.at;

import org.junit.Assert;
import org.junit.Test;

public class ArtifactRegistryTest {
	@Test
	public void shouldProperlyRemoveByClass() {
		ArtifactRegistry registry = new ArtifactRegistry();
		Apple apple = new Apple(null, null);
		registry.tryPutAt(at(1,2), apple);
		registry.tryPutAt(at(2,2), new Strawberry(null, null));
		registry.tryPutAt(at(2,3), apple);
		Assert.assertEquals(2, registry.byType().size());
		
		registry.removeAllOf(Strawberry.class);
		
		Assert.assertEquals(1, registry.byType().size());
		Object remainingElementsType = registry.byType().keySet().iterator().next();
		Assert.assertEquals(apple.type(), remainingElementsType);
	}
}
