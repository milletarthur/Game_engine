package Automates;

import java.util.LinkedList;

import gal.ast.AST;
import gal.parser.Parser;

public class AutomatonLoader {

	static Object loadAutomata(String filename) {
		LinkedList<Automate> fsm_list = new LinkedList<Automate>();
		try {
			AST ast = (AST) Parser.from_file(filename);
			GeneralVisitor vis = new GeneralVisitor();
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