package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class purpleChicken extends Chicken {
	public final ImageIcon purpleChickenIcon;
	
	public purpleChicken(Game game, Point p){
		super(game, 5, p);
		purpleChickenIcon= Util.getScaledImage("/resources/chicken/chicken_purple.PNG", chickenSize.width, chickenSize.height);
	}
	
	@Override
	public void visit(redShot red) {
		ArrayList<Point> toKill = new ArrayList<Point>();
		toKill.add(location);
		game.killChicken(toKill);
	}

	@Override
	public void visit(yellowShot yellow) {
		return;
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
		return purpleChickenIcon;
	}

}
