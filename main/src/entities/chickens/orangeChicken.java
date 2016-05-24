package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class orangeChicken extends Chicken {
	public final ImageIcon orangeChickenIcon;
	
	public orangeChicken(Game game, Point p){
		super(game, 4, p);
		orangeChickenIcon= Util.getScaledImage("/resources/chicken/chicken_orange.PNG", chickenSize.width, chickenSize.height);
	}
	
	@Override
	public void visit(redShot red) {
		ArrayList<Point> toKill = new ArrayList<Point>();
		toKill.add(location);
		game.killChicken(toKill);
	}

	@Override
	public void visit(yellowShot yellow) {
		ArrayList<Point> toKill = new ArrayList<Point>();
		toKill.add(location);
		game.killChicken(toKill);
	}

	@Override
	public void visit(blueShot blue) {
		return;
	}

	@Override
	public void visit(blackShot black) {
		return;
	}
	
	public ImageIcon getIcon(){
		return orangeChickenIcon;
	}

}
