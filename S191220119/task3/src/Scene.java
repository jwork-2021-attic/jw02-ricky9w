package S191220119.task3.src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.IntStream;
import java.lang.AssertionError;

import S191220119.utils.GetColor;

public class Scene {

    public static void main(String[] args) throws IOException {
        // set rows and cols
        int row = 16;
        int col = 16;
        if(row * col > 256) {
            throw new AssertionError(String.format("Number of elements must be less than 256(currently %d)!", row * col));
        }
        // set algorithm
        // String alg = "Bubble";
        String alg = "Select";

        String dir = "./S191220119/task3/asset/";
        String filename = dir + alg + ".txt";
        

        int[][] colors = GetColor.getColor("./utils/c256.png", 16, 16);

        
        int[] randomOrder = IntStream.rangeClosed(1, row * col).toArray();
        randomOrder = Sorter.randomArrange(randomOrder);
        
        // set Youkai in random order
        Matrix matrix = new Matrix(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int randIndex = randomOrder[i * col + j];
                matrix.put(new Youkai(colors[randIndex - 1][0], colors[randIndex - 1][1], colors[randIndex - 1][2], randIndex), i, j);
            }
        }

        Snake theSnake = Snake.getTheSnake();

        // two implements of sorter
        Sorter sorter = null;
        if (alg == "Bubble") {
            sorter = new SelectSorter();
        }
        else if (alg == "Select") {
            sorter = new SelectSorter();
        }
        theSnake.setSorter(sorter);

        String log = theSnake.sortYoukai(matrix);

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(filename));
        writer.write(log);
        writer.flush();
        writer.close();

    }
    
}
