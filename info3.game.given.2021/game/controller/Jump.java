package controller;

import java.util.LinkedList;

import Automates.IAction;
import Labyrinthe.*;

public class Jump implements IAction {

	private Field terrain;

	public Jump(Field terrain) {
		this.terrain = terrain;
	}

	@Override
	public void exec(Entity e) {
		if (e instanceof Zombie) {
			LinkedList<Entity> l_entity = terrain.getElement(e.ligne(), e.colonne());
			Entity entity = l_entity.getLast();
			if (entity instanceof Selection) {
				int team = entity.team();
				((Zombie) e).setOtherTeam(team);
			}
		} else if (e instanceof Squelette) {
			LinkedList<Entity> l_entity = terrain.getElement(e.ligne(), e.colonne());
			Entity entity = l_entity.getLast();
			if (entity instanceof Selection) {
				int team = entity.team();
				((Squelette) e).setOtherTeam(team);
			}
		}
		e.jump();
	}

	@Override
	public String toString() {
		String s = "Jump";
		return s;
	}

}
