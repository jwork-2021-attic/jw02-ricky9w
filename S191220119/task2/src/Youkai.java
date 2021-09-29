package S191220119.task2.src;

import java.util.HashMap;

import S191220119.task2.src.Matrix.Position;

public class Youkai implements Element {
    
    private final int r, g, b, rank;
    private Position position;

    private static HashMap<Integer, Youkai> youkais = new HashMap<Integer, Youkai>();

    Youkai(int r, int g, int b, int rank) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.rank = rank;
        Youkai.youkais.put(rank, this);
    }

    public int rank() {
        return this.rank;
    }

    public static Youkai getYoukaiByRank(int rank) {
        return Youkai.youkais.get(rank);
    }

    @Override
    public String toString() {
        String spaces = " ".repeat(3 - Integer.toString(rank).length());
        return "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;0;0;0m    " + this.rank() + spaces + "  \033[0m";
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    public void swapPosition(Youkai another) {
        Position p = another.position;
        this.position.setElement(another);
        p.setElement(this);
    }

    @Override
    public int getValue() {
        return this.rank();
    }
}
