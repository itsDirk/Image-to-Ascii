package org.example;

import java.io.File;

public class ImageChecker {
    private static String path;

    public ImageChecker(String path) {
        this.path = path;
    }

    public boolean isValidImage() {
        if (path == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        File imageFile = new File(path);
        String fileType = getFileType(imageFile);
        if (fileType.equals("jpg") || fileType.equals("jpeg") || fileType.equals("png")) {
            return true;
        }
        return false;
    }

    private String getFileType(File imageFile) {
        if (imageFile == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        String fileName = imageFile.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
