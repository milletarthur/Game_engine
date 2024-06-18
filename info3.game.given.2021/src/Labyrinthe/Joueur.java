package Labyrinthe;

import java.util.ArrayList;
import java.util.LinkedList;

import toolkit.Categorie;
import toolkit.Direction;

/*
 * Classe Joueur
 * 
 * x et y sont les coordonnées de ce joueur dans la matrice
 */
public class Joueur extends Entity {
	
	private Entity picked;
	private int layer = 2;
	
	public Joueur(int x, int y) {
		this.x = x ;
		this.y = y ;
		super.vie = 10;
	}
	
	@Override
	public Entity egg(int x, int y) {
		
		return null;
	}
	
	public Entity picked() {
		return picked;
	}
	
	public void setpicked(Entity p) {
		picked = p;
	}

	@Override
	public void move() {
		super.move();
	}

	@Override
	public void pick() {
		
	}

	@Override
	public void turn(int dir) {
		super.turn(dir);
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz() {
		super.turn(Direction.B);
	}

	@Override
	public void explode() {
		super.kill();
	}

	@Override
	public void power(int vie) {
		super.power(vie);
	}

	@Override
	public int hit() {
		if (picked != null)
			return 1;
		return picked.hit();
	}

}
