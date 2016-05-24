package Game;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tal on 24/05/2016.
 */
public class MainMenu extends JPanel implements ActionListener {
    public MainFrame mainFrame;
    private JButton playButton;
    private JButton levelSelectButton;
    private JButton highScoreButton;
    private JButton quitButton;
    private Timer musicTimer;
    public Clip music = Util.getclip("/resources/sound/music.WAV");

    public MainMenu(MainFrame mainFrame){
        super();
        setLayout(null);
        setSize(mainFrame.getSize());
        this.mainFrame = mainFrame;

        musicTimer = new Timer(150000, this);
        musicTimer.start();
        startMusic();
        initContentPane();
    }

    public void initContentPane(){
        int width = (int)(this.getWidth()*0.1);
        //components
        Point playButtonlocation = new Point((int) (getWidth()*0.550),(int) (getHeight()*0.20));
        Point levelSelectButtonlocation = new Point((int) (getWidth()*0.532),(int) (getHeight()*0.35));
        Point highScoreButtonlocation = new Point((int) (getWidth()*0.550),(int) (getHeight()*0.50));
        Point quitButtonlocation = new Point((int) (getWidth()*0.550),(int) (getHeight()*0.65));
        if(playButton==null)
            playButton = initPlayButton(playButtonlocation , width);
        if(levelSelectButton==null)
            levelSelectButton = initLevelSelectButton(levelSelectButtonlocation, width);
        if(highScoreButton==null)
            highScoreButton = initHighScoreButton(highScoreButtonlocation, width);
        if(quitButton==null)
            quitButton = initQuitButton(quitButtonlocation, width);

        add(playButton);
        add(levelSelectButton);
        add(highScoreButton);
        add(quitButton);

        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(this);
        mainFrame.Refresh();

    }

    private JButton initPlayButton(Point location ,  int width) {
        JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/play.PNG")));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setLocation(location);
        button.setSize(button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
        button.addActionListener(this);
        return button;
    }

    private JButton initLevelSelectButton(Point location ,  int width) {
        JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/levelSelection.PNG")));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setLocation(location);
        button.setSize(button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
        button.addActionListener(this);
        return button;
    }

    private JButton initHighScoreButton(Point location ,  int width) {
        JButton button = new JButton("High Score");
        //button.setBorderPainted(false);
        //button.setContentAreaFilled(false);
        //button.setFocusPainted(false);
        button.setLocation(location);
        button.setSize(150,50);
        //button.setSize(button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
        button.addActionListener(this);
        return button;
    }

    private JButton initQuitButton(Point location ,  int width) {
        JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/quit.PNG")));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setLocation(location);
        button.setSize(button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton){
            mainFrame.setGame(new Game(mainFrame, 1));
            stopMusic();
        }
        else if(e.getSource() == levelSelectButton)
            mainFrame.setLevelSelection(new LevelSelection(mainFrame));
        else if(e.getSource() == highScoreButton){
            //mainFrame.loadScore();
            String highScore = "";
            for(int i=0; i<mainFrame.getHighScore().size(); i++){
                highScore+=(mainFrame.getHighScore().get(i).getName() + "   -   " + mainFrame.getHighScore().get(i).getScore() +"\n");
            }
            JOptionPane.showMessageDialog(null,highScore.equals("") ? "No previous records." : highScore);
        }
        else if(e.getSource() == quitButton)
            System.exit(0);
        else if(e.getSource() == musicTimer){
            startMusic();
        }
    }

    public void paintComponent(Graphics g) { // Background image.
        ImageIcon bg = new ImageIcon(getClass().getResource("/resources/mainMenuBG.PNG"));
        super.paintComponent(g);
        g.drawImage(bg.getImage(), 0, 0, this);
    }

    public void startMusic(){
        music = Util.getclip("/resources/sound/music.WAV");
        music.start();
    }

    public void stopMusic(){
        music.stop();
        musicTimer.stop();
    }
}
