package Game;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is a implements Various static functions:  getImage, getScaledImage, getclip, jComponentlOverlap , CompPoints;
 * @author Hadar Amran
 */
public class Util {

    /**
     * This function return ImageIcon in the url path
     * @param url string of the path of the source image
     * @return ImageIcon if source url is vaild and null otherwise returning null.
     */
    public static ImageIcon getImage(String url){
        ImageIcon image = null;

        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(url));
            image = new ImageIcon(myPicture);
        } catch (IOException e) {
        }
        return image;
    }

    /**
     * This function return Scaled ImageIcon in the url path with  width, hight.
     * @param url string of the path of the source image
     * @param width the width of the Scaled ImageIcon
     * @param hight the hight of the Scaled ImageIcon
     * @return Scaled ImageIcon if source url is vaild and null otherwise returning null.
     */
    public static ImageIcon getScaledImage(String url, int width, int hight){
        ImageIcon image = null;

        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(MainFrame.class.getResource(url));
            Image dimg = myPicture.getScaledInstance(width,hight, Image.SCALE_SMOOTH);
            image = new ImageIcon(dimg);
        } catch (IOException e) {
        }
        return image;
    }

    /**
     * This function return Clip in the url path
     * @param url string of the path of the source Clip
     * @return Clip if source url is vaild and null otherwise returning null.
     */
    public static Clip getclip(String url) {
        Clip clip=null;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(MainFrame.class.getResource(url));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {

        }

        return clip;
    }

    /**
     * This function return true if and only if Comp1 and Comp2 are overlap.
     * @param Comp1 first JComponent
     * @param Comp2 second JComponent
     * @return return true if and only if Comp1 and Comp2 are overlap.
     */
    public static boolean jComponentlOverlap(JComponent  Comp1, JComponent  Comp2) {
        if(Comp1==null || Comp2==null)
            return false;

        Point[] Comp1_points = CompPoints(Comp1);
        Point[] Comp2_points = CompPoints(Comp2);
        int lowBound_x = Comp2_points[0].x;
        int lowBound_y = Comp2_points[0].y;
        int highBound_x= Comp2_points[3].x;
        int highBound_y= Comp2_points[3].y;

        for(Point p : Comp1_points){
            if(lowBound_y <= p.y && p.y <= highBound_y
                    && lowBound_x <= p.x && p.x <= highBound_x)
                return true;
        }
        return false;
    }

    /**
     * This function return all the Endpoint of the JComponent comp
     * @param comp JComponent
     * @return return array of all Endpoint of the JComponent comp
     */
    private static Point[] CompPoints(JComponent comp){
        Point point= comp.getLocation();
        Dimension dim = comp.getSize();

        Point[] points = new  Point[4];
        points[0]= new Point(point);
        points[1]= new Point(point.x,point.y+dim.height);
        points[2]= new Point(point.x+dim.width,point.y);
        points[3]= new Point(point.x+dim.width,point.y+dim.height);
        return points;
    }
}
