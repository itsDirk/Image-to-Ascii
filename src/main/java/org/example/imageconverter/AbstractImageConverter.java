package org.example.imageconverter;

import org.example.dto.Pixel;
import org.example.utils.AsciiImageUtilities;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractImageConverter implements IImageConverter {
    protected String path;

    public AbstractImageConverter(String path) {
        this.path = path;
    }

    protected void verifyImage(String path) {
        AsciiImageUtilities.ImageChecker imageChecker = new AsciiImageUtilities.ImageChecker(path);
        if (!imageChecker.isValidImage()) {
            throw new IllegalArgumentException("Invalid image type");
        }
    }

    protected Pixel[][] getPixels(BufferedImage myImage) {
        int imageHeight = myImage.getHeight(null);
        int imageWidth = myImage.getWidth(null);

        Pixel[][] pixels = new Pixel[imageHeight][imageWidth];
        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {
                int pixelValue = myImage.getRGB(i, j);
                int red = (pixelValue >> 16) & 0xFF;
                int green = (pixelValue >> 8) & 0xFF;
                int blue = pixelValue & 0xFF;
                pixels[j][i] = new Pixel(red, green, blue);
            }
        }
        return pixels;
    }

    protected float[][] getBrightnesses(Pixel[][] pixels) {
        float[][] brightnesses = new float[pixels.length][pixels[0].length];
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                brightnesses[i][j] = Color.RGBtoHSB(pixels[i][j].getRed(), pixels[i][j].getGreen(), pixels[i][j].getBlue(), null)[2];
            }
        }
        return brightnesses;
    }

    protected char[][] getAsciiImage(float[][] brightnesses) {
        char[][] asciiImage = new char[brightnesses.length][brightnesses[0].length];
        for (int i = 0; i < brightnesses.length; i++) {
            for (int j = 0; j < brightnesses[i].length; j++) {
                asciiImage[i][j] = getAsciiCharacterForBrightness(brightnesses[i][j]);
            }
        }
        return asciiImage;
    }

    protected char getAsciiCharacterForBrightness(float brightness) {
        char[] asciiCharacters = {' ', '.', ':', '-', '=', '+', '*', '#', '%', '@'};
        int index = (int) (brightness * (asciiCharacters.length)) == asciiCharacters.length ? asciiCharacters.length - 1 : (int) (brightness * (asciiCharacters.length));
        return asciiCharacters[index];
    }
}
