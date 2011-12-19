package s3.client.artifact;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import s3.client.domain.CellContent;
import s3.client.domain.Position;

public class ArtifactRegistry {
	private Map<Position, Artifact> artifacts = new HashMap<Position, Artifact>();
	
	public Map<CellContent, Collection<Position>> byType() {
		Map<CellContent, Collection<Position>> resultMap = new HashMap<CellContent, Collection<Position>>();
		for (Map.Entry<Position, Artifact> pair : artifacts.entrySet()) {			
			Artifact a = pair.getValue();
			CellContent type = a.type();
			Collection<Position> vals = getOrCreate(resultMap, type);
			vals.add(pair.getKey());
		}
		return resultMap;
	}
	
	public void removeAllOf(Class<? extends Artifact> type) {
		Iterator<Entry<Position, Artifact>> it = artifacts.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Position, Artifact> e = it.next();
			Artifact a = e.getValue();
			if (type == a.getClass()) {
				it.remove();
			}
		}
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
		
		artifacts.put(p, newOne);
	}
	
	public void clear() {
		artifacts.clear();
	}
	

	private Collection<Position> getOrCreate(
			Map<CellContent, Collection<Position>> result, CellContent type) {
		Collection<Position> vals = result.get(type);
		if (vals == null) {
			vals = new LinkedList<Position>();
			result.put(type, vals);
		}
		return vals;
	}
}
