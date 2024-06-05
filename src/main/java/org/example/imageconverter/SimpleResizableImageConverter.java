package org.example.imageconverter;

import org.example.Pixel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimpleResizableImageConverter extends AbstractImageConverter {
    private int newWidth;
    private int newHeight;

    public SimpleResizableImageConverter(String path, int newWidth, int newHeight) {
        super(path);
        this.newWidth = newWidth;
        this.newHeight = newHeight;
    }

    @Override
    public char[][] convertImageToAscii() throws IOException {
        super.verifyImage(path);
        BufferedImage image = ImageIO.read(new File(path));

        Pixel[][] pixels = getPixels(image, newWidth, newHeight);
        float[][] brightnesses = super.getBrightnesses(pixels);
        char[][] asciiImage = super.getAsciiImage(brightnesses);

        return asciiImage;
    }

    private Pixel[][] getPixels(BufferedImage myImage, int newWidth, int newHeight) {
        int imageHeight = myImage.getHeight(null);
        int imageWidth = myImage.getWidth(null);

        Pixel[][] pixels = new Pixel[newHeight][newWidth];
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                int pixelValue = myImage.getRGB(i * imageWidth / newWidth, j * imageHeight / newHeight);
                Color color = new Color(pixelValue);
                pixels[j][i] = new Pixel(color.getRed(), color.getGreen(), color.getBlue());
            }
        }
        return pixels;
    }
}
