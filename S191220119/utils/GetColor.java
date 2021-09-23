package S191220119.utils;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class GetColor {
    public GetColor(String filename) throws IOException {
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);
        //TODO: store color info into array
        for(int i = 0; i < 16; i++) {
            for(int j = 0; j < 16; j++) {
                int x = (int)(35.75 * i);
                int y = (int)(26.74 * j);
                int clr = image.getRGB(x, y);
                int red = (clr & 0x00ff0000) >> 16;
                int green  = (clr & 0x0000ff00) >> 8;
                int blue = clr & 0x000000ff;
                System.out.println(String.format("%d, %d) = %d", x, y, red));
                System.out.println(String.format("%d, %d) = %d", x, y, green));
                System.out.println(String.format("%d, %d) = %d", x, y, blue));
            }
        }
    }
}
