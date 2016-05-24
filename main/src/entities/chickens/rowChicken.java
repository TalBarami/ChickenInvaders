package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class rowChicken extends Chicken {
	public final ImageIcon rowChickenIcon;
	
	public rowChicken(Game game, Point p){
		super(game, 11, p);
		rowChickenIcon= Util.getScaledImage("/resources/chicken/Bonus/chicken_row.PNG", chickenSize.width, chickenSize.height);
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
		for(int i=0; i<8; i++)
			if(game.getChickens()[location.x][i] != null)
				toKill.add(game.getChickens()[location.x][i].getLocation());
		game.killChicken(toKill);
	}
	
	public ImageIcon getIcon(){
		return rowChickenIcon;
	}
}
