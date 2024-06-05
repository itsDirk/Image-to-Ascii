package org.example.imageconverter;

import org.example.ImageChecker;
import org.example.Pixel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimpleImageConverter implements IImageConverter {
    private final String path;

    public SimpleImageConverter(String path) {
        this.path = path;
    }

    @Override
    public char[][] convertImageToAscii() throws IOException {
        verifyImage(path);
        BufferedImage image = ImageIO.read(new File(path));

        Pixel[][] pixels = getPixels(image);
        float[][] brightnesses = getBrightnesses(pixels);
        char[][] asciiImage = getAsciiImage(brightnesses);

        return asciiImage;
    }

    private char[][] getAsciiImage(float[][] brightnesses) {
        char[][] asciiImage = new char[brightnesses.length][brightnesses[0].length];
        for (int i = 0; i < brightnesses.length; i++) {
            for (int j = 0; j < brightnesses[i].length; j++) {
                asciiImage[i][j] = getAsciiCharacterForBrightness(brightnesses[i][j]);
            }
        }
        return asciiImage;
    }

    private char getAsciiCharacterForBrightness(float brightness) {
        char[] asciiCharacters = {' ', '.', ':', '-', '=', '+', '*', '#', '%', '@'};
        int index = (int) (brightness * (asciiCharacters.length)) == asciiCharacters.length ? asciiCharacters.length - 1 : (int) (brightness * (asciiCharacters.length));
        return asciiCharacters[index];
    }


    private float[][] getBrightnesses(Pixel[][] pixels) {
        float[][] brightnesses = new float[pixels.length][pixels[0].length];
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                brightnesses[i][j] = Color.RGBtoHSB(pixels[i][j].getRed(), pixels[i][j].getGreen(), pixels[i][j].getBlue(), null)[2];
            }
        }
        return brightnesses;
    }

    private Pixel[][] getPixels(BufferedImage myImage) {
        int imageHeight = myImage.getHeight(null);
        int imageWidth = myImage.getWidth(null);

        Pixel[][] pixels = new Pixel[imageHeight][imageWidth];
        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {
                int pixelValue = myImage.getRGB(i, j);
                Color color = new Color(pixelValue);
                pixels[j][i] = new Pixel(color.getRed(), color.getGreen(), color.getBlue());
            }
        }
        return pixels;
    }

    private void verifyImage(String path) {
        ImageChecker imageChecker = new ImageChecker(path);
        if (!imageChecker.isValidImage()) {
            throw new IllegalArgumentException("Invalid image type");
        }
    }
}
