package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class xorChicken extends Chicken {
	public final ImageIcon xorChickenIcon;
	
	public xorChicken(Game game, Point p){
		super(game, 8, p);
		xorChickenIcon= Util.getScaledImage("/resources/chicken/Bonus/chicken_xor.PNG", chickenSize.width, chickenSize.height);
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
		if(location.x > 0 && location.y > 0 && game.getChickens()[location.x-1][location.y-1] != null)
			toKill.add(game.getChickens()[location.x-1][location.y-1].getLocation());
		if(location.x < 3 && location.y < 7 && game.getChickens()[location.x+1][location.y+1] != null)
			toKill.add(game.getChickens()[location.x+1][location.y+1].getLocation());
		if(location.x < 3 && location.y > 0 && game.getChickens()[location.x+1][location.y-1] != null)
			toKill.add(game.getChickens()[location.x+1][location.y-1].getLocation());
		if(location.x > 0 && location.y < 7 && game.getChickens()[location.x-1][location.y+1] != null)
			toKill.add(game.getChickens()[location.x-1][location.y+1].getLocation());
		game.killChicken(toKill);
	}
	
	public ImageIcon getIcon(){
		return xorChickenIcon;
	}
}
