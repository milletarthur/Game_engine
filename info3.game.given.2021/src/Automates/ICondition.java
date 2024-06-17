package Automates;

import Labyrinthe.Entity;
import toolkit.Direction;
import toolkit.Categorie;

public interface ICondition {

	public boolean eval(Entity e);
	
	public void setDir(Direction dir);
	
	public void setCat(Categorie cat);
	
	public Categorie getCategorie();
	
	public Direction getDirection();
}
