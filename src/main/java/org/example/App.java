package org.example;

import java.io.IOException;
import java.util.Arrays;

public class App
{
    public static void main( String[] args ) throws IOException {
        SimpleImageConverter simpleImageConverter = new SimpleImageConverter("src/main/resources/Linus.jpg");
        System.out.println(Arrays.deepToString(simpleImageConverter.convertImageToAscii()));
    }
}
