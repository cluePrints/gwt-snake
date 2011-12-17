package s3.client.artifact;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import s3.client.domain.Position;

public class ArtifactTracker {
	private Map<Position, Artifact> artifacts = new HashMap<Position, Artifact>();
	
	public Map<String, Collection<Position>> byType() {
		Map<String, Collection<Position>> resultMap = new HashMap<String, Collection<Position>>();
		for (Map.Entry<Position, Artifact> pair : artifacts.entrySet()) {			
			Artifact a = pair.getValue();
			String type = a.type();
			Collection<Position> vals = getOrCreate(resultMap, type);
			vals.add(pair.getKey());
		}
		return resultMap;
	}

	private Collection<Position> getOrCreate(
			Map<String, Collection<Position>> result, String type) {
		Collection<Position> vals = result.get(type);
		if (vals == null) {
			vals = new LinkedList<Position>();
			result.put(type, vals);
		}
		return vals;
	}
	
	public Artifact getArtifactAt(Position p) {
		return artifacts.get(p);
	}
	
	public void removeAt(Position p) {
		artifacts.remove(p);
	}
	
	public void tryPutAt(Position p, Artifact newOne) {
		Artifact existing = artifacts.get(p);
		if (existing != null)
			return;
		
		System.out.println("New artifact at: "+p);
		artifacts.put(p, newOne);
	}
	
	public void clear() {
		artifacts.clear();
	}
}
