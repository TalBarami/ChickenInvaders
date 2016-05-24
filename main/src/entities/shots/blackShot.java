package entities.shots;


import entities.Visitor;

public class blackShot implements Shot {

	public void shooting(Visitor v) {
		v.visit(this);
	}
}
