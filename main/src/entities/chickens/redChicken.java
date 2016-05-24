package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class redChicken extends Chicken {
	public ImageIcon redChickenIcon;
	
	public redChicken(Game game, Point p){
		super(game, 1, p);
		redChickenIcon= Util.getScaledImage("/resources/chicken/chicken_red.PNG", chickenSize.width, chickenSize.height);
		
	}
	
	@Override
	public void visit(redShot red) {
		ArrayList<Point> toKill = new ArrayList<Point>();
		toKill.add(location);
		game.killChicken(toKill);
	}

	@Override
	public void visit(yellowShot yellow) {
		killTwoChickens(4);
	}

	@Override
	public void visit(blueShot blue) {
		killTwoChickens(5);
	}

	@Override
	public void visit(blackShot black) {
		return;
	}
	
	public ImageIcon getIcon(){
		return redChickenIcon;
	}
	
}
