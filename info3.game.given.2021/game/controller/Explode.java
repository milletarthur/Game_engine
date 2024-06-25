package controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import Automates.IAction;
import Labyrinthe.*;

public class Explode implements IAction {

	private Field terrain;
	private TickListener tl;

	public Explode(Field terrain, TickListener tl) {
		this.terrain = terrain;
		this.tl = tl;
	}

	@Override
	public void exec(Entity e) {
		if (e instanceof Zombie || e instanceof Squelette) {
			Random rand = new Random();
			int r = rand.nextInt(101);
			if (r < 100) {
				Entity pick = e.picked();
				e.resetpick();
				if (pick != null) {
					terrain.add(pick, e.ligne(), e.colonne());
					tl.add(pick);
				}
			} else {
				e.resetpick();
			}
			for (int i = 1; i <= 8; i++) {
				int[] coor = terrain.next_to_outside(e, i);
				int l = coor[0];
				int c = coor[1];
				if (coor[0] < 0 || coor[1] < 0 || coor[0] > terrain.get_colonne()-1 || coor[1] > terrain.get_ligne()-1)
					continue;
				Entity pickable = null;
				r = rand.nextInt(10);
				if (r > 30) {
					continue;
				}
				r = rand.nextInt(4);
				switch (r) {
				case 0:
					pickable = new Bombe(l, c);
					break;
				case 1:
					pickable = new Apple(l, c);
					break;
				case 2:
					pickable = new Potion(l, c);
					break;
				case 3:
					pickable = new Pioche(l, c);
					break;
				}
				if (pickable == null) {
					continue;
				}
				terrain.add(pickable, l, c);
				if (!(pickable instanceof Bombe))
					tl.add(pickable);
			}
		} else if (e instanceof Joueur) {
			Entity pick = e.picked();
			if (pick != null) {
				terrain.add(pick, e.ligne(), e.colonne());
				tl.add(pick);
				e.resetpick();
			}
		} else if (e instanceof Lave) {
			Entity elem = terrain.getLastnotSelect(e.ligne(), e.colonne());
			if (! (elem instanceof Lave)) {
				Explode cmd = new Explode(terrain,tl);
				cmd.exec(elem);
			}
		}
		if (e instanceof Mine || e instanceof Bombe) {
//			System.out.print("boom");
//			String classnamelong = e.getClass().getName();
//			String classname = (String) classnamelong.subSequence(classnamelong.indexOf(".") + 1, classnamelong.length());
//			System.out.print(classname);
			if (e instanceof Mine)
				((Mine) e).changeState();
			if (e instanceof Bombe)
				((Bombe) e).changeState();
			for (int i = 0; i > -9; i--) {
				int[] cell = terrain.next_to_outside(e, i);
				int x = cell[0];
				int y = cell[1];
				if (cell[0] < 0 || cell[1] < 0 || cell[0] > terrain.get_colonne()-1 || cell[1] > terrain.get_ligne()-1)
					continue;
				LinkedList<Entity> l = terrain.getElement(x, y);
				int taille = l.size();
				for (int j = 0; j < taille; j++) {
					taille = l.size();
					if (j >= taille)
						break;
					Entity elem = l.get(j);
					if (elem instanceof Mine && ((Mine) elem).exploded())
						continue;
					if (elem instanceof Bombe && ((Bombe) elem).exploded())
						continue;
					if (elem instanceof Cassable && ((Cassable) elem).exploded())
						continue;
					if (elem instanceof Mine || elem instanceof Bombe || elem instanceof Cassable) {
						if (elem instanceof Cassable) 
							((Cassable) elem).changeState();
						Explode ex = new Explode(terrain, tl);
						ex.exec(elem);
						taille--;
					} else if (elem instanceof Joueur || elem instanceof Zombie || elem instanceof Squelette) {
						elem.power(-5);
					}
				}
			}
		}
//		System.out.print("Explode ");
//		String classnamelong = e.getClass().getName();
//		String classname = (String) classnamelong.subSequence(classnamelong.indexOf(".")+1,classnamelong.length());
//		System.out.print(classname);
//		System.out.print(" (");
//		System.out.print(e.ligne());
//		System.out.print(";");
//		System.out.print(e.colonne());
//		System.out.println(")");
		if (! (e instanceof Lave)) {
			e.explode();
			terrain.remove(e.ligne(), e.colonne(), e);
			tl.remove(e);
		}
	}

	@Override
	public String toString() {
		String s = "Explode";
		return s;
	}
}
