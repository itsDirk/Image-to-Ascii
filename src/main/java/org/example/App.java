package org.example;

import org.example.imageconverter.DownsamplingImageConverter;
import org.example.imageconverter.SimpleImageConverter;
import org.example.imageconverter.SimpleResizableImageConverter;
import org.example.utils.AsciiImageUtilities;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        String path = "src/main/resources/towersofivory.jpg";

        // Image to ASCII
        SimpleImageConverter simpleImageConverter = new SimpleImageConverter(path);
        AsciiImageUtilities.printAsciiImage(simpleImageConverter.convertImageToAscii());


        // Resizable Image to ASCII
        SimpleResizableImageConverter resizableImageConverter = new SimpleResizableImageConverter(path, 100, 40);
        AsciiImageUtilities.printAsciiImage(resizableImageConverter.convertImageToAscii());


        // Downsampling Image to ASCII
        DownsamplingImageConverter mergingResizableImageConverter = new DownsamplingImageConverter(path, 100, 40);
        AsciiImageUtilities.printAsciiImage(mergingResizableImageConverter.convertImageToAscii());
    }
}
