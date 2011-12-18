package s3.client.platform;


import java.util.Timer;
import java.util.TimerTask;

public class J2SEPlatform implements Platform {
	@Override
	public void scheduleLater(Runnable task, int delayMs) {
		Timer timer = new Timer();
		timer.schedule(wrap(task), delayMs);
	}

	private TimerTask wrap(final Runnable task) {
		return new TimerTask() {					
			@Override
			public void run() {
				task.run();
			}
		};
	}
}
