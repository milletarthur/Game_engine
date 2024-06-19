package Automates;

import java.util.LinkedList;

import Labyrinthe.Field;
import controller.KeyPressed;
import gal.ast.AST;
import gal.parser.Parser;

public class AutomatonLoader {
	private Field f;
	private KeyPressed kp;
	
	public AutomatonLoader(Field field, KeyPressed kp) {
		f = field;
		this.kp = kp;
	}

	public LinkedList<Automate> loadAutomata(String filename) {
		LinkedList<Automate> fsm_list = new LinkedList<Automate>();
		AST ast = null;
		try {
			ast = (AST) Parser.from_file(filename);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
			GeneralVisitor vis = new GeneralVisitor(f, kp);
			fsm_list = (LinkedList<Automate>) ast.accept(vis);
			return fsm_list;
//		} catch (Exception ex) {
//			throw new RuntimeException(ex);
//		}
	}

//	public static void main(String[] args) {
//		 loadAutomata("resources/automata/apple.gal");
//	}
}