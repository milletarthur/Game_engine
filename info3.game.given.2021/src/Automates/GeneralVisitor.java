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
import controller.Jump;
import controller.KeyPressed;
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

	LinkedList<Automate> l_aut;
	LinkedList<TransitionAutomate> l_trans;
	LinkedList<IAction> l_act;
	//LinkedList<ICondition> l_cond;
	ICondition cond;
	LinkedList<Automates.State> l_state = new LinkedList<Automates.State>();
	LinkedList<Integer> l_param;
	Automates.State current;
	boolean is_in_mode = false;
	KeyPressed kp;
	
	public GeneralVisitor(Field field, KeyPressed kp) {
		f = field;
		this.kp = kp;
	}

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
		case "@":
			category = Categorie.Arobase;
			break;
		case "#":
			category = Categorie.Diese;
			break;
		case "_":
			category = Categorie.Tiret;
			break;
		case "Power":
			category = GotCat.Life;
			break;
		case "Stuff":
			category = GotCat.Stuff;
			break;
//		case "Time":
//			category = GotCat.Time;
//			break;
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
		String s = key.toString();
		switch (s) {
		case "0":
			keyboard = toolkit.KeyBoard.VK_0;
			break;
		case "1":
			keyboard = toolkit.KeyBoard.VK_1;
			break;
		case "2":
			keyboard = toolkit.KeyBoard.VK_2;
			break;
		case "3":
			keyboard = toolkit.KeyBoard.VK_3;
			break;
		case "4":
			keyboard = toolkit.KeyBoard.VK_4;
			break;
		case "5":
			keyboard = toolkit.KeyBoard.VK_5;
			break;
		case "6":
			keyboard = toolkit.KeyBoard.VK_6;
			break;
		case "7":
			keyboard = toolkit.KeyBoard.VK_7;
			break;
		case "8":
			keyboard = toolkit.KeyBoard.VK_8;
			break;
		case "9":
			keyboard = toolkit.KeyBoard.VK_9;
			break;
		case "a":
			keyboard = toolkit.KeyBoard.VK_A;
			break;
		case "b":
			keyboard = toolkit.KeyBoard.VK_B;
			break;
		case "c":
			keyboard = toolkit.KeyBoard.VK_C;
			break;
		case "d":
			keyboard = toolkit.KeyBoard.VK_D;
			break;
		case "e":
			keyboard = toolkit.KeyBoard.VK_E;
			break;
		case "f":
			keyboard = toolkit.KeyBoard.VK_F;
			break;
		case "g":
			keyboard = toolkit.KeyBoard.VK_G;
			break;
		case "h":
			keyboard = toolkit.KeyBoard.VK_H;
			break;
		case "i":
			keyboard = toolkit.KeyBoard.VK_I;
			break;
		case "j":
			keyboard = toolkit.KeyBoard.VK_J;
			break;
		case "k":
			keyboard = toolkit.KeyBoard.VK_K;
			break;
		case "l":
			keyboard = toolkit.KeyBoard.VK_L;
			break;
		case "m":
			keyboard = toolkit.KeyBoard.VK_M;
			break;
		case "n":
			keyboard = toolkit.KeyBoard.VK_N;
			break;
		case "o":
			keyboard = toolkit.KeyBoard.VK_O;
			break;
		case "p":
			keyboard = toolkit.KeyBoard.VK_P;
			break;
		case "q":
			keyboard = toolkit.KeyBoard.VK_Q;
			break;
		case "r":
			keyboard = toolkit.KeyBoard.VK_R;
			break;
		case "s":
			keyboard = toolkit.KeyBoard.VK_S;
			break;
		case "t":
			keyboard = toolkit.KeyBoard.VK_T;
			break;
		case "u":
			keyboard = toolkit.KeyBoard.VK_U;
			break;
		case "v":
			keyboard = toolkit.KeyBoard.VK_V;
			break;
		case "w":
			keyboard = toolkit.KeyBoard.VK_W;
			break;
		case "x":
			keyboard = toolkit.KeyBoard.VK_X;
			break;
		case "y":
			keyboard = toolkit.KeyBoard.VK_Y;
			break;
		case "z":
			keyboard = toolkit.KeyBoard.VK_Z;
			break;
		case "FU":
			keyboard = toolkit.KeyBoard.VK_UP;
			break;
		case "FR":
			keyboard = toolkit.KeyBoard.VK_RIGHT;
			break;
		case "FD":
			keyboard = toolkit.KeyBoard.VK_DOWN;
			break;
		case "FL":
			keyboard = toolkit.KeyBoard.VK_LEFT;
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
	}

	@Override
	public void exit(FunCall funcall) {
	}

	@Override
	public Object build(FunCall funcall, List<Object> parameters) {
		switch (funcall.name) {
		case "Pick":
			return new Pick(f);
		case "Throw":
			return new Throw(f);
		case "Hit":
			return new Hit(f);
		case "Turn":
			return new Turn(f, (int) parameters.get(0)); 
		case "Egg":
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
		case "Jump":
			if (l_param.size() != 0)
				throw new RuntimeException("Wrong arguments");
			return new Jump(f);
		default:
			break;
		}

		ICondition c;
		switch (funcall.name) {
		case "Cell":
				c = new Cell(f, (int) parameters.get(0),(int) parameters.get(1));
			break;
		case "True":
			c = new True();
			break;
		case "False":
			c = new False();
			break;
		case "Key":
			c = new controller.Key(f, (int) parameters.get(0), kp);  //l_param.get(0), kp);
			break;
		case "Got":
			c = new Got(f, (int) parameters.get(0));
			break;
		default:
			throw new RuntimeException("Unknown action !");
		}
		cond = c;
		return c;
	}

	@Override
	public void enter(BinaryOp binop) {
	}

	@Override
	public void visit(BinaryOp binop) {
	}

	@Override
	public void exit(BinaryOp binop) {
	}

	@Override
	public Object build(BinaryOp binop, Object left, Object right) {
		ICondition c;
		switch (binop.operator) {
		case "&":
			c = new Conjonction((ICondition) left, (ICondition) right);  //l_cond.get(l_cond.size() - 2), l_cond.get(l_cond.size() - 1));
			if (left instanceof Cell)
				((Cell) left).toString();
			break;
		case "/":
			c = new Disjonction((ICondition) left, (ICondition) right); //l_cond.get(l_cond.size() - 2), l_cond.get(l_cond.size() - 1));
			break;
		default:
			throw new RuntimeException("Wrong arguments");
		}
		//l_cond.add(c);
		cond = c;
		return c;
	}

	@Override
	public void enter(UnaryOp unop) {
	}

	@Override
	public void exit(UnaryOp unop) {
	}

	@Override
	public Object build(UnaryOp unop, Object expression) {
		ICondition c;
		if (unop.operator.equals("!")) {
			c = new Not((ICondition) expression); //l_cond.get(l_cond.size() - 1));
			//l_cond.add(c);
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
		l_trans = new LinkedList<TransitionAutomate>();
		current = (Automates.State) visit(mode.state);
		is_in_mode = true;
	}

	@Override
	public void visit(Mode mode) {
	}

	@Override
	public void exit(Mode mode) {
		is_in_mode = false;
	}

	@Override
	public Object build(Mode mode, Object source_state, Object behaviour) {
		return l_trans;
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		return l_trans;
	}

	@Override
	public void enter(Condition condition) {
	}

	@Override
	public void exit(Condition condition) {
	}

//	private List<Object> parameterToObject(List<Parameter> l){
//		Iterator<Parameter> i = l.iterator();
//		List<Object> rv = new LinkedList<Object>();
//		while(i.hasNext())
//			rv.add(i.next());
//		return rv;
//	}
	
	//Je ne suis pas sure des params des fonctions
	@Override
	public Object build(Condition condition, Object expression) {
		if(expression instanceof UnaryOp)
			return build((UnaryOp) expression, expression);
		if(expression instanceof BinaryOp)
			return build((BinaryOp) expression, ((BinaryOp) expression).left_operand, ((BinaryOp) expression).right_operand);
		return expression;//build((FunCall) expression, parameterToObject(((FunCall) expression).parameters));
	}

	@Override
	public void enter(Actions action) {
	}

	@Override
	public void visit(Actions action) {
	}

	@Override
	public void exit(Actions action) {
	}

	@Override
	public Object build(Actions action, String operator, List<Object> funcalls) {
		Iterator<Object> i = funcalls.iterator();
		while(i.hasNext()) {
			//FunCall act = (FunCall) i.next();
			l_act.add((IAction) i.next());//(IAction) build(act, parameterToObject(act.parameters)));
		}
		return l_act;
	}

	@Override
	public void enter(Transition transition) {
		l_trans.add(new TransitionAutomate());
		l_act = new LinkedList<IAction>();
		//l_cond = new LinkedList<ICondition>();
	}

	@Override
	public void exit(Transition transition) {
	}

	@Override
	public Object build(Transition transition, Object condition, Object action, Object target_state) {
		TransitionAutomate t = l_trans.getLast();
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
		l_aut.add(new Automate());
	}

	@Override
	public void exit(Automaton automaton) {
	}

	@Override
	public Object build(Automaton automaton, Object initial_state, List<Object> modes) {
		Automate a = l_aut.getLast();
		a.set_name(automaton.name);
		a.change_init((Automates.State) initial_state);//(Automates.State) visit((State) initial_state));
		Iterator<TransitionAutomate> i = l_trans.iterator();
		while(i.hasNext())
			a.add_transition(i.next());
		return a;
	}

	@Override
	public void enter(AST ast) {
		l_aut = new LinkedList<Automate>();
	}

	@Override
	public void exit(AST ast) {
	}

	@Override
	public Object build(AST ast, List<Object> automata) {
		return l_aut;
	}

}
