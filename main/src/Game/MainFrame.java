package Game;

import entities.Player;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Tal on 24/05/2016.
 */
public class MainFrame extends JFrame {
    private MainMenu mainMenu;
    private Game game;
    private LevelSelection levelSelection;
    private int totalScore;
    private ArrayList<Player> highScore;

    public MainFrame(){
        super("Chicken Invaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(maxBounds.width, maxBounds.height);
        getContentPane().setLayout(null);
        setVisible(true);
        this.setResizable(false);
        totalScore = 0;
        highScore = new ArrayList<Player>();
        loadScore();
        mainMenu = new MainMenu(this);
    }

    public static void main(String[] args){
        @SuppressWarnings("unused")
        MainFrame m = new MainFrame();
    }

    public void saveScore(){
        try {
            int length = Math.min(5, getHighScore().size());
            File file = new File("highscore.txt");
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            for(int i=0; i<length; i++){
                bw.write(getHighScore().get(i).toString());
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    public void loadScore(){
        try{
            File file = new File("highscore.txt");
            if(!file.exists())
                return;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            highScore.clear();
            for(int i=0; i<countLines("highscore.txt"); i++)
                highScore.add(new Player(br.readLine()));
            br.close();
        }
        catch(IOException exp){
            exp.printStackTrace();
        }
    }

    private int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }

    public void Refresh(){
        invalidate();
        validate();
        repaint();
        setVisible(true);
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public LevelSelection getLevelSelect(){
        return levelSelection;
    }

    public void setLevelSelection(LevelSelection levelSelection){
        this.levelSelection = new LevelSelection(this);
    }

    public int getTotalScore(){
        return totalScore;
    }

    public void setTotalScore(int totalScore){
        this.totalScore = totalScore;
    }

    public void returnToMenu(){
        mainMenu.initContentPane();
    }

    public MainMenu getMainMenu(){
        return mainMenu;
    }

    public ArrayList<Player> getHighScore(){
        return highScore;
    }
}
