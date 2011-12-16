package s3.client;

public interface Artifact {
	void consumed();
}

class Apple implements Artifact {
	@Override
	public void consumed() {
		
	}
}
