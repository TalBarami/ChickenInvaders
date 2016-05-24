package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class greenChicken extends Chicken {
	public final ImageIcon greenChickenIcon;
	
	public greenChicken(Game game, Point p){
		super(game, 6, p);
		greenChickenIcon = Util.getScaledImage("/resources/chicken/chicken_green.PNG", chickenSize.width, chickenSize.height);
	}
	
	@Override
	public void visit(redShot red) {
		return;
	}

	@Override
	public void visit(yellowShot yellow) {
		ArrayList<Point> toKill = new ArrayList<Point>();
		toKill.add(location);
		game.killChicken(toKill);
	}

	@Override
	public void visit(blueShot blue) {
		ArrayList<Point> toKill = new ArrayList<Point>();
		toKill.add(location);
		game.killChicken(toKill);
	}

	@Override
	public void visit(blackShot black) {
		return;
	}

	public ImageIcon getIcon(){
		return greenChickenIcon;
	}
	
}
