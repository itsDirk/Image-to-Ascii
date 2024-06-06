package org.example.imageconverter;

import org.example.dto.Pixel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimpleImageConverter extends AbstractImageConverter {

    public SimpleImageConverter(String path) {
        super(path);
    }

    @Override
    public char[][] convertImageToAscii() throws IOException {
        super.verifyImage(path);
        BufferedImage image = ImageIO.read(new File(path));

        Pixel[][] pixels = super.getPixels(image);
        float[][] brightnesses = super.getBrightnesses(pixels);
        char[][] asciiImage = super.getAsciiImage(brightnesses);

        return asciiImage;
    }
}
