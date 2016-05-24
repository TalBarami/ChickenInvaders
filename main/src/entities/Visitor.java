package entities;

import entities.shots.blackShot;
import entities.shots.blueShot;
import entities.shots.redShot;
import entities.shots.yellowShot;

/**
 * Created by Tal on 24/05/2016.
 */
public interface Visitor {
    void visit(redShot red);
    void visit(yellowShot yellow);
    void visit(blueShot blue);
    void visit(blackShot black);
}
