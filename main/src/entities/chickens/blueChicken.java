package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class blueChicken extends Chicken {
	private ImageIcon blueChickenIcon;
	
	
	public blueChicken(Game game, Point p){
		super(game,2, p);
		blueChickenIcon= Util.getScaledImage("/resources/chicken/chicken_blue.PNG", chickenSize.width, chickenSize.height);
	}
	
	@Override
	public void visit(redShot red) {
		killTwoChickens(5);
	}

	@Override
	public void visit(yellowShot yellow) {
		killTwoChickens(6);
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
		return blueChickenIcon;
	}
	
	
}
