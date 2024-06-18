package Automates;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Labyrinthe.Field;
import controller.Cell;
import controller.Closest;
import controller.Conjonction;
import controller.Disjonction;
import controller.Egg;
import controller.Explode;
import controller.False;
import controller.Get;
import controller.Got;
import controller.Hit;
import controller.Move;
import controller.Not;
import controller.Pick;
import controller.Power;
import controller.Store;
import controller.Throw;
import controller.True;
import controller.Turn;
import controller.Wait;
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

public class GeneralVisitor implements gal.ast.IVisitor {
	Field f;

	LinkedList<Automates.Automate> l_aut;
	LinkedList<Automates.Transition> l_trans;
	LinkedList<IAction> l_act;
	LinkedList<ICondition> l_cond;
	ICondition cond;
	LinkedList<Automates.State> l_state = new LinkedList<Automates.State>();
	LinkedList<Integer> l_param;
	Automates.State current;
	boolean is_in_mode = false;

	@Override
	public Object visit(Category cat) {
		int category;
		switch (cat.toString()) {
		case "A":
			category = Categorie.A;
			break;
		case "C":
			category = Categorie.C;
			break;
		case "D":
			category = Categorie.D;
			break;
		case "G":
			category = Categorie.G;
			break;
		case "J":
			category = Categorie.J;
			break;
		case "M":
			category = Categorie.M;
			break;
		case "O":
			category = Categorie.O;
			break;
		case "P":
			category = Categorie.P;
			break;
		case "T":
			category = Categorie.T;
			break;
		case "V":
			category = Categorie.V;
			break;
		case "Arobase":
			category = Categorie.Arobase;
			break;
		case "Diese":
			category = Categorie.Diese;
			break;
		case "Tiret":
			category = Categorie.Tiret;
			break;
		default:
			return null;
		}
		l_param.add(category);
		return category;
	}

	@Override
	public Object visit(Direction dir) {
		int direction;
		switch (dir.toString()) {
		case "N":
			direction = toolkit.Direction.N;
			break;
		case "S":
			direction = toolkit.Direction.S;
			break;
		case "E":
			direction = toolkit.Direction.E;
			break;
		case "W":
			direction = toolkit.Direction.W;
			break;
		case "NE":
			direction = toolkit.Direction.NE;
			break;
		case "NW":
			direction = toolkit.Direction.NW;
			break;
		case "SE":
			direction = toolkit.Direction.SE;
			break;
		case "SW":
			direction = toolkit.Direction.SW;
			break;
		case "H":
			direction = toolkit.Direction.H;
			break;
		case "F":
			direction = toolkit.Direction.F;
			break;
		case "B":
			direction = toolkit.Direction.B;
			break;
		case "L":
			direction = toolkit.Direction.L;
			break;
		case "R":
			direction = toolkit.Direction.R;
			break;
		default:
			return null;
		}
		l_param.add(direction);
		return direction;
	}

	@Override
	public Object visit(Key key) {
		int keyboard;
		switch (key.toString()) {
		case "VK_0":
			keyboard = toolkit.KeyBoard.VK_0;
			break;
		case "VK_1":
			keyboard = toolkit.KeyBoard.VK_1;
			break;
		case "VK_2":
			keyboard = toolkit.KeyBoard.VK_2;
			break;
		case "VK_3":
			keyboard = toolkit.KeyBoard.VK_3;
			break;
		case "VK_4":
			keyboard = toolkit.KeyBoard.VK_4;
			break;
		case "VK_5":
			keyboard = toolkit.KeyBoard.VK_5;
			break;
		case "VK_6":
			keyboard = toolkit.KeyBoard.VK_6;
			break;
		case "VK_7":
			keyboard = toolkit.KeyBoard.VK_7;
			break;
		case "VK_8":
			keyboard = toolkit.KeyBoard.VK_8;
			break;
		case "VK_9":
			keyboard = toolkit.KeyBoard.VK_9;
			break;
		case "VK_A":
			keyboard = toolkit.KeyBoard.VK_A;
			break;
		case "VK_B":
			keyboard = toolkit.KeyBoard.VK_B;
			break;
		case "VK_C":
			keyboard = toolkit.KeyBoard.VK_C;
			break;
		case "VK_D":
			keyboard = toolkit.KeyBoard.VK_D;
			break;
		case "VK_E":
			keyboard = toolkit.KeyBoard.VK_E;
			break;
		case "VK_F":
			keyboard = toolkit.KeyBoard.VK_F;
			break;
		case "VK_G":
			keyboard = toolkit.KeyBoard.VK_G;
			break;
		case "VK_H":
			keyboard = toolkit.KeyBoard.VK_H;
			break;
		case "VK_I":
			keyboard = toolkit.KeyBoard.VK_I;
			break;
		case "VK_J":
			keyboard = toolkit.KeyBoard.VK_J;
			break;
		case "VK_K":
			keyboard = toolkit.KeyBoard.VK_K;
			break;
		case "VK_L":
			keyboard = toolkit.KeyBoard.VK_L;
			break;
		case "VK_M":
			keyboard = toolkit.KeyBoard.VK_M;
			break;
		case "VK_N":
			keyboard = toolkit.KeyBoard.VK_N;
			break;
		case "VK_O":
			keyboard = toolkit.KeyBoard.VK_O;
			break;
		case "VK_P":
			keyboard = toolkit.KeyBoard.VK_P;
			break;
		case "VK_Q":
			keyboard = toolkit.KeyBoard.VK_Q;
			break;
		case "VK_R":
			keyboard = toolkit.KeyBoard.VK_R;
			break;
		case "VK_S":
			keyboard = toolkit.KeyBoard.VK_S;
			break;
		case "VK_T":
			keyboard = toolkit.KeyBoard.VK_T;
			break;
		case "VK_U":
			keyboard = toolkit.KeyBoard.VK_U;
			break;
		case "VK_V":
			keyboard = toolkit.KeyBoard.VK_V;
			break;
		case "VK_W":
			keyboard = toolkit.KeyBoard.VK_W;
			break;
		case "VK_X":
			keyboard = toolkit.KeyBoard.VK_X;
			break;
		case "VK_Y":
			keyboard = toolkit.KeyBoard.VK_Y;
			break;
		case "VK_Z":
			keyboard = toolkit.KeyBoard.VK_Z;
			break;
		default:
			return null;
		}
		l_param.add(keyboard);
		return keyboard;
	}

