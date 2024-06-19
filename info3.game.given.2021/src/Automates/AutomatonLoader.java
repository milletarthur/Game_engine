package Automates;

import java.util.LinkedList;

import Labyrinthe.Field;
import gal.ast.AST;
import gal.parser.Parser;

public class AutomatonLoader {
	static Field f;
	
	public AutomatonLoader(Field field) {
		f = field;
	}

	static Object loadAutomata(String filename) {
		LinkedList<Automate> fsm_list = new LinkedList<Automate>();
		try {
			AST ast = (AST) Parser.from_file(filename);
			GeneralVisitor vis = new GeneralVisitor(f);
			fsm_list = (LinkedList<Automate>) ast.accept(vis);
			return fsm_list;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void main(String[] args) {
		 loadAutomata("resources/automata/apple.gal");
	}
}