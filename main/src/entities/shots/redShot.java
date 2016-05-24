package entities.shots;

import entities.Visitor;

public class redShot implements Shot {

	public void shooting(Visitor v) {
		v.visit(this);
	}
}
