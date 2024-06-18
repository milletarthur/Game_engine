package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import ViewWindow.Window;
import info3.game.Game;

/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 */

public class TicTac {

	Timer timer;
	long last_tick ;
	long init = System.currentTimeMillis();
	
	private static final int TICK_PERIOD = 500; // en milliseconde
	
	private Window w ;
	private TickListener List;

	public TicTac(Window w, TickListener List) { // initialise le timer
		this.w = w ;
		this.createTimer();
		this.List = List;
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
    	last_tick = (System.currentTimeMillis() - init) / 500 ;
        w.repaint();
        //System.out.println("Tick at: " + last_tick);
    }

	public long getTick() {
		return last_tick;
	}

}
