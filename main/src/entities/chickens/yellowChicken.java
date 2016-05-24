package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class yellowChicken extends Chicken {
	public ImageIcon yellowChickenIcon;
	
	public yellowChicken(Game game, Point p){
		super(game, 3, p);
		yellowChickenIcon= Util.getScaledImage("/resources/chicken/chicken_yellow.PNG", chickenSize.width, chickenSize.height);
	}
	
	@Override
	public void visit(redShot red) {
		killTwoChickens(4);
	}

	@Override
	public void visit(yellowShot yellow) {
		ArrayList<Point> toKill = new ArrayList<Point>();
		toKill.add(location);
		game.killChicken(toKill);
	}

	@Override
	public void visit(blueShot blue) {
		killTwoChickens(6);
	}

	@Override
	public void visit(blackShot black) {
		return;
	}

	public ImageIcon getIcon(){
		return yellowChickenIcon;
	}
	
}
