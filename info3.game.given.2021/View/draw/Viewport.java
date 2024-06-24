package draw;

import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.*;

import Labyrinthe.Entity;

public class Viewport extends JViewport {

	private static final long serialVersionUID = 1L;
	private DrawTerrain dt;
	private int T_case, visibility;
	/*private int current_x, current_y, endx, endy;
	private long startTime;
	private Timer timer;
	private static final int SLIDE_DURATION = 500;
    private static final int TIMER_DELAY = 20;*/

	public Viewport(DrawTerrain dt, int T_case, int visibility) {
		this.dt = dt;
		this.T_case = T_case;
		this.visibility = visibility * T_case * 2 + T_case ;
		this.setPreferredSize(new Dimension(this.visibility, this.visibility));
		this.setView(dt);
	}

	public void centrerViewport(Entity e) {
		int x = e.ligne() * T_case + T_case / 2 - this.visibility / 2;
		int y = e.colonne() * T_case + T_case / 2 - this.visibility / 2;
		dt.repaint();
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		this.setViewPosition(new Point(y, x));		
	}
	
	/*public void slideTo(int targetX, int targetY) {
        endx = targetX;
        endy = targetY;
        startTime = System.currentTimeMillis();

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                float progress = Math.min(1.0f, (float) elapsedTime / SLIDE_DURATION);

                int currentX = (int) (current_x + progress * (endx - current_x));
                int currentY = (int) (current_y + progress * (endy - current_y));

                setViewPosition(new Point(currentY, currentX));

                if (progress >= 1.0f) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }*/

}