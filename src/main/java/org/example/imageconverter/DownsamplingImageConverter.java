package org.example.imageconverter;

import org.example.dto.Pixel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DownsamplingImageConverter extends AbstractImageConverter {
    private int newWidth;
    private int newHeight;

    public DownsamplingImageConverter(String path, int newWidth, int newHeight) {
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
        int imageHeight = myImage.getHeight();
        int imageWidth = myImage.getWidth();

        // Bereken de grootte van elk blok
        int blockWidth = imageWidth / newWidth;
        int blockHeight = imageHeight / newHeight;

        Pixel[][] pixels = new Pixel[newHeight][newWidth];

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                // Bepaal het gemiddelde van de kleuren in het huidige blok
                int redTotal = 0, greenTotal = 0, blueTotal = 0;
                int numPixels = 0;

                for (int blockY = 0; blockY < blockHeight; blockY++) {
                    for (int blockX = 0; blockX < blockWidth; blockX++) {
                        int imgX = x * blockWidth + blockX;
                        int imgY = y * blockHeight + blockY;

                        if (imgX < imageWidth && imgY < imageHeight) {
                            int pixelValue = myImage.getRGB(imgX, imgY);
                            redTotal += (pixelValue >> 16) & 0xFF;
                            greenTotal += (pixelValue >> 8) & 0xFF;
                            blueTotal += pixelValue & 0xFF;
                            numPixels++;
                        }
                    }
                }

                // Bereken de gemiddelde kleur
                int avgRed = redTotal / numPixels;
                int avgGreen = greenTotal / numPixels;
                int avgBlue = blueTotal / numPixels;

                // Sla de gemiddelde kleur op in de nieuwe pixel
                pixels[y][x] = new Pixel(avgRed, avgGreen, avgBlue);
            }
        }

        return pixels;
    }
}
