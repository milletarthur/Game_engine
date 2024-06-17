package Automates;

import java.util.LinkedList;
import java.util.List;

import gal.ast.AST;
import gal.ast.Actions;
import gal.ast.Automaton;
import gal.ast.Behaviour;
import gal.ast.BinaryOp;
import gal.ast.Category;
import gal.ast.Condition;
import gal.ast.Direction;
import gal.ast.FunCall;
import gal.ast.Key;
import gal.ast.Mode;
import gal.ast.State;
import gal.ast.Transition;
import gal.ast.UnaryOp;
import gal.ast.Underscore;
import gal.ast.Value;
import toolkit.*;

import toolkit.*;

public class GeneralVisitor implements gal.ast.IVisitor {
	
	LinkedList<Automate> l_aut;
	LinkedList<Transition> l_trans;
	LinkedList<IAction> l_act;
	State current;

	@Override
	public Object visit(Category cat) {
		switch (cat.terminal.content) {
		case "A":
			return Categorie.A;
		case "C":
			return Categorie.C;
		case "D":
			return Categorie.D;
		case "G":
			return Categorie.G;
		case "J":
			return Categorie.J;
		case "M":
			return Categorie.M;
		case "O":
			return Categorie.O;
		case "P":
			return Categorie.P;
		case "T":
			return Categorie.T;
		case "V":
			return Categorie.V;
		case "Arobase":
			return Categorie.Arobase;
		case "Diese":
			return Categorie.Diese;
		case "Tiret":
			return Categorie.Tiret;
		default:
			return null;
		}
	}

	@Override
	public Object visit(Direction dir) {
		switch (dir.terminal.content) {
		case "N":
			return toolkit.Direction.N;
		case "S":
			return toolkit.Direction.S;
		case "E":
			return toolkit.Direction.E;
		case "W":
			return toolkit.Direction.W;
		case "NE":
			return toolkit.Direction.NE;
		case "NW":
			return toolkit.Direction.NW;
		case "SE":
			return toolkit.Direction.SE;
		case "SW":
			return toolkit.Direction.SW;
		case "H":
			return toolkit.Direction.H;
		case "F":
			return toolkit.Direction.F;
		case "B":
			return toolkit.Direction.B;
		case "L":
			return toolkit.Direction.L;
		case "R":
			return toolkit.Direction.R;
		default:
			return null;
		}
	}

	@Override
	public Object visit(Key key) {
		return key.toString();
	}

	@Override
	public Object visit(Value v) {
		return Integer.valueOf(v.toString());
	}

	@Override
	public Object visit(Underscore u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(FunCall funcall) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunCall funcall) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(FunCall funcall) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(FunCall funcall, List<Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(BinaryOp binop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BinaryOp binop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(BinaryOp binop) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(BinaryOp binop, Object left, Object right) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(UnaryOp unop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(UnaryOp unop) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(UnaryOp unop, Object expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(Mode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Mode mode) {
		current = mode.state;
	}

	@Override
	public void exit(Mode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Mode mode, Object source_state, Object behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(Condition condition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Condition condition) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Condition condition, Object expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(Actions action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Actions action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Actions action) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Actions action, String operator, List<Object> funcalls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(Transition transition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Transition transition) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Transition transition, Object condition, Object action, Object target_state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(Automaton automaton) {
		l_aut = new LinkedList<Automate>();
	}

	@Override
	public void exit(Automaton automaton) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Automaton automaton, Object initial_state, List<Object> modes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(AST ast) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(AST ast) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(AST ast, List<Object> automata) {
		return l_aut;
	}

}
