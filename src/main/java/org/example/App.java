package org.example;

import org.example.imageconverter.MergingResizableImageConverter;
import org.example.imageconverter.SimpleResizableImageConverter;
import org.example.utils.AsciiImageUtilities;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
//        SimpleImageConverter simpleImageConverter = new SimpleImageConverter("src/main/resources/pepe.png");
//        AsciiImageUtilities.printAsciiImage(simpleImageConverter.convertImageToAscii());

        SimpleResizableImageConverter resizableImageConverter = new SimpleResizableImageConverter("src/main/resources/lijnen.png", 100, 40);
        AsciiImageUtilities.printAsciiImage(resizableImageConverter.convertImageToAscii());

        MergingResizableImageConverter mergingResizableImageConverter = new MergingResizableImageConverter("src/main/resources/lijnen.png", 100, 40);
        AsciiImageUtilities.printAsciiImage(mergingResizableImageConverter.convertImageToAscii());
    }
}
