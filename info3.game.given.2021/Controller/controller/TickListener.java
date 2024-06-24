package controller;

import java.util.Iterator;
import java.util.LinkedList;

import Automates.Automate;
import Labyrinthe.Entity;
import Labyrinthe.Field;
import Labyrinthe.Joueur;
import listener.JSONWindow;
import toolkit.Pair;

public class TickListener {
	LinkedList<Pair<Automate, Entity>> auto_list;
	LinkedList<Automate> all_auto_list;

	public TickListener(Field terrain) {
		auto_list = new LinkedList<Pair<Automate, Entity>>();
	}

	public void add(Automate a, Entity e) {
		e.setCurrent(a.getInit());
		Pair<Automate, Entity> p = new Pair<Automate, Entity>(a, e);
		auto_list.add(p);
	}

	public void setAllAutoList(LinkedList<Automate> l) {
		all_auto_list = l;
	}
	
	public void remove(Entity e) {
		int length = auto_list.size();
		for (int i = 0; i < length; i++) {
			Pair<Automate, Entity> p = auto_list.get(i);
			if (p.geto2().equals(e))
				auto_list.remove(p);
		}
	}

	public void add(Entity e) {
		String classnamelong = e.getClass().getName();
		String classname = (String) classnamelong.subSequence(classnamelong.indexOf(".") + 1, classnamelong.length());
		int length = all_auto_list.size();
		for (int i = 0; i < length; i++) {
			Automate a = all_auto_list.get(i);
			String name = a.get_name();
			switch (classname) {
			case "Apple":
				if (name.equals(JSONWindow.aut_apple))
					add(a, e);
				break;
			case "Arc":
				if (name.equals(JSONWindow.aut_arc))
					add(a, e);
				break;
			case "Bombe":
				if (name.equals(JSONWindow.aut_bombe))
					add(a, e);
				break;
			case "Cassable":
				if (name.equals(JSONWindow.aut_cassable))
					add(a, e);
				break;
			case "Epee":
				if (name.equals(JSONWindow.aut_epee))
					add(a, e);
				break;
			case "Fleche":
				if (name.equals(JSONWindow.aut_fleche))
					add(a, e);
				break;
			case "Interrupteur":
				if (name.equals(JSONWindow.aut_interrupteur))
					add(a, e);
				break;
			case "Invisible":
				if (name.equals(JSONWindow.aut_invisible))
					add(a, e);
				break;
			case "Joueur":
				if (((Joueur) e).team() == 1) {
					if (name.equals(JSONWindow.aut_j1))
						add(a, e);
				} else {
					if (name.equals(JSONWindow.aut_j2))
						add(a, e);
				}
				break;
			case "Lave":
				if (name.equals(JSONWindow.aut_lave))
					add(a, e);
				break;
			case "Mine":
				if (name.equals(JSONWindow.aut_mine))
					add(a, e);
				break;
			case "Normal":
				if (name.equals(JSONWindow.aut_normal))
					add(a, e);
				break;
			case "Pioche":
				if (name.equals(JSONWindow.aut_pioche))
					add(a, e);
				break;
			case "Porte":
				if (name.equals(JSONWindow.aut_porte))
					add(a, e);
				break;
			case "Potion":
				if (name.equals(JSONWindow.aut_potion))
					add(a, e);
				break;
			case "Sable":
				if (name.equals(JSONWindow.aut_sable))
					add(a, e);
				break;
			case "Selection":
				if (name.equals(JSONWindow.aut_selection))
					add(a, e);
				break;
			case "Squelette":
				if (name.equals(JSONWindow.aut_squelette))
					add(a, e);
				break;
			case "Teleporteur":
				if (name.equals(JSONWindow.aut_teleporteur))
					add(a, e);
				break;
			case "Void":
				if (name.equals(JSONWindow.aut_void))
					add(a, e);
				break;
			case "Zombie":
				if (name.equals(JSONWindow.aut_zombie))
					add(a, e);
				break;
			default:
			}
		}
	}

	public void step() {
		int length = auto_list.size();
		for (int i = 0; i < length; i++) {
			Pair<Automate, Entity> p = auto_list.get(i);
			Automate auto = p.geto1();
			Entity e = p.geto2();
			if (e.getCurrent().getName().equals(""))
				auto_list.remove(p);
			auto.step(e);
		}
	}

}
