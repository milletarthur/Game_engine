package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import draw.DrawWindow;

/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 */

public class TicTac {

	Timer timer;
	long last_tick ;
	long init = System.currentTimeMillis();
	
	private static final int TICK_PERIOD = 1000; // en milliseconde
	
	private DrawWindow w ;
	private TickListener List;

	public TicTac(TickListener List) { // initialise le timer
		this.createTimer();
		this.List = List;
	}
	
	public void add_window(DrawWindow w) {
		this.w = w ;
	}

	private void createTimer() {
        int tick = TICK_PERIOD;
        last_tick = System.currentTimeMillis() - init;
        timer = new Timer(tick, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tick();
            }
        });
        timer.start();
    }

    private void tick() {
    	List.step();
    	last_tick = (System.currentTimeMillis() - init) / TICK_PERIOD ;
        w.repaint();
    }

	public long getTick() {
		return last_tick;
	}

}
