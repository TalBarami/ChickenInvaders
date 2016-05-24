package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class circleChicken extends Chicken{
	public final ImageIcon circleChickenIcon;
	
	public circleChicken(Game game, Point p){
		super(game, 9, p);
		circleChickenIcon= Util.getScaledImage("/resources/chicken/special/chicken_circle.PNG", chickenSize.width, chickenSize.height);
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
		
		toKill.add(location);
		if(location.x > 0 && location.y > 0 && game.getChickens()[location.x-1][location.y-1] != null)
			toKill.add(game.getChickens()[location.x-1][location.y-1].getLocation());
		if(location.x > 0 && game.getChickens()[location.x-1][location.y] != null)
			toKill.add(game.getChickens()[location.x-1][location.y].getLocation());
		if(location.y > 0 && game.getChickens()[location.x][location.y-1] != null)
			toKill.add(game.getChickens()[location.x][location.y-1].getLocation());
		if(location.x < 3 && location.y < 7 && game.getChickens()[location.x+1][location.y+1] != null)
			toKill.add(game.getChickens()[location.x+1][location.y+1].getLocation());
		if(location.x < 3 && game.getChickens()[location.x+1][location.y] != null)
			toKill.add(game.getChickens()[location.x+1][location.y].getLocation());
		if(location.y < 7 && game.getChickens()[location.x][location.y+1] != null)
			toKill.add(game.getChickens()[location.x][location.y+1].getLocation());
		if(location.x < 3 && location.y > 0 && game.getChickens()[location.x+1][location.y-1] != null)
			toKill.add(game.getChickens()[location.x+1][location.y-1].getLocation());
		if(location.x > 0 && location.y < 7 && game.getChickens()[location.x-1][location.y+1] != null)
			toKill.add(game.getChickens()[location.x-1][location.y+1].getLocation());
		
		game.killChicken(toKill);
	}
	
	public ImageIcon getIcon(){
		return circleChickenIcon;
	}

}
