package entities.shots;

import entities.Visitor;

public class yellowShot implements Shot {

	public void shooting(Visitor v) {
		v.visit(this);
	}
}
