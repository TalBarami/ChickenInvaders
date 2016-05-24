package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class plusChicken extends Chicken {
	public final ImageIcon plusChickenIcon;
	
	public plusChicken(Game game, Point p){
		super(game, 8, p);
		plusChickenIcon= Util.getScaledImage("/resources/chicken/special/chicken_plus.PNG", chickenSize.width, chickenSize.height);
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
		for(int i=0; i<8; i++)
			if(game.getChickens()[location.x][i] != null)
				toKill.add(game.getChickens()[location.x][i].getLocation());
		game.killChicken(toKill);
	}
	
	public ImageIcon getIcon(){
		return plusChickenIcon;
	}
}
