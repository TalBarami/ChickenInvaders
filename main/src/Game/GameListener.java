package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Tal on 24/05/2016.
 */
public class GameListener implements KeyListener,ActionListener {
    private Game game;
    private Timer left;
    private Timer right;
    private Timer shoot;
    private final int shootingMovement;
    private int switchShot;

    public GameListener(Game game){
        this.game=game;
        left = new Timer(10, this);
        right = new Timer(10, this);
        shoot = new Timer(10, this);
        shootingMovement = (int)(game.getSize().height/100);
        switchShot = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Moving
        if(e.getKeyCode()==KeyEvent.VK_LEFT && !left.isRunning()){
            game.getSpaceship().setFocusable(true);
            left.start();
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT && !right.isRunning()){
            game.getSpaceship().setFocusable(true);
            right.start();
        }

        // Shooting
        else if(e.getKeyCode()==KeyEvent.VK_SPACE && !shoot.isRunning()){
            game.getShotAnimation().setFocusable(true);
            game.getShotAnimation().setLocation(game.getSpaceship().getLocation().x+(int)(game.getSize().width/72.5),game.getSpaceship().getLocation().y-shootingMovement);
            game.getShotAnimation().setVisible(true);
            game.setShotsCount(game.getShotsCount()+1);
            game.getShotsLabel().setText("Shots: "+game.getShotsCount());
            shoot.start();
        }

        // Switch Ammo
        if(shoot.isRunning())
        {
            if(e.getKeyCode()==KeyEvent.VK_1 || e.getKeyCode()==KeyEvent.VK_NUMPAD1)
                switchShot = 0;
            else if(e.getKeyCode()==KeyEvent.VK_2 || e.getKeyCode()==KeyEvent.VK_NUMPAD2)
                switchShot = 1;
            else if(e.getKeyCode()==KeyEvent.VK_3 || e.getKeyCode()==KeyEvent.VK_NUMPAD3)
                switchShot = 2;
            else if(e.getKeyCode()==KeyEvent.VK_4 || e.getKeyCode()==KeyEvent.VK_NUMPAD4)
                switchShot = 3;
        }
        else
        {
            if(e.getKeyCode()==KeyEvent.VK_1 || e.getKeyCode()==KeyEvent.VK_NUMPAD1)
                switchShot(0);
            else if(e.getKeyCode()==KeyEvent.VK_2 || e.getKeyCode()==KeyEvent.VK_NUMPAD2)
                switchShot(1);
            else if(e.getKeyCode()==KeyEvent.VK_3 || e.getKeyCode()==KeyEvent.VK_NUMPAD3)
                switchShot(2);
            else if(e.getKeyCode()==KeyEvent.VK_4 || e.getKeyCode()==KeyEvent.VK_NUMPAD4)
                switchShot(3);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
            left.stop();
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            right.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == left && game.getSpaceship().getLocation().x-10>0)
            game.getSpaceship().setLocation(game.getSpaceship().getLocation().x-10,game.getSpaceship().getLocation().y);
        else if(e.getSource() == right && game.getSpaceship().getLocation().x+game.getSpaceship().getSize().getWidth()+15<game.getSize().getWidth())
            game.getSpaceship().setLocation(game.getSpaceship().getLocation().x+10,game.getSpaceship().getLocation().y);
        else if(e.getSource()==shoot)
        {
            if(game.getShotAnimation().getLocation().y+40<0)
            {
                shoot.stop();
                game.getShotAnimation().setVisible(false);
                switchShot();
            }
            else if(getNextHit() != null)
            {
                shoot.stop();
                game.getShotAnimation().setVisible(false);
                Point p = getNextHit();
                game.getShot().shooting(game.getChickens()[p.x][p.y]);
                switchShot();
            }
            else
                game.getShotAnimation().setLocation(game.getShotAnimation().getLocation().x,game.getShotAnimation().getLocation().y-10);
        }
        game.mainFrame.Refresh();

    }

    private Point getNextHit(){
        Point p = null;
        for(int i=3; i>=0; i--)
            for(int j=0; j<8; j++)
                if(game.isHit(game.getShotAnimation(), game.getChickensPositions()[i][j]))
                    p = new Point(i,j);
        return p;
    }

    private void switchShot(){
        switch(switchShot)
        {
            case 0:
                game.getShotAnimation().setBackground(Color.black);
                game.setShot(game.shots[0]);
                break;
            case 1:
                game.getShotAnimation().setBackground(Color.red);
                game.setShot(game.shots[1]);
                break;
            case 2:
                game.getShotAnimation().setBackground(Color.blue);
                game.setShot(game.shots[2]);
                break;
            case 3:
                game.getShotAnimation().setBackground(Color.yellow);
                game.setShot(game.shots[3]);
                break;
        }
    }
    private void switchShot(int switchShot){
        this.switchShot = switchShot;
        switchShot();
    }
}
