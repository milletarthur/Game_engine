package Automates;

import java.util.LinkedList;
import java.util.List;

import gal.ast.AST;
import gal.parser.Parser;

public class AutomatonLoader {
	
	List<Automate> loadAutomata(String filename) {
	    LinkedList<Automate> fsm_list  = new LinkedList<Automate>();
	    try {
	      AST ast = (AST) Parser.from_file(filename);
	      
	      // TODO à vous de constuire les FSM (automates exécutables) à partir de l'AST
	      
	      return fsm_list;
	    } catch (Exception ex) {
	      return null;
	    }
	  }
}
