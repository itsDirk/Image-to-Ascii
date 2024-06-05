package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimpleImageConverter implements IImageConverter {
    private String path;

    public SimpleImageConverter(String path) {
        this.path = path;
    }

    @Override
    public char[][] convertImageToAscii() throws IOException {
        verifyImage(path);

        Image myImage = ImageIO.read(new File(path));


        int imageHeight = myImage.getHeight(null);
        int imageWidth = myImage.getWidth(null);
        int[][] pixels = new int[imageHeight][imageWidth];
        for (int i = 0; i < imageWidth; ++i) {
            for (int j = 0; j < imageHeight; ++j) {
                pixels[j][i] = ((BufferedImage) myImage).getRGB(i, j);
            }
        }
        for (int[] row : pixels) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }

        return null;
    }

    private void verifyImage(String path) {
        ImageChecker imageChecker = new ImageChecker(path);
        if (!imageChecker.isValidImage()){
            throw new IllegalArgumentException("Invalid image type");
        }
    }
}
