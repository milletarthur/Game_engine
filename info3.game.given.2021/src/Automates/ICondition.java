package Automates;

import Labyrinthe.Entity;
import toolkit.Direction;
import toolkit.Categorie;

public interface ICondition {

	public boolean eval(Entity e);
	
	public void setDir(int dir);
	
	public void setCat(int cat);
	
	public int getCategorie();
	
	public int getDirection();
}