	@Override
	public Object visit(Value v) {
		l_param.add(v.value);
		return v.value;
	}

	@Override
	public Object visit(Underscore u) {
		int i = Categorie.Tiret;
		l_param.add(i);
		return i;
	}

	@Override
	public void enter(FunCall funcall) {
		l_param = new LinkedList<Integer>();
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
		switch (funcall.name) {
		case "Pick":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			return new Pick(f);
		case "Throw":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			return new Throw(f);
		case "Hit":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			return new Hit(f);
		case "Turn":
			if (l_param.size() != 1)
				throw new RuntimeException("Wrong arguments");
			return new Turn(f, l_param.get(0));
		case "Egg":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			return new Egg(f);
		case "Store":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			return new Store(f);
		case "Explode":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			return new Explode(f);
		case "Get":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			return new Get(f);
		case "Power":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			return new Power(f);
		case "Wait":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			return new Wait(f);
		case "Closest":
			if (l_param.size() != 2)
				throw new RuntimeException("Wrong arguments");
			return new Closest(f, l_param.get(0), l_param.get(1));
		case "Move":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			return new Move(f);
		default:
			break;
		}

		ICondition c;
		switch (funcall.name) {
		case "Cell":
			if (l_param.size() != 2)
				throw new RuntimeException("Wrong arguments");
			c = new Cell(f, l_param.get(0), l_param.get(1));
			break;
		case "True":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			c = new True();
			break;
		case "False":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			c = new False();
			break;
		case "Key":
			if (l_param.size() != 1)
				throw new RuntimeException("Wrong arguments");
			c = new controller.Key(f, l_param.get(0));
			break;
		case "Got":
			if (l_param.size() != 1)
				throw new RuntimeException("Wrong arguments");
			c = new Got(f, l_param.get(0));
			break;
		default:
			throw new RuntimeException("Unknown action !");
		}
		l_cond.add(c);
		cond = c;
		return c;
	}

	@Override
	public void enter(BinaryOp binop) {
		l_cond = new LinkedList<ICondition>();
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
		if (l_cond.size() != 2)
			throw new RuntimeException("wrong arguments !");
		ICondition c;
		switch (binop.operator) {
		case "&":
			c = new Disjonction(l_cond.get(0), l_cond.get(1));
			break;
		case "|":
			c = new Conjonction(l_cond.get(0), l_cond.get(1));
			break;
		default:
			throw new RuntimeException("Wrong arguments");
		}
		cond = c;
		return c;
	}

	@Override
	public void enter(UnaryOp unop) {
		l_cond = new LinkedList<ICondition>();
	}

	@Override
	public void exit(UnaryOp unop) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(UnaryOp unop, Object expression) {
		if (l_cond.size() != 1)
			throw new RuntimeException("wrong arguments !");
		ICondition c;
		if (unop.operator.equals("!")) {
			c = new Not(l_cond.get(0));
			cond = c;
			return c;
		}
		throw new RuntimeException("Wrong arguments");
	}

	@Override
	public Object visit(State state) {
		Iterator<Automates.State> i = l_state.iterator();
		String ast_state_name = state.toString();
		while (i.hasNext()) {
			Automates.State s = i.next();
			if (ast_state_name.equals(s.getName()))
				return s;
		}
		Automates.State s = new Automates.State(ast_state_name);
		l_state.add(s);
		return s;
	}

	@Override
	public void enter(Mode mode) {
		current = (Automates.State) visit(mode.state);
		is_in_mode = true;
	}

	@Override
	public void visit(Mode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Mode mode) {
		is_in_mode = false;
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
		l_trans.add(new Automates.Transition());
		l_act = new LinkedList<IAction>();
		l_cond = new LinkedList<ICondition>();
	}

	@Override
	public void exit(Transition transition) {
	}

	@Override
	public Object build(Transition transition, Object condition, Object action, Object target_state) {
		Automates.Transition t = l_trans.getLast();
		Iterator<IAction> i = l_act.iterator();
		while(i.hasNext())
			t.add_action(i.next());
		t.add_condition(cond);
		t.add_source_state(current);
		t.add_cible_state((Automates.State) visit(transition.target));
		return t;
	}

	@Override
	public void enter(Automaton automaton) {
		l_aut.add(new Automates.Automate());
	}

	@Override
	public void exit(Automaton automaton) {
	}

	@Override
	public Object build(Automaton automaton, Object initial_state, List<Object> modes) {
		Automate a = l_aut.getLast();
		a.add_init_state((Automates.State) visit((State) initial_state));
		Iterator<Automates.Transition> i = l_trans.iterator();
		while(i.hasNext())
			a.add_transition(i.next());
		return a;
	}

	@Override
	public void enter(AST ast) {
		l_aut = new LinkedList<Automates.Automate>();
	}

	@Override
	public void exit(AST ast) {
	}

	@Override
	public Object build(AST ast, List<Object> automata) {
		return l_aut;
	}

}
