package Game;

import entities.Player;
import entities.chickens.*;
import entities.shots.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tal on 24/05/2016.
 */
public class Game extends JPanel implements ActionListener {
    private static final int MAX_LEVEL = 7;
    public Clip clip = Util.getclip("/resources/sound/explosion.wav");
    public ImageIcon spaceShipIcon;
    public ImageIcon explosionIcon;
    public final int [][][] levels=	{{{1,2,3,1,1,3,2,1},{3,1,2,2,2,2,1,3},{2,3,1,3,3,1,3,2},{1,2,3,4,4,3,2,1}},
            {{5,5,5,6,6,4,4,4},{3,9,1,1,1,1,9,3},{3,1,2,2,2,2,1,3},{6,5,4,3,3,4,5,6}},
            {{5,5,5,3,3,4,4,4},{5,8,5,2,2,6,6,6},{5,5,5,1,1,6,8,6},{4,4,4,8,4,6,6,6}},
            {{5,5,4,4,5,5,4,4},{6,7,6,7,6,7,6,7},{4,3,5,2,5,3,5,2},{1,1,1,1,1,1,1,1}},
            {{13,13,13,13,13,13,13,13},{1,1,1,1,1,1,1,1},{2,2,2,2,2,2,2,2},{3,3,3,3,3,3,3,3}},
            {{5,6,7,1,1,7,6,5},{4,3,3,4,5,2,2,5},{2,2,2,10,11,2,2,2},{6,6,1,2,2,1,6,6}},
            {{5,5,4,4,5,5,4,4},{6,2,12,2,6,2,6,2},{4,3,5,2,12,3,5,2},{1,1,1,1,1,1,1,1}}};
    public final Shot[] shots = {new blackShot(), new redShot(), new blueShot(), new yellowShot()};

    public MainFrame mainFrame;
    private int level;
    private JPanel chickensPanel;
    private Chicken[][] chickens;
    private JLabel[][] chickensPositions;
    private JLabel spaceship;
    private JPanel shotAnimation;
    private Shot shot;
    private Timer explode;
    private ArrayList<Point> toKill;
    private int chickensCount;

    private JLabel timeLabel;
    private JLabel shotsLabel;
    private int time;
    private int shotsCount;
    private int levelScore;
    private Timer levelTimer;

    public Game (MainFrame mainFrame,int level){
        super();
        setLayout(null);
        setSize(mainFrame.getSize());
        this.mainFrame = mainFrame;
        this.level=level;
        spaceShipIcon = Util.getScaledImage("/resources/spaceship.PNG", (int)(getSize().width/25) , (int)(getSize().height/15));
        explosionIcon = Util.getScaledImage("/resources/explosion.PNG", (int)(getSize().width/21) , (int)(getSize().height/9));

        explode = new Timer(250, this);
        toKill = new ArrayList<Point>();
        chickensCount = 8*4;
        shot=shots[0];

        levelTimer = new Timer(1000, this);
        time = 0;
        shotsCount = 0;
        levelScore = 0;

        mainFrame.setFocusable(true);
        mainFrame.addKeyListener(new GameListener(this));
        initContentPane();
    }

    private void initContentPane(){
        // Initializing Components
        Point shipStatingPosition = new Point((int) (mainFrame.getWidth()*0.5),(int) (mainFrame.getHeight()*0.85));
        Point timeLabelPosition = new Point((int) (mainFrame.getWidth()*0.9),(int) (mainFrame.getHeight()*0.01));
        Point shotsLabelPosition = new Point((int) (mainFrame.getWidth()*0.8),(int) (mainFrame.getHeight()*0.01));

        if(spaceship == null)
            spaceship = initSpaceShip(shipStatingPosition);
        if(chickens == null)
            chickens = initChickens();
        if(chickensPositions == null)
            chickensPositions = initChickensPositions();
        if(chickensPanel == null)
            chickensPanel = initChickensPanel();
        if(shotAnimation == null)
            shotAnimation = initShotAnimation();
        if(timeLabel == null)
            timeLabel = initGameInfoLabel(timeLabelPosition, "Time: "+time);
        if(shotsLabel == null)
            shotsLabel = initGameInfoLabel(shotsLabelPosition, "Shots: "+shotsCount);

        // Adding components
        add(spaceship);
        add(chickensPanel);
        add(shotAnimation);
        add(timeLabel);
        add(shotsLabel);
        levelTimer.start();

        // Updating main frame
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(this);
        mainFrame.Refresh();
    }

    private JPanel initShotAnimation(){
        JPanel panel = new JPanel();
        panel.setSize(getSize().width/90,getSize().height/25);
        panel.setBackground(Color.black);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setVisible(false);
        return panel;

    }

    private JLabel initSpaceShip(Point location) {
        JLabel label = new JLabel(spaceShipIcon);
        label.setLocation(location);
        label.setSize(spaceShipIcon.getIconWidth(), spaceShipIcon.getIconHeight());
        return label;
    }

    private JPanel initChickensPanel(){
        JPanel chickensGrid = new JPanel();
        chickensGrid.setSize((int)(getWidth()), getHeight()/2);
        chickensGrid.setLocation(0,0);
        chickensGrid.setOpaque(false);
        for(int i=0; i<4; i++)
            for(int j=0; j<8; j++)
                add(chickensPositions[i][j]);
        return chickensGrid;
    }

    private JLabel[][] initChickensPositions(){
        JLabel[][] chickensPositions = new JLabel[4][8];
        int H = getHeight(); int W = getWidth(); int labelW = (int)(getSize().width/12.4); int labelH = (int)(getSize().height/10.6);
        for(int i=0; i<4; i++)
            for(int j=0; j<8; j++){
                chickensPositions[i][j] = new JLabel(chickens[i][j].getIcon());
                chickensPositions[i][j].setSize(chickens[i][j].getIcon().getIconWidth(), chickens[i][j].getIcon().getIconHeight());
                chickensPositions[i][j].setLocation((int)(W*0.05)+(labelW+(int)(W*0.0375))*j, (int)(H*0.05)+(labelH+(int)(H*0.05))*i);
            }
        return chickensPositions;
    }

