package cs1302.arcade.game2048;

public class Tile {
    private int value;

    public Tile() {
        value = 2;
    }
    
    public void setValue(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }

    public boolean mergeTile(Tile t) {
        if(value == t.getValue()) {
            value *= 2;
            return true;
        }
        return false;
    }
}
    