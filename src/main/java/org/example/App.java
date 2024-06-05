package org.example;

import org.example.imageconverter.SimpleImageConverter;
import org.example.utils.AsciiImageUtilities;

import java.io.IOException;
import java.util.Arrays;

public class App
{
    public static void main( String[] args ) throws IOException {
        SimpleImageConverter simpleImageConverter = new SimpleImageConverter("src/main/resources/pepe.png");
        AsciiImageUtilities.printAsciiImage(simpleImageConverter.convertImageToAscii());
    }
}
