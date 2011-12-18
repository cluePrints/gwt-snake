package s3.client.platform;

public interface Platform {
	void scheduleLater(Runnable task, int delayMs);
}