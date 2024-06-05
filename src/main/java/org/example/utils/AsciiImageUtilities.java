package org.example.utils;

public class AsciiImageUtilities {
    public static void printAsciiImage(char[][] asciiImage) {
        for (char[] row : asciiImage) {
            for (char asciiPixel : row) {
                System.out.print(asciiPixel);
            }
            System.out.print("\n");
        }
    }
}
