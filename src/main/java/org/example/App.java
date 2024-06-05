package org.example;

import org.example.imageconverter.SimpleResizableImageConverter;
import org.example.utils.AsciiImageUtilities;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
//        SimpleImageConverter simpleImageConverter = new SimpleImageConverter("src/main/resources/pepe.png");
//        AsciiImageUtilities.printAsciiImage(simpleImageConverter.convertImageToAscii());

        SimpleResizableImageConverter resizableImageConverter = new SimpleResizableImageConverter("src/main/resources/Linus.jpg", 100, 40);
        AsciiImageUtilities.printAsciiImage(resizableImageConverter.convertImageToAscii());
    }
}
