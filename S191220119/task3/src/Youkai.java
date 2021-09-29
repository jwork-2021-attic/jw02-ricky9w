package S191220119.task3.src;

import S191220119.task3.src.Matrix.Position;

public class Youkai implements Element {
    
    private final int r;
    private final int g;
    private final int b;
    
    private Position position;

    Youkai(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int rank() {
        // TODO: 完成rank(函数
        return 0;
    }

    @Override
    public String toString() {
        return "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;0;0;0m    " + this.rank() + "  \033[0m";
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public int getValue() {
        // TODO: 完成getValue
        return 0;
    }
}
