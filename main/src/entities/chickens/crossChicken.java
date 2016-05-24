package entities.chickens;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Game.Game;
import Game.Util;
import entities.shots.*;

public class crossChicken extends Chicken {
	public final ImageIcon crossChickenIcon;
	
	public crossChicken(Game game, Point p){
		super(game, 12, p);
		crossChickenIcon = Util.getScaledImage("/resources/chicken/special/chicken_x.PNG", chickenSize.width, chickenSize.height);
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
		
		for(int i=1; i<4; i++){
			if(location.x+i < 4 && location.y+i < 8 && game.getChickens()[location.x+i][location.y+i] != null)
				toKill.add(game.getChickens()[location.x+i][location.y+i].getLocation());
			if(location.x+i < 4 && location.y-i > -1 && game.getChickens()[location.x+i][location.y-i] != null)
				toKill.add(game.getChickens()[location.x+i][location.y-i].getLocation());
			if(location.x-i > -1 && location.y+i < 8 && game.getChickens()[location.x-i][location.y+i] != null)
				toKill.add(game.getChickens()[location.x-i][location.y+i].getLocation());
			if(location.x-i > -1 && location.y-i > -1 && game.getChickens()[location.x-i][location.y-i] != null)
				toKill.add(game.getChickens()[location.x-i][location.y-i].getLocation());
		}
		
		game.killChicken(toKill);
	}
	
	public ImageIcon getIcon(){
		return crossChickenIcon;
	}

}