    private Chicken[][] initChickens(){
        Chicken[][] chickens = new Chicken[4][8];
        for(int i=0; i<4; i++)
            for(int j=0; j<8; j++)
                chickens[i][j] = createChicken(levels[level-1][i][j], new Point(i,j));
        return chickens;
    }

    private JLabel initGameInfoLabel(Point location, String content){
        JLabel label = new JLabel(content);
        label.setFont(new Font("Times New Roman", Font.BOLD,30));
        label.setForeground(Color.white);
        label.setLocation(location);
        label.setSize(getSize().width/7,getSize().height/30);
        return label;
    }

    private Chicken createChicken(int i, Point p){
        switch(i){
            case 1:
                return new redChicken(this, p);
            case 2:
                return new blueChicken(this, p);
            case 3:
                return new yellowChicken(this, p);
            case 4:
                return new orangeChicken(this, p);
            case 5:
                return new purpleChicken(this, p);
            case 6:
                return new greenChicken(this, p);
            case 7:
                return new crossChicken(this, p);
            case 8:
                return new plusChicken(this, p);
            case 9:
                return new circleChicken(this, p);
            case 10:
                return new columnChicken(this, p);
            case 11:
                return new rowChicken(this, p);
            case 12:
                return new xorChicken(this, p);
            case 13:
                return new avianInfluenzaChicken(this, p);
            default:
                throw new RuntimeException("");
        }
    }

    /**
     * This function return true if and only if {(digit and left_panel are overlap) OR (digit and right_panel are overlap)}.
     * @return return true if and only if {(digit and left_panel are overlap) OR (digit and right_panel are overlap)}.
     */
    public boolean isHit(JComponent a, JComponent b) {
        return Util.jComponentlOverlap(a, b);
    }

    public void killChicken(ArrayList<Point> list){
        if(list.isEmpty())
            return;
        clip.start();
        toKill = list;
        for(int i=0; i<toKill.size(); i++)
            chickensPositions[toKill.get(i).x][toKill.get(i).y].setIcon(explosionIcon);
        explode.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == explode){
            for(int i=0; i<toKill.size(); i++)
                if(chickensPositions[toKill.get(i).x][toKill.get(i).y] != null){
                    chickensPositions[toKill.get(i).x][toKill.get(i).y].setIcon(null);
                    chickensPositions[toKill.get(i).x][toKill.get(i).y] = null;
                    chickens[toKill.get(i).x][toKill.get(i).y] = null;
                    chickensCount--;
                }
            explode.stop();
            clip = Util.getclip("/resources/sound/explosion.wav");
            if(chickensCount == 0)
                nextLevel();
        }
        else if(e.getSource() == levelTimer){
            time++;
            timeLabel.setText("Time: "+time);
        }
    }
    public ArrayList<Point> findChickensByID(int id){
        ArrayList<Point> points = new ArrayList<Point>();
        for(int i=0; i<4; i++)
            for(int j=0; j<8; j++)
                if(chickens[i][j] != null && chickens[i][j].getID() == id)
                    points.add(new Point(i,j));
        return points;
    }

    public void nextLevel(){
        levelTimer.stop();
        levelScore = Math.max(0, 600-shotsCount*10-time);
        mainFrame.setTotalScore(mainFrame.getTotalScore()+levelScore);
        JOptionPane.showMessageDialog(null,
                "Level Score: "+levelScore+
                        "\nTotal Score: "+mainFrame.getTotalScore()+
                        "\nPress OK to continue.");
        if(level < MAX_LEVEL)
            mainFrame.setGame(new Game(mainFrame, level+1));
        else{
            if(mainFrame.getHighScore().size() <= 5)
            {
                String name = JOptionPane.showInputDialog("Please enter your name:");
                mainFrame.getHighScore().add(new Player(name, mainFrame.getTotalScore()));
                Collections.sort(mainFrame.getHighScore());
                mainFrame.saveScore();
            }
            else
            {
                for(int i=0; i<5; i++)
                    if(mainFrame.getTotalScore() > mainFrame.getHighScore().get(i).getScore()){
                        String name = JOptionPane.showInputDialog("Please enter your name:");
                        mainFrame.getHighScore().add(new Player(name, mainFrame.getTotalScore()));
                        Collections.sort(mainFrame.getHighScore());
                        mainFrame.saveScore();
                        break;
                    }
            }
            mainFrame.setTotalScore(0);
            mainFrame.returnToMenu();
        }
    }

    public void paintComponent(Graphics g) { // Background image.
        ImageIcon bg = new ImageIcon(getClass().getResource("/resources/gameBG.PNG"));
        super.paintComponent(g);
        g.drawImage(bg.getImage(), 0, 0, this);
    }


    public JLabel getSpaceship(){
        return spaceship;
    }

    public JPanel getShotAnimation(){
        return shotAnimation;
    }

    public Shot getShot(){
        return shot;
    }

    public void setShot(Shot shot){
        this.shot = shot;
    }
    public JLabel[][] getChickensPositions(){
        return chickensPositions;
    }

    public Chicken[][] getChickens(){
        return chickens;
    }

    public Timer getExplodeTimer(){
        return explode;
    }

    public JLabel getShotsLabel(){
        return shotsLabel;
    }

    public int getShotsCount(){
        return shotsCount;
    }

    public void setShotsCount(int shotsCount){
        this.shotsCount = shotsCount;
    }

}
