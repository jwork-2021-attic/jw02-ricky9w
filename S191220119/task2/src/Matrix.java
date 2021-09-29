package S191220119.task2.src;

public class Matrix {

    private Position[] positions;
    int row, col;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.positions = new Position[row * col];
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
        String matrixString = "";
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrixString += positions[i * col + j].element.toString();
            }
            matrixString += "\n";
        }
        return matrixString;
    }

    public Element[] toArray() {
        Element[] elements = new Element[this.positions.length];

        for (int i = 0; i < elements.length; i++) {
            elements[i] = positions[i].element;
        }

        return elements;
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
