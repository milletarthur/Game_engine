package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import Labyrinthe.Joueur;
import draw.DrawTerrain;
import draw.DrawWindow;
import draw.Viewport;

/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 */

public class TicTac {

	Timer timer;
	long last_tick ;
	long init = System.currentTimeMillis();
	Joueur j1;
	Joueur j2;
	Viewport v1;
	Viewport v2;
	
	private static final int TICK_PERIOD = 100; // en milliseconde
	
	private DrawWindow w ;
	private TickListener List;

	public TicTac(TickListener List, Joueur j1, Joueur j2, Viewport v1, Viewport v2) { // initialise le timer
		this.createTimer();
		this.List = List;
		this.j1 = j1;
		this.j2 = j2;
		this.v1 = v1;
		this.v2 = v2;
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
        v1.centrerViewport(j1);
        v2.centrerViewport(j2);
    }

	public long getTick() {
		return last_tick;
	}


}
