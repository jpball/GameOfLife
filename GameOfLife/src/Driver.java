import java.awt.Color;

public class Driver {
    public static void main(String[] args) throws Exception {
        Player p = new Player(1, Color.red, "Jordan");
        PlayerBoard pb = new PlayerBoard(p);
    }
}
