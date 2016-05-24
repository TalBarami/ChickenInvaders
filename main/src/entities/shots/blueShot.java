package entities.shots;


import entities.Visitor;

public class blueShot implements Shot {

	public void shooting(Visitor v) {
		v.visit(this);
	}
}
