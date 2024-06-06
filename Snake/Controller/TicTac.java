import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

public class TicTac {

	Timer timer;
	long last_tick;
	
	static final int TICK_PERIOD = 1; // tick every milli-second.

	public TicTac() { // initialise le timer
		//this.timer = new Timer();
	}

	public void tick() { // tick d'horloge

	}

	private void createTimer() {
		int tick = TICK_PERIOD;
		last_tick = System.currentTimeMillis();
		timer = new Timer(tick, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tick();
			}
		});
		//timer.start();
	}

	public long getTick() {
		return last_tick;
	}

}
