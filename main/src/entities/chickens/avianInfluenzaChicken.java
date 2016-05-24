package entities.chickens;

import Game.Game;
import Game.Util;
import entities.shots.*;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class avianInfluenzaChicken extends Chicken {
	public final ImageIcon avianInfluenzaChickenIcon;
	
	public avianInfluenzaChicken(Game game, Point p){
		super(game, 13, p);
		avianInfluenzaChickenIcon= Util.getScaledImage("/resources/chicken/Bonus/the_chicken_flu.PNG", chickenSize.width, chickenSize.height);
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
			for(int j=0; j<8; j++)
				if(game.getChickens()[i][j] != null)
					toKill.add(game.getChickens()[i][j].getLocation());
		game.killChicken(toKill);
	}
	
	public ImageIcon getIcon(){
		return avianInfluenzaChickenIcon;
	}

}
