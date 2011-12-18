package s3.client.platform;


import com.google.gwt.user.client.Timer;

public class GWTPlatform implements Platform {
	@Override
	public void scheduleLater(Runnable task, int delayMs) {
		Timer timer = wrapWithTimer(task);
		timer.schedule(delayMs);
	}

	private Timer wrapWithTimer(final Runnable task) {
		Timer timer = new Timer() {
			@Override
			public void run() {
				task.run();
			}
		};
		return timer;
	}
}
