package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tal on 24/05/2016.
 */
public class LevelSelection extends JPanel implements ActionListener {
    public MainFrame mainFrame;
    private JButton[] levelButtons;
    private JButton backButton;

    public LevelSelection(MainFrame mainFrame){
        super();
        setLayout(null);
        setSize(mainFrame.getSize());
        this.mainFrame = mainFrame;
        initContentPane();
    }

    private void initContentPane(){
        if(levelButtons == null)
            levelButtons = initButtons();
        if(backButton == null)
            backButton = initBackButton();

        for(int i=0; i<levelButtons.length; i++)
            add(levelButtons[i]);
        add(backButton);

        // Updating main frame
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(this);
        mainFrame.Refresh();
    }

    private JButton[] initButtons(){
        JButton[] buttons = new JButton[7];
        for(int i=0; i<7; i++){
            buttons[i] = new JButton("Level: "+(i+1));
            buttons[i] = new JButton(new ImageIcon(getClass().getResource("/resources/level"+(i+1)+".PNG")));
            buttons[i].setLocation((int) (getWidth()*0.45), (int) ((getHeight()*0.20)+i*1.25*buttons[i].getPreferredSize().getHeight()));
            buttons[i].setBorderPainted(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setFocusPainted(false);
            buttons[i].setSize(buttons[i].getPreferredSize());
            buttons[i].addActionListener(this);
        }
        return buttons;
    }

    private JButton initBackButton(){
        JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/back.PNG")));
        button.setLocation((int)(getWidth()*0.75), (int)(getHeight()*0.8));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setSize(button.getPreferredSize().width*2,button.getPreferredSize().height*2);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() != backButton)
        {
            mainFrame.getMainMenu().stopMusic();
            if(e.getSource() == levelButtons[0])
                mainFrame.setGame(new Game(mainFrame,1));
            else if(e.getSource() == levelButtons[1])
                mainFrame.setGame(new Game(mainFrame,2));
            else if(e.getSource() == levelButtons[2])
                mainFrame.setGame(new Game(mainFrame,3));
            else if(e.getSource() == levelButtons[3])
                mainFrame.setGame(new Game(mainFrame,4));
            else if(e.getSource() == levelButtons[4])
                mainFrame.setGame(new Game(mainFrame,5));
            else if(e.getSource() == levelButtons[5])
                mainFrame.setGame(new Game(mainFrame,6));
            else if(e.getSource() == levelButtons[6])
                mainFrame.setGame(new Game(mainFrame,7));
        }
        else
            mainFrame.returnToMenu();
    }

    public void paintComponent(Graphics g) { // Background image.
        ImageIcon bg = new ImageIcon(getClass().getResource("/resources/levelSelectionBG.PNG"));
        super.paintComponent(g);
        g.drawImage(bg.getImage(), 0, 0, this);
    }
}
