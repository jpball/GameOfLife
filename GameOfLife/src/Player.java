import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class Player {
    private Color playerColor;
    private int playerNum;
    private String playerName;
    private ArrayList<Boolean> board;
    //--
    public Player(int num, Color col, String name){
        setPlayerNum(num);
        setPlayerColor(col);
        setPlayerName(name);
    }
    //--
    public void setPlayerColor(Color col){
        playerColor = col;
    }
    //--
    public Color getPlayerColor(){
        return playerColor;
    }
    //--
    public void setPlayerNum(int val){
        playerNum = val;
    }
    //--
    public int getPlayerNum(){
        return playerNum;
    }
    //--
    public void setPlayerName(String val){
        playerName = val;
    }
    //--
    public String getPlayerName(){
        return playerName;
    }
    //--
}
