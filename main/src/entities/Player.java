package entities;

/**
 * Created by Tal on 24/05/2016.
 */
public class Player implements Comparable<Player>{
    private String name;
    private int score;

    public Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    public Player(String player){
        String[] tmp = player.split(";");
        name = tmp[0];
        score = Integer.parseInt(tmp[1]);
    }

    public String toString(){
        return (name+";"+score);
    }

    public int getScore(){
        return score;
    }

    public String getName(){
        return name;
    }

    @Override
    public int compareTo(Player p) {
        return (p.score - score);
    }
}
