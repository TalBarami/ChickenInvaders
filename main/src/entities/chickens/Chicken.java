package entities.chickens;

import entities.Visitor;
import Game.Game;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public abstract class Chicken implements Visitor {
	protected final Dimension chickenSize;
	protected int id;
	protected Game game;
	protected Point location;
	
	public Chicken(Game game,int id, Point p){
		this.game=game;
		chickenSize = new Dimension((int)(game.getSize().width/12.4),(int)(game.getSize().height/10.6));
		this.id=id;
		this.location = p;
	}
	
	public abstract ImageIcon getIcon();
	public int getID(){
		return id;
	}
	public Point getLocation(){
		return location;
	}
	public void killTwoChickens(int id){
		ArrayList<Point> killList = game.findChickensByID(id);
		ArrayList<Point> toKill = new ArrayList<Point>();
		if(killList.size() > 0)
		{
			if(killList.size() > 1){
				int firstKill = (int)(Math.random()*killList.size());
				int secondKill = (int)(Math.random()*killList.size());
				while(firstKill == secondKill)
					secondKill = (int)(Math.random()*killList.size());
				toKill.add(killList.get(firstKill));
				toKill.add(killList.get(secondKill));
			}
			else
				toKill.add(killList.get(0));
		}
		game.killChicken(toKill);
	}
}
