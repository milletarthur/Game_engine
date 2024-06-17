package Automates;

import java.io.IOException;
import java.util.LinkedList;

import gal.ast.AST;
import gal.parser.Parser;

import gal.ast.IVisitor;

import gal.ast.export.Ast2Gal;

public class AutomatonLoader {

	static Object loadAutomata(String filename) {
		//LinkedList<Automate> fsm_list = new LinkedList<Automate>();
		try {
			AST ast = (AST) Parser.from_file(filename);
			
			Ast2Gal vis = new Ast2Gal();
			
			Object  fsm_list = ast.accept(vis);
			
			
			
			
			return fsm_list;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static void main(String[] args) {
		 loadAutomata("resources/automata/apple.gal");
	}
}
