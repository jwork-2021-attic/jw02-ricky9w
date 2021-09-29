package S191220119.task3.src;

public class Matrix {

    private Position[] positions;
    int row, col;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void put(Element element, int i, int j) {
        int index = i * col + j;
        if (this.positions[index] == null) {
            this.positions[index] = new Position(null);
        }
        this.positions[index].setElement(element);
    }

    public Element get(int i, int j) {
        if (i < 0 || j < 0 || i >= row || j >= col)
            return null;
        return positions[i * col + j].element;
    }

    @Override
    public String toString() {
        // TODO: 完成矩阵的toString()方法
        String matrixString = "\t";
        for (Position p : positions)
            matrixString += p.element.toString();
        return matrixString;
    }

    class Position {
        private Element element;

        Position(Element element) {
            this.element = element;
        }

        public void setElement(Element element) {
            this.element = element;
            element.setPosition(this);
        }
    }
}
