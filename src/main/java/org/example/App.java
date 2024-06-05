package org.example;

import org.example.imageconverter.SimpleImageConverter;

import java.io.IOException;
import java.util.Arrays;

public class App
{
    public static void main( String[] args ) throws IOException {
        SimpleImageConverter simpleImageConverter = new SimpleImageConverter("src/main/resources/Joost.jpg");
        System.out.println(Arrays.deepToString(simpleImageConverter.convertImageToAscii()));
    }
}
