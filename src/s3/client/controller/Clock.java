package s3.client.controller;

import s3.client.domain.GameState;
import s3.client.platform.Platform;

public class Clock {
	private Controller controller;
	private GameState game;
	private Platform platform;
	private boolean paused;
	public Clock(Controller controller, GameState game, Platform platform) {
		super();
		this.controller = controller;
		this.game = game;
		this.platform = platform;
		paused = true;
	}

	public void pause() {
		paused = true;
	}
	
	public void resume() {
		if (!paused)
			return;
		
		paused = false;
		scheduleNextTick();
	}
	

	private void scheduleNextTick() {
		Runnable tickTask = newTickTask();
		
		int delayMs = game.getSpeed().getTimeQuantMs();
		platform.scheduleLater(tickTask, delayMs);
	}
	
	private void tick() {
		if (paused)
			return;
		
		scheduleNextTick();
		controller.onGameClockTick();
	}
	
	private Runnable newTickTask() {
		Runnable tickTask = new Runnable() {
			@Override
			public void run() {
				tick();				
			}			
		};
		return tickTask;
	}
}
