package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class columnChicken extends Chicken {
	public final ImageIcon columnChickenIcon;
	
	public columnChicken(Game game, Point p){
		super(game, 10, p);
		columnChickenIcon= Util.getScaledImage("/resources/chicken/Bonus/chicken_Column.PNG", chickenSize.width, chickenSize.height);
	}
	
	@Override
	public void visit(redShot red) {
		return;
	}

	@Override
	public void visit(yellowShot yellow) {
		return;
	}

	@Override
	public void visit(blueShot blue) {
		return;
	}

	@Override
	public void visit(blackShot black) {
		ArrayList<Point> toKill = new ArrayList<Point>();
		for(int i=0; i<4; i++)
			if(game.getChickens()[i][location.y] != null)
				toKill.add(game.getChickens()[i][location.y].getLocation());
		
		game.killChicken(toKill);
	}
	
	public ImageIcon getIcon(){
		return columnChickenIcon;
	}
}
