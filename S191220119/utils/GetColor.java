package S191220119.utils;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class GetColor {
    public static int[][] getColor(String filename, int row, int col) throws IOException {
        int[][] colors = new int[row * col][3];
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                int x = (int)(35.75 * i);
                int y = (int)(26.74 * j);
                int clr = image.getRGB(x, y);
                int red = (clr & 0x00ff0000) >> 16;
                int green  = (clr & 0x0000ff00) >> 8;
                int blue = clr & 0x000000ff;
                colors[i * col + j][0] = red;
                colors[i * col + j][1] = green;
                colors[i * col + j][2] = blue;
            }
        }
        return colors;
    }
}
